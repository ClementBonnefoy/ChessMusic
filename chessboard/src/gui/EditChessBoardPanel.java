package gui;

import static board.Type.King;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import board.Board;
import board.EPiece;
import board.ESquare;
import board.Piece;

@SuppressWarnings("serial")
public class EditChessBoardPanel extends ChessBoardPanel implements MouseListener, MouseMotionListener {

	private ESquare draggedFrom;

	private Piece draggedPiece;

	public void copyBoard(Board boardToCopy) {
		this.board = boardToCopy.clone();
	}

	public Board getBoard() {
		return board;
	}

	public EditChessBoardPanel(ChessFrame chessFrame) {
		super(chessFrame);
		setMinimumSize(new Dimension(409,409));
		setPreferredSize(new Dimension(409,409));
		loadPiecesImages();
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		chessFrame.setDraggedFromChessBoard(true);
		int x = e.getX();
		int y = e.getY();
		if (x%(squareSize+1) == 0 || y%(squareSize+1) == 0)
			return;

		x /= squareSize + 1;
		y /= squareSize + 1;

		if (!chessFrame.isInverted())
			y = 7-y;	

		draggedFrom = ESquare.getSquare(x, y);
		Piece piece = board.getPiece(draggedFrom);
		if (piece != null) {
			board.putOnSquare(null, draggedFrom);
			draggedPiece = piece;
			chessFrame.setDragged(piece.getEPiece());
			chessFrame.setDraggedDiffX(x*(squareSize+1) - e.getX());
			if (!chessFrame.isInverted())
				y = 7-y;
			chessFrame.setDraggedDiffY(y*(squareSize+1) - e.getY());
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (chessFrame.getDragged() != null) {
			EPiece dragged = chessFrame.getDragged();

			if (contains(e.getPoint())) {
				int x = e.getX();
				int y = e.getY();
				if (x%(squareSize+1) == 0 || y%(squareSize+1) == 0)
					return;

				x /= squareSize + 1;
				y /= squareSize + 1;

				if (!chessFrame.isInverted())
					y = 7-y;	

				ESquare eSquare = ESquare.getSquare(x,y);
				if (chessFrame.isDraggedFromChessBoard()) {
					if (board.getPiece(eSquare) == null) {
						board.putOnSquare(draggedPiece, eSquare);
						if (draggedPiece.getType() == King)
							board.getSide(dragged.getColor()).setKing(board.get(eSquare));
						else
							board.getSide(dragged.getColor()).replace(board.get(draggedFrom),board.get(eSquare));
					}
					else
						board.putOnSquare(draggedPiece, draggedFrom);
				}
				else {
					if (dragged.getType() == King) {
						if (board.getSide(dragged.getColor()).getKing() == null) {
							board.putOnSquare(board.makePiece(dragged), eSquare);
							board.getSide(dragged.getColor()).setKing(board.get(eSquare));
						}
					}
					else {
						board.putOnSquare(board.makePiece(dragged), eSquare);
						board.getSide(dragged.getColor()).add(board.get(eSquare));
					}
				}
			}
			else {
				if (dragged.getType() == King) {
					board.getSide(dragged.getColor()).setKing(null);
				}
				else
					board.getSide(dragged.getColor()).remove(board.get(draggedFrom));
			}
			chessFrame.setDragged(null);
			getParent().repaint();

//			repaint();
		}

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
		// TODO Auto-generated method stub

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
