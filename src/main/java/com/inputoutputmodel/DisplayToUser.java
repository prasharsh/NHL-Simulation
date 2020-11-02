package com.inputoutputmodel;

public class DisplayToUser implements IDisplayToUser {

	@Override
	public void displayMsgToUser(String displayMsg) {
		System.out.println(displayMsg);
	}
}