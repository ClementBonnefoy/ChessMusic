package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import board.EPiece;
import board.Type;

public class PiecesPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	Image emptyBoard, initialBoard, invertBoard; 

	private final ChessFrame chessFrame;
	
	private final EditChessBoardPanel editChessBoardPanel;

	public PiecesPanel(ChessFrame chessFrame, EditChessBoardPanel editChessBoardPanel) {
		super();
		this.editChessBoardPanel = editChessBoardPanel;
		setMinimumSize(new Dimension(409,103));
		setPreferredSize(new Dimension(409,103));
		try {
			emptyBoard = ImageIO.read(new File("images/"+chessFrame.getImagesSet()+"/empty_board_50x50.png"));
			initialBoard = ImageIO.read(new File("images/"+chessFrame.getImagesSet()+"/initial_board_50x50.png"));
//			invertBoard = ImageIO.read(new File("images/invert_board_50x50.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.chessFrame = chessFrame;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics graphics) {
		int width = getWidth();

		int squareSize = (width-9)/8;

		graphics.setColor(Color.white);

		graphics.fillRect(0, 0, getWidth(), getHeight());

		graphics.setColor(Color.black);

		for (int i = 0; i < 9; i++)
			graphics.drawLine(i*(squareSize+1), 0, i*(squareSize+1), 2*(squareSize+1));

		for (int i = 0; i < 3; i++)
			graphics.drawLine(0, i*(squareSize+1), 8*(squareSize+1), i*(squareSize+1));

		graphics.drawImage(emptyBoard,1,1,null);
		graphics.drawImage(initialBoard,1,squareSize+2,null);
//		graphics.drawImage(invertBoard, squareSize+2, 1, null);


		int i = 0, j;
		for (board.Color color : board.Color.values()) {
			j = 0;
			for (Type type : Type.values()) {
				graphics.drawImage(chessFrame.getImages().get(EPiece.get(type, color)),
						(j+2)*(squareSize+1)+1, i*(squareSize+1)+1, null);
				j++;
			}
			i++;
		}

		//		if (chessFrame.getDragged() != null)
		//			graphics.drawImage(draggedImg,
		//					posX + chessFrame.getDraggedDiffX(),
		//					posY + chessFrame.getDraggedDiffY(),
		//					null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		getParent().dispatchEvent(new MouseEvent(getParent(), e.getID(),
				e.getWhen(), e.getModifiers(), e.getX()+getX(), e.getY()+getY(),
				e.getClickCount(), e.isPopupTrigger(), e.getButton()));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int width = getWidth();

		int squareSize = (width-9)/8;

		if (x%(squareSize+1) == 0 || y%(squareSize+1) == 0)
			return;

		x /= squareSize + 1;
		y /= squareSize + 1;

		if (x == 0) {
			if (y == 1) {
				editChessBoardPanel.getBoard().init();
				getParent().repaint();
			}
			else {
				editChessBoardPanel.getBoard().reset();
				getParent().repaint();
			}	
		}
//		else if (x == 1) {
//			if (y == 0) {
//				chessFrame.invertBoard();
//				getParent().repaint();
//			}
//		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int width = getWidth();

		int squareSize = (width-9)/8;

		if (x%(squareSize+1) == 0 || y%(squareSize+1) == 0)
			return;

		x /= squareSize + 1;
		y /= squareSize + 1;
		if (x > 1) {
			EPiece ePiece = EPiece.get(Type.values()[x-2], board.Color.values()[y]);
			chessFrame.setDragged(ePiece);
			chessFrame.setDraggedDiffX(x*(squareSize+1) - e.getX());
			chessFrame.setDraggedDiffY(y*(squareSize+1) - e.getY());
		}

		chessFrame.setDraggedFromChessBoard(false);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getParent().dispatchEvent(new MouseEvent(getParent(), e.getID(),
				e.getWhen(), e.getModifiers(), e.getX()+getX(), e.getY()+getY(),
				e.getClickCount(), e.isPopupTrigger(), e.getButton()));

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
