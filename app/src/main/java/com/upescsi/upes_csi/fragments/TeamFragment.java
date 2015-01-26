package com.upescsi.upes_csi.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.activities.MainActivity;
import com.upescsi.upes_csi.adapters.EventsAdapter;
import com.upescsi.upes_csi.adapters.TeamAdapter;
import com.upescsi.upes_csi.asynctasks.TeamItemsTask;
import com.upescsi.upes_csi.database.Event;
import com.upescsi.upes_csi.database.TeamHandler;

import java.util.ArrayList;

/**
 * Created by Vishal on 19-01-2015.
 */
public class TeamFragment extends Fragment {
    private View rootView;
    public ListView listView;
    public TeamAdapter teamAdapter;
    private ArrayList<String> titleItems, summaryItems;
    private TeamHandler teamHandler;

    private static final int ARG_SECTION_NUMBER = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_team, null);
        listView = (ListView) rootView.findViewById(R.id.listView2);
        teamHandler = new TeamHandler(getActivity(), null, null, 1);
        titleItems = new ArrayList<String>();
        summaryItems = new ArrayList<String>();

        setView();
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
                setView();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(ARG_SECTION_NUMBER);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        } else
            return false;
    }

    private void setView() {

        if (isNetworkAvailable()) {

            new TeamItemsTask(this).execute();
        } else {
            if (teamHandler.getAllEvents().size()!=0) {
                for (Event event : teamHandler.getAllEvents()) {
                    titleItems.add(event.getEventTitle());
                    summaryItems.add(event.getEventSummary());
                }
                teamAdapter = new TeamAdapter(getActivity(), android.R.layout.activity_list_item, titleItems, summaryItems);
                listView.setAdapter(teamAdapter);
            } else {
                Toast.makeText(getActivity(), "Connect to internet..", Toast.LENGTH_LONG).show();
            }
        }
    }

}
