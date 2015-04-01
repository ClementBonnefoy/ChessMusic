package music;

import music.scale.Myxolydian;
import board.Rank;
import sml.elements.ComplexNote;

/*
 * Definit des gammes à 7 notes
 */
public class Scale {

	protected NoteName fundamental;
	private int[] scale;


	/**
	 * constructeur de gammes
	 * @param fundamental
	 * @param scale : un tableau de 6 boolean pour déterminer la gamme
	 * true=demi ton, false=ton
	 */
	public Scale(NoteName fundamental, boolean[] scale){
		this.fundamental=fundamental;
		readScale(scale);
	}
	
	public Scale(NoteName fundamental,int[] scale){
		this.fundamental=fundamental;
		this.scale=scale;
	}

	public Scale(Scale scale,Boolean[] alterations){
		this.fundamental=scale.fundamental;
		this.scale=scale.getScale().clone();
		for(int i=0;i<alterations.length;i++){
			Boolean alt=alterations[i];
			if(alt!=null){
				if(alt)
					this.scale[i]++;
				else 
					this.scale[i]--;
			}
		}
	}

	private void readScale(boolean[] s) {
		scale=new int[7];
		scale[0]=0;
		for(int i=1;i<7;i++){
			scale[i]=scale[i-1]+(s[i-1]?1:2);
		}

	}

	private int[] getScale(){
		return scale;
	}

	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("Notes:");
		NoteName note=fundamental;
		int i=0;
		do{
			sb.append(" "+note.incr(getScale()[i]));
			i++;
		}while(i<7);
		return sb.toString();


	}


	public Note getNote(ComplexNote n) {
		int octave=n.getOctave().getValue();
		int role=n.getRole().getRole();
		int alteration=n.getRole().getAlteration();
		Note note=new Note(fundamental,0);
		while(role>scale.length){
			role-=scale.length;
			octave++;
		}
		note=note.incr(this.getScale()[role-1]);
		if(alteration>0)
			note=note.incr(alteration);
		else
			note=note.decr(-alteration);
		while(octave>0){
			note=note.nextOctave();
			octave--;
		}


		return note;


	}


	public NoteName getFundamental() {
		return fundamental;
	}
	
	public Note getNote(Rank rank,int octave) {
		Note note=new Note(fundamental,octave);
		int rankNumber=rank.getNum();
		while(rankNumber>=scale.length){
			rankNumber-=scale.length;
			note=note.nextOctave();
		}
			
		note=note.incr(this.getScale()[rankNumber]);
			
		return note;
	}
	
	public static void main(String[] args){
		Scale ionian=new Myxolydian(NoteName.C);
		Boolean[] b= new Boolean[7];
		b[1]=false;
		b[5]=false;
		Scale s=new Scale(ionian,b);
		System.out.println(s);
	}

}
