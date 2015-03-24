package gui;

import static board.Color.*;
import static board.Type.Bishop;
import static board.Type.Knight;
import static board.Type.Queen;
import static board.Type.Rook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import move.Move;
import move.Movement;
import move.Promotion;
import board.Board;
import board.ESquare;
import board.Piece;
import board.Type;

@SuppressWarnings("serial")
public class PlayChessBoardPanel extends ChessBoardPanel implements MouseListener {

	private boolean showPossibleMoves = false, autoQueenPromotion = false, gettingPromotion = false;

	private Type promotionType;

	private ESquare selected;

	public PlayChessBoardPanel(ChessFrame chessFrame, Board board) {
		super(chessFrame);
		this.board = board;
		setMinimumSize(new Dimension(409,409));
		setPreferredSize(new Dimension(409,409));
		loadPiecesImages();
		setLayout(null);
		addMouseListener(this);
	}

	public void setShowPossibleMoves(boolean showPossibleMoves) {
		this.showPossibleMoves = showPossibleMoves;
	}

	public void setAutoQueenPromotion(boolean autoQueenPromotion) {
		this.autoQueenPromotion = autoQueenPromotion;
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (selected != null) {
			int i = selected.getFile().getNum();
			int j = chessFrame.isInverted() ? selected.getRank().getNum() : 7 - selected.getRank().getNum();
			graphics.setColor(Color.red);
			graphics.fillRect(i*(squareSize+1)+1, j*(squareSize+1)+1, 3, squareSize);
			graphics.fillRect(i*(squareSize+1)+1, j*(squareSize+1)+1, squareSize, 3);
			graphics.fillRect((i+1)*(squareSize+1)-3, j*(squareSize+1)+1, 3, squareSize);
			graphics.fillRect(i*(squareSize+1)+1, (j+1)*(squareSize+1)-3, squareSize, 3);

			if (showPossibleMoves && board.get(selected).getPiece() != null) {
				Piece selectedPiece = board.get(selected).getPiece();
				Movement mvmt = Movement.get(selectedPiece.getType());
				graphics.setColor(new Color(255,0,0,200));
				int promotionCount = 0;
				for (Move move : mvmt.realMoves(board, selected)) {
					ESquare to = move.getTo();
					int x = to.getFile().getNum();
					int y = to.getRank().getNum();

					// TODO pour que la promotion ne superpose qu'un carré rouge
					//					if (move instanceof Promotion)
					//						continue;

					if (!chessFrame.isInverted())
						y = 7-y;
					if (move instanceof Promotion) {
						promotionCount = (promotionCount+1)%4;
						if (promotionCount == 0)
							graphics.fillRect(x*(squareSize+1)+1, y*(squareSize+1)+1,
									squareSize, squareSize);
					}
					else
						graphics.fillRect(x*(squareSize+1)+1, y*(squareSize+1)+1,
								squareSize, squareSize);
				}
			}
		}
		paintChildren(graphics);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gettingPromotion)
			return;
		int x = e.getX();
		int y = e.getY();
		if (x%(squareSize+1) == 0 || y%(squareSize+1) == 0)
			return;

		x /= squareSize + 1;
		y /= squareSize + 1;

		if (!chessFrame.isInverted())
			y = 7-y;

		ESquare tmp = ESquare.getSquare(x, y);
		if (selected == null)
			selected = tmp;
		else if (selected == tmp)
			selected = null;
		else {
			Piece selectedPiece = board.get(selected).getPiece();
			if (selectedPiece != null) {
				Movement mvmt = Movement.get(selectedPiece.getType());
				Move move;
				ArrayList<Move> moves = mvmt.realMoves(board, selected);
				if (moves.size() > 0) {
					int i = 0;
					do {
						move = moves.get(i++);
					} while (i < moves.size() && move.getTo() != tmp);
					if (move instanceof Promotion) {
						if (autoQueenPromotion)
							move = ((Promotion) move).copyMoveWithType(Queen);
						else {
							processPromotion(x, move);
							return;
						}
					}
					if (move.getTo() == tmp) {
						chessFrame.getPlayedMoves().addFirst(move);
						chessFrame.getPgnTextArea().addMove(move.makePGNMove(board), board.getMoveNumber());
						move.applyTo(board);
					}
				}
			}
			selected = null;
		}

		repaint();
	}

	public void processPromotion(int x, final Move move) {
		gettingPromotion = true;

		final int newSquareSize = squareSize/2 + 5;
		int posX = squareSize/2 - (newSquareSize+1)  + (squareSize+1)*x + getX();
		int posY = squareSize/2 - (newSquareSize+1) + getY();

		if (chessFrame.isInverted()) {
			if (board.getCurrentPlayer() == White)
				posY += 7*(squareSize+1);
		}
		else if (board.getCurrentPlayer() == Black)
			posY += 7*(squareSize+1);


		final PromotionPanel promoPanel = new PromotionPanel(newSquareSize,
				board.getCurrentPlayer(), chessFrame);

		promoPanel.setBounds(posX, posY, 3+2*squareSize, 3+2*squareSize);
		getParent().add(promoPanel,0);
		getParent().repaint();
		promoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int px = event.getX();
				int py = event.getY();
				if (px%(newSquareSize+1) == 0 || py%(newSquareSize+1) == 0)
					return;

				px /= newSquareSize + 1;
				py /= newSquareSize + 1;			

				if (py == 0) {
					if (px == 0)
						promotionType = Queen;
					else
						promotionType = Rook;
				}
				else {
					if (px == 0)
						promotionType = Knight;
					else
						promotionType = Bishop;

				}
				Move newMove = ((Promotion) move).copyMoveWithType(promotionType);

				chessFrame.getPlayedMoves().addFirst(newMove);
				chessFrame.getPgnTextArea().addMove(newMove.makePGNMove(board), board.getMoveNumber());
				newMove.applyTo(board);
				getParent().remove(promoPanel);
				selected = null;
				gettingPromotion = false;
				getParent().repaint();
			}

		});


	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
