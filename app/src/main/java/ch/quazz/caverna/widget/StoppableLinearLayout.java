package ch.quazz.caverna.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class StoppableLinearLayout extends LinearLayout {
    public StoppableLinearLayout(Context context) {
        super(context);
    }

    public StoppableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoppableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollingEnabled(boolean enabled) {
        ((StoppableRecyclerView)getParent()).setScrollingEnabled(enabled);
    }
}
