package com.example.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by bitjini on 2/3/16.
 */
public class PlayList extends Fragment implements View.OnClickListener {

    Button play1,play2,play3,play4;
    View playView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        playView = inflater.inflate(R.layout.play_list, container, false);


        play1=(Button)playView.findViewById(R.id.play1);
        play2=(Button)playView.findViewById(R.id.play2);
        play3=(Button)playView.findViewById(R.id.play3);
        play4=(Button)playView.findViewById(R.id.play4);

        play1.setOnClickListener(this);
        play2.setOnClickListener(this);
        play3.setOnClickListener(this);
        play4.setOnClickListener(this);

return playView;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play1:
                Fragment newfragment = new Otp();
                // get the id of fragment
                FrameLayout contentView1 = (FrameLayout) playView.findViewById(R.id.playListFrame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(contentView1.getId(), newfragment).addToBackStack(contentView1.toString())
                        .commit();

                break;
            case R.id.play2:
                Fragment newfragment2 = new Otp();
                // get the id of fragment
                FrameLayout contentView2 = (FrameLayout) playView.findViewById(R.id.playListFrame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager2 = getFragmentManager();
                fragmentManager2.beginTransaction()
                        .replace(contentView2.getId(), newfragment2).addToBackStack(contentView2.toString())
                        .commit();

                break;
            case R.id.play3:
                Fragment newfragment3 = new Otp();
                // get the id of fragment
                FrameLayout contentView3 = (FrameLayout) playView.findViewById(R.id.playListFrame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager3 = getFragmentManager();
                fragmentManager3.beginTransaction()
                        .replace(contentView3.getId(), newfragment3).addToBackStack(contentView3.toString())
                        .commit();

                break;
            case R.id.play4:
                Fragment newfragment4 = new Otp();
                FrameLayout contentView4 = (FrameLayout) playView.findViewById(R.id.playListFrame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager4 = getFragmentManager();
                fragmentManager4.beginTransaction()
                        .replace(contentView4.getId(), newfragment4).addToBackStack(contentView4.toString())
                        .commit();

                break;

        }
    }
}
