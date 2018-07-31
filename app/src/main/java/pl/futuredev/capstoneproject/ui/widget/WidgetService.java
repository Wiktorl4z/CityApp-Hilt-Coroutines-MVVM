package pl.futuredev.capstoneproject.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityDataBase;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;

public class WidgetService extends RemoteViewsService {

    private CityDataBase cityDataBase;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetViewsFactory(this.getApplicationContext());
    }

    public class WidgetViewsFactory implements RemoteViewsFactory {

        private Context context;
        List<CityPOJO> cityPOJOList;

        public WidgetViewsFactory(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            cityDataBase = CityDataBase.getInstance(getApplicationContext());
            cityPOJOList = cityDataBase.cityDao().loadAllCitiesSync();
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public int getCount() {
            return (cityPOJOList != null) ? cityPOJOList.size() : 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {

            if (cityPOJOList == null) return null;


            String recipeName = cityPOJOList.get(position).getCityName();

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_item);

            remoteViews.setTextViewText(R.id.tv_widget_recipe_name, recipeName);
            remoteViews.setViewVisibility(R.id.tv_widget_recipe_name, View.VISIBLE);

            Intent fillIntent = new Intent();
            remoteViews.setOnClickFillInIntent(R.id.tv_widget_recipe_name, fillIntent);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }

}
