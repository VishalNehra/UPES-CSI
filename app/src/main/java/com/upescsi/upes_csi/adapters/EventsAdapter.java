package com.upescsi.upes_csi.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.database.Event;
import com.upescsi.upes_csi.database.EventHandler;

import java.util.ArrayList;

/**
 * Created by Vishal on 19-01-2015.
 */
public class EventsAdapter extends ArrayAdapter<String> {
    private Context context;
    private View row;
    private TextView eventTitle;
    private ImageView eventImage;
    private ArrayList<String> titleItems, imageURLs;
    private ArrayList<Integer> eventImgWidths;
    private ImageLoader imageLoader;
    DisplayImageOptions imageOptions;
    private ImageLoaderConfiguration imageLoaderConfiguration;
    private DisplayImageOptions displayImageOptions;
    ImageSize imageSize;
    RelativeLayout relativeLayout;
    private EventHandler eventHandler;

    public EventsAdapter(Context context, int resource, ArrayList<String> titleItems
            , ArrayList<String> imageURLs) {
        super(context, resource, titleItems);
        this.context = context;
        this.titleItems = titleItems;
        this.imageURLs = imageURLs;

        eventHandler = new EventHandler(context, null, null, 1);

        // Image loaders
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
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.events_row, null);
        eventTitle = (TextView) row.findViewById(R.id.eventTitle);
        eventTitle.setText(titleItems.get(position));
        eventImage = (ImageView) row.findViewById(R.id.eventImage);
        relativeLayout = (RelativeLayout) row.findViewById(R.id.events_row_parent);

        imageSize = new ImageSize(240,240);
        ImageLoader.getInstance().loadImage(imageURLs.get(position), imageSize,
                displayImageOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        // Do whatever you want with Bitmap
                        eventImage.setImageBitmap(loadedImage);
                        checkWidth(loadedImage, position);
                    }
                });
        //ImageLoader.getInstance().displayImage(summaryItems.get(position), eventImage, displayImageOptions);

        eventTitle.setBackgroundColor(Color.parseColor("#50989898"));
        return row;
    }

    private void checkWidth(Bitmap loadedImage, int position) {
        int holderWidth = 480;
        int bitmapWidth = loadedImage.getWidth();
        if (bitmapWidth < holderWidth) {
            Log.d("image width", bitmapWidth + "" + " of " + position);
            eventTitle.setWidth(bitmapWidth);
        } else {
            Log.d("image width", holderWidth + " of " + position);
            eventTitle.setWidth(holderWidth);
        }
        //return 0;
    }
}
