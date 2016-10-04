package com.innopolis.androidtutors.androidtetris.representation;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Сергей on 03.10.2016.
 */
public class NotScrollableGridLayoutManager extends GridLayoutManager {
    public NotScrollableGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NotScrollableGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public NotScrollableGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
