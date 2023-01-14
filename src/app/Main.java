package app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Main extends JFrame {

	public static Piece selectedPiece = null;

	public static int redWin = 0;
	public static int blueWin = 0;

	public static int redCount = 0;
	public static int blueCount = 0;

	public static int pieceClicked = 0;

	public static int canMove = 0;

	public Main() {

		setTitle("Dou Shou Qi");
		setSize(490, 667);
		setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Icon.png")));

		// Make the game appears in the middle of the screen
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

		Board bd = new Board();
		add(bd);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				if (selectedPiece != null) {
					// ------------------- regular pieces movement
					// -------------------------------------
					if ((e.getX() - 7) / 70 == selectedPiece.x + 1 && (e.getY() - 30) / 70 == selectedPiece.y) { // right
						selectedPiece.move(selectedPiece.x + 1, selectedPiece.y);
						repaint();
					} else if ((e.getX() - 7) / 70 == selectedPiece.x - 1 && (e.getY() - 30) / 70 == selectedPiece.y) { // left
						selectedPiece.move(selectedPiece.x - 1, selectedPiece.y);
						repaint();
					} else if ((e.getX() - 7) / 70 == selectedPiece.x && (e.getY() - 30) / 70 == selectedPiece.y - 1) { // up
						selectedPiece.move(selectedPiece.x, selectedPiece.y - 1);
						repaint();
					} else if ((e.getX() - 7) / 70 == selectedPiece.x && (e.getY() - 30) / 70 == selectedPiece.y + 1) { // down
						selectedPiece.move(selectedPiece.x, selectedPiece.y + 1);
						repaint();
					}
					// ------------------- lion and tiger jumps
					// -------------------------------------
					else if ((selectedPiece.name == "L" || selectedPiece.name == "bL" || selectedPiece.name == "T"
							|| selectedPiece.name == "bT") && (e.getX() - 7) / 70 == selectedPiece.x + 3
							&& (e.getY() - 30) / 70 == selectedPiece.y) { // right
						if (selectedPiece.y >= 3 && selectedPiece.y <= 5) {
							if (Board.getPiece("bR") != null && Board.getPiece("R") != null) { // no RAT killed
								if (Board.getPiece("bR").y != selectedPiece.y
										&& Board.getPiece("R").y != selectedPiece.y) {
									selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
									repaint();
								} else if (Board.getPiece("bR").y == selectedPiece.y
										|| Board.getPiece("R").y == selectedPiece.y) {
									// selectdPiece.x==0
									if (selectedPiece.x == 0 && Board.getPiece("bR").x >= 3
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									} else if (selectedPiece.x == 0 && Board.getPiece("R").x >= 3
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									}
									// selectdPiece.x==3
									else if (selectedPiece.x == 3
											&& (Board.getPiece("bR").x < 3 || Board.getPiece("bR").x == 6)
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									} else if (selectedPiece.x == 3
											&& (Board.getPiece("R").x < 3 || Board.getPiece("R").x == 6)
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") == null) { // BOTH killed
								selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
								repaint();
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") != null) { // BLUE killed
								if (Board.getPiece("R").y != selectedPiece.y) {
									selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
									repaint();
								} else if (Board.getPiece("R").y == selectedPiece.y) {
									// selectdPiece.x==0
									if (selectedPiece.x == 0 && Board.getPiece("R").x >= 3
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									}
									// selectdPiece.x==3
									else if (selectedPiece.x == 3
											&& (Board.getPiece("R").x < 3 || Board.getPiece("R").x == 6)
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}
								}
							} else if (Board.getPiece("bR") != null && Board.getPiece("R") == null) { // RED killed
								if (Board.getPiece("bR").y != selectedPiece.y) {
									selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
									repaint();
								} else if (Board.getPiece("bR").y == selectedPiece.y) {
									// selectdPiece.x==0
									if (selectedPiece.x == 0 && Board.getPiece("bR").x >= 3
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									}
									// selectdPiece.x==3
									else if (selectedPiece.x == 3
											&& (Board.getPiece("bR").x < 3 || Board.getPiece("bR").x == 6)
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x + 3, selectedPiece.y);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}
								}
							}

						}
					} else if ((selectedPiece.name == "L" || selectedPiece.name == "bL" || selectedPiece.name == "T"
							|| selectedPiece.name == "bT") && (e.getX() - 7) / 70 == selectedPiece.x - 3
							&& (e.getY() - 30) / 70 == selectedPiece.y) { // left
						if (selectedPiece.y >= 3 && selectedPiece.y <= 5) {
							if (Board.getPiece("bR") != null && Board.getPiece("R") != null) { // no RAT killed
								if (Board.getPiece("bR").y != selectedPiece.y
										&& Board.getPiece("R").y != selectedPiece.y) {
									selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
									repaint();
								} else if (Board.getPiece("bR").y == selectedPiece.y
										|| Board.getPiece("R").y == selectedPiece.y) {
									// selectdPiece.x==6
									if (selectedPiece.x == 6 && Board.getPiece("bR").x <= 3
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									} else if (selectedPiece.x == 6 && Board.getPiece("R").x <= 3
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									}
									// selectdPiece.x==3
									else if (selectedPiece.x == 3
											&& (Board.getPiece("bR").x > 3 || Board.getPiece("bR").x == 0)
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									} else if (selectedPiece.x == 3
											&& (Board.getPiece("R").x > 3 || Board.getPiece("R").x == 0)
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") == null) { // BOTH killed
								selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
								repaint();
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") != null) { // BLUE killed
								if (Board.getPiece("R").y != selectedPiece.y) {
									selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
									repaint();
								} else if (Board.getPiece("R").y == selectedPiece.y) {
									// selectdPiece.x==6
									if (selectedPiece.x == 6 && Board.getPiece("R").x <= 3
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									}
									// selectdPiece.x==3
									else if (selectedPiece.x == 3
											&& (Board.getPiece("R").x > 3 || Board.getPiece("R").x == 0)
											&& Board.getPiece("R").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}
								}
							} else if (Board.getPiece("bR") != null && Board.getPiece("R") == null) { // RED killed
								if (Board.getPiece("bR").y != selectedPiece.y) {
									selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
									repaint();
								} else if (Board.getPiece("bR").y == selectedPiece.y) {
									// selectdPiece.x==6
									if (selectedPiece.x == 6 && Board.getPiece("bR").x <= 3
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									}
									// selectdPiece.x==3
									else if (selectedPiece.x == 3
											&& (Board.getPiece("bR").x > 3 || Board.getPiece("bR").x == 0)
											&& Board.getPiece("bR").y == selectedPiece.y) {
										selectedPiece.move(selectedPiece.x - 3, selectedPiece.y);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}
								}
							}

						}
					} else if ((selectedPiece.name == "L" || selectedPiece.name == "bL" || selectedPiece.name == "T"
							|| selectedPiece.name == "bT") && (e.getX() - 7) / 70 == selectedPiece.x
							&& (e.getY() - 30) / 70 == selectedPiece.y - 4) { // ---UP
						if (selectedPiece.x == 1 || selectedPiece.x == 2 || selectedPiece.x == 4
								|| selectedPiece.x == 5) {
							if (Board.getPiece("bR") != null && Board.getPiece("R") != null) { // no RAT killed
								if (Board.getPiece("bR").x != selectedPiece.x
										&& Board.getPiece("R").x != selectedPiece.x) {
									selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
									repaint();
								} else if (Board.getPiece("bR").x == selectedPiece.x
										|| Board.getPiece("R").x == selectedPiece.x) {
									if ((Board.getPiece("bR").y <= 2 || Board.getPiece("bR").y >= 6)
											&& Board.getPiece("bR").x == selectedPiece.x) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
										repaint();
									} else if ((Board.getPiece("R").y <= 2 || Board.getPiece("R").y >= 6)
											&& Board.getPiece("R").x == selectedPiece.x) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") == null) { // BOTH killed
								selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
								repaint();
							} else if (Board.getPiece("bR") != null && Board.getPiece("R") == null) { // RED killed
								if (Board.getPiece("bR").x != selectedPiece.x) {
									selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
									repaint();
								} else if (Board.getPiece("bR").x == selectedPiece.x) {
									if ((Board.getPiece("bR").y <= 2 || Board.getPiece("bR").y >= 6)) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") != null) { // BLUE killed
								if (Board.getPiece("R").x != selectedPiece.x) {
									selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
									repaint();
								} else if (Board.getPiece("R").x == selectedPiece.x) {
									if ((Board.getPiece("R").y <= 2 || Board.getPiece("R").y >= 6)) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y - 4);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							}

						}
					} else if ((selectedPiece.name == "L" || selectedPiece.name == "bL" || selectedPiece.name == "T"
							|| selectedPiece.name == "bT") && (e.getX() - 7) / 70 == selectedPiece.x
							&& (e.getY() - 30) / 70 == selectedPiece.y + 4) { // down
						if (selectedPiece.x == 1 || selectedPiece.x == 2 || selectedPiece.x == 4
								|| selectedPiece.x == 5) {
							if (Board.getPiece("bR") != null && Board.getPiece("R") != null) { // no RAT killed
								if (Board.getPiece("bR").x != selectedPiece.x
										&& Board.getPiece("R").x != selectedPiece.x) {
									selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
									repaint();
								} else if (Board.getPiece("bR").x == selectedPiece.x
										|| Board.getPiece("R").x == selectedPiece.x) {
									if ((Board.getPiece("bR").y <= 2 || Board.getPiece("bR").y >= 6)
											&& Board.getPiece("bR").x == selectedPiece.x) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
										repaint();
									} else if ((Board.getPiece("R").y <= 2 || Board.getPiece("R").y >= 6)
											&& Board.getPiece("R").x == selectedPiece.x) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}

							} else if (Board.getPiece("bR") == null && Board.getPiece("R") == null) { // BOTH killed
								selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
								repaint();

							} else if (Board.getPiece("bR") != null && Board.getPiece("R") == null) { // RED killed
								if (Board.getPiece("bR").x != selectedPiece.x) {
									selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
									repaint();
								} else if (Board.getPiece("bR").x == selectedPiece.x) {
									if ((Board.getPiece("bR").y <= 2 || Board.getPiece("bR").y >= 6)) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							} else if (Board.getPiece("bR") == null && Board.getPiece("R") != null) { // BLUE killed
								if (Board.getPiece("R").x != selectedPiece.x) {
									selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
									repaint();
								} else if (Board.getPiece("R").x == selectedPiece.x) {
									if ((Board.getPiece("R").y <= 2 || Board.getPiece("R").y >= 6)) {
										selectedPiece.move(selectedPiece.x, selectedPiece.y + 4);
										repaint();
									} else {
										SecondFrame.str += "There is a RAT\n";
										SecondFrame.records.setText(SecondFrame.str);
									}

								} else {
									SecondFrame.str += "There is a RAT\n";
									SecondFrame.records.setText(SecondFrame.str);
								}
							}

						}
					} else {
						if (pieceClicked == 0) {
							SecondFrame.str += "You can only move Up, Down, Left or Right. Try Again!\n";
							SecondFrame.records.setText(SecondFrame.str);
						}

					}

					// BLUE
					if (selectedPiece.player == 2) {
						if (Board.countYellow() == 0 || (selectedPiece.x == 3 && selectedPiece.y == 8)) {

							blueWin++;
							SecondFrame.str += "------- BLUE wins -------\n";
							SecondFrame.str += "-------------------------\n";
							SecondFrame.str += "Score : YELLOW    " + redWin + "\n";
							SecondFrame.str += "        BLUE      " + blueWin + "\n";
							SecondFrame.str += "-------------------------\n";
							SecondFrame.records.setText(SecondFrame.str);

							// options
							Object[] possibleValues = { "New Game", "Exit" };
							Object selectedValue = JOptionPane.showInputDialog(rootPane, "Wanna play again ?",
									"Game Over", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
									possibleValues[0]);
							if (selectedValue == possibleValues[1]) {
								System.exit(0);
							} else {
								Board.restart();
								repaint();
							}

						}
					}

					// YELLOW
					if (selectedPiece.player == 1) {
						if (Board.countBlue() == 0 || (selectedPiece.x == 3 && selectedPiece.y == 0)) {

							redWin++;
							SecondFrame.str += "------- YELLOW wins -------\n";
							SecondFrame.str += "-------------------------\n";
							SecondFrame.str += "Score : YELLOW    " + redWin + "\n";
							SecondFrame.str += "        BLUE      " + blueWin + "\n";
							SecondFrame.str += "-------------------------\n";
							SecondFrame.records.setText(SecondFrame.str);

							// options
							Object[] possibleValues = { "New Game", "Exit" };
							Object selectedValue = JOptionPane.showInputDialog(rootPane, "Wanna play again ?",
									"Game Over", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
									possibleValues[0]);
							if (selectedValue == possibleValues[1]) {
								System.exit(0);
							} else {
								Board.restart();
								repaint();
							}
						}
					}

				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectedPiece = Board.findPiece(e.getX() - 7, e.getY() - 30);
				pieceClicked = 1;

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pieceClicked = 1;
				if (pieceClicked == 1) {
					SecondFrame.str += "Try to drag the piece please!!\n";
					SecondFrame.records.setText(SecondFrame.str);
				}

			}
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				pieceClicked = 0;
				if (selectedPiece != null) {
					selectedPiece.real_x = e.getX() - 7;
					selectedPiece.real_y = e.getY() - 30;
					repaint();

				}

			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		new SecondFrame();
	}

}
