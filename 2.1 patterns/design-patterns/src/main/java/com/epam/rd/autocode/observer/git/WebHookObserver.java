package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class WebHookObserver implements WebHook {
    private final Event.Type type;
    private final String branch;
    private final List<Event> caughtEvents = new ArrayList<>();

    public WebHookObserver(Event.Type type, String branch) {
        this.type = type;
        this.branch = branch;
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return caughtEvents;
    }

    @Override
    public void onEvent(Event event) {
        if (event.type() == type && event.branch().equalsIgnoreCase(branch)) {
            caughtEvents.add(event);
        }
    }
}
