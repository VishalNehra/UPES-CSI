package com.upescsi.upes_csi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.upescsi.upes_csi.R;

import java.util.ArrayList;

/**
 * Created by Vishal on 19-01-2015.
 */
public class EventsAdapter extends ArrayAdapter<String> {
    private Context context;
    private View row;
    private TextView eventTitle, eventSummary;
    private ImageView eventImage;
    private ArrayList<String> titleItems, summaryItems;

    public EventsAdapter(Context context, int resource, ArrayList<String> titleItems, ArrayList<String> summaryItems) {
        super(context, resource, titleItems);
        this.context = context;
        this.titleItems = titleItems;
        this.summaryItems = summaryItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.events_row, null);
        eventTitle = (TextView) row.findViewById(R.id.eventTitle);
        eventTitle.setText(titleItems.get(position));
        eventSummary = (TextView) row.findViewById(R.id.eventSummary);
        eventSummary.setText(summaryItems.get(position));

        return row;
    }
}
