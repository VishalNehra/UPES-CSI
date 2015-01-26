package com.upescsi.upes_csi.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.upescsi.upes_csi.R;

public class SplashActivity extends ActionBarActivity


{
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pb = (ProgressBar) findViewById(R.id.progressBar);


        Thread t = new Thread() {
            public void run() {
                try {

                    int i=0;
                    while(i<5)
                    {
                        pb.setProgress(i*5);
                        Thread.sleep(1000);
                        i++;
                    }
                }
                catch (InterruptedException e) {

                }

                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        };

        t.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
