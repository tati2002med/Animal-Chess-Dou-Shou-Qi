package app;

import java.util.ArrayList;

public class Piece {

	public static int isKilled = 0;

	protected int x;
	protected int y;
	protected int real_x;
	protected int real_y;
	protected int test_x;
	protected int test_y;
	protected boolean isBlue;
	protected String name;
	protected ArrayList<Piece> ps;
	protected int rank;
	protected int player;
	protected String rname;

	public Piece(int x, int y, int test_x, int test_y, String name, boolean isBlue, ArrayList<Piece> ps, int rank,
			int player, String rname) {
		this.x = x;
		this.y = y;
		this.isBlue = isBlue;
		this.name = name;
		this.ps = ps;
		this.rank = rank;
		this.player = player;
		this.rname = rname;
		this.test_x = test_x;
		this.test_y = test_y;
		ps.add(this);

		real_x = x * Board.width;
		real_y = y * Board.height;
	}

	public void kill(Piece p) {

		if (player != p.player) {
			if (rank >= p.rank) { // Checking if you are strong.
				x = p.x;
				y = p.y;
				real_x = p.x * Board.width;
				real_y = p.y * Board.height;
				ps.remove(p);
				isKilled = 0;

			} else if (rank == 0 && p.rank == 7) {
				x = p.x;
				y = p.y;
				real_x = p.x * Board.width;
				real_y = p.y * Board.height;
				ps.remove(p); // RAT kills the ELEPHANT.
				isKilled = 0;

			}
			// Killing the BLUE animals in the traps, ranking doesn't matter anymore.
			else if (isBlue && p.y == 0 && (p.x == 2 || p.x == 4)) {
				x = p.x;
				y = p.y;
				real_x = p.x * Board.width;
				real_y = p.y * Board.height;
				ps.remove(p);
				isKilled = 0;

			} else if (isBlue && p.y == 1 && p.x == 3) {
				x = p.x;
				y = p.y;
				real_x = p.x * Board.width;
				real_y = p.y * Board.height;
				ps.remove(p);
				isKilled = 0;

			}
			// Killing the RED animals in the traps, ranking doesn't matter anymore.
			else if (!isBlue && p.y == 8 && (p.x == 2 || p.x == 4)) {
				x = p.x;
				y = p.y;
				real_x = p.x * Board.width;
				real_y = p.y * Board.height;
				ps.remove(p);
				isKilled = 0;

			} else if (!isBlue && p.y == 7 && p.x == 3) {
				x = p.x;
				y = p.y;
				real_x = p.x * Board.width;
				real_y = p.y * Board.height;
				ps.remove(p);
				isKilled = 0;

			} else {
				isKilled = 1;
				SecondFrame.str += "You can NOT kill powerful animals\n";
				SecondFrame.records.setText(SecondFrame.str);

			}
		}

	}

	public void move(int px, int py) {

		// RED Player
		if (player == 1) {
			if (Main.redCount == 0) {
				// MOVE
				if (Board.findPiece(px * Board.width, py * Board.height) == null) { // The destination is empty.

					// The lake coordination
					if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank == 0) { // Piece is a
																											// RAT.
						// RAT get into the lake.
						x = px;
						y = py;
						real_x = px * Board.width;
						real_y = py * Board.height;
						// MOVED
						SecondFrame.str += "YELLOW moves " + rname + ": (" + px + " , " + py + ")\n";
						SecondFrame.records.setText(SecondFrame.str);
						// --- TURN ----
						Main.redCount++;
						Main.blueCount = 0;
					} else if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank != 0) { // Piece
																												// is
																												// not
																												// a
																												// RAT.
						SecondFrame.str += "You are NOT a RAT\n";
						SecondFrame.records.setText(SecondFrame.str);

						// The code for the board of the game.
					} else if ((px != 1 && px != 2 && px != 4 && px != 5) || py < 3 || py > 5) {
						if (isBlue && px == 3 && py == 0) {
							SecondFrame.str += "You can NOT enter your sanctuary\n";
							SecondFrame.records.setText(SecondFrame.str);
						} else if (!isBlue && px == 3 && py == 8) {
							SecondFrame.str += "You can NOT enter your sanctuary\n";
							SecondFrame.records.setText(SecondFrame.str);
						} else {
							// Regular move.
							x = px;
							y = py;
							real_x = px * Board.width;
							real_y = py * Board.height;

							// MOVED
							SecondFrame.str += "YELLOW moves " + rname + ": (" + px + " , " + py + ")\n";
							SecondFrame.records.setText(SecondFrame.str);
							// --- TURN ----
							Main.redCount++;
							Main.blueCount = 0;
						}
					}

				} else { // The destination is NOT empty.
					if (player != Board.findPiece(px * Board.width, py * Board.height).player) { // Checking that there
																									// is an enemy.

						// The lake coordination
						if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank == 0) { // Piece is
																												// a
																												// RAT.
							// RAT get into the lake.
							kill(Board.findPiece(px * Board.width, py * Board.height));
							if (isKilled == 0) {
								// MOVED
								SecondFrame.str += "YELLOW moves " + rname + ": (" + px + " , " + py + ")\n";
								SecondFrame.records.setText(SecondFrame.str);
								// --- TURN ----
								Main.redCount++;
								Main.blueCount = 0;
							}

						} else if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank != 0) { // Piece
																													// is
																													// not
																													// a
																													// RAT.
							SecondFrame.str += "You con not kill the RAT in the lake\n";
							SecondFrame.records.setText(SecondFrame.str);
						} else if ((px != 1 && px != 2 && px != 4 && px != 5) || py < 3 || py > 5) {
							// Regular killing.
							kill(Board.findPiece(px * Board.width, py * Board.height));
							if (isKilled == 0) {
								// MOVED
								SecondFrame.str += "YELLOW moves " + rname + ": (" + px + " , " + py + ")\n";
								SecondFrame.records.setText(SecondFrame.str);
								// --- TURN ----
								Main.redCount++;
								Main.blueCount = 0;
							}

						}

					} else {

						SecondFrame.str += "You can not kill your ALLY\n";
						SecondFrame.records.setText(SecondFrame.str);

					}
				}

			} else {
				SecondFrame.str += "NOT your trun.\n";
				SecondFrame.records.setText(SecondFrame.str);
			}
		}

		// BLUE Player
		if (player == 2) {
			if (Main.blueCount == 0) {
				// MOVE
				if (Board.findPiece(px * Board.width, py * Board.height) == null) { // The destination is empty.

					// The lake coordination
					if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank == 0) { // Piece is a
																											// RAT.
						// RAT get into the lake.
						x = px;
						y = py;
						real_x = px * Board.width;
						real_y = py * Board.height;
						// MOVED
						SecondFrame.str += "BLUE moves " + rname + ": (" + px + " , " + py + ")\n";
						SecondFrame.records.setText(SecondFrame.str);
						// --- TURN ----
						Main.blueCount++;
						Main.redCount = 0;
					} else if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank != 0) { // Piece
																												// is
																												// not
																												// a
																												// RAT.
						SecondFrame.str += "You are NOT a RAT\n";
						SecondFrame.records.setText(SecondFrame.str);

						// The code for the board of the game.
					} else if ((px != 1 && px != 2 && px != 4 && px != 5) || py < 3 || py > 5) {
						if (isBlue && px == 3 && py == 0) {
							SecondFrame.str += "You can NOT enter your sanctuary\n";
							SecondFrame.records.setText(SecondFrame.str);
						} else if (!isBlue && px == 3 && py == 8) {
							SecondFrame.str += "You can NOT enter your sanctuary\n";
							SecondFrame.records.setText(SecondFrame.str);
						} else {
							// Regular move.
							x = px;
							y = py;
							real_x = px * Board.width;
							real_y = py * Board.height;
							// MOVED
							SecondFrame.str += "BLUE moves " + rname + ": (" + px + " , " + py + ")\n";
							SecondFrame.records.setText(SecondFrame.str);
							// --- TURN ----
							Main.blueCount++;
							Main.redCount = 0;
						}
					}

				} else { // The destination is NOT empty.
					if (player != Board.findPiece(px * Board.width, py * Board.height).player) { // Checking that there
																									// is an enemy.

						// The lake coordination
						if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank == 0) { // Piece is
																												// a
																												// RAT.
							// RAT get into the lake.
							kill(Board.findPiece(px * Board.width, py * Board.height));
							if (isKilled == 0) {
								// MOVED
								SecondFrame.str += "BLUE moves " + rname + ": (" + px + " , " + py + ")\n";
								SecondFrame.records.setText(SecondFrame.str);
								// --- TURN ----
								Main.blueCount++;
								Main.redCount = 0;
							}

						} else if ((px == 1 || px == 2 || px == 4 || px == 5) && py >= 3 && py <= 5 && rank != 0) { // Piece
																													// is
																													// not
																													// a
																													// RAT.
							SecondFrame.str += "You con not kill the RAT in the lake\n";
							SecondFrame.records.setText(SecondFrame.str);
						} else if ((px != 1 && px != 2 && px != 4 && px != 5) || py < 3 || py > 5) {
							// Regular killing.
							kill(Board.findPiece(px * Board.width, py * Board.height));
							if (isKilled == 0) {
								// MOVED
								SecondFrame.str += "BLUE moves " + rname + ": (" + px + " , " + py + ")\n";
								SecondFrame.records.setText(SecondFrame.str);
								// --- TURN ----
								Main.blueCount++;
								Main.redCount = 0;
							}

						}

					} else {

						SecondFrame.str += "You can not kill your ALLY\n";
						SecondFrame.records.setText(SecondFrame.str);

					}
				}
			} else {
				SecondFrame.str += "NOT your trun.\n";
				SecondFrame.records.setText(SecondFrame.str);
			}
		}

	}

	public static boolean isWin() {
		if (Main.selectedPiece.player == 1) {
			if (Board.countBlue() == 0 || (Main.selectedPiece.x == 3 && Main.selectedPiece.y == 0)) {
				return true;
			}
		}

		if (Main.selectedPiece.player == 2) {
			if (Board.countYellow() == 0 || (Main.selectedPiece.x == 3 && Main.selectedPiece.y == 8)) {
				return true;
			}
		}

		return false;

	}

	public static int evalution(ArrayList<Piece> board, int maxiColor) {
		int evalYellow = 0;
		int evalBlue = 0;
		for (Piece p : board) {
			if (p.player == 1) {
				evalYellow += p.rank;
			}
			if (p.player == 2) {
				evalBlue += p.rank;
			}
		}
		if (maxiColor == 2) {
			return evalBlue - evalYellow;
		} else {
			return evalYellow - evalBlue;
		}

	}

	public ArrayList<Move> getAllPossibleMoves(ArrayList<Piece> board) {
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		if (player == 1) { // yellow
			for (Piece p : board) {
				if (p.player == 2) {

					// RIGHT move
					if (p.x != 6) {
						// sanctuary
						if (p.y == 0 && p.x + 1 == 3) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} // Lake
						else if (p.x == 0 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else if (p.x == 3 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece((p.x + 1) * Board.width, p.y * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x + 1, p.y, p.name));
							}
							
						}
					}
					// LEFT move
					if (p.x != 0) {
						// sanctuary
						if (p.y == 0 && p.x - 1 == 3) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} // Lake
						else if (p.x == 6 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else if (p.x == 3 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece((p.x - 1) * Board.width, p.y * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x - 1, p.y, p.name));
							}
						}
					}
					// UP move
					if (p.y != 0) {
						// sanctuary
						if (p.y - 1 == 0 && p.x == 3) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} // Lake
						else if (p.y == 6 && (p.x == 1 || p.x == 2 || p.x == 4 || p.x == 5)) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece(p.x * Board.width, (p.y - 1) * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x, p.y - 1, p.name));
							}
							
						}
					}
					// DOWN move
					if (p.y != 8) {
						// Lake
						if (p.y == 2 && (p.x == 1 || p.x == 2 || p.x == 4 || p.x == 5)) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece(p.x * Board.width, (p.y + 1) * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x, p.y + 1, p.name));
							}
						}
					}

				}
			}
			return possibleMoves;
		}

		if (player == 2) { // blue
			for (Piece p : board) {
				if (p.player == 1) {

					// RIGHT move
					if (p.x != 6) {
						// sanctuary
						if (p.y == 8 && p.x + 1 == 3) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} // Lake
						else if (p.x == 0 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else if (p.x == 3 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece((p.x + 1) * Board.width, p.y * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x + 1, p.y, p.name));
							}
						}
					}
					// LEFT move
					if (p.x != 0) {
						// sanctuary
						if (p.y == 8 && p.x - 1 == 3) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} // Lake
						else if (p.x == 6 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else if (p.x == 3 && p.y >= 3 && p.y <= 5) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece((p.x - 1) * Board.width, p.y * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x - 1, p.y, p.name));
							}
						}
					}
					// UP move
					if (p.y != 0) {
						// Lake
						if (p.y == 6 && (p.x == 1 || p.x == 2 || p.x == 4 || p.x == 5)) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece(p.x * Board.width, (p.y - 1) * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x, p.y - 1, p.name));
							}
						}
					}
					// DOWN move
					if (p.y != 8) {
						// sanctuary
						if (p.y + 1 == 8 && p.x == 3) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						}
						// Lake
						else if (p.y == 2 && (p.x == 1 || p.x == 2 || p.x == 4 || p.x == 5)) {
							possibleMoves.remove(new Move(p.x, p.y, p.name));
						} else {
							Piece piece = Board.findPiece(p.x * Board.width, (p.y + 1) * Board.height);
							if(piece == null || piece.player != p.player ) {
								possibleMoves.add(new Move(p.x, p.y + 1, p.name));
							}
						}
					}

				}
			}
			return possibleMoves;
		}
		return null;

	}

	public String toString() {
		return "(" + test_x + "," + test_y + ") " + name + "\n";
	}
}
