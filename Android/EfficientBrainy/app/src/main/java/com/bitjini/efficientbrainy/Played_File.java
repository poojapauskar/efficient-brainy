package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by bitjini on 2/3/16.
 */
public class Played_File extends Activity {
    Button stopExit;
    SeekBar seekBar;
    Handler seekHandler = new Handler();

    ImageView imgStop;
    MediaPlayer mediaPlayer = new MediaPlayer();
    public byte[] decrpt = new byte[]{};

    Context ctx;

    TextView txtDuration, txtStart;
    private final String KEY = "abc";
    File tempMp3;

    private double startTime = 0;
    private double finalTime = 0;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.played_file);
        ctx = this;

        txtStart=(TextView) findViewById(R.id.txtStart);
        txtDuration=(TextView) findViewById(R.id.txtDuration);
        seekBar = (SeekBar)findViewById(R.id.seekbar);
        imgStop=(ImageView) findViewById(R.id.stopImageBtn);
        stopExit = (Button) findViewById(R.id.stopExit);
        stopExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calling on BackPressed Method
                onBackPressed();
            }
        });



        // decrypt the file here first argument is key and second is encrypted file which we get from SD card.

        try {


            Decrypted_AudioFiles d = new Decrypted_AudioFiles();
            decrpt = d.decrypt(KEY, getAudioFile());

            playMp3(decrpt);

        } catch (Exception e) {
            e.printStackTrace();
        }




        seekBar.setClickable(true);
        seekBar.setProgress(0);
        // to remove the default indicator icon
//        seekBar.getThumb().mutate().setAlpha(0);

  imgStop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          onBackPressed();
      }
  });


    }

    // getting the audio file from assets folder
    public byte[] getAudioFile() throws FileNotFoundException

    {

        byte[] audio_data = null;

        byte[] inarry = null;


        AssetManager am = ctx.getAssets();

        try {
            // retrieve audio file sent by Otp class
            String audio = getIntent().getStringExtra("audio");

            InputStream is = am.open(audio);
            int length = is.available();

            audio_data = new byte[length];


            is.read(audio_data);

            is.close();

            inarry = audio_data;
            Log.e(" inarry", "" + inarry);


        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        return inarry;

    }

    /**
     * This Method is used to play audio file after decrepting.
     *
     * @param mp3SoundByteArray : This is our audio file which will be play and it converted in byte array.
     */

    public void playMp3(byte[] mp3SoundByteArray) {

        try {

            // create temp file that will hold byte array

            tempMp3 = File.createTempFile("kurchina", "mp3", getCacheDir());
            Log.e(" tempMp3", "" + tempMp3);


            FileOutputStream fos = new FileOutputStream(tempMp3);
            Log.e(" file output stream", "" + fos.toString());
            fos.write(mp3SoundByteArray);

            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);
            Log.e(" file output stream", "" + fis.getFD());

            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();

            startMyPlayer();

        } catch (IOException ex) {


            ex.printStackTrace();

        }

    }

    public void startMyPlayer() {

            mediaPlayer.start();


        finalTime = mediaPlayer.getDuration();
         startTime=mediaPlayer.getCurrentPosition();
        seekBar.setMax((int) finalTime);
            txtDuration.setText(String.format("%d:%d ",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));


        txtStart.setText(String.format("%d :%d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

        seekBar.setProgress((int) startTime);
        seekHandler.postDelayed(updateSongTime, 100);

  mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mediaPlayer) {
          onBackPressed();
      }
  });

    }



    public void stopMyPlayer() {

        if (mediaPlayer.isPlaying()) {

            tempMp3.delete();

            mediaPlayer.stop();

        }
        tempMp3.delete();

        mediaPlayer.stop();

    }
    //handler to change seekBarTime

    private Runnable updateSongTime=new Runnable() {
        @Override
        public void run() {
            //get current position
            startTime=mediaPlayer.getCurrentPosition();

            //set seekbar progress
            seekBar.setProgress((int)startTime);
            //set time remaing
            double timeRemaining = finalTime - startTime;

            txtStart.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            //repeat yourself that again in 100 miliseconds
            seekHandler.postDelayed(this,100);


        }
    };


    // On Back press
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("tag", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("tag", "onBackPressed Called");
        stopMyPlayer();
        Fragment newfragment = new PlayList();
        // get the id of fragment
        FrameLayout contentView1 = (FrameLayout) findViewById(R.id.file_frame);
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager1 = getFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(contentView1.getId(), newfragment)
                .commit();
        finish();
    }


}




