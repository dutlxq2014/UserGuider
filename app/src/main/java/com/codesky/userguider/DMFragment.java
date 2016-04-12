package com.codesky.userguider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesky.glibrary.DGuider;
import com.codesky.glibrary.DMultiGuider;

/**
 *
 * Created by xueqiulxq on 4/11/16.
 */
public class DMFragment extends GuideFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.guider_panel_layout, container, false);
        findView(root);
        initClickAction();
        return root;
    }

    public void initClickAction() {
        initRow0();
        initRow1();
        initRow2();
        initRow3();
        initRow4();
    }

    public void initRow0() {

        View[] row1 = guideDot[R0];
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_0))
                        .setPosition(DGuider.LEFT_OUT | DGuider.TOP_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_0))
                        .setPosition(DGuider.LEFT_IN | DGuider.TOP_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_0))
                        .setPosition(DGuider.CENTER | DGuider.TOP_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_0))
                        .setPosition(DGuider.RIGHT_IN | DGuider.TOP_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_0))
                        .setPosition(DGuider.RIGHT_OUT | DGuider.TOP_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
    }

    public void initRow1() {
        View[] row1 = guideDot[R1];
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_1))
                        .setPosition(DGuider.LEFT_OUT | DGuider.TOP_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_1))
                        .setPosition(DGuider.LEFT_IN | DGuider.TOP_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_1))
                        .setPosition(DGuider.CENTER | DGuider.TOP_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_1))
                        .setPosition(DGuider.RIGHT_IN | DGuider.TOP_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_1))
                        .setPosition(DGuider.RIGHT_OUT | DGuider.TOP_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
    }

    public void initRow2() {
        View[] row1 = guideDot[R2];
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_2))
                        .setPosition(DGuider.LEFT_OUT | DGuider.CENTER).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_2))
                        .setPosition(DGuider.LEFT_IN | DGuider.CENTER).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_2))
                        .setPosition(DGuider.LEFT_OUT| DGuider.CENTER).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.RIGHT_OUT | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_2))
                        .setPosition(DGuider.RIGHT_IN | DGuider.CENTER).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_2))
                        .setPosition(DGuider.RIGHT_OUT | DGuider.CENTER).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
    }

    public void initRow3() {
        View[] row1 = guideDot[R3];
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_3))
                        .setPosition(DGuider.LEFT_OUT | DGuider.BOTTOM_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_3))
                        .setPosition(DGuider.LEFT_IN | DGuider.BOTTOM_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_3))
                        .setPosition(DGuider.CENTER | DGuider.BOTTOM_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_3))
                        .setPosition(DGuider.RIGHT_IN | DGuider.BOTTOM_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_3))
                        .setPosition(DGuider.RIGHT_OUT | DGuider.BOTTOM_IN).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
    }

    public void initRow4() {
        View[] row1 = guideDot[R4];
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_4))
                        .setPosition(DGuider.LEFT_OUT | DGuider.BOTTOM_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_4))
                        .setPosition(DGuider.LEFT_IN | DGuider.BOTTOM_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_4))
                        .setPosition(DGuider.CENTER | DGuider.BOTTOM_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_4))
                        .setPosition(DGuider.RIGHT_IN | DGuider.BOTTOM_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMultiGuider.Builder builder = new DMultiGuider.Builder();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setBackground(getResources().getColor(R.color.color_guide_row_4))
                        .setPosition(DGuider.RIGHT_OUT | DGuider.BOTTOM_OUT).buildItem();
                builder.setAnchor(anchorView).setGuideViewId(R.layout.guider_view_layout)
                        .setPosition(DGuider.CENTER | DGuider.CENTER).buildItem();
                builder.build(getContext()).show();
            }
        });
    }

}
