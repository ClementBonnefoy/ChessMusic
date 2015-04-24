package sml.elements;

import sml.interfaces.IRole;
import sml.interfaces.IVisitor;

public class Role implements IRole {
	
	private int role;
	
	public static Role role(int n){
		return new Role(n);
	}

	public Role(int n) {
		this.role=n;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}

	@Override
	public int getRole() {
		return role;
	}

	@Override
	public int getAlteration() {
		return 0;
	}

}
