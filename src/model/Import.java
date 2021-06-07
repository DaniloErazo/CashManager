package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Import extends Thread {

	String filename;

	public Import(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			line = bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			String[] parts = line.split(",");
			new CashManager().addMovement(new Movement(parts[0], Double.parseDouble(parts[1]), parts[2], parts[3],
					MovementType.values()[Integer.parseInt(parts[4])], parts[5]));
			try {
				line = bReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
