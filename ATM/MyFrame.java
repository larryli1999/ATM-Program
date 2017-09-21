package Chapter_Three;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;

	public static void main(String[] args) {

		JButton withdrawButton, depositeButton, savingButton, chequeButton;
		JFrame MyFrame = new JFrame();

		MyFrame.setVisible(true);
		MyFrame.setTitle("Finace Manager");
		MyFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyFrame.setBackground(Color.magenta);
		MyFrame.setLayout(new GridLayout(2, 2));

		withdrawButton = new JButton("Withdraw");
		MyFrame.add(withdrawButton);

		depositeButton = new JButton("Deposite");
		MyFrame.add(depositeButton);

		savingButton = new JButton("Savings");
		MyFrame.add(savingButton);

		chequeButton = new JButton("Chequing");
		MyFrame.add(chequeButton);

		Savings saving = new Savings();
		savingButton.addActionListener(saving);

		Chequing cheque = new Chequing();
		chequeButton.addActionListener(cheque);

		Deposite deposite = new Deposite();
		depositeButton.addActionListener(deposite);

		Withdraw withdraw = new Withdraw();
		withdrawButton.addActionListener(withdraw);
	}

}
