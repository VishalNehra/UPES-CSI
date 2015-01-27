package com.upescsi.upes_csi.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.upescsi.upes_csi.R;
import com.upescsi.upes_csi.activities.MainActivity;
import java.util.ArrayList;

public class AboutFragment extends Fragment {
    private View rootView;
    public TextView textView1;
    public TextView textView2;


    private static final int ARG_SECTION_NUMBER = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_about, null);
        textView1=(TextView)rootView.findViewById(R.id.animtext);
        textView2=(TextView)rootView.findViewById(R.id.animtext2);

        try {
            final ImageView splashImageView = (ImageView) rootView.findViewById(R.id.anim);
            splashImageView.setBackgroundResource((int)R.drawable.flag);
            final AnimationDrawable frameAnimation = (AnimationDrawable) splashImageView.getBackground();
            splashImageView.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation.start();
                }
            });}
        catch(Exception i)
        {

            i.printStackTrace();
        }
        textView1.setText(R.string.who_title);
        textView2.setText(R.string.content);

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
 break;       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(ARG_SECTION_NUMBER);
    }

}