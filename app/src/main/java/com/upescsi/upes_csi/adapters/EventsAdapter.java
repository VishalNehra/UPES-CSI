package com.upescsi.upes_csi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
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
    private ArrayList<String> titleItems, summaryItems, imageURLs;
    private ImageLoader imageLoader;
    DisplayImageOptions imageOptions;
    private ImageLoaderConfiguration imageLoaderConfiguration;
    private DisplayImageOptions displayImageOptions;
    ImageSize imageSize;

    public EventsAdapter(Context context, int resource, ArrayList<String> titleItems,
                         ArrayList<String> summaryItems, ArrayList<String> imageURLs) {
        super(context, resource, titleItems);
        this.context = context;
        this.titleItems = titleItems;
        this.summaryItems = summaryItems;
        this.imageURLs = imageURLs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.events_row, null);
        eventTitle = (TextView) row.findViewById(R.id.eventTitle);
        eventTitle.setText(titleItems.get(position));
        eventSummary = (TextView) row.findViewById(R.id.eventSummary);
        eventImage = (ImageView) row.findViewById(R.id.eventImage);
        eventSummary.setText(summaryItems.get(position));

        // Create default options which will be used for every displayImage() call
        // if no options will be passed to this method
        displayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        // Create global configuration and initialize ImageLoader with this config
        imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        imageSize = new ImageSize(80,50);
        //ImageLoader.getInstance().displayImage(summaryItems.get(position), eventImage, displayImageOptions);
        ImageLoader.getInstance().loadImage(summaryItems.get(position), imageSize,
                displayImageOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        // Do whatever you want with Bitmap
                        eventImage.setImageBitmap(loadedImage);
                    }
                });

        return row;
    }
}
