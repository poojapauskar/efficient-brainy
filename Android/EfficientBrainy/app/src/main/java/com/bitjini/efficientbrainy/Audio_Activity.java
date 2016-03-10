//package com.bitjini.efficientbrainy;
//
//import android.app.Activity;
//import android.content.res.AssetFileDescriptor;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by bitjini on 24/2/16.
// */
//
//public class Audio_Activity extends Activity {
//
//    MediaPlayer mediaPlayer;
//    Button forward, pause,play, backward;
//    private TextView tx1, tx2, tx3;
//
//    SeekBar seekBar;
//    Handler seekHandler = new Handler();
//
//    Button start,stop;
//    private TextView timerText,timerstopText;
//    private double startTime = 0;
//    private double finalTime = 0;
//
//    private int forwardTime = 50000;
//    private int backwardTime = 5000;
//
//    public static int oneTimeOnly = 0;
//
//    /**
//     * Called when the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.media_layout);
//
//        forward = (Button) findViewById(R.id.button);
//        pause = (Button) findViewById(R.id.button2);
//        play = (Button) findViewById(R.id.button3);
//        backward = (Button) findViewById(R.id.button4);
//
//
//        tx1 = (TextView) findViewById(R.id.textView2);
//        tx2 = (TextView) findViewById(R.id.textView3);
//        tx3 = (TextView) findViewById(R.id.textView4);
//        tx3.setText("Song.mp3");
//
//        start = (Button) findViewById(R.id.start);
//        stop = (Button) findViewById(R.id.stop);
//
//        timerText = (TextView) findViewById(R.id.timerText);
//        timerstopText = (TextView) findViewById(R.id.timerstopText);
//        timerText.setText("");
//
//        seekBar=(SeekBar) findViewById(R.id.seekBar);
//        seekBar.setClickable(false);
//        pause.setEnabled(false);
//
//        mediaPlayer = new MediaPlayer();
//        playSong();
//        seekBar.setProgress(0);
//
//
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_LONG).show();
//
//                mediaPlayer.start();
//
//                finalTime = mediaPlayer.getDuration();
//                startTime = mediaPlayer.getCurrentPosition();
//
//                seekBar.setMax((int) finalTime);
//                tx2.setText(String.format("%d min, %d sec",
//                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
//
//                tx1.setText(String.format("%d min, %d sec",
//                                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
//                );
//
//                seekBar.setProgress((int) startTime);
//                seekHandler.postDelayed(updateSongTime, 100);
//                pause.setEnabled(true);
//                play.setEnabled(false);
//
//            }
//
//        });
//        pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Pausing sound", Toast.LENGTH_LONG).show();
//                mediaPlayer.pause();
//                pause.setEnabled(false);
//                play.setEnabled(true);
//            }
//        });
//        forward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int temp = (int) startTime;
//                if ((temp + forwardTime) <= finalTime) {
//                    startTime = startTime + forwardTime;
//                    mediaPlayer.seekTo((int) startTime);
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        backward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int temp=(int) startTime;
//
//                if((temp-backwardTime)>0)
//                {
//                    startTime=startTime-backwardTime;
//                    mediaPlayer.seekTo((int) startTime);
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                if (mediaPlayer != null && fromUser) {
////                    mediaPlayer.seekTo(progress * 1000);
////                }
//                if(fromUser)
//                {
//                   mediaPlayer.seekTo(progress);
//                    seekBar.setProgress(progress);
//                }
//            }
//        });
//
//    start.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
//                public void onTick(long millisUntilFinished) {
//                    timerText.setText(" Started Timer to play audio- seconds remaining: " + millisUntilFinished / 1000);
//
//                }
//
//                public void onFinish() {
//                    timerText.setText("Playing !");
//                    mediaPlayer.start();
//                }
//            }.start();
//        }
//    });
//    stop.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
//                public void onTick(long millisUntilFinished) {
//                    timerstopText.setText("Started Timer to stop audio-seconds remaining: " + millisUntilFinished / 1000);
//
//                }
//
//                public void onFinish() {
//                    timerText.setText("Stopped!");
//                    timerstopText.setText("");
//                    mediaPlayer.stop();
//                }
//            }.start();
//        }
//    });
//
//}
//    public void playSong() {
//        try {
//            if (mediaPlayer.isPlaying()) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = new MediaPlayer();
//            }
//
//            AssetFileDescriptor descriptor = getAssets().openFd("song1.mp3");
//            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
//            descriptor.close();
//
//            mediaPlayer.prepare();
//            mediaPlayer.setVolume(1f, 1f);
//            mediaPlayer.setLooping(true);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//private Runnable updateSongTime=new Runnable() {
//    @Override
//    public void run() {
//        startTime=mediaPlayer.getCurrentPosition();
//        tx1.setText(String.format(" %d min, %d sec",
//                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                        TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))
//        ));
//        seekBar.setProgress((int)startTime);
//        seekHandler.postDelayed(this,100);
//    }
//};
//
//}