import java.util.*;
import javax.swing.*;
import java.text.*;
import java.io.*;

public class ATM {

	public ATM() {
	}

	public String getDate() {
		TimeZone myTZ = TimeZone.getTimeZone("EST");
		Calendar myCalendar = Calendar.getInstance(myTZ);
		SimpleDateFormat simpleDF = new SimpleDateFormat("yyyy/MM/dd/");
		Date currentDate = new Date();
		String dateOutput = simpleDF.format(currentDate).toString();
		return dateOutput;
	}

	public boolean freeWithdrawal(String fileName, int attempts) throws IOException {
		Scanner input = new Scanner(new File(fileName));
		input.useDelimiter("/");

		int validCounter = 0;
		int c = 0;
		int arr_len = elementCount(fileName);
		if (arr_len < attempts)
			return true;

		if (arr_len <= 0)
			arr_len++;

		String[] chkDate = new String[arr_len];
		boolean caseMonitor = true;

		if (chkDate.length <= 2)
			return true;

		while (input.hasNext()) {

			validCounter++;

			if (validCounter % 2 == 0 && validCounter % 4 > 0) {

				chkDate[c] = input.next();
				c++;
			} else {
				input.next();
			}

		}

		for (int i = 0; i < attempts; i++) {
			if (Integer.parseInt(chkDate[i]) == Integer.parseInt(chkDate[chkDate.length - 1])) {
				caseMonitor = false;
			}
		}

		return caseMonitor;
	}

	public void initializeAccount(String user) {
		String newMoney = JOptionPane.showInputDialog(null,
				"Our records indicate you do not have any money in your account.\nPlease enter the amount you have in dollars.");

		if (newMoney == null) {
			Object[] options = { "Okay", "Abort" };

			int x = JOptionPane.showOptionDialog(null,
					"To initialize your account, you must enter a dollar value. Do you wish to proceed?", "__",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

			switch (x) {
			case 0:
				initializeAccount(user);
			case 1:
				JOptionPane.showMessageDialog(null, "Exiting from program.");
				System.exit(0);
			}
		}

		for (int i = 0; i < newMoney.length(); i++) {
			if (newMoney.charAt(i) == '$') {
				newMoney = newMoney.replace(newMoney.charAt(i), ' ');
			}
		}

		writeTransaction(user, newMoney.trim(), true);
	}

	public void writeTransaction(String fileName, String amount, boolean callFromTransaction) {
		int amountOfElements = 0;
		DecimalFormat df = new DecimalFormat("0.00");

		try {
			amount = df.format(Double.parseDouble(amount));
		}

		catch (IllegalArgumentException iae) {
			amount = amount.concat(".00");
			amount = df.format(Double.parseDouble(amount));
		}

		try {
			FileWriter fw = new FileWriter(fileName, true);
			Scanner reader = new Scanner(new File(fileName));

			while (reader.hasNext()) {
				reader.next();
				amountOfElements++;
			}

			amountOfElements += 1;

			if (!callFromTransaction && elementCount(fileName) != 0) {
				fw.write("\n" + getDate().concat(amount.concat("/")));
				fw.close();
			}

			if (callFromTransaction && elementCount(fileName) != 0) {
				fw.write(getDate().concat(amount.concat("/")));
				fw.close();
			}

			if (elementCount(fileName) == 0 && fileName.contains("_balance.txt")) {
				fw.write("-2017/-01/-01/".concat(amount.concat("/")));
				JOptionPane.showMessageDialog(null,
						"Thank you. Please relaunch the program. Your account will be set up.");
				fw.close();
				System.exit(0);
			}

			if (fileName.contains("_transactions.txt") || fileName.contains("_cheques.txt")) {
				fw.write(getDate().concat(amount.concat("/")));
				fw.close();
			}

		}

		catch (IOException ioproblem) {
		}

	}

	private int elementCount(String fileName) throws IOException {

		int finalNum = 0;
		Scanner input = new Scanner(new File(fileName));
		if (!input.hasNext())
			return 0;

		while (input.hasNext()) {
			finalNum++;

			input.next();
		}
		return finalNum;
	}
}