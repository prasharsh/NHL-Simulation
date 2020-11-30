package com.statemachine;

import org.apache.log4j.Logger;

public interface IState {

	Logger logger = Logger.getLogger(IState.class);

	default void entry() {
		logger.info("abstract default implementation for the state called");
	}

	IState doTask();


}
