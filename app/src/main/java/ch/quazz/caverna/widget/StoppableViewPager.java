package ch.quazz.caverna.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class StoppableViewPager extends ViewPager {
    private static final String TAG = "StoppableViewPager";
    private boolean scrollingEnabled = true;

    public StoppableViewPager(Context context) {
        super(context);
    }

    public StoppableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollingEnabled(boolean scrollingEnabled) {
        //Log.i(TAG, "Scroll is enabled: " + scrollingEnabled);
        this.scrollingEnabled = scrollingEnabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!scrollingEnabled) return false;
        return super.onInterceptTouchEvent(ev);
    }
}
