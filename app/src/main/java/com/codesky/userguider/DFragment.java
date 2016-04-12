package com.codesky.userguider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesky.glibrary.DGuider;

/**
 *
 * Created by xueqiulxq on 4/11/16.
 */
public class DFragment extends GuideFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.guider_panel_layout, container, false);
        initFindView(root);
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
        final DGuider.Builder build = new DGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_0))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_OUT | DGuider.TOP_OUT).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_IN | DGuider.TOP_OUT).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.CENTER | DGuider.TOP_OUT).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_IN | DGuider.TOP_OUT).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_OUT | DGuider.TOP_OUT).build(getContext()).show();

            }
        });
    }

    public void initRow1() {
        View[] row1 = guideDot[R1];
        final DGuider.Builder build = new DGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_1))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_OUT | DGuider.TOP_IN).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_IN | DGuider.TOP_IN).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.CENTER | DGuider.TOP_IN).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_IN | DGuider.TOP_IN).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_OUT | DGuider.TOP_IN).build(getContext()).show();

            }
        });
    }

    public void initRow2() {
        View[] row1 = guideDot[R2];
        final DGuider.Builder build = new DGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_2))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_OUT | DGuider.CENTER).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_IN | DGuider.CENTER).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.CENTER | DGuider.CENTER).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_IN | DGuider.CENTER).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_OUT | DGuider.CENTER).build(getContext()).show();

            }
        });
    }

    public void initRow3() {
        View[] row1 = guideDot[R3];
        final DGuider.Builder build = new DGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_3))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_OUT | DGuider.BOTTOM_IN).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_IN | DGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.CENTER | DGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_IN | DGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_OUT | DGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
    }

    public void initRow4() {
        View[] row1 = guideDot[R4];
        final DGuider.Builder build = new DGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_4))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_OUT | DGuider.BOTTOM_OUT).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.LEFT_IN | DGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.CENTER | DGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_IN | DGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(DGuider.RIGHT_OUT | DGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
    }
}
