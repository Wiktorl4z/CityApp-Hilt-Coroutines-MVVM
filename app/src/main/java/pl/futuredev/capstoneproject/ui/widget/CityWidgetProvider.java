package pl.futuredev.capstoneproject.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.ui.activities.main.MainActivity;


public class CityWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.city_widget);
        Intent gridIntent = new Intent(context, WidgetService.class);
        views.setRemoteAdapter(R.id.gv_city_widget, gridIntent);
        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.gv_city_widget, intent);
        views.setEmptyView(R.id.gv_city_widget, R.id.ll_single_city);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

