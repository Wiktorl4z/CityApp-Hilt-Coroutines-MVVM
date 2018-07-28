package pl.futuredev.capstoneproject.ui.activities;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class ThreeTwoImageView extends AppCompatImageView {

    public ThreeTwoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int desiredHeight = width * 2 / 3;
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY));
    }
}