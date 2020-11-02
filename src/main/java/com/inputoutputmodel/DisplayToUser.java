package com.inputoutputmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DisplayToUser implements IDisplayToUser {

	@Override
	public void displayMsgToUser(String displayMsg) {
		System.out.println(displayMsg);
	}

	@Override
	public int takeNumberInputFromUser() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int id = 0;
		try {
			while (true) {
				String s = br.readLine();
				if (s.matches("\\d+")) {
					id = Integer.parseInt(s);
					break;
				} else {
					System.out.println("Invalid format! Please enter again");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}
}