package com.upescsi.upes_csi.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.adapters.EventsAdapter;
import com.upescsi.upes_csi.database.Event;
import com.upescsi.upes_csi.database.EventHandler;
import com.upescsi.upes_csi.fragments.EventsFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal on 20-01-2015.
 */
public class EventItemsTask extends AsyncTask<Void, Void, Void> {
    private static final String URL_EVENTS = "http://upescsi.in/event.html";
    private static final String URL = "http://upescsi.in";
    private ArrayList<String> eventTitleItems, eventSummaryItems, eventImageURLs;
    private EventsFragment eventsFragment;
    private Document document;
    private EventHandler eventHandler;
    private Event event;

    public EventItemsTask (EventsFragment eventsFragment) {
        this.eventsFragment = eventsFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(eventsFragment.getActivity(), "Fetching...", Toast.LENGTH_LONG).show();
        eventHandler = new EventHandler(eventsFragment.getActivity(), null, null, 1);
        if (eventHandler.getAllEvents().size()!=0) {
            eventHandler.clear();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            eventTitleItems = new ArrayList<String>();
            eventSummaryItems = new ArrayList<String>();
            eventImageURLs = new ArrayList<String>();
            
            document = Jsoup.connect(URL_EVENTS).get();
            // Using elements to get class data
            Elements headers = document.select("h4[class=lh_inherit m_md_bottom_5 d_sm_none d_xs_block]");
            Elements imgTags = document.select("img[src$=.jpg]");
            Element pngTag = document.select("img[src$=.png").get(1);
            imgTags.add(pngTag);
            for (Element e : headers) {
                eventTitleItems.add(e.text());
            }
            for (Element e : imgTags) {
                eventSummaryItems.add(URL + "/" + e.attr("src"));
                eventImageURLs.add(URL + "/" + e.attr("src"));
            }

            event = new Event();
            for (int i = 0 ; i<eventTitleItems.size(); i++) {
                event.setEventNo(i);
                event.setEventTitle(eventTitleItems.get(i));
                event.setEventSummary(eventSummaryItems.get(i));
                eventHandler.addEvent(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        eventsFragment.eventsAdapter = new EventsAdapter(eventsFragment.getActivity(),
                R.layout.events_row, eventTitleItems, eventSummaryItems, eventImageURLs);
        eventsFragment.gridView.setAdapter(eventsFragment.eventsAdapter);
    }
}
