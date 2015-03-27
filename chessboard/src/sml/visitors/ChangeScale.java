package sml.visitors;

import sml.elements.Scale;
import sml.elements.ScaleName;
import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitable;
import sml.interfaces.IVisitor;

public class ChangeScale implements IVisitor{

	private ScaleName scale;

	public ChangeScale(ScaleName scale){
		this.scale=scale;
	}

	@Override
	public void visit(IVisitable obj) {
		if(obj instanceof Scale)
			((Scale)obj).setScale(scale);
		
	}
}
