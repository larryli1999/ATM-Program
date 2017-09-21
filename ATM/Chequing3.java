package Chapter_Three;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Chequing3 implements ActionListener {

	String amount2;
	String mydate;
	ArrayList<String> amount4 = new ArrayList<String>();
	ArrayList<String> date = new ArrayList<String>();
	double amount;
	int x = 0;

	public void actionPerformed(ActionEvent e) {
		try {
			do {
				try {
					amount2 = JOptionPane.showInputDialog(null, "Enter the amount to deposite: $");
					amount = Double.parseDouble(amount2);
					amount4.add(amount2);
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					java.util.Date today = Calendar.getInstance().getTime();
					mydate = df.format(today);
					date.add(mydate);

					if (amount <= 0) {
						throw new RuntimeException("");
					} else {
						x++;
					}

				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Please enter a valid amount.");

				}

			} while (x == 0);

			BufferedWriter dos = new BufferedWriter(new FileWriter("chequing2.txt",true));
			for (int i = 0; i < amount4.size(); i++) {

				dos.write(amount4.get(i) + "  " + "Deposite" + "  " + date.get(i));
				dos.newLine();
			}
			dos.close();
		} catch (IOException e1) {
			System.out.println("File not found.");
		}
	}

}
