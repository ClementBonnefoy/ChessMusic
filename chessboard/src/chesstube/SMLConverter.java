package chesstube;

import static music.NoteName.A;
import static music.NoteName.AS;
import static music.NoteName.B;
import static music.NoteName.C;
import static music.NoteName.CS;
import static music.NoteName.D;
import static music.NoteName.DS;
import static music.NoteName.E;
import static music.NoteName.F;
import static music.NoteName.FS;
import static music.NoteName.G;
import static music.NoteName.GS;
import music.Instrument;
import music.NoteName;
import music.Scale;
import music.scale.Aeolian;
import music.scale.Dorian;
import music.scale.Ionian;
import music.scale.Locrian;
import music.scale.Lydian;
import music.scale.Myxolydian;
import music.scale.Phrygian;
import sml.elements.AlterableScale;
import sml.elements.Declarations;
import sml.elements.Note;
import sml.elements.ScaleName;
import sml.elements.Variable;
import sml.interfaces.IPlayableScale;
import sml.interfaces.IScale;

/**
 * Ensemble de fonctions statiques permettant la conversion des types 
 * du package sml.elements vers des types du package chesstube.music
 *
 */
public class SMLConverter {

	public static Scale convertScale(IPlayableScale playableScale, Declarations env){
		Scale res=null;
		NoteName note=convertNote(playableScale.getScaleFundamental(env));
		IScale s=playableScale.getScale(env);
		if(s instanceof ScaleName){
			ScaleName sn=(ScaleName)s;
			switch(sn){
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
		}
		else if(s instanceof AlterableScale){
			AlterableScale as=(AlterableScale)s;
			return new Scale(convertScale(new sml.elements.Scale(
					playableScale.getScaleFundamental(env),
					as.getScale()), env),as.getAlterations());
		}
		else if(s instanceof Variable){
			Variable v=(Variable)s;
			IScale s2=(IScale)v.getValue(env);
			return convertScale(new sml.elements.Scale(
					playableScale.getScaleFundamental(env),s2), env);
		}
		return res;
	}

	public static Instrument convertInstrument(
			sml.elements.Instrument instrument){
		Instrument result=null;
		switch(instrument){
		case flute:
			result = Instrument.flute;
			break;
		case piano:
			result = Instrument.piano;
			break;
		case electricBass:
			result = Instrument.electricBassFinger;
			break;
		case harpsichord:
			result = Instrument.harpsichord;
			break;
		case marimba:
			result = Instrument.marimba;
			break;
		case vibraphone:
			result = Instrument.vibraphone;
			break;
		case xylophone:
			result = Instrument.xylophone;
			break;
		case alto:
			result = Instrument.alto;
			break;
		case cello:
			result = Instrument.cello;
			break;
		case pizzicato:
			result = Instrument.pizzicato;
			break;
		case churchorgan:
			result = Instrument.churchorgan;
			break;
		case string:
			result = Instrument.string;
			break;
		case drumKit:
			result = Instrument.drumKit;
			break;
		case organ:
			result = Instrument.organ;
			break;
		case orchestraHit:
			result = Instrument.orchestraHit;
			break;
		case harp:
			result = Instrument.harp;
			break;
		case guitar:
			result = Instrument.guitar;
			break;
		case brassSection:
			result = Instrument.brassSection;
			break;
		case clarinet:
			result = Instrument.clarinet;
			break;
		case frenchHorn:
			result = Instrument.frenchHorn;
			break;
		case oboe:
			result = Instrument.oboe;
			break;
		case trumpet:
			result = Instrument.trumpet;
			break;
		default:
			break;
		}
		return result;
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

	public static Scale convertScale(ScaleName scale, Note scaleFundamental) {
		switch(scale){
		case aeolian:
			return new Aeolian(convertNote(scaleFundamental));
		case dorian:
			return new Dorian(convertNote(scaleFundamental));
		case ionian:
			return new Ionian(convertNote(scaleFundamental));
		case locrian:
			return new Locrian(convertNote(scaleFundamental));
		case lydian:
			return new Lydian(convertNote(scaleFundamental));
		case myxolydian:
			return new Myxolydian(convertNote(scaleFundamental));
		case phrygian:
			return new Phrygian(convertNote(scaleFundamental));
		default:
			return null;
		
		}
	}

}
