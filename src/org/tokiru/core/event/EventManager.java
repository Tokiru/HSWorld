package org.tokiru.core.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class EventManager {

    private List<Entry> subscribers;

    public EventManager() {
        subscribers = new ArrayList<>();
    }

    public void subscribe(Subscriber subscriber, Event.EventType eventType) {
        subscribers.add(new Entry(subscriber, eventType));
    }

    public void unsubscribe(Subscriber subscriber, Event.EventType eventType) {
        subscribers.remove(new Entry(subscriber, eventType));
    }

    public void unsubscribe(Subscriber subscriber) {
        List<Entry> entriesToRemove = new ArrayList<>();
        for (Entry subscriber1 : subscribers) {
            if (subscriber1.subscriber == subscriber) {
                entriesToRemove.add(subscriber1);
            }
        }

        subscribers.removeAll(entriesToRemove);
    }

    public void send(Event event) {
        List<Entry> subscribersCopy = new ArrayList<>(subscribers);
        for (Entry subscriber : subscribersCopy) {
            if (subscriber.eventType == event.getType()) {
                subscriber.subscriber.accept(event);
            }
        }
    }

    private class Entry {
        public Subscriber subscriber;
        public Event.EventType eventType;

        public Entry(Subscriber subscriber, Event.EventType eventType) {
            this.subscriber = subscriber;
            this.eventType = eventType;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }

            Entry entry = (Entry) obj;
            return entry.eventType == this.eventType && entry.subscriber == this.subscriber;
        }
    }
}
