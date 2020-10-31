package com.statemachine;

public interface IState {

    void entry();

    IState doTask();

    void exit();
}
