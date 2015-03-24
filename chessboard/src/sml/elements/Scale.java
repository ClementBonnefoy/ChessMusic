package sml.elements;

import sml.interfaces.IDeclarable;
import sml.interfaces.IVisitor;

public class Scale implements IDeclarable {
	
	private Note note;
	private ScaleName scale;
	
	public Scale(Note note, ScaleName scale) {
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
	
	public void setScale(ScaleName scale){
		this.scale=scale;
	}

	public ScaleName getScale() {
		return scale;
	}
	
	public Note getNote(){
		return note;
	}
	
	
	
	

}
