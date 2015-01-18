package com.upescsi.upes_csi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.upescsi.upes_csi.R;

/**
 * Created by Vishal on 19-01-2015.
 */
public class EventsFragment extends Fragment {
    private View rootView;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_events, null);
        listView = (ListView) rootView.findViewById(R.id.listView);
        return rootView;
    }
}
