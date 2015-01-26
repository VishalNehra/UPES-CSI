package com.upescsi.upes_csi.asynctasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.adapters.EventsAdapter;
import com.upescsi.upes_csi.database.Event;
import com.upescsi.upes_csi.database.EventHandler;
import com.upescsi.upes_csi.fragments.AboutFragment;
import com.upescsi.upes_csi.fragments.EventsFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by 500029448 on 1/26/2015.
 */
public class AboutItemsTask extends AsyncTask<Void, Void, Void> {
    private static final String URL_EVENTS = "http://upescsi.in/about.html";
    private ArrayList<String> eventTitleItems, eventSummaryItems, eventImageURLs;
    private AboutFragment aboutFragment;
    private Document document;
    private EventHandler eventHandler;
    private Event event;

    public AboutItemsTask (AboutFragment aboutFragment) {
        this.aboutFragment = aboutFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(aboutFragment.getActivity(), "Fetching...", Toast.LENGTH_LONG).show();
            }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            eventTitleItems = new ArrayList<String>();
            eventSummaryItems = new ArrayList<String>();
            eventImageURLs = new ArrayList<String>();

            document = Jsoup.connect(URL_EVENTS).get();
            // Using elements to get class data
            Elements headers = document.select("h3[class=color_dark fw_light m_bottom_15 heading_1]");
            Elements imgTags = document.select("p[class=m_bottom_15 fw_light fs_large]");
            Elements tag3=document.select("p[class=m_bottom_25 fw_light fs_large]");
            for (Element e : headers)
            {

                eventTitleItems.add(e.text());
            }
            for (Element e : imgTags)
            {

                eventSummaryItems.add(e.text());
            }
            eventSummaryItems.add("\n\n");
            for (Element e : tag3) {
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

       aboutFragment.textView1.setText(eventTitleItems.toString().substring(1,eventTitleItems.toString().lastIndexOf("]")));
        aboutFragment.textView2.setText(eventSummaryItems.toString().substring(1,eventSummaryItems.toString().lastIndexOf("]")));


    }
}
