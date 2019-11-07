package com.example.androidparticlestarter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.ParticleEvent;
import io.particle.android.sdk.cloud.ParticleEventHandler;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;


public class MainActivity extends AppCompatActivity {
    // MARK: Debug info
    private final String TAG="";

    // MARK: Particle Account Info
    private final String PARTICLE_USERNAME = "hmehta095@gmail.com";
    private final String PARTICLE_PASSWORD = "Himu@123";

    // MARK: Particle device-specific info
    private final String DEVICE_ID = "31002e000447363333343435";

    // MARK: Particle Publish / Subscribe variables
    private long subscriptionId;
    Button monitorButton;
    TextView txtView, showAmountOfM;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    String him0 = "3";
    String him = "6";
    String him1 = "10";
    String him2 = "13";
    String him3 = "16";
    String him4 = "20";
    Handler handler;

    int Seconds, Minutes, MilliSeconds ;

    ListView listView ;

    String[] ListElements = new String[] {  };

    List<String> ListElementsArrayList ;

    ArrayAdapter<String> adapter ;
    SeekBar seekBar;
    int progresss;

    // MARK: Particle device
    private ParticleDevice mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monitorButton= findViewById(R.id.timerButton);
        txtView = findViewById(R.id.showTime);
        seekBar = findViewById(R.id.seekBar);
        showAmountOfM = findViewById(R.id.showMAmount);


//        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                showAmountOfM.setText("Time Slow Down By: "+progress);
                seekBar.setMax(10);

                progresss = progress;

                him = String.valueOf(Integer.parseInt(him) -progress);
                him0 = String.valueOf(Integer.parseInt(him0) -progress);
                him1 = String.valueOf(Integer.parseInt(him1) -progress);
                him2 = String.valueOf(Integer.parseInt(him2) -progress);
                him3 = String.valueOf(Integer.parseInt(him3) -progress);
                him4 = String.valueOf(Integer.parseInt(him4) -progress);
                Log.d(TAG, "onProgressChanged:"+ him0);
                Log.d(TAG, "onProgressChanged:"+ him);
                Log.d(TAG, "onProgressChanged:"+ him1);
                Log.d(TAG, "onProgressChanged:"+ him2);
                Log.d(TAG, "onProgressChanged:"+ him3);

            }
        });

        handler = new Handler() ;

        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );

//        listView.setAdapter(adapter);

        monitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

//                reset.setEnabled(false);

            }




            public Runnable runnable = new Runnable() {

                public void run() {

                    MillisecondTime = SystemClock.uptimeMillis() - StartTime;

                    UpdateTime = TimeBuff + MillisecondTime;

                    Seconds = (int) (UpdateTime / 1000);

                    Minutes = Seconds / 60;

                    Seconds = Seconds % 60;

                    MilliSeconds = (int) (UpdateTime % 1000);


//                    txtView.setText("" + Minutes + ":"
//                            + String.format("%02d", Seconds) + ":"
//                            + String.format("%03d", MilliSeconds));
                    txtView.setText(String.format("%02d", Seconds));



                    try {


                        if (String.valueOf(Seconds).equals(him0)){
                            happy0();
                        }
                        else if (String.valueOf(Seconds).equals(him)){
                            happy();
                        }
                        else if (String.valueOf(Seconds).equals(him1)){
                            happy1();
                        }
                        else if (String.valueOf(Seconds).equals(him2)){
                            happy2();
                        }
                        else if (String.valueOf(Seconds).equals(him3)){
                            happy3();
                        }
                        else if (String.valueOf(Seconds).equals(him4)){
                            Angry();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
                    }





//

                    handler.postDelayed(this, 0);
                }


            };







        });

//        if (txtView.equals(him)){
//            happy0();
//        }


        // 1. Initialize your connection to the Particle API
        ParticleCloudSDK.init(this.getApplicationContext());

        // 2. Setup your device variable
        getDeviceFromCloud();

    }






    /**
     * Custom function to connect to the Particle Cloud and get the device
     */
    public void getDeviceFromCloud() {
        // This function runs in the background
        // It tries to connect to the Particle Cloud and get your device
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {

            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn(PARTICLE_USERNAME, PARTICLE_PASSWORD);
                mDevice = particleCloud.getDevice(DEVICE_ID);
                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Successfully got device from Cloud");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }


    public void happy0(){
//        if(Answer.equals(him)){
//
//        }
//            Log.d(TAG, "happy0:");
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
//                functionParameters.add();
                try {
                    mDevice.callFunction("hppy0", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }



    public void Angry(){
//        if(Answer.equals(him)){
//
//        }
//            Log.d(TAG, "happy0:");
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
//                functionParameters.add();
                try {
                    mDevice.callFunction("Angry", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void happy(){
//        if(Answer.equals(him)){
//
//        }
//            Log.d(TAG, "happy0:");
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
//                functionParameters.add();
                try {
                    mDevice.callFunction("hppy", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }


    public void happy1(){
//        if(Answer.equals(him)){
//
//        }
//            Log.d(TAG, "happy0:");
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
//                functionParameters.add();
                try {
                    mDevice.callFunction("hppy1", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }


    public void happy2(){
//        if(Answer.equals(him)){
//
//        }
//            Log.d(TAG, "happy0:");
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
//                functionParameters.add();
                try {
                    mDevice.callFunction("hppy2", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }


    public void happy3(){
//        if(Answer.equals(him)){
//
//        }
//            Log.d(TAG, "happy0:");
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
//                functionParameters.add();
                try {
                    mDevice.callFunction("hppy3", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }









}
