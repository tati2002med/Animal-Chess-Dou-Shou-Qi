package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class SecondFrame extends JFrame {

	public static String str = "";
	public static JTextPane records = new JTextPane();

	public SecondFrame() {

		setTitle("Board of Dou Shou Qi");
		setSize(370, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icon.png")));

		setBackground(Color.decode("#64B5F6"));

		// Make the game appears in the middle of the screen
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 + 504 / 2, size.height / 2 - getHeight() / 2);

		str += "-------------------------------------\n";
		str += "   J U N G L E  G A M E  B O A R D\n";
		str += "-------------------------------------\n";
		str += "-------------------------\n";
		str += "Score : YELLOW    " + Main.redWin + "\n";
		str += "        BLUE      " + Main.blueWin + "\n";
		str += "-------------------------\n";

		Font font = new Font("courier", Font.BOLD, 16);

		records.setBackground(Color.decode("#212121"));
		records.setForeground(Color.WHITE);
		records.setAlignmentY(700);
		records.setAlignmentX(300);
		records.setBounds(20, 90, 320, 700);
		records.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		records.setFont(font);

		records.setText(str);

		records.setEditable(false);

		JScrollPane sp = new JScrollPane(records, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(sp);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

}
