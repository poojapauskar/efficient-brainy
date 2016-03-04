package com.bitjini.efficientbrainy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    MediaPlayer mediaPlayer=new MediaPlayer();


    public byte [] decrpt=new byte[]{};

    Context ctx;

    private final String KEY = "abc";

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.played_file);

        ctx = this;


//        // decrypt the file here first argument is key and second is encrypted file which we get from SD card.
//
//        try {
//            decrpt = decrypt(KEY, getAudioFile());
//
//
//           playMp3(decrpt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


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
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        Log.e("encrypted data :",""+encrypted);
        byte[] decrypted = cipher.doFinal(encrypted);

        return decrypted;



    }

}

