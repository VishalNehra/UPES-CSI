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

import java.util.ArrayList;

public class TeamFragment extends Fragment {
    private View rootView;
    public ListView listView;
    public TeamAdapter teamAdapter;
    private ArrayList<String> titleItems, summaryItems;


    private static final int ARG_SECTION_NUMBER = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_team, null);
        listView = (ListView) rootView.findViewById(R.id.listView2);

        titleItems = new ArrayList<String>(){{add("Mr. Vinay Avasthi");add("Mr. Ajay Rawat");add("Paritosh Gupta");add("Rishabh Miglani");add("Ashna Mehta");
            add("Akul Narang");add("Gautam Raj");add("Atulya Chandra");add("Divyansh Agarwal");add("Chirag Mangla");
            add("Abhishek Tyagi");add("Shivani Srivastava");add("Niharika Sharma");add("Avish Sharma");add("Mayank Mahajan");
            add("Saransh Mani");add("Mayur Arora");add("Soumil Agarwal");add("Rashmi Shukla");add("Ankit Jaggi");
            add("Shubham Mathur");add("Nikhil Deo");add("Sarthak Khandelwal");}};

        summaryItems = new ArrayList<String>(){{add("Branch Head");add("Student Branch Co-Ordinator");add("President");add("Vice President");add("Public Relation");
            add("General Secretary");add("Treasurer");add("Secretary");add("Joint Secretary");add("Associative Treasurer");
            add("Internal Public Relation");add("Associate Public Relation");add("Associate Public Relation");add("Marketing & Media Head");add("Working Committee Head");
            add("Event Head");add("Associative Event Head");add("Associative Event Head");add("Chief Editor");add("Technical Head");
            add("Designing Head");add("Branch Head");add("Branch Head");}};

        teamAdapter = new TeamAdapter(getActivity(), android.R.layout.activity_list_item, titleItems, summaryItems);
        listView.setAdapter(teamAdapter);


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
       break; }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(ARG_SECTION_NUMBER);
    }



}
