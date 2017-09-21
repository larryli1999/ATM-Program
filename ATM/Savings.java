package Chapter_Three;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Savings implements ActionListener {
	private JLabel label1;
	double balance1 = 0;
	String balance2;
	ArrayList<String> withdraw = new ArrayList<String>();
	ArrayList<String> deposite = new ArrayList<String>();

	public void actionPerformed(ActionEvent evt) {

		try {
			Scanner s3 = new Scanner(new File("savings1.txt"));
			while (s3.hasNextLine()) {
				withdraw.add(s3.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Scanner s4 = new Scanner(new File("savings2.txt"));
			while (s4.hasNextLine()) {
				deposite.add(s4.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[][] list = new String[withdraw.size() + deposite.size()][3];
		Double[] numlist = new Double[withdraw.size() + deposite.size()];

		for (int i = 0; i < withdraw.size(); i++) {
			Scanner s5 = new Scanner(withdraw.get(i)).useDelimiter("\\s*  \\s*");
			String num = s5.next();
			String info = s5.next();
			String date = s5.next();
			list[i][0] = num;
			list[i][1] = info;
			list[i][2] = date;
		}

		for (int i = 0; i < deposite.size(); i++) {
			Scanner s6 = new Scanner(deposite.get(i)).useDelimiter("\\s*  \\s*");
			String num = s6.next();
			String info = s6.next();
			String date = s6.next();
			list[i + withdraw.size()][0] = num;
			list[i + withdraw.size()][1] = info;
			list[i + withdraw.size()][2] = date;
		}

		for (int i = 0; i < withdraw.size(); i++) {
			numlist[i] = -Double.parseDouble(list[i][0]);
		}

		for (int i = 0; i < deposite.size(); i++) {
			numlist[i + withdraw.size()] = Double.parseDouble(list[i + withdraw.size()][0]);
		}

		for (int i = 0; i < numlist.length; i++) {
			balance1 = balance1 + numlist[i];
		}

		if (withdraw.size() > 3) {
			balance1 = balance1 - (withdraw.size() - 3);
		}
		withdraw.removeAll(withdraw);
		deposite.removeAll(deposite);
		String[] columnNames = { "Amount", "Transaction Type", "Time" };

		JTable table = new JTable(list, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);

		JFrame savings = new JFrame();
		savings.setVisible(true);
		savings.setSize(400, 400);
		savings.setLayout(new BorderLayout());
		savings.setTitle("Savings Account");
		balance2 = String.valueOf(balance1);
		balance1 = 0;
		savings.setBackground(Color.magenta);
		label1 = new JLabel("Your account balance is: $" + balance2);
		savings.add(label1, BorderLayout.PAGE_START);
		savings.add(scrollPane);
		savings.add(table, BorderLayout.CENTER);
	}

}
