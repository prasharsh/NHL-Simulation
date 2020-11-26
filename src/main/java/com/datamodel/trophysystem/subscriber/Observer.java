package com.datamodel.trophysystem.subscriber;

import com.datamodel.trophysystem.publisher.Subject;

public abstract class Observer {
    public abstract void update(Subject subject);
}
