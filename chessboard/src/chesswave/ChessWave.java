package chesswave;

import move.InvalidMoveException;
import move.Move;
import chesswave.piece.AbstractChessWavePiece;
import board.Board;
import board.BoardTools;
import board.Square;
import board.EnumBoard;
import board.InstanceBoard;
import static board.Square.*;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNMove;
import pgn.PGNParser;

public class ChessWave {

	private static Square[][] plage = 
		{ { A1 , A2 , A3 , A4 } ,	{ B1 , B2 , B3 , B4 } ,	{ C1 , C2 , C3 , C4 } ,	{ D1 , D2 , D3 , D4 } ,
		{ E1 , E2 , E3 , E4 } ,	{ F1 , F2 , F3 , F4 } ,	{ G1 , G2 , G3 , G4 } ,	{ H1 , H2 , H3 , H4 } ,
		{ A5 , A6 , A7 , A8 } ,	{ B5 , B6 , B7 , B8 } ,	{ C5 , C6 , C7 , C8 } ,	{ D5 , D6 , D7 , D8 } ,
		{ E5 , E6 , E7 , E8 } ,	{ F5 , F6 , F7 , F8 } ,	{ G5 , G6 , G7 , G8 } ,	{ H5 , H6 , H7 , H8 }
		};

	private Board board;

	public ChessWave(){
		board = EnumBoard.TheBoard;
		BoardTools.initBoard(board);
	}


	public void playAMove(Move mv){
		mv.applyTo(board);
	}

	public void createMidiFromPGN(String pgnFileName,String midiFileName){

		ChessWaveMidi md=new ChessWaveMidi();

		PGNParser parser=new PGNParser(pgnFileName);

		PGNGame pgnGame = null;

		try {
			parser.parse();
			pgnGame = parser.makePgnGame();
		} catch (InvalidMoveException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InvalidPGNMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int time=0;

		LoopSequence ls = new LoopSequence();
		
		for(PGNMove pm : pgnGame){

			playAMove(pm.makeMove(board));
			
			ls.add(new Loop(board));


//			for(int j=0;j<plage.length;j++){
//				for(Square c: plage[j]){
//					if(!board.isEmpty(c)){
//						System.out.println(c);
//						md.addKey(time, AbstractChessWavePiece.fromPiece(board.getPiece(c)), c);
//					}
//				}
//
//				time++;
//			}
		}
		
		System.out.println(ls);

		md.saveMidi(midiFileName);
	}



}
