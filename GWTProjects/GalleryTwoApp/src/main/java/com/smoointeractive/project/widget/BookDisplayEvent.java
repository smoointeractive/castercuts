package com.smoointeractive.project.widget;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sachamoo on 4/29/17.
 */
public class BookDisplayEvent {
    private ArrayList<BookDisplayEventListener> listeners = new ArrayList<>();

    public BookDisplayEvent()
    {

    }

    public void addEventListener(BookDisplayEventListener listener)
    {
        this.listeners.add(listener);
    }
    public void removeEventListener(BookDisplayEventListener listener)
    {
        this.listeners.remove(listener);
    }

    public void previousButtonStateChanged()
    {
        notifyListeners("previousDisabled");
    }
    public void nextButtonStateChanged()
    {
        notifyListeners("nextDisabled");
    }
    public void enableButtonsStateChanged()
    {
        notifyListeners("enableAllButtons");
    }


    private void notifyListeners(String message)
    {
        Iterator listeners = this.listeners.iterator();
        while(listeners.hasNext()) {
            ((BookDisplayEventListener)listeners.next()).NotifyChanges(message);
        }
    }
}
