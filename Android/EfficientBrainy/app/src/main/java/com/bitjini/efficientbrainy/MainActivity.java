package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Intent;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView splashBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        splashBtn=(ImageView)findViewById(R.id.splashbtn);


        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                public void onTick(long millisUntilFinished) {
//                    timerText.setText(" Started Timer to play audio- seconds remaining: " + millisUntilFinished / 1000);

                }

                public void onFinish() {
                    Fragment newfragment = new Login();
                    FrameLayout contentView2 = (FrameLayout)findViewById(R.id.mainFrame);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(contentView2.getId(),newfragment)
                            .commit();
                }
            }.start();
//        splashBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Fragment newfragment = new Login();
//                FrameLayout contentView2 = (FrameLayout)findViewById(R.id.mainFrame);
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(contentView2.getId(),newfragment)
//                        .commit();
//
//
//            }
//        });

    }
}
