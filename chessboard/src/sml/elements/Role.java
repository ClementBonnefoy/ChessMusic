package sml.elements;

import sml.interfaces.IRole;
import sml.interfaces.IVisitor;

public enum Role implements IRole {
	
	fundamental,second,third,fourth,fifth,sixth,seventh;
	
	public static Role role(int n){
		n=(n-1)%7;
		return Role.values()[n];
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}

	@Override
	public int getRole() {
		return this.ordinal()+1;
	}

	@Override
	public int getAlteration() {
		return 0;
	}

}