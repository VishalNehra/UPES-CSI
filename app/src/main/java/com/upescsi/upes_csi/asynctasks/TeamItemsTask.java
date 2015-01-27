package com.upescsi.upes_csi.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.adapters.TeamAdapter;
import com.upescsi.upes_csi.database.Event;
import com.upescsi.upes_csi.database.TeamHandler;
import com.upescsi.upes_csi.fragments.TeamFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class TeamItemsTask extends AsyncTask<Void, Void, Void> {
    private static final String URL = "http://upescsi.in/team.html";
    private ArrayList<String> eventTitleItems, eventSummaryItems;
    private TeamFragment teamFragment;
    private Document document;
    private TeamHandler teamHandler;
    private Event event;

    public TeamItemsTask (TeamFragment teamFragment) {
        this.teamFragment = teamFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(teamFragment.getActivity(), "Fetching...", Toast.LENGTH_LONG).show();
        teamHandler = new TeamHandler(teamFragment.getActivity(), null, null, 1);
        if (teamHandler.getAllEvents().size()!=0) {
            teamHandler.clear();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            eventTitleItems = new ArrayList<String>();
            eventSummaryItems = new ArrayList<String>();

            document = Jsoup.connect(URL).get();
            // Using elements to get class data
            Elements headers = document.select("h4[class=m_bottom_5]");
            Elements post = document.select("i[class=color_grey fs_medium d_inline_b m_bottom_5]");

            for (Element e : headers) {
                eventTitleItems.add(e.text());
            }
            for (Element e : post) {

                    eventSummaryItems.add(e.text());
            }

            event = new Event();
            for (int i = 0 ; i<eventTitleItems.size(); i++) {
                event.setEventNo(i);
                event.setEventTitle(eventTitleItems.get(i));
                event.setEventSummary(eventSummaryItems.get(i));
                teamHandler.addEvent(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        teamFragment.teamAdapter = new TeamAdapter(teamFragment.getActivity(), R.layout.team_layout, eventTitleItems, eventSummaryItems);
        teamFragment.listView.setAdapter(teamFragment.teamAdapter);
    }
}
