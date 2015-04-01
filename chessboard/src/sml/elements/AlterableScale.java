package sml.elements;

import sml.interfaces.IScale;
import sml.interfaces.IVisitor;

public class AlterableScale implements IScale {
	
	private IScale scale;
	private Boolean[] alterations;
	
	public AlterableScale(IScale s,Boolean a1,Boolean a2,Boolean a3,Boolean a4,
			Boolean a5,Boolean a6,Boolean a7){
		this.scale=s;
		alterations=new Boolean[7];
		alterations[0]=a1;
		alterations[1]=a2;
		alterations[2]=a3;
		alterations[3]=a4;
		alterations[4]=a5;
		alterations[5]=a6;
		alterations[6]=a7;
		
	}

	public Boolean[] getAlterations() {
		return alterations;
	}
	
	public IScale getScale(){
		return scale;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		scale.accept(visitor);
		
	}

}
