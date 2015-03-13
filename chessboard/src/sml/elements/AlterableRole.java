package sml.elements;

import sml.interfaces.IRole;

public class AlterableRole implements IRole {
	
	private boolean alteration;
	private IRole role;
	
	public AlterableRole(IRole role,boolean alteration){
		this.role=role;
		this.alteration=alteration;
	}

}
