package gui;

import static board.Color.Black;
import static board.Color.White;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.EnumMap;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import move.Move;
import board.Board;
import board.EPiece;

public class ChessFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private String imagesSet = "set_1";

	private EnumMap<EPiece, Image> images = new EnumMap<>(EPiece.class);

	private EPiece dragged;

	private int draggedDiffX, draggedDiffY;

	private int posX, posY;

	private LinkedList<Move> playedMoves = new LinkedList<>();

	private final PGNTextArea pgnTextArea;

	private boolean inverted = false;

	private boolean draggedFromChessBoard;

	@SuppressWarnings("serial")
	public ChessFrame(Board board) throws HeadlessException {
		super("ChessMusic");

		JTabbedPane onglets = new JTabbedPane();

		GridBagLayout layout = new GridBagLayout();

		final PlayChessBoardPanel playChessBoardPanel = new PlayChessBoardPanel(this, board);

		final JPanel playPanel = new JPanel(new GridBagLayout());

		playPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U,0), "undoLastMove");
		playPanel.getActionMap().put("undoLastMove", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!playedMoves.isEmpty()) {
					playedMoves.pop().undo(playChessBoardPanel.getBoard());
					pgnTextArea.removeLastMove();
					playPanel.repaint();
				}
			}
		});

		pgnTextArea = new PGNTextArea();

		JScrollPane scrollPane = new JScrollPane(pgnTextArea); 
		pgnTextArea.setEditable(false);
		scrollPane.setPreferredSize(new Dimension(200,410));
		scrollPane.setMinimumSize(new Dimension(200,410));

		JPanel overPlayPanel = new JPanel(null) ;
		playChessBoardPanel.setBounds(7, 7, 409, 409);
		overPlayPanel.setMinimumSize(new Dimension(409+14,409+14));
		overPlayPanel.setPreferredSize(new Dimension(409+14,409+14));
		overPlayPanel.add(playChessBoardPanel);
		overPlayPanel.validate();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		playPanel.add(overPlayPanel, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		playPanel.add(scrollPane, gbc);

		JPanel optionPanel = new JPanel ();

		JCheckBox invertCheckBox = new JCheckBox("Invert");
		invertCheckBox.setSelected(false);
		invertCheckBox.setFocusable(false);
		invertCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					inverted = true;
					playChessBoardPanel.repaint();
				}
				else {
					inverted = false;
					playChessBoardPanel.repaint();
				}
			}
		});

		optionPanel.add(invertCheckBox);

		JCheckBox showMovesCheckBox = new JCheckBox("Show moves");
		showMovesCheckBox.setSelected(false);
		showMovesCheckBox.setFocusable(false);
		showMovesCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					playChessBoardPanel.setShowPossibleMoves(true);
					playChessBoardPanel.repaint();
				}
				else {
					playChessBoardPanel.setShowPossibleMoves(false);
					playChessBoardPanel.repaint();
				}
			}
		});

		optionPanel.add(showMovesCheckBox);

		JCheckBox autoPromotionCheckBox = new JCheckBox("Auto queen promotion");
		autoPromotionCheckBox.setSelected(false);
		autoPromotionCheckBox.setFocusable(false);
		autoPromotionCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					playChessBoardPanel.setAutoQueenPromotion(true);
				}
				else {
					playChessBoardPanel.setAutoQueenPromotion(false);
				}
			}
		});

		optionPanel.add(autoPromotionCheckBox);

		optionPanel.validate();

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		playPanel.add(optionPanel,gbc);

		final EditChessBoardPanel editChessBoardPanel = new EditChessBoardPanel(this);

		PiecesPanel piecesPanel = new PiecesPanel(this, editChessBoardPanel);

		final JPanel editPanel = new JPanel(layout) {

			public void paint(Graphics graphics) {
				super.paint(graphics);
				if (dragged != null) {
					graphics.drawImage(images.get(dragged), posX + draggedDiffX, posY + draggedDiffY, null);
				}
			}
		};

		editPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragged != null) {
					posX = e.getX();
					posY = e.getY();
					editPanel.repaint();
				}
			}

		});

		editPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(editPanel.getComponentAt(e.getX(),e.getY()) == editChessBoardPanel) {
					editChessBoardPanel.dispatchEvent(new MouseEvent(editChessBoardPanel, e.getID(),
							e.getWhen(), e.getModifiers(),
							e.getX()-editChessBoardPanel.getX(),
							e.getY()-editChessBoardPanel.getY(),
							e.getClickCount(), e.isPopupTrigger(), e.getButton()));
				}
				else {
					if (dragged != null)
						dragged = null;
					editPanel.repaint();
				}
			}


		});

		setMinimumSize(new Dimension(400,400));

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		editPanel.add(editChessBoardPanel, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		editPanel.add(piecesPanel, gbc);

		optionPanel = new JPanel();

		invertCheckBox = new JCheckBox("Invert");
		invertCheckBox.setSelected(false);
		invertCheckBox.setFocusable(false);
		invertCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					inverted = true;
					editChessBoardPanel.repaint();
				}
				else {
					inverted = false;
					editChessBoardPanel.repaint();
				}
			}
		});

		optionPanel.add(invertCheckBox);
		optionPanel.validate();

		final JCheckBox playerCheckBox = new JCheckBox("White to play");
		playerCheckBox.setSelected(true);
		playerCheckBox.setFocusable(false);
		playerCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED)
					editChessBoardPanel.getBoard().setCurrentPlayer(White);
				else
					editChessBoardPanel.getBoard().setCurrentPlayer(Black);
			}
		});

		optionPanel.add(playerCheckBox);
		optionPanel.validate();

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		editPanel.add(optionPanel, gbc);

		JButton validateButton = new JButton("Validate");
		validateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (editChessBoardPanel.getBoard().getSide(White).getKing() != null && editChessBoardPanel.getBoard().getSide(Black) != null) {
					clearMoves();
					pgnTextArea.setText("");
					playChessBoardPanel.setBoard(editChessBoardPanel.getBoard());
				}
			}
		});

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		editPanel.add(validateButton,gbc);

		editPanel.validate();
		//		getContentPane().setLayout(new GridBagLayout());
		//		gbc = new GridBagConstraints();
		//		gbc.weighty = 1.0;
		//		gbc.gridy = 0;
		onglets.addTab("Play", playPanel);
		onglets.addTab("Edit mode", editPanel);
		onglets.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if (index == 1)
					editChessBoardPanel.copyBoard(playChessBoardPanel.getBoard());
			}
		});

		getContentPane().add(onglets);
		pack();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void clearMoves() {
		playedMoves = new LinkedList<>();
	}


	public LinkedList<Move> getPlayedMoves() {
		return playedMoves;
	}

	public String getImagesSet() {
		return imagesSet;
	}


	public EnumMap<EPiece, Image> getImages() {
		return images;
	}


	public PGNTextArea getPgnTextArea() {
		return pgnTextArea;
	}


	public boolean isInverted() {
		return inverted;
	}


	public void invertBoard() {
		inverted = !inverted;
	}


	public EPiece getDragged() {
		return dragged;
	}


	public void setDragged(EPiece dragged) {
		this.dragged = dragged;
	}


	public int getDraggedDiffY() {
		return draggedDiffY;
	}


	public void setDraggedDiffY(int draggedDiffY) {
		this.draggedDiffY = draggedDiffY;
	}


	public int getDraggedDiffX() {
		return draggedDiffX;
	}


	public void setDraggedDiffX(int draggedDiffX) {
		this.draggedDiffX = draggedDiffX;
	}


	public boolean isDraggedFromChessBoard() {
		return draggedFromChessBoard;
	}


	public void setDraggedFromChessBoard(boolean draggedFromChessBoard) {
		this.draggedFromChessBoard = draggedFromChessBoard;
	}


}
