package Chapter_Three;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Deposite implements ActionListener {
	private JButton savings1, chequing1;
	public void actionPerformed(ActionEvent evt) {

		JFrame deposite = new JFrame();
		deposite.setVisible(true);
		deposite.setSize(400, 100);
		deposite.setLayout(new GridLayout(2, 2));
		deposite.setTitle("Deposite");
		deposite.setBackground(Color.magenta);
		
		savings1 = new JButton("Savings");
		chequing1 = new JButton("Chequing");
		deposite.add(savings1);
		deposite.add(chequing1);
		
		Savings3 s3 = new Savings3();
		savings1.addActionListener(s3);
		
		Chequing3 c3 = new Chequing3();
		chequing1.addActionListener(c3);
	}

}
