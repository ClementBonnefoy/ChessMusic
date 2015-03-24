package chesstube;

import chesstube.music.NoteName;
import chesstube.music.Scale;
import chesstube.music.scale.Aeolian;
import chesstube.music.scale.Dorian;
import chesstube.music.scale.Ionian;
import chesstube.music.scale.Locrian;
import chesstube.music.scale.Lydian;
import chesstube.music.scale.Myxolydian;
import chesstube.music.scale.Phrygian;
import static chesstube.music.NoteName.*;

/**
 * Ensemble de fonctions statiques permettant la conversion des types 
 * du package sml.elements vers des types du package chesstube.music
 *
 */
public class SMLConverter {
	
	public static Scale convertScale(sml.elements.Scale scale){
		Scale res=null;
		NoteName note=convertNote(scale.getNote());
		switch(scale.getScale()){
		case aeolian:
			res=new Aeolian(note);
			break;
		case dorian:
			res=new Dorian(note);
			break;
		case ionian:
			res=new Ionian(note);
			break;
		case locrian:
			res=new Locrian(note);
			break;
		case lydian:
			res=new Lydian(note);
			break;
		case myxolydian:
			res=new Myxolydian(note);
			break;
		case phrygian:
			res=new Phrygian(note);
			break;
		default:
			break;
		}
		return res;
	}
	
	public static NoteName convertNote(sml.elements.Note note){
		switch(note){
		case a:
			return A;
		case as:
			return AS;
		case b:
			return B;
		case c:
			return C;
		case cs:
			return CS;
		case d:
			return D;
		case ds:
			return DS;
		case e:
			return E;
		case f:
			return F;
		case fs:
			return FS;
		case g:
			return G;
		case gs:
			return GS;
		default:
			return null;
		
		}
		
	}

}
