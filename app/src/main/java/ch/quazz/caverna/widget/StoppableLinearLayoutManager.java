package ch.quazz.caverna.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class StoppableLinearLayoutManager extends LinearLayoutManager {
    private boolean scrollingEnabled = true;


    public StoppableLinearLayoutManager(Context context) {
        super(context);
    }

    public StoppableLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public StoppableLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollingEnabled(boolean scrollingEnabled) {
        this.scrollingEnabled = scrollingEnabled;
    }

    @Override
    public boolean canScrollVertically() {
        return scrollingEnabled && super.canScrollVertically();
    }
}
