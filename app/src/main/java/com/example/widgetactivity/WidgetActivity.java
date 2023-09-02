package com.example.widgetactivity;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WidgetActivity extends Activity {

    private static final String TAG = "MainActivity";
    private BroadcastReceiver mReceiver;

    public View emptyListView;
    public FloatingActionButton fabWidget;
    private boolean isDisplayingKissBar = false;

    private PopupWindow mPopup;

    private AppWidgetHost mAppWidgetHost;
    private static final int APPWIDGET_HOST_ID = 100;
    private static final int REQUEST_APPWIDGET_PICKED = 1;

    private final Widgets widgetsForwarder;

    public WidgetActivity() {
        this.widgetsForwarder = new Widgets(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        /*
         * Initialize data handler and start loading providers
         */

        setContentView(R.layout.activity_widget);
        this.emptyListView = this.findViewById(android.R.id.empty);
        this.fabWidget = findViewById(R.id.fabWidget);

        mAppWidgetHost = new WidgetHost(this, APPWIDGET_HOST_ID);

        fabWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
                Intent pickIntent = new Intent(WidgetActivity.this, PickWidgetActivity.class);
                pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                startActivityForResult(pickIntent, REQUEST_APPWIDGET_PICKED);
            }
        });

        widgetsForwarder.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.mReceiver);
    }

    @Override
    public void onBackPressed() {
        if (mPopup != null) {
            mPopup.dismiss();
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        widgetsForwarder.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
}
