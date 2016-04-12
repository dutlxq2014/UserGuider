package com.codesky.userguider;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesky.glibrary.VGuider;

/**
 *
 * Created by xueqiulxq on 4/11/16.
 */
public class VFragment extends GuideFragment {

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
        final VGuider.Builder build = new VGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_0))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_OUT | VGuider.TOP_OUT).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_IN | VGuider.TOP_OUT).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.CENTER | VGuider.TOP_OUT).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_IN | VGuider.TOP_OUT).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_OUT | VGuider.TOP_OUT).build(getContext()).show();

            }
        });
    }

    public void initRow1() {
        View[] row1 = guideDot[R1];
        final VGuider.Builder build = new VGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_1))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_OUT | VGuider.TOP_IN).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_IN | VGuider.TOP_IN).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.CENTER | VGuider.TOP_IN).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_IN | VGuider.TOP_IN).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_OUT | VGuider.TOP_IN).build(getContext()).show();

            }
        });
    }

    public void initRow2() {
        View[] row1 = guideDot[R2];
        final VGuider.Builder build = new VGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_2))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_OUT | VGuider.CENTER).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_IN | VGuider.CENTER).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.CENTER | VGuider.CENTER).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_IN | VGuider.CENTER).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_OUT | VGuider.CENTER).build(getContext()).show();

            }
        });
    }

    public void initRow3() {
        View[] row1 = guideDot[R3];
        final VGuider.Builder build = new VGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_3))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_OUT | VGuider.BOTTOM_IN).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_IN | VGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.CENTER | VGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_IN | VGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_OUT | VGuider.BOTTOM_IN).build(getContext()).show();

            }
        });
    }

    public void initRow4() {
        View[] row1 = guideDot[R4];
        final VGuider.Builder build = new VGuider.Builder().setAnchor(anchorView)
                .setBackground(getResources().getColor(R.color.color_guide_row_4))
                .setGuideViewId(R.layout.guider_view_layout);
        row1[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_OUT | VGuider.BOTTOM_OUT).build(getContext()).show();
            }
        });
        row1[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.LEFT_IN | VGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
        row1[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.CENTER | VGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
        row1[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_IN | VGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
        row1[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.setPosition(VGuider.RIGHT_OUT | VGuider.BOTTOM_OUT).build(getContext()).show();

            }
        });
    }

}
