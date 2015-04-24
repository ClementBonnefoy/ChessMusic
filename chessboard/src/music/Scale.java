package music;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

	public Scale(NoteName fundamental,int number){
		this.fundamental=fundamental;
		readScale(number);
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

	private void readScale(int n){
		ArrayList<Integer> scale = new ArrayList<>();
		scale.add(0);

		int number = n;
		int tmp=0;

		do{
			number=n;
			while (number != 0) {
				tmp += 2;
				tmp += number & 1;
				if (tmp >= 12)
					break;
				scale.add(tmp);
				number >>= 1;
			}
		}while(tmp<12);

			int[] tab=new int[scale.size()];
		for(int i=0;i<scale.size();i++){
			tab[i]=scale.get(i);
		}
		this.scale=tab;

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
		}while(i<scale.length);
		return sb.toString();


	}


	public Note getNote(ComplexNote n, int transpose) {
		int octave=n.getOctave().getValue();
		int role=n.getRole().getRole()+transpose;
		int alteration=n.getRole().getAlteration();
		Note note=new Note(fundamental,0);
		while(role<=0){
			role+=scale.length;
			octave--;
		}
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
