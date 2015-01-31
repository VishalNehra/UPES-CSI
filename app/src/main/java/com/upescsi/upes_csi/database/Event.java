package com.upescsi.upes_csi.database;

/**
 * Created by Vishal on 20-01-2015.
 */
public class Event {

    private int _id, _event_no, _event_imgWidth;
    private String _event_title;

    public Event() {

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
}
