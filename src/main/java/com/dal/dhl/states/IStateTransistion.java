package com.dal.dhl.states;

public interface IStateTransistion {

	void entry();
	void doTask();
	void exit();
}
