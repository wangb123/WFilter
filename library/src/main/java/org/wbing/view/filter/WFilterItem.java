package org.wbing.view.filter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Checkable;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/9/26
 */
public class WFilterItem extends LinearLayout implements Checkable, View.OnClickListener {
    private static final int DEFAULT_ROTATE_ANI_TIME = 200;
    private boolean checked;
    private RotateAnimation mFlipAnimation;
    private RotateAnimation mReverseFlipAnimation;
    private int mRotateAniTime = DEFAULT_ROTATE_ANI_TIME;
    private List<OnCheckedChangeListener> listeners = new ArrayList<>();


    {
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setFillAfter(true);

        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(mRotateAniTime);
        mReverseFlipAnimation.setFillAfter(true);
    }

    public WFilterItem(Context context) {
        super(context);
        init();
    }

    public WFilterItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WFilterItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        toggle();
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked != this.checked) {
            this.checked = checked;
            View icon = findViewWithTag(getResources().getString(R.string.w_filter_icon));
            if (icon != null) {
                icon.clearAnimation();
                icon.startAnimation(this.checked ? mFlipAnimation : mReverseFlipAnimation);
            }

            for (OnCheckedChangeListener listener : listeners) {
                listener.onCheckedChange(this, checked);
            }
        }
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }

    public void addOnCheckedChangeListener(OnCheckedChangeListener listener) {
        listeners.add(listener);
    }

    public void removeOnCheckedChangeListener(OnCheckedChangeListener listener) {
        listeners.remove(listener);
    }

    /**
     * 状态变化监听
     */
    public interface OnCheckedChangeListener {
        /**
         * 状态变化回调方法
         *
         * @param view      变化的view
         * @param isChecked 是否选中
         */
        void onCheckedChange(WFilterItem view, boolean isChecked);
    }
}
