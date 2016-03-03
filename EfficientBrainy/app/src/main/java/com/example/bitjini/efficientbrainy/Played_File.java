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
public class Played_File extends Fragment {
    Button stopExit;
    View fileView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fileView = inflater.inflate(R.layout.played_file, container, false);

        stopExit=(Button)fileView.findViewById(R.id.stopExit);

        stopExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Fragment newfragment = new PlayList();
                // get the id of fragment
                FrameLayout contentView1 = (FrameLayout) fileView.findViewById(R.id.file_frame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(contentView1.getId(), newfragment).addToBackStack(contentView1.toString())
                        .commit();
            }
        });
return fileView;
    }
}
