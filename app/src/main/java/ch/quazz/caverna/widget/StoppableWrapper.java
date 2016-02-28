package ch.quazz.caverna.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class StoppableWrapper extends FrameLayout {
    private static final String TAG = "StoppableWrapper";
    private boolean mScrollable = false;

    public StoppableWrapper(Context context) {
        super(context);
    }

    public StoppableWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoppableWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollable(boolean enable) {
        this.mScrollable = enable;
    }

    public void reenableParentScroll() {
        ((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mScrollable) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "Motion Event: ACTION_DOWN");
                    ((StoppableLinearLayout) getParent()).setScrollingEnabled(false);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "Motion Event: ACTION_UP");
                    //((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.i(TAG, "Motion Event: ACTION_CANCEL");
                    //((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "Motion Event: ACTION_MOVE");
                    //((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
                    break;
            }
            return false;
        } else {
            //((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
            return super.onInterceptTouchEvent(ev);
        }

    }
}
