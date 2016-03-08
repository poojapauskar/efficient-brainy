package com.example.bitjini.efficientbrainy;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bitjini on 2/3/16.
 */
public class Played_File extends Activity {
    Button stopExit;
    SeekBar seekBar;
    Handler seekHandler = new Handler();

    MediaPlayer mediaPlayer = new MediaPlayer();
    public byte[] decrpt = new byte[]{};

    Context ctx;

    private final String KEY = "abc";


    private double startTime = 0;
    private double finalTime = 0;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.played_file);
        ctx = this;
        stopExit = (Button) findViewById(R.id.stopExit);
        stopExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Played_File.this," button clicked",Toast.LENGTH_LONG).show();
                
                stopMyPlayer();

                Fragment newfragment = new PlayList();
                // get the id of fragment
                FrameLayout contentView1 = (FrameLayout) findViewById(R.id.file_frame);
                 // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(contentView1.getId(), newfragment)
                        .commit();
            }
        });

        //create an Intent object
        Intent intent = new Intent(Played_File.this, Decrypted_AudioFiles.class);
        // Retrieving audio from otp
        String value1 = getIntent().getStringExtra("audio");
        if (value1 != null) {
            //add data to the Intent object
            intent.putExtra("audio", value1);   //put the value to pass

            //start the second activity
            startActivity(intent);
        }


        // decrypt the file here first argument is key and second is encrypted file which we get from SD card.

        try {

            Decrypted_AudioFiles d = new Decrypted_AudioFiles();
            decrpt = d.decrypt(KEY, getAudioFile());
            playMp3(decrpt);

        } catch (Exception e) {
            e.printStackTrace();
        }


        seekBar = (SeekBar)

                findViewById(R.id.seekbar);

        seekBar.setClickable(false);


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

            File tempMp3 = File.createTempFile("kurchina", "mp3", getCacheDir());
            Log.e(" tempMp3", "" + tempMp3);
            tempMp3.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(tempMp3);
            Log.e(" file output stream", "" + fos.toString());
            fos.write(mp3SoundByteArray);

            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);
            Log.e(" file output stream", "" + fis.getFD());
            mediaPlayer.setDataSource(fis.getFD());

            startMyPlayer();

        } catch (IOException ex) {


            ex.printStackTrace();

        }

    }

    public void startMyPlayer() {


        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void stopMyPlayer() {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();

        }

    }



}




