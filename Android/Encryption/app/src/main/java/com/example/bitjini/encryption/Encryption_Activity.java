package com.example.bitjini.encryption;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bitjini.encryption.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by bitjini on 2/3/16.
 */
public class Encryption_Activity extends Activity {
    public byte[] encryptedKey=new byte[100];

    Button  btn_In;

    byte[] incrept;
    Context ctx;

    private final String KEY = "abc";

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        ctx = this;
        btn_In = (Button) findViewById(R.id.btn_In);
        btn_In.setOnClickListener(btnInListner);


    }

    public View.OnClickListener btnInListner = new View.OnClickListener() {
        public void onClick(View v) {

            try {

                // encrypt audio file send as second argument and corresponding key in first argument.

                incrept = encrypt(KEY, getAudioFile());


                //Store encrypted file in SD card of your mobile with name vincent.mp3.

// use "File.separator" instead of "/"
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "audio_4.xxx");

//create the file
                file.createNewFile();

//check if file exists
                if (file.exists()) {
                    OutputStream fo = new FileOutputStream(file);

                    //write the data
                    fo.write(incrept);

                    //close to avoid memory leaks
                    fo.close();

                    //give a log message that the file was created with "text"
                    System.out.println("file created: " + file);
                    Toast.makeText(Encryption_Activity.this,"Audio file encrypted",Toast.LENGTH_LONG).show();
                }


            } catch (IOException e) {

                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    };
    //Code for getting audio file from assets folder
    public byte[] getAudioFile() throws FileNotFoundException

    {

        byte[] audio_data = null;

        byte[] inarry = null;



        AssetManager am = ctx.getAssets();

        try {
            InputStream is = am.open("audio_4.mp3"); // use recorded file instead of getting file from assets folder.

            int length = is.available();

            audio_data = new byte[length];



            int bytesRead;

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            while ((bytesRead = is.read(audio_data)) != -1)

            {

                output.write(audio_data, 0, bytesRead);

            }

            inarry = output.toByteArray();



        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }



        return inarry;

    }

    public  byte[] encrypt(String seed, byte[] cleartext) throws Exception {



        byte[] rawKey = getRawKey(seed.getBytes());

        byte[] result = encrypt(rawKey, cleartext);

        //  return toHex(result);

        return result;

    }
    //pass this audio file for encryption .The below code show process of encryption
//Generate a key for encryption :
    private  byte[] getRawKey(byte[] seed) throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG","Crypto");

        sr.setSeed(seed);

        kgen.init(128, sr); // 192 and 256 bits may not be available

        SecretKey skey = kgen.generateKey();

        byte[] raw = skey.getEncoded();

        return raw;

    }


    //    Encrypt the audio file with the help of RawKey generated
    private  byte[] encrypt(byte[] raw, byte[] clear) throws Exception {

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");


        Log.e("encryptedKey for ",""+encryptedKey);
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(clear);

        return encrypted;

    }



}





