package com.statemachine;

import org.apache.log4j.Logger;

public interface IState {

	final static Logger logger = Logger.getLogger(IState.class);

	
	 default public void entry() {
		 logger.info("abstract default implementation for the state called");
	 };

	IState doTask();

	
}
