package com.upescsi.upes_csi.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.activities.MainActivity;
import com.upescsi.upes_csi.adapters.EventsAdapter;
import com.upescsi.upes_csi.asynctasks.EventItemsTask;

import java.util.ArrayList;

/**
 * Created by Vishal on 19-01-2015.
 */
public class EventsFragment extends Fragment {
    private View rootView;
    public ListView listView;
    public EventsAdapter eventsAdapter;
    private ArrayList<String> titleItems, summaryItems;

    private static final int ARG_SECTION_NUMBER = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_events, null);
        listView = (ListView) rootView.findViewById(R.id.listView);

        new EventItemsTask(this).execute();
        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.events_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refresh:
                new EventItemsTask(this).execute();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(ARG_SECTION_NUMBER);
    }
}
