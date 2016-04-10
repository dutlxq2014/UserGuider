package com.codesky.glibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;


/**
 *
 * Created by xueqiulxq on 4/9/16.
 */
public class VGuider extends FrameLayout implements Runnable {

    /**
     *             TO
     *      |-------------|
     *      |      TI     |
     *      |             |
     *    LO|LI    c    RI|RO
     *      |             |
     *      |______BI_____|
     *             BO
     */
    public static final int CENTER      = 0;
    public static final int LEFT_OUT    = 1;
    public static final int RIGHT_OUT   = 1<<1;
    public static final int TOP_OUT     = 1<<2;
    public static final int BOTTOM_OUT  = 1<<3;
    public static final int LEFT_IN     = 1<<4;
    public static final int RIGHT_IN    = 1<<5;
    public static final int TOP_IN      = 1<<6;
    public static final int BOTTOM_IN   = 1<<7;

    Handler mHandler;
    View mAnchor;
    View mGuideView;
    int mGuideViewId;
    FrameLayout mDecorView;
    int mBgColor;
    int mPosition;
    int mDelayMillis;
    boolean mDisableAnim;

    public VGuider(Context context) {
        super(context);
    }

    public VGuider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGuider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private VGuider init() {
        mHandler = new Handler(Looper.getMainLooper());
        mDecorView = (FrameLayout)((Activity)getContext()).getWindow().getDecorView();
        LayoutParams lParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lParams);
        return this;
    }

    public void destroy() {
        mDecorView.removeView(this);

        if (mGuideView != null && mGuideView.getParent() != null) {
            ((ViewGroup)mGuideView.getParent()).removeAllViews();
        }
        mAnchor = null;
        mGuideView = null;
        mGuideViewId = -1;

        mHandler.removeCallbacks(this);
    }

    public void show() {
        mHandler.postDelayed(this, mDelayMillis);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                animOut();
                break;
            default:
        }
        return true;
    }

    @Override
    public void run() {
        prepareView();
        final ViewTreeObserver observer = getViewTreeObserver();
        observer.addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        locateGuideView();
                        animIn();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
        // Add this to decorView
        mDecorView.addView(this);
    }

    private void animIn() {
        if (!mDisableAnim) {
            AnimationSet as = new AnimationSet(true);
            as.setDuration(120);
            as.setInterpolator(new DecelerateInterpolator());
            as.addAnimation(new ScaleAnimation(0.9f, 1.0f, 0.8f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
            as.addAnimation(new AlphaAnimation(0.0f, 1.0f));
            startAnimation(as);
        }
    }

    private void animOut() {
        if (!mDisableAnim) {
            AnimationSet as = new AnimationSet(true);
            as.setDuration(120);
            as.setInterpolator(new AccelerateInterpolator());
            as.addAnimation(new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.8f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
            as.addAnimation(new AlphaAnimation(1.0f, 0.0f));
            as.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    destroy();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            startAnimation(as);
        } else {
            destroy();
        }
    }

    private void prepareView() {
        setBackgroundColor(mBgColor);

        if (mGuideView == null && mGuideViewId > 0) {
            mGuideView = LayoutInflater.from(getContext()).inflate(mGuideViewId, this, false);
        } else if (mGuideView == null) {
            mGuideView = new View(getContext());
        }
        // If guider was delayed, guideView may be applied multi times when user click too quick.
        if (mGuideView.getParent() != null) {
            ((ViewGroup)mGuideView.getParent()).removeView(mGuideView);
        }
        // Default LayoutParams if not set.
        if (mGuideView.getLayoutParams() == null) { // Default LayoutParams
            LayoutParams guideParams = new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mGuideView.setLayoutParams(guideParams);
        }
        addView(mGuideView);
    }

    private void locateGuideView() {

        // 1. Calculate anchor view position relative to window DecorView.
        int aLeft, aTop, aRight, aBottom, width, height;
        if (mAnchor != null) {
            int[] location = new int[2];
            mDecorView.getLocationOnScreen(location);
            mAnchor.getLocationOnScreen(location);
            aLeft = location[0];
            aTop = location[1];
            aRight = aLeft + mAnchor.getWidth();
            aBottom = aTop + mAnchor.getHeight();
        } else {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            aLeft = aRight = dm.widthPixels >> 1;
            aTop = aBottom = dm.heightPixels >> 1;
        }
        width = mGuideView.getWidth();
        height = mGuideView.getHeight();

        // 2. Calculate guideView coordinates in window DecorView according to mPosition bits.
        LayoutParams guideParams = (LayoutParams)mGuideView.getLayoutParams();

        if ((mPosition & LEFT_OUT) != 0) {
            guideParams.leftMargin = aLeft - width;
        } else if ((mPosition & LEFT_IN) != 0) {
            guideParams.leftMargin = aLeft;
        } else if ((mPosition & RIGHT_IN) != 0) {
            guideParams.leftMargin = aRight - width;
        } else if ((mPosition & RIGHT_OUT) != 0) {
            guideParams.leftMargin = aRight;
        } else {
            guideParams.leftMargin = (aLeft + aRight - width) >> 1;
        }

        if ((mPosition & TOP_OUT) != 0) {
            guideParams.topMargin = aTop - height;
        } else if ((mPosition & TOP_IN) != 0) {
            guideParams.topMargin = aTop;
        } else if ((mPosition & BOTTOM_IN) != 0) {
            guideParams.topMargin = aBottom - height;
        } else if ((mPosition & BOTTOM_OUT) != 0) {
            guideParams.topMargin = aBottom;
        } else {
            guideParams.topMargin = (aTop + aBottom - height) >> 1;
        }

        // 3. Locate guide view into container.
        mGuideView.setLayoutParams(guideParams);
    }

    public static class Builder {

        private View mAnchor;
        private View mGuideView;
        private int mGuideViewId = -1;
        private int mPosition = 0;
        private int mBgColor = 0x4C000000;
        private int mDelayMillis = 0;
        private boolean mDisableAnim = false;

        public Builder setAnchor(View anchor) {
            mAnchor = anchor;
            return this;
        }

        public Builder setGuideView(View view) {
            mGuideView = view;
            return this;
        }

        public Builder setGuideViewId(int id) {
            mGuideViewId = id;
            return this;
        }

        public Builder setBackground(int color) {
            mBgColor = color;
            return this;
        }

        public Builder setPosition(int position) {
            mPosition = position;
            return this;
        }

        public Builder setDelay(int delayMillis) {
            this.mDelayMillis = delayMillis;
            return this;
        }

        public Builder disableAnim(boolean disableAnim) {
            this.mDisableAnim = disableAnim;
            return this;
        }

        public VGuider build(Context context) {
            if (mGuideView == null && mGuideViewId <= 0) {
                throw new RuntimeException("Builder#setGuideView or Builder#setGuiderViewId must be called.");
            }
            VGuider guider = new VGuider(context);
            guider.mAnchor = mAnchor;
            guider.mGuideView = mGuideView;
            guider.mGuideViewId = mGuideViewId;
            guider.mBgColor = mBgColor;
            guider.mPosition = mPosition;
            guider.mDelayMillis = mDelayMillis;
            guider.mDisableAnim = mDisableAnim;
            return guider.init();
        }
    }

}
