package sml.various;

public class Music {
	
	private Declarations declarations;
	private Body body;
	
	public Music(Declarations declarations, Body body) {
		super();
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public String toString() {
		return "Music [declarations=" + declarations + ", body=" + body + "]";
	}
	
	

}
