package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import board.Board;
import board.EPiece;
import board.Square;

public class ChessBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected int squareSize = 50;

	public Color blackSquares = Color.cyan;
	public Color whiteSquares = Color.white;
	
	protected final ChessFrame chessFrame;
	
	protected Board board;

	public Color getBlackSquaresColor() {
		return blackSquares;
	}

	public Color getWhiteSquaresColor() {
		return whiteSquares;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}
	
	public ChessBoardPanel(final ChessFrame chessFrame) {
		this.chessFrame = chessFrame;
	}

	public void loadPiecesImages() {
		String path = "images/"+chessFrame.getImagesSet()+"/";
		String end = "_50x50.png";
		StringBuilder name;
		try {
			for (EPiece ePiece : EPiece.values()) {
				name = new StringBuilder();
				name.append(ePiece.name().toLowerCase());
				name.insert(5, '_');
				chessFrame.getImages().put(ePiece, ImageIO.read(new File(path+name.toString()+end))
						.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics graphics) {
//		int height = getHeight();
//
//		if (squareSize != (height-9)/8) {
//			System.out.println(getWidth()+" "+getHeight());
//			squareSize = (height-9)/8;
//			loadPiecesImages();
//		}

		graphics.setColor(Color.black);

		for (int i = 0; i < 9; i++) {
			graphics.drawLine(i*(squareSize+1), 0, i*(squareSize+1), 8*(squareSize+1));
			graphics.drawLine(0, i*(squareSize+1), 8*(squareSize+1), i*(squareSize+1));
		}

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if ((i+j)%2 == 0)
					if (chessFrame.isInverted())
						graphics.setColor(blackSquares);
					else
						graphics.setColor(whiteSquares);
				else
					if (chessFrame.isInverted())
						graphics.setColor(whiteSquares);
					else
						graphics.setColor(blackSquares);
				graphics.fillRect(i*(squareSize+1)+1, j*(squareSize+1)+1,
						squareSize, squareSize);
			}

		int i = 0, j = 0;
		Image img;
		for (Square square : board.values()) {
			if (square.getPiece() != null) {
				img = chessFrame.getImages().get(square.getPiece().getEPiece());
				if (!chessFrame.isInverted())
					graphics.drawImage(img,
							i*(squareSize+1)+1, (7-j)*(squareSize+1)+1, null);
				else
					graphics.drawImage(img,
							i*(squareSize+1)+1, j*(squareSize+1)+1, null);
			}
			if (i == 7) {
				j++;
				i = 0;
			}
			else
				i++;

		}
	}
}
