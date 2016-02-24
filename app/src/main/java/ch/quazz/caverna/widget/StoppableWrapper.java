package ch.quazz.caverna.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class StoppableWrapper extends FrameLayout {

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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mScrollable) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ((StoppableLinearLayout) getParent()).setScrollingEnabled(false);
                    break;
                case MotionEvent.ACTION_UP:
                    ((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
                    break;
            }
            return false;
        } else {
            ((StoppableLinearLayout) getParent()).setScrollingEnabled(true);
            return super.onInterceptTouchEvent(ev);
        }

    }
}
