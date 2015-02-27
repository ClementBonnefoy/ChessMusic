package board;

import java.util.ArrayList;
import java.util.Iterator;

public enum File implements Iterable<ESquare> {	
	FileA, FileB, FileC, FileD, FileE, FileF, FileG, FileH;

	public int getNum() {
		return ordinal();
	}

	public static File getFile(int num) {
		return File.values()[num];
	}
	
	public static File getFile(char c) {
		if (c <= 'H' && c >= 'A')
			return File.values()[c - 'A'];
		return File.values()[c - 'a'];
	}

	public ESquare getSquare(int num) {
		return ESquare.values()[(num << 3) + getNum()];
	}
	
	public File nextFile() {
		if (this == FileH)
			return null;
		return File.values()[getNum()+1];
	}
	
	public File previousFile() {
		if (this == FileA)
			return null;
		return File.values()[getNum()-1];
	}
	
	@Override
	public Iterator<ESquare> iterator() {
		
		ArrayList<ESquare> squares = new ArrayList<>(8);
		
		for (int i = 0; i < 8; i++)
			squares.add(getSquare(i));
		
		return squares.iterator();
	}
	
	@Override
	public String toString() {
		return "" + super.toString().charAt(4);
	}

}

