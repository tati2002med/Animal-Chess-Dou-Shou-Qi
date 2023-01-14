package app;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Setup {

	public Setup() {

		Main setup = new Main();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"------------------------------------- Wolcome To Dou Shou Qi ---------------------------------------\n");
		sb.append(
				"------------------------------------------- Rules of the Game --------------------------------------------\n");
		sb.append("I - You can move only one box up, down, left or right\n");
		sb.append("II - Diagonal move not allowed\n");
		sb.append("III - The rat is the only animal who can swim in the lake\n");
		sb.append("IV - In order of winning the game you should get into the sanctuairy\n");
		sb.append("V - Pieces hierarchy : (from the powerful piece to the weakest)\n");
		sb.append("         - E -> Elephant (The powerful)\n");
		sb.append("         - L -> Lion\n");
		sb.append("         - T -> Tiger\n");
		sb.append("         - P -> Panther\n");
		sb.append("         - D -> Dog\n");
		sb.append("         - W -> Wolf\n");
		sb.append("         - C -> Cat\n");
		sb.append("         - R -> Rat (The weakest)\n");
		sb.append("VI - Each animal can eat the same other animal (Exemple : Wolf kills Wolf)\n");
		sb.append("VII - Exception : (The rat is the only animal who can kill the elephant)\n");
		sb.append("VIII - You can not enter your sanctuairy\n");
		sb.append("IX - The tiger & lion can jump over the lake but not if there is a rat in the way\n");
		sb.append("X - If you are in a trap any animal can kill you.\n");
		sb.append(
				"--------------------------------------------------- Good Luck ----------------------------------------------------\n");
		String msg = sb.toString();
		String msg1 = "- You can move the piece by draging it.";

		ImageIcon oldIcon = new ImageIcon(getClass().getResource("Icon.png"));
		Image oldImage = oldIcon.getImage();
		Image newImage1 = oldImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		Image newImage2 = oldImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newIcon1 = new ImageIcon(newImage1);
		ImageIcon newIcon2 = new ImageIcon(newImage2);

		UIManager.put("OptionPane.background", Color.decode("#4FC3F7"));
		UIManager.put("Panel.background", Color.decode("#4FC3F7"));

		JOptionPane.showMessageDialog(setup, msg.toUpperCase(), "Welcome User!", JOptionPane.INFORMATION_MESSAGE,
				newIcon1);
		JOptionPane.showMessageDialog(setup, msg1.toUpperCase(), "How to Move", JOptionPane.INFORMATION_MESSAGE,
				newIcon2);

	}

	public static void main(String[] args) {

		new Setup();

	}

}
