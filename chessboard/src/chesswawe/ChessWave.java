package chesswawe;

import static board.ESquare.A1;
import static board.ESquare.A2;
import static board.ESquare.A3;
import static board.ESquare.A4;
import static board.ESquare.A5;
import static board.ESquare.A6;
import static board.ESquare.A7;
import static board.ESquare.A8;
import static board.ESquare.B1;
import static board.ESquare.B2;
import static board.ESquare.B3;
import static board.ESquare.B4;
import static board.ESquare.B5;
import static board.ESquare.B6;
import static board.ESquare.B7;
import static board.ESquare.B8;
import static board.ESquare.C1;
import static board.ESquare.C2;
import static board.ESquare.C3;
import static board.ESquare.C4;
import static board.ESquare.C5;
import static board.ESquare.C6;
import static board.ESquare.C7;
import static board.ESquare.C8;
import static board.ESquare.D1;
import static board.ESquare.D2;
import static board.ESquare.D3;
import static board.ESquare.D4;
import static board.ESquare.D5;
import static board.ESquare.D6;
import static board.ESquare.D7;
import static board.ESquare.D8;
import static board.ESquare.E1;
import static board.ESquare.E2;
import static board.ESquare.E3;
import static board.ESquare.E4;
import static board.ESquare.E5;
import static board.ESquare.E6;
import static board.ESquare.E7;
import static board.ESquare.E8;
import static board.ESquare.F1;
import static board.ESquare.F2;
import static board.ESquare.F3;
import static board.ESquare.F4;
import static board.ESquare.F5;
import static board.ESquare.F6;
import static board.ESquare.F7;
import static board.ESquare.F8;
import static board.ESquare.G1;
import static board.ESquare.G2;
import static board.ESquare.G3;
import static board.ESquare.G4;
import static board.ESquare.G5;
import static board.ESquare.G6;
import static board.ESquare.G7;
import static board.ESquare.G8;
import static board.ESquare.H1;
import static board.ESquare.H2;
import static board.ESquare.H3;
import static board.ESquare.H4;
import static board.ESquare.H5;
import static board.ESquare.H6;
import static board.ESquare.H7;
import static board.ESquare.H8;

import java.io.File;

import move.InvalidMoveException;
import move.KingSideCastling;
import move.Move;
import move.QueenSideCastling;
import music.NoteName;
import music.Scale;
import music.scale.PentatonicFake;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNMove;
import pgn.PGNParser;
import board.Board;
import board.EPiece;
import board.ESquare;
import board.Piece;
import board.Type;
import chesswawe.piece.ChessWavePiece;
import eco.ECOParser;

public class ChessWave {
	
	private Scale scale;
	private ChessWaveMidi md;
	private PGNGame pgnGame;
	private Board board;
	private boolean analyse=false;
	
	public class MovedPiece extends Piece {
		public MovedPiece(EPiece ePiece) {
			super(ePiece);
		}

		private boolean hasAlreadyMoved = false;
		
		public boolean hasAlreadyMoved() {
			return hasAlreadyMoved;
		}
		
		@Override
		public void onMove(Board board, Move move) {
			hasAlreadyMoved = true;
			if(move instanceof KingSideCastling 
					|| move instanceof QueenSideCastling){
				if(this.getType()==Type.King){
					hasAlreadyMoved=false;
				}
			}
				
		}
	}

	private static ESquare[][] plage = 
		{ { A1 , A2 , A3 , A4 } ,	{ B1 , B2 , B3 , B4 } ,	{ C1 , C2 , C3 , C4 } ,	{ D1 , D2 , D3 , D4 } ,
		{ E1 , E2 , E3 , E4 } ,	{ F1 , F2 , F3 , F4 } ,	{ G1 , G2 , G3 , G4 } ,	{ H1 , H2 , H3 , H4 } ,
		{ A5 , A6 , A7 , A8 } ,	{ B5 , B6 , B7 , B8 } ,	{ C5 , C6 , C7 , C8 } ,	{ D5 , D6 , D7 , D8 } ,
		{ E5 , E6 , E7 , E8 } ,	{ F5 , F6 , F7 , F8 } ,	{ G5 , G6 , G7 , G8 } ,	{ H5 , H6 , H7 , H8 }
		};

	

	@SuppressWarnings("serial")
	public ChessWave(File pgnFile){
		board = new Board(){
			@Override
			public Piece makePiece (EPiece ePiece) {
				return new MovedPiece (ePiece) {
					
				};
			}
		};
		board.init();
		

		PGNParser parser=new PGNParser(pgnFile);

		try {
			parser.parse();
			pgnGame = parser.makePgnGame();
		} catch (InvalidMoveException | InvalidPGNMoveException e) {
			e.printStackTrace();
			return;
		}
		
		initScale(pgnGame);

	}
	
	public ChessWave(String pgnFileName){
		this(new File(pgnFileName));
	}


	public void playAMove(Move mv){
		mv.applyTo(board);
	}
	
	
	/*public void createMidiFromPGN(String pgnFileName, String midiFileName) {
		createMidiFromPGN(new File(pgnFileName), midiFileName);
	}*/
	

	public void analyse(){
		if(analyse)
			return;
		
		
		md=new ChessWaveMidi();
		
		int time=0;

		for(PGNMove pm : pgnGame){
			playAMove(pm.makeMove(board));

			for(int j=0;j<plage.length;j++){
				for(ESquare c: plage[j]){
					if(!board.isEmpty(c)){
						if(((MovedPiece) board.getPiece(c)).hasAlreadyMoved())
							md.addKey(time, ChessWavePiece.fromPiece(board.getPiece(c),scale), c);
					}
				}

				time++;
			}
		}
		analyse=true;
	}
	
	
	public void saveAsMidi(String fileName){
		analyse();
		md.saveMidi(fileName);
	}
	
	public void play(){
		analyse();
		md.play();
		
	}


	private void initScale(PGNGame pgnGame) {
		if(!Configuration.ECOscale){
			scale=new PentatonicFake(NoteName.C);
			return;
		}
		ECOParser parser=new ECOParser("data/eco.txt");
		String code=parser.findCode(pgnGame);
		NoteName note=NoteName.valueOf(code.substring(0, 1));
		Integer nombre=Integer.valueOf(code.substring(1,code.length()));
		if(nombre==0){
			scale=new PentatonicFake(note);
		}else{
			scale=new Scale(note,nombre);
		}
		System.out.println(scale);
		
	}


	



}
