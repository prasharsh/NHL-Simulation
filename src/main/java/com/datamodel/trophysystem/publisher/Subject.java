package com.datamodel.trophysystem.publisher;

import com.datamodel.trophysystem.subscriber.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Subject {

    private ArrayList<Observer> subscribers;
    protected Map<String, Object> subjectMap;

    public Subject() {
        this.subscribers = new ArrayList<>();
        this.subjectMap = new HashMap<>();
    }

    public void attach(Observer subscriber) {
        this.subscribers.add(subscriber);
    }

    public void detach(Observer subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void notifyObservers() {
        for (Observer observer : subscribers) {
            observer.update(this);
        }
        subjectMap.clear();
    }

    public Object getValue(String key) {
        return subjectMap.get(key);
    }
}
