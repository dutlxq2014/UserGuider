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

import java.util.ArrayList;
import java.util.List;


/**
 * User guide based on Dialog.
 *
 * Usage:
 *
     MultiGuider.Builder builder = new MultiGuider.Builder();

     builder.setAnchor(anchor4).setGuideViewId(R.layout.guide_view)
            .setBackground(0x4Cf00000).disableAnim(true).setDelay(200)  // set window property
            .setPosition(MultiGuider.TOP_OUT|MultiGuider.LEFT_IN)
            .buildItem();
     builder.setAnchor(anchor5).setGuideViewId(R.layout.guide_view)
            .setPosition(MultiGuider.CENTER|MultiGuider.CENTER)
            .buildItem();
     builder.setAnchor(anchor6).setGuideViewId(R.layout.guide_view)
            .setPosition(MultiGuider.RIGHT_IN|MultiGuider.BOTTOM_OUT)
            .buildItem();
     builder.build(Context).show();

 *
 * Created by xueqiulxq on 4/8/16.
 */
public class DMultiGuider extends Dialog implements DialogInterface.OnDismissListener, Runnable {

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
    List<GuideItem> mGuideItems;
    int mBgColor;
    int mDelayMillis;
    boolean mDisableAnim = false;

    private DMultiGuider(Context context) {
        super(context);
    }

    private DMultiGuider(Context context, int themeResId) {
        super(context, themeResId);
    }

    private DMultiGuider init() {
        mHandler = new Handler(Looper.getMainLooper());
        setOnDismissListener(this);
        if (mDisableAnim) {
            getWindow().getAttributes().windowAnimations = -1;
        }
        return this;
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
        for (int i = 0; i< mGuideItems.size(); ++i) {
            GuideItem item = mGuideItems.get(i);
            if (item.mGuideView != null && item.mGuideView.getParent() != null) {
                ((ViewGroup)item.mGuideView.getParent()).removeAllViews();
            }
            item.mAnchor = null;
            item.mGuideView = null;
            item.mGuideViewId = -1;
        }
    }

    public void destroy() {
        if (isShowing()) {
            dismiss();
        }
        mHandler.removeCallbacks(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        prepareView();
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                // Support multi guide views in one single window.
                for (int i=0; i<mGuideItems.size(); ++i) {
                    locateGuideView(mGuideItems.get(i));
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    private void prepareView() {
        getWindow().getDecorView().setBackgroundColor(mBgColor);
        container = new FrameLayout(getContext());
        setContentView(container);  // DecorView会自动设置LayoutParams为MATCH_PARENT

        for (int i = 0; i< mGuideItems.size(); ++i) {
            GuideItem item = mGuideItems.get(i);
            if (item.mGuideView == null && item.mGuideViewId > 0) {
                item.mGuideView = getLayoutInflater().inflate(item.mGuideViewId, container, false);
            } else if (item.mGuideView == null) {
                item.mGuideView = new View(getContext());
            }
            // If guider was delayed, guideView may be applied multi times when user click too quick.
            if (item.mGuideView.getParent() != null) {
                ((ViewGroup)item.mGuideView.getParent()).removeView(item.mGuideView);
            }
            // Default LayoutParams if not set.
            if (item.mGuideView.getLayoutParams() == null) {
                FrameLayout.LayoutParams guideParams = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                item.mGuideView.setLayoutParams(guideParams);
            }
            container.addView(item.mGuideView);
        }
    }

    private void locateGuideView(GuideItem guideItem) {

        View anchor = guideItem.mAnchor;
        View guideView = guideItem.mGuideView;
        int position = guideItem.mPosition;

        // 1. Calculate anchor view position relative to window DecorView.
        int aLeft, aTop, aRight, aBottom, width, height;
        if (anchor != null) {
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            aLeft = location[0];
            aTop = location[1] - getStatusBarHeight();
            aRight = aLeft + anchor.getWidth();
            aBottom = aTop + anchor.getHeight();
        } else {
            DisplayMetrics dm = new DisplayMetrics();
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
            aLeft = aRight = dm.widthPixels >> 1;
            aTop = aBottom = dm.heightPixels >> 1;
        }
        width = guideView.getWidth();
        height = guideView.getHeight();

        // 2. Calculate guideView coordinates in window DecorView according to mPosition bits.
        FrameLayout.LayoutParams guideParams = (FrameLayout.LayoutParams)guideView.getLayoutParams();
        if ((position & LEFT_OUT) != 0) {
            guideParams.leftMargin = aLeft - width;
        } else if ((position & LEFT_IN) != 0) {
            guideParams.leftMargin = aLeft;
        } else if ((position & RIGHT_IN) != 0) {
            guideParams.leftMargin = aRight - width;
        } else if ((position & RIGHT_OUT) != 0) {
            guideParams.leftMargin = aRight;
        } else {
            guideParams.leftMargin = (aLeft + aRight - width) >> 1;
        }

        if ((position & TOP_OUT) != 0) {
            guideParams.topMargin = aTop - height;
        } else if ((position & TOP_IN) != 0) {
            guideParams.topMargin = aTop;
        } else if ((position & BOTTOM_IN) != 0) {
            guideParams.topMargin = aBottom - height;
        } else if ((position & BOTTOM_OUT) != 0) {
            guideParams.topMargin = aBottom;
        } else {
            guideParams.topMargin = (aTop + aBottom - height) >> 1;
        }

        // 3. Locate guide view into container.
        guideView.setLayoutParams(guideParams);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static class GuideItem {
        public View mAnchor;
        public View mGuideView;
        public int mGuideViewId = -1;
        public int mPosition = 0;
    }

    public static class Builder {

        private List<GuideItem> mItems;
        private GuideItem mItem;

        public int mBgColor = 0x4C000000;
        public int mDelayMillis = 0;
        public boolean mDisableAnim = false;

        public Builder() {
            mItem = new GuideItem();
            mItems = new ArrayList<GuideItem>();
        }

        public Builder setAnchor(View anchor) {
            mItem.mAnchor = anchor;
            return this;
        }

        public Builder setGuideView(View view) {
            mItem.mGuideView = view;
            return this;
        }

        public Builder setGuideViewId(int id) {
            mItem.mGuideViewId = id;
            return this;
        }

        public Builder setBackground(int color) {
            mBgColor = color;
            return this;
        }

        public Builder setPosition(int position) {
            mItem.mPosition = position;
            return this;
        }

        public Builder setDelay(int delayMillis) {
            mDelayMillis = delayMillis;
            return this;
        }

        public Builder disableAnim(boolean disableAnim) {
            mDisableAnim = disableAnim;
            return this;
        }

        public Builder buildItem() {
            mItems.add(mItem);
            mItem = new GuideItem();
            return this;
        }

        public DMultiGuider build(Context context) {

            if (mItems.size() == 0) {
                mItems.add(mItem);
            }
            for (GuideItem item : mItems) {
                if (item.mGuideView == null && item.mGuideViewId <= 0) {
                    throw new RuntimeException("Builder#setGuideView or Builder#setGuiderViewId must be called.");
                }
            }

            DMultiGuider guider = new DMultiGuider(context, R.style.GuiderStyle);
            guider.mGuideItems = mItems;
            guider.mBgColor = mBgColor;
            guider.mDelayMillis = mDelayMillis;
            guider.mDisableAnim = mDisableAnim;
            return guider.init();
        }
    }
}
