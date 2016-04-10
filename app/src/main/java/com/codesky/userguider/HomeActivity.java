package com.codesky.userguider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codesky.glibrary.DGuider;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new DGuider.Builder()
                .setAnchor(findViewById(R.id.hello_world))
                .setBackground(0x4Cf00000)
                .setGuideViewId(R.layout.guide_layout)
                .setPosition(DGuider.LEFT_IN | DGuider.BOTTOM_OUT)
                .disableAnim(true)
                .build(this)
                .show();
    }
}
