package Chapter_Three;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Chequing implements ActionListener {
	private JLabel label1;
	double balance1 = 0;
	String balance2;
	ArrayList<String> withdraw = new ArrayList<String>();
	ArrayList<String> deposite = new ArrayList<String>();

	public void actionPerformed(ActionEvent evt) {

		try {
			Scanner s3 = new Scanner(new File("chequing1.txt"));
			while (s3.hasNextLine()) {
				withdraw.add(s3.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Scanner s4 = new Scanner(new File("chequing2.txt"));
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

		if (withdraw.size() <= 10) {
			balance1 = balance1 - 0.5 * withdraw.size();
		}

		else {
			balance1 = balance1 - 5;
		}
		withdraw.removeAll(withdraw);
		deposite.removeAll(deposite);

		String[] columnNames = { "Amount", "Transaction Type", "Time" };

		JTable table = new JTable(list, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);

		JFrame chequing = new JFrame();
		chequing.setVisible(true);
		chequing.setSize(400, 400);
		chequing.setLayout(new BorderLayout());
		chequing.setTitle("Savings Account");
		balance2 = String.valueOf(balance1);
		chequing.setBackground(Color.magenta);
		label1 = new JLabel("Your account balance is: $" + balance2);
		chequing.add(label1, BorderLayout.PAGE_START);
		balance1 = 0;
		chequing.add(scrollPane);
		chequing.add(table, BorderLayout.CENTER);
	}

}
