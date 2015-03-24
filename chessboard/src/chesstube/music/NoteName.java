package chesstube.music;

public enum NoteName {

	A,AS,B,C,CS,D,DS,E,F,FS,G,GS; //en notation anglo-saxone, S=sharp

	public int getNumber(){
		return this.ordinal();
	}

	public NoteName incr(int i) {
		return NoteName.values()[(this.ordinal()+i)%12];
	}

	public NoteName decr(int i) {
		return NoteName.values()[(this.ordinal()-i+12)%12];
	}
	
//	public static void main(String[] args){
//		System.out.println(A.incr(12));
//		System.out.println(A.incr(5));
//		System.out.println(C.incr(7));
//	}


}


