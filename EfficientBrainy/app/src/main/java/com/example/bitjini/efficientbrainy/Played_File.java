package com.example.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by bitjini on 2/3/16.
 */
public class Played_File extends Fragment {
    Button stopExit;
    SeekBar seekBar;
    Handler seekHandler = new Handler();
     Context context;
    View fileView;


    private double startTime = 0;
    private double finalTime = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fileView = inflater.inflate(R.layout.played_file, container, false);

        stopExit=(Button)fileView.findViewById(R.id.stopExit);


        seekBar=(SeekBar)fileView. findViewById(R.id.seekbar);
        seekBar.setClickable(false);
        stopExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Decrypted_AudioFiles d=new Decrypted_AudioFiles();
                d.mediaPlayer.stop();
               Fragment newfragment = new PlayList();
                // get the id of fragment
                FrameLayout contentView1 = (FrameLayout) fileView.findViewById(R.id.file_frame);



                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(contentView1.getId(), newfragment)
                        .commit();
            }
        });
return fileView;
    }


}
