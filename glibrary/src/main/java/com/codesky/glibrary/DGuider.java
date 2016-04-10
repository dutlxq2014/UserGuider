package com.codesky.glibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;


/**
 * User guide based on Dialog.
 *
 * Usage:
 *
     new Guider.Builder()
         .setAnchor(anchorView)
         .setBackground(0x4Cf00000)
         .setGuideView(guideView)   or
         .setGuideViewId(R.layout.guide_view)
         .setPosition(Guider.RIGHT_IN | Guider.BOTTOM_OUT)
         .setDelay(200)
         .build(Context)
         .show();
 *
 * Created by xueqiulxq on 4/8/16.
 */
public class DGuider extends Dialog implements DialogInterface.OnDismissListener, Runnable {

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
    FrameLayout container;
    View mAnchor;
    View mGuideView;
    int mGuideViewId;
    int mBgColor;
    int mPosition;
    int mDelayMillis;
    boolean mDisableAnim;

    private DGuider(Context context) {
        super(context);
    }

    private DGuider(Context context, int themeResId) {
        super(context, themeResId);
    }

    private DGuider init() {
        mHandler = new Handler(Looper.getMainLooper());
        setOnDismissListener(this);
        if (mDisableAnim) {
            getWindow().getAttributes().windowAnimations = -1;
        }
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                destroy();
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mGuideView != null && mGuideView.getParent() != null) {
            ((ViewGroup)mGuideView.getParent()).removeAllViews();
        }
        mAnchor = null;
        mGuideView = null;
        mGuideViewId = -1;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        prepareView();
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                locateGuideView();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void destroy() {
        if (isShowing()) {
            dismiss();
        }
        mHandler.removeCallbacks(this);
    }

    public void show() {
        mHandler.postDelayed(this, mDelayMillis);
    }

    @Override
    public void run() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        super.show();
    }

    private void prepareView() {
        getWindow().getDecorView().setBackgroundColor(mBgColor);
        container = new FrameLayout(getContext());
        setContentView(container);  // DecorView will set LayoutParams to be MATCH_PARENT automatically.

        if (mGuideView == null && mGuideViewId > 0) {
            mGuideView = getLayoutInflater().inflate(mGuideViewId, container, false);
        } else if (mGuideView == null) {
            mGuideView = new View(getContext());
        }
        // If guider was delayed, guideView may be applied multi times when user click too quick.
        if (mGuideView.getParent() != null) {
            ((ViewGroup)mGuideView.getParent()).removeView(mGuideView);
        }
        // Default LayoutParams if not set.
        if (mGuideView.getLayoutParams() == null) { // Default LayoutParams
            FrameLayout.LayoutParams guideParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mGuideView.setLayoutParams(guideParams);
        }
        container.addView(mGuideView);
    }

    private void locateGuideView() {

        // 1. Calculate anchor view position relative to window DecorView.
        int aLeft, aTop, aRight, aBottom, width, height;
        if (mAnchor != null) {
            int[] location = new int[2];
            mAnchor.getLocationOnScreen(location);
            aLeft = location[0];
            aTop = location[1] - getStatusBarHeight();
            aRight = aLeft + mAnchor.getWidth();
            aBottom = aTop + mAnchor.getHeight();
        } else {
            DisplayMetrics dm = new DisplayMetrics();
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            aLeft = aRight = dm.widthPixels >> 1;
            aTop = aBottom = dm.heightPixels >> 1;
        }
        width = mGuideView.getWidth();
        height = mGuideView.getHeight();

        // 2. Calculate guideView coordinates in window DecorView according to mPosition bits.
        FrameLayout.LayoutParams guideParams = (FrameLayout.LayoutParams)mGuideView.getLayoutParams();

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

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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

        public DGuider build(Context context) {
            if (mGuideView == null && mGuideViewId <= 0) {
                throw new RuntimeException("Builder#setGuideView or Builder#setGuiderViewId must be called.");
            }
            DGuider guider = new DGuider(context, R.style.GuiderStyle);
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
