package org.wbing.view.filter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * @author wangbing
 * @date 2018/9/26
 */
public class WFilterGroup extends LinearLayout {
    private WFilterItem checkChild;
    private WFilterItem.OnCheckedChangeListener onCheckedChangeListener = new WFilterItem.OnCheckedChangeListener() {
        @Override
        public void onCheckedChange(WFilterItem view, boolean isChecked) {
            if (isChecked) {
                checkChild = view;
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    if (child != view && child instanceof WFilterItem) {
                        ((WFilterItem) child).setChecked(false);
                    }
                }
            } else {
                if (checkChild == view) {
                    checkChild = null;
                }
            }
        }
    };

    public WFilterGroup(Context context) {
        super(context);
    }

    public WFilterGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WFilterGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child instanceof WFilterItem) {
            ((WFilterItem) child).addOnCheckedChangeListener(onCheckedChangeListener);
        }
    }

}
