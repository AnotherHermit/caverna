package ch.quazz.caverna.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class StoppableRecyclerView extends RecyclerView {
    public StoppableRecyclerView(Context context) {
        super(context);
    }

    public StoppableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoppableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setScrollingEnabled(boolean enabled) {
        ((StoppableViewPager)getParent()).setScrollingEnabled(enabled);
        ((StoppableLinearLayoutManager)this.getLayoutManager()).setScrollingEnabled(enabled);
    }
}
