package com.upescsi.upes_csi.database;

/**
 * Created by Vishal on 20-01-2015.
 */
public class Event {

    private int _id, _event_no;
    private String _event_title, _event_summary;

    public Event() {

    }

    public Event(int event_no, String event_title, String event_summary) {
        this._event_no = event_no;
        this._event_title = event_title;
        this._event_summary = event_summary;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setEventNo(int eventNo) {
        this._event_no = eventNo;
    }

    public int getEventNo() {
        return this._event_no;
    }

    public void setEventTitle(String eventTitle) {
        this._event_title = eventTitle;
    }

    public String getEventTitle() {
        return this._event_title;
    }

    public void setEventSummary(String eventSummary) {
        this._event_summary = eventSummary;
    }

    public String getEventSummary() {
        return this._event_summary;
    }
}
