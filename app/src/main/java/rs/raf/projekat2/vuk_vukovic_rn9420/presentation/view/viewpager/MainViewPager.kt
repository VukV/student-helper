package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class MainViewPager (context: Context, attrs: AttributeSet) : ViewPager(context, attrs){

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}