package com.upescsi.upes_csi.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.adapters.EventsAdapter;
import com.upescsi.upes_csi.fragments.EventsFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Vishal on 20-01-2015.
 */
public class EventItemsTask extends AsyncTask<Void, Void, Void> {
    private static final String URL = "http://upescsi.in/events/index.html";
    private ArrayList<String> eventTitleItems, eventSummaryItems;
    private EventsFragment eventsFragment;
    private Document document;

    public EventItemsTask (EventsFragment eventsFragment) {
        this.eventsFragment = eventsFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(eventsFragment.getActivity(), "Fetching...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            eventTitleItems = new ArrayList<String>();
            eventSummaryItems = new ArrayList<String>();
            
            document = Jsoup.connect(URL).get();
            // Using elements to get class data
            Elements headers = document.select("h3");
            headers.remove(0);
            Elements paragraph = document.select("p");
            for (Element e : headers) {
                eventTitleItems.add(e.text());
            }
            for (Element e : paragraph) {
                eventSummaryItems.add(e.text());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        eventsFragment.eventsAdapter = new EventsAdapter(eventsFragment.getActivity(), R.layout.events_row, eventTitleItems, eventSummaryItems);
        eventsFragment.listView.setAdapter(eventsFragment.eventsAdapter);
    }
}
