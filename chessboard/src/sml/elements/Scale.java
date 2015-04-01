package sml.elements;

import sml.interfaces.IPlayableScale;
import sml.interfaces.IScale;
import sml.interfaces.IVisitor;

public class Scale implements IPlayableScale {
	
	private Note note;
	private IScale scale;
	
	public Scale(Note note, IScale scale) {
		super();
		this.note = note;
		this.scale = scale;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		note.accept(visitor);
		scale.accept(visitor);
		
	}
	
	@Override
	public String toString(){
		return note+" "+scale;
	}
	
	public void setScale(IScale scale){
		this.scale=scale;
	}

	@Override
	public IScale getScale(Declarations env) {
		return scale;
	}
	@Override
	public Note getScaleFundamental(Declarations env) {
		return note;
	}
	
	
	
	

}
