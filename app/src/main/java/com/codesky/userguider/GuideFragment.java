package com.codesky.userguider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 *
 * Created by xueqiulxq on 4/11/16.
 */
public class GuideFragment extends Fragment {

    public View anchorView;
    public View[][] guideDot;
    public static int R0 = 0;
    public static int R1 = 1;
    public static int R2 = 2;
    public static int R3 = 3;
    public static int R4 = 4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void findView(View root) {
        if (guideDot != null) {
            return;
        }
        anchorView = root.findViewById(R.id.anchor_square);
        guideDot = new View[5][];
        for (int i = 0; i < guideDot.length; ++i) {
            guideDot[i] = new View[5];
        }
        guideDot[0][0] = root.findViewById(R.id.left_out_top_out);
        guideDot[1][0] = root.findViewById(R.id.left_out_top_in);
        guideDot[2][0] = root.findViewById(R.id.left_out_center);
        guideDot[3][0] = root.findViewById(R.id.left_out_bottom_in);
        guideDot[4][0] = root.findViewById(R.id.left_out_bottom_out);

        guideDot[0][1] = root.findViewById(R.id.left_in_top_out);
        guideDot[1][1] = root.findViewById(R.id.left_in_top_in);
        guideDot[2][1] = root.findViewById(R.id.left_in_center);
        guideDot[3][1] = root.findViewById(R.id.left_in_bottom_in);
        guideDot[4][1] = root.findViewById(R.id.left_in_bottom_out);

        guideDot[0][2] = root.findViewById(R.id.center_top_out);
        guideDot[1][2] = root.findViewById(R.id.center_top_in);
        guideDot[2][2] = root.findViewById(R.id.center_center);
        guideDot[3][2] = root.findViewById(R.id.center_bottom_in);
        guideDot[4][2] = root.findViewById(R.id.center_bottom_out);

        guideDot[0][3] = root.findViewById(R.id.right_in_top_out);
        guideDot[1][3] = root.findViewById(R.id.right_in_top_in);
        guideDot[2][3] = root.findViewById(R.id.right_in_center);
        guideDot[3][3] = root.findViewById(R.id.right_in_bottom_in);
        guideDot[4][3] = root.findViewById(R.id.right_in_bottom_out);

        guideDot[0][4] = root.findViewById(R.id.right_out_top_out);
        guideDot[1][4] = root.findViewById(R.id.right_out_top_in);
        guideDot[2][4] = root.findViewById(R.id.right_out_center);
        guideDot[3][4] = root.findViewById(R.id.right_out_bottom_in);
        guideDot[4][4] = root.findViewById(R.id.right_out_bottom_out);
    }
}
