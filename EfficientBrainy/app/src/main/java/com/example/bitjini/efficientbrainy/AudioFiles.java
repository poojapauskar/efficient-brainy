package com.example.bitjini.efficientbrainy;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by bitjini on 3/3/16.
 */
public class Decrypted_AudioFiles extends Activity{

    Button btn_Dec;



    byte [] decrpt;

    Context ctx;

    private final String KEY = "abc";

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.played_file);

        ctx = this;


        // decrypt the file here first argument is key and second is encrypted file which we get from SD card.

        try {
            decrpt = decrypt(KEY, getAudioFile());

             //play decrypted audio file.

                playMp3(decrpt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getting the audio file from assets folder
    public byte[] getAudioFile() throws FileNotFoundException

    {

        byte[] audio_data = null;

        byte[] inarry = null;



        AssetManager am = ctx.getAssets();

        try {

          InputStream is = am.open("vincent.xxx"); // use recorded file instead of getting file from assets folder.
            int length = is.available();

            audio_data = new byte[length];


            is.read(audio_data);

            is.close();

            inarry = audio_data;
            Log.e(" inarry",""+ inarry);


        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        return inarry;

    }


    /**

     * This Method is used to play audio file after decrepting.

     * @param mp3SoundByteArray : This is our audio file which will be play and it converted in byte array.

     */

    private void playMp3(byte[] mp3SoundByteArray) {

        try {

            // create temp file that will hold byte array

            File tempMp3 = File.createTempFile("kurchina", "mp3", getCacheDir());
            Log.e(" tempMp3", "" + tempMp3);
            tempMp3.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(tempMp3);
            Log.e(" file output stream",""+fos.toString());
            fos.write(mp3SoundByteArray);

            fos.close();



            // Tried reusing instance of media player

            // but that resulted in system crashes...

            MediaPlayer mediaPlayer = new MediaPlayer();

            FileInputStream fis = new FileInputStream(tempMp3);
            Log.e(" file output stream",""+fis.getFD());
            mediaPlayer.setDataSource(fis.getFD());

            Log.e(" mediaPlayer",""+mediaPlayer);

            mediaPlayer.prepare();

            mediaPlayer.start();

        } catch (IOException ex) {



            ex.printStackTrace();

        }

    }
    public  byte[] decrypt(String seed, byte[] encrypted) throws Exception {

        byte[] rawKey = getRawKey(seed.getBytes());

        byte[] enc = encrypted;

        byte[] result = decrypt(rawKey, enc);



        return result;

    }

    //pass this audio file for encryption .The below code show process of encryption
//Generate a key for encryption :
    private  byte[] getRawKey(byte[] seed) throws Exception {

        Log.e(" Seed value ",""+seed);
        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG","Crypto");

        sr.setSeed(seed);

        kgen.init(128, sr); // 192 and 256 bits may not be available

        SecretKey skey = kgen.generateKey();

        byte[] raw = skey.getEncoded();

        return raw;

    }

    private  byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        Encryption e=new Encryption();


        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Log.e("raw key for decryption",""+e.encryptedKey);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        Log.e("encrypted data :",""+encrypted);
        byte[] decrypted = cipher.doFinal(encrypted);

        return decrypted;



    }

}

