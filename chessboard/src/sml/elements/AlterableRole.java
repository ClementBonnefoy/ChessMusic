package sml.elements;

import sml.interfaces.IRole;
import sml.interfaces.IVisitor;

public class AlterableRole implements IRole{
	
	private boolean alteration;
	private IRole role;
	
	public AlterableRole(IRole role,boolean alteration){
		this.role=role;
		this.alteration=alteration;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		role.accept(visitor);
	}

	@Override
	public int getRole() {
		return role.getRole();
	}

	@Override
	public int getAlteration() {
		if(alteration)
			return 1+role.getAlteration();
		return -1+role.getAlteration();
	}

}
