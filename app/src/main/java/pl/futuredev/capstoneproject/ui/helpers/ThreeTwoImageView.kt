package pl.futuredev.capstoneproject.ui.helpers

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class ThreeTwoImageView(context: Context?, attrs: AttributeSet?) :
    AppCompatImageView(context!!, attrs) {
    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val desiredHeight = width * 2 / 3
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY)
        )
    }
}