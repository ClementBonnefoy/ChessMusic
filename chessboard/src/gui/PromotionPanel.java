package gui;

import static board.Type.Bishop;
import static board.Type.Knight;
import static board.Type.Queen;
import static board.Type.Rook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import board.EPiece;
import board.Type;

public class PromotionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int squareSize;

	private Type promotionType = null;

	private final board.Color currentPlayer;
	
	private final ChessFrame chessFrame;

	public PromotionPanel(int squareSize, board.Color currentPlayer, ChessFrame chessFrame) {
		this.chessFrame = chessFrame;
		this.squareSize = squareSize;
		setMinimumSize(new Dimension(2*squareSize+3, 2*squareSize+3));
		setPreferredSize(new Dimension(2*squareSize+3, 2*squareSize+3));
		this.currentPlayer = currentPlayer;

	}

	public Type getPromotionType() {
		return promotionType;
	}

	@Override
	public void paint(Graphics graphics) {
		graphics.setColor(Color.black);
		for (int i = 0; i < 3; i++) {
			graphics.drawLine(i*(squareSize+1), 0, i*(squareSize+1), 2*(squareSize+1));
			graphics.drawLine(0, i*(squareSize+1), 2*(squareSize+1), i*(squareSize+1));
		}

		graphics.setColor(new Color(.3f,.3f,1f));
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				graphics.fillRect(i*(squareSize+1)+1, j*(squareSize+1)+1,
						squareSize, squareSize);
			}

		graphics.drawImage(chessFrame.getImages().get(
				EPiece.get(Queen, currentPlayer))
				.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH), 1, 1, null);
		graphics.drawImage(chessFrame.getImages().get(
				EPiece.get(Rook, currentPlayer))
				.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH), squareSize + 2, 1, null);
		graphics.drawImage(chessFrame.getImages().get(
				EPiece.get(Knight, currentPlayer))
				.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH), 1, squareSize + 2, null);
		graphics.drawImage(chessFrame.getImages().get(
				EPiece.get(Bishop, currentPlayer))
				.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH), squareSize + 2, squareSize + 2, null);
	}


}
