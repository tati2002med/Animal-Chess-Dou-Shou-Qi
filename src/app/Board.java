package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	public static ArrayList<Piece> pieces = new ArrayList<Piece>();

	public final static int width = 70;
	public final static int height = 70;

	public static void restart() {

		pieces.clear();
		new Board() {

			// The Board
			@Override
			public void paint(Graphics g) {

				// red pieces
				new Piece(6, 8, 6, 8, "L", false, pieces, 6, 1, "Lion");
				new Piece(1, 7, 1, 7, "C", false, pieces, 1, 1, "Cat");
				new Piece(5, 7, 5, 7, "D", false, pieces, 3, 1, "Dog");
				new Piece(0, 6, 0, 6, "E", false, pieces, 7, 1, "Elephant");
				new Piece(4, 6, 4, 6, "P", false, pieces, 4, 1, "Panther");
				new Piece(0, 8, 0, 8, "T", false, pieces, 5, 1, "Tiger");
				new Piece(6, 6, 6, 6, "R", false, pieces, 0, 1, "Rat");
				new Piece(2, 6, 2, 6, "W", false, pieces, 2, 1, "Wolf");

				// Blue pieces
				new Piece(0, 0, 0, 0, "bL", true, pieces, 6, 2, "Lion");
				new Piece(5, 1, 5, 1, "bC", true, pieces, 1, 2, "Cat");
				new Piece(1, 1, 1, 1, "bD", true, pieces, 3, 2, "Dog");
				new Piece(6, 2, 6, 2, "bE", true, pieces, 7, 2, "Elephant");
				new Piece(2, 2, 2, 2, "bP", true, pieces, 4, 2, "Panther");
				new Piece(6, 0, 6, 0, "bT", true, pieces, 5, 2, "Tiger");
				new Piece(0, 2, 0, 2, "bR", true, pieces, 0, 2, "Rat");
				new Piece(4, 2, 4, 2, "bW", true, pieces, 2, 2, "Wolf");

				// Drawing the Board.
				for (int row = 0; row < 9; row++) {
					for (int col = 0; col < 7; col++) {
						// The ground
						g.setColor(Color.decode("#2E7D32"));
						g.fillRect(col * width, row * height, width, height);

						// Sanctuaries
						if (col == 3 && (row == 0 || row == 8)) {
							g.setColor(Color.decode("#FFFF00"));
							g.fillRect(col * width, row * height, width, height);
						}

						// Traps
						if (col == 2 || col == 4) {
							if (row == 0 || row == 8) {
								g.setColor(Color.decode("#212121"));
								g.fillRect(col * width, row * height, width, height);
							}
						}
						if (col == 3 && (row == 1 || row == 7)) {
							g.setColor(Color.decode("#212121"));
							g.fillRect(col * width, row * height, width, height);
						}

						// the Lake
						if (row >= 3 && row <= 5 && (col == 1 || col == 2 || col == 4 || col == 5)) {
							g.setColor(Color.decode("#651FFF"));
							g.fillRect(col * width, row * height, width, height);
						}

						// The lines
						g.setColor(Color.decode("#90CAF9"));
						g.drawRect(col * width, row * height, width, height);
					}
				}

				// Drawing the pieces
				for (Piece p : pieces) {
					try {
						File imgR = new File("res/" + p.name + ".png");
						Image img1 = ImageIO.read(imgR);
						g.drawImage(img1, width * p.x, height * p.y, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		};

	}

	// red pieces
	Piece lr = new Piece(6, 8, 6, 8, "L", false, pieces, 6, 1, "Lion");
	Piece cr = new Piece(1, 7, 1, 7, "C", false, pieces, 1, 1, "Cat");
	Piece dr = new Piece(5, 7, 5, 7, "D", false, pieces, 3, 1, "Dog");
	Piece er = new Piece(0, 6, 0, 6, "E", false, pieces, 7, 1, "Elephant");
	Piece pr = new Piece(4, 6, 4, 6, "P", false, pieces, 4, 1, "Panther");
	Piece tr = new Piece(0, 8, 0, 8, "T", false, pieces, 5, 1, "Tiger");
	Piece rr = new Piece(6, 6, 6, 6, "R", false, pieces, 0, 1, "Rat");
	Piece wr = new Piece(2, 6, 2, 6, "W", false, pieces, 2, 1, "Wolf");

	// Blue pieces
	Piece lb = new Piece(0, 0, 0, 0, "bL", true, pieces, 6, 2, "Lion");
	Piece cb = new Piece(5, 1, 5, 1, "bC", true, pieces, 1, 2, "Cat");
	Piece db = new Piece(1, 1, 1, 1, "bD", true, pieces, 3, 2, "Dog");
	Piece eb = new Piece(6, 2, 6, 2, "bE", true, pieces, 7, 2, "Elephant");
	Piece pb = new Piece(2, 2, 2, 2, "bP", true, pieces, 4, 2, "Panther");
	Piece tb = new Piece(6, 0, 6, 0, "bT", true, pieces, 5, 2, "Tiger");
	Piece rb = new Piece(0, 2, 0, 2, "bR", true, pieces, 0, 2, "Rat");
	Piece wb = new Piece(4, 2, 4, 2, "bW", true, pieces, 2, 2, "Wolf");

	// The Board
	@Override
	public void paint(Graphics g) {

		// Drawing the Board.
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 7; col++) {
				// The ground
				g.setColor(Color.decode("#2E7D32"));
				g.fillRect(col * width, row * height, width, height);

				// Sanctuaries
				if (col == 3 && (row == 0 || row == 8)) {
					g.setColor(Color.decode("#FFFF00"));
					g.fillRect(col * width, row * height, width, height);
				}

				// Traps
				if (col == 2 || col == 4) {
					if (row == 0 || row == 8) {
						g.setColor(Color.decode("#212121"));
						g.fillRect(col * width, row * height, width, height);
					}
				}
				if (col == 3 && (row == 1 || row == 7)) {
					g.setColor(Color.decode("#212121"));
					g.fillRect(col * width, row * height, width, height);
				}

				// the Lake
				if (row >= 3 && row <= 5 && (col == 1 || col == 2 || col == 4 || col == 5)) {
					g.setColor(Color.decode("#651FFF"));
					g.fillRect(col * width, row * height, width, height);
				}

				// The lines
				g.setColor(Color.decode("#90CAF9"));
				g.drawRect(col * width, row * height, width, height);
			}
		}

		// Drawing the pieces
		for (Piece p : pieces) {
			try {
				File imgR = new File("res/" + p.name + ".png");
				Image img1 = ImageIO.read(imgR);
				g.drawImage(img1, width * p.x, height * p.y, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Piece findPiece(int xp, int yp) {
		for (Piece p : pieces) {
			if (p.x == xp / width && p.y == yp / height) {
				return p;
			}
		}
		return null;
	}

	public static Piece getPiece(String name) {
		for (Piece p : pieces) {
			if (p.name == name) {
				return p;
			}
		}
		return null;
	}

	public static int countBlue() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.player == 2) {
				count++;
			}
		}
		return count;
	}

	public static int countYellow() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.player == 1) {
				count++;
			}
		}
		return count;
	}

	public static ArrayList<Piece> copyBoard() {
		return pieces;
	}

}
