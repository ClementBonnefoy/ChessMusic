package sml.elements;

import sml.interfaces.IRole;

public enum Role implements IRole {
	
	fundamental,second,third,fourth,fifth,sixth,seventh;
	
	public static Role role(int n){
		n=n%7-1;
		return Role.values()[n];
	}

}
