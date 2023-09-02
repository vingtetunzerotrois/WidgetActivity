package com.example.widgetactivity;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.res.Resources;

public class WidgetHost extends AppWidgetHost {

    public WidgetHost(Context context, int hostId) {
        super(context, hostId);
    }

    @Override
    protected AppWidgetHostView onCreateView(Context context, int appWidgetId, AppWidgetProviderInfo appWidget) {
        // We need to create a custom view to handle long click events
        return new WidgetView(context);
    }

    @Override
    public void startListening() {
        try {
            super.startListening();
        } catch (Resources.NotFoundException e) {
        }
    }

    @Override
    public void stopListening() {
        try {
            super.stopListening();
        } catch (NullPointerException e) {
        }
        clearViews();
    }
}