package com.dal.dhl.states;

public interface IState {

	void entry();
	IState doTask();
	void exit();
}
