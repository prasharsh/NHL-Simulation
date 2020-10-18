package com.dal.dhl.stateMachine;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import g4db.GameDB;
import g4db.IGameDB;
import g4dhl.Game;;

public class Main {


	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException, ParseException {
		String filePath = null;
		try {
			filePath = args[0];
		} catch (ArrayIndexOutOfBoundsException ae) {
			// do nothing, state machine will take care
		}
		
		
		  DHLStateMachine stateMachine = new DHLStateMachine(filePath);
		  stateMachine.start();
		
	}
}
