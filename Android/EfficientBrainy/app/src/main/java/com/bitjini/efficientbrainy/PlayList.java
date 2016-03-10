package com.bitjini.efficientbrainy;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

/**
 * Created by bitjini on 2/3/16.
 */
public class PlayList extends Fragment implements View.OnClickListener {

    String URL_Generate_OTP = "https://efficient-brainy.herokuapp.com/generate_otp/?access_token=";

    String token_sharedPreference;

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

        Login l=new Login();
        l.sharedPreferences = getActivity().getSharedPreferences(l.Efficient_Brainy, 0);
       token_sharedPreference =l.sharedPreferences.getString(l.TOKEN_KEY, null);
        System.out.println(" getting token from sharedpreference " + token_sharedPreference);

return playView;


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play1:


                try {
                    new GetOtp_AsyncTask(getActivity()).execute(URL_Generate_OTP+token_sharedPreference).get();


                FragmentManager fm = getFragmentManager();
                Otp dialogFragment = new Otp ();

                Bundle args = new Bundle();
                args.putString("audio", "audio_1.xxx");
                    args.putString("file", "Playing File 1");

                dialogFragment.setArguments(args);
                dialogFragment.show(fm, "Sample Fragment");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.play2:
                try {
                    new GetOtp_AsyncTask(getActivity()).execute(URL_Generate_OTP+token_sharedPreference).get();

                    FragmentManager fm2 = getFragmentManager();
                Otp dialogFragment2 = new Otp ();

                Bundle args2 = new Bundle();
                args2.putString("audio", "audio_2.xxx");
                    args2.putString("file", "Playing File 2");
                dialogFragment2.setArguments(args2);
                dialogFragment2.show(fm2, "Sample Fragment");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.play3:
                try {
                    new GetOtp_AsyncTask(getActivity()).execute(URL_Generate_OTP+token_sharedPreference).get();

                    FragmentManager fm3 = getFragmentManager();
                Otp dialogFragment3 = new Otp ();

                Bundle args3 = new Bundle();
                args3.putString("audio", "audio_3.xxx");
                    args3.putString("file", "Playing File 3");
                FrameLayout contentView3 = (FrameLayout)playView.findViewById(R.id.file_frame);
                dialogFragment3.setArguments(args3);
                dialogFragment3.show(fm3, "Sample Fragment");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.play4:
                try {
                    new GetOtp_AsyncTask(getActivity()).execute(URL_Generate_OTP+token_sharedPreference).get();

                    FragmentManager fm4 = getFragmentManager();
                Otp dialogFragment4 = new Otp ();

                Bundle args4 = new Bundle();
                args4.putString("audio", "audio_4.xxx");
                    args4.putString("file", "Playing File 4");
                FrameLayout contentView4 = (FrameLayout)playView.findViewById(R.id.file_frame);
                dialogFragment4.setArguments(args4);
                dialogFragment4.show(fm4, "Sample Fragment");
                break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

        }
    }
}
