package com.statemachine;

public interface IState {

	 default public void entry() {
		 System.out.println("no implemenation of the state");
	 };

	IState doTask();

	
}
