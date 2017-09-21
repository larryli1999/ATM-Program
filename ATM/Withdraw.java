package Chapter_Three;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Withdraw implements ActionListener {
	private JButton savings1, chequing1;

	public void actionPerformed(ActionEvent evt) {

		JFrame withdraw = new JFrame();
		withdraw.setVisible(true);
		withdraw.setLayout(new GridLayout(2, 2));
		withdraw.setSize(400, 100);
		withdraw.setTitle("Withdraw");
		withdraw.setBackground(Color.magenta);
		
		savings1 = new JButton("Savings");
		chequing1 = new JButton("Chequing");
		withdraw.add(savings1);
		withdraw.add(chequing1);
		
		Savings2 s2 = new Savings2();
		savings1.addActionListener(s2);
		
		Chequing2 c2 = new Chequing2();
		chequing1.addActionListener(c2);
	}

}
