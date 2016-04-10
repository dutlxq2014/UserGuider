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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by xueqiulxq on 4/9/16.
 */
public class VMultiGuider extends FrameLayout implements Runnable {

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
    List<GuideItem> mGuideItems;
    FrameLayout mDecorView;
    int mBgColor;
    int mDelayMillis;
    boolean mDisableAnim = false;

    public VMultiGuider(Context context) {
        super(context);
    }

    public VMultiGuider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMultiGuider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private VMultiGuider init() {
        mHandler = new Handler(Looper.getMainLooper());
        mDecorView = (FrameLayout)((Activity)getContext()).getWindow().getDecorView();
        LayoutParams lParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lParams);
        return this;
    }

    private void destroy() {
        mDecorView.removeView(this);
        for (int i = 0; i< mGuideItems.size(); ++i) {
            GuideItem item = mGuideItems.get(i);
            if (item.mGuideView != null && item.mGuideView.getParent() != null) {
                ((ViewGroup)item.mGuideView.getParent()).removeAllViews();
            }
            item.mAnchor = null;
            item.mGuideView = null;
            item.mGuideViewId = -1;
        }
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
                        for (int i=0; i<mGuideItems.size(); ++i) {
                            locateGuideView(mGuideItems.get(i));
                        }
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

        for (int i = 0; i< mGuideItems.size(); ++i) {
            GuideItem item = mGuideItems.get(i);
            if (item.mGuideView == null && item.mGuideViewId > 0) {
                item.mGuideView = LayoutInflater.from(getContext()).inflate(item.mGuideViewId, this, false);
            } else if (item.mGuideView == null) {
                item.mGuideView = new View(getContext());
            }
            // If guider was delayed, guideView may be applied multi times when user click too quick.
            if (item.mGuideView.getParent() != null) {
                ((ViewGroup)item.mGuideView.getParent()).removeView(item.mGuideView);
            }
            // Default LayoutParams if not set.
            if (item.mGuideView.getLayoutParams() == null) {
                LayoutParams guideParams = new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                item.mGuideView.setLayoutParams(guideParams);
            }
            addView(item.mGuideView);
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
            aTop = location[1];
            aRight = aLeft + anchor.getWidth();
            aBottom = aTop + anchor.getHeight();
        } else {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            aLeft = aRight = dm.widthPixels >> 1;
            aTop = aBottom = dm.heightPixels >> 1;
        }
        width = guideView.getWidth();
        height = guideView.getHeight();

        // 2. Calculate guideView coordinates in window DecorView according to mPosition bits.
        LayoutParams guideParams = (LayoutParams)guideView.getLayoutParams();
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

        public VMultiGuider build(Context context) {

            if (mItems.size() == 0) {
                mItems.add(mItem);
            }
            for (GuideItem item : mItems) {
                if (item.mGuideView == null && item.mGuideViewId <= 0) {
                    throw new RuntimeException("Builder#setGuideView or Builder#setGuiderViewId must be called.");
                }
            }

            VMultiGuider guider = new VMultiGuider(context);
            guider.mGuideItems = mItems;
            guider.mBgColor = mBgColor;
            guider.mDelayMillis = mDelayMillis;
            guider.mDisableAnim = mDisableAnim;
            return guider.init();
        }
    }

}
