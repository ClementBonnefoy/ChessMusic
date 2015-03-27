package chesstube.music;

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
		while(role>7){
			role-=7;
			octave--;
		}
		note=note.incr(this.getScale()[role-1]);
		note=note.incr(alteration);
		while(octave>0){
			note=note.nextOctave();
			octave--;
		}
			
		return note;
		
		
	}

}
