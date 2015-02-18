package move;

import board.Square;
import board.File;
import board.Rank;
import static board.Rank.*;
import static board.File.*;

public enum Direction {
	North (null, Rank8),
	NorthEast (FileH, Rank8),
	East (FileH, null),
	SouthEast (FileH, Rank1),
	South (null, Rank1),
	SouthWest (FileA, Rank1),
	West (FileA, null),
	NorthWest (FileA, Rank8);
	
	private final File limitFile;
	private final Rank limitRank;

	
	
	private Direction(File limitFile, Rank limitRank) {
		this.limitRank = limitRank;
		this.limitFile = limitFile;
	}
	
	public boolean hasNextSquare(Square c) {
		return c.getFile() != limitFile && c.getRank() != limitRank;
		
	}
	
	public Square nextSquare(Square c) {

		File file = c.getFile();
		Rank rank = c.getRank();
		
		switch (this) {
		case North :
			rank = rank.nextRank();
			break;
		case NorthEast :
			rank = rank.nextRank();
			file = file.nextFile();
			break;
		case East :
			file = file.nextFile();
			break;
		case SouthEast :
			rank = rank.previousRank();
			file = file.nextFile();
			break;
		case South :
			rank = rank.previousRank();
			break;
		case SouthWest :
			rank = rank.previousRank();
			file = file.previousFile();
			break;
		case West :
			file = file.previousFile();
			break;
		case NorthWest :
			file = file.previousFile();
			rank = rank.nextRank();
			break;
		}
		
		return Square.getSquare(file,rank);
	}
	
	
}
