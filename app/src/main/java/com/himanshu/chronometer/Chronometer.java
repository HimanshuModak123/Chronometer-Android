package com.himanshu.chronometer;

import android.content.Context;

import java.util.Locale;

/**
 * Created by Himanshu on 6/13/2017.
 */

public class Chronometer implements Runnable {

    public static final long MILLIS_TO_MINUTES= 60000;
    public static final long MILLIS_TO_HOURES= 3600000;

    private Context mContext;
    private long mStartTime;
    private boolean mIsRunning;

    public Chronometer(Context context) {
        mContext = context;
    }

    public void start() {
        mStartTime= System.currentTimeMillis();
        mIsRunning=true;
    }

    public void stop() {

        mIsRunning = false;

    }

    @Override
    public void run() {
        while(mIsRunning) {
            long since= System.currentTimeMillis() - mStartTime;
            int seconds = (int) ((since/1000)%60);
            int minutes = (int) ((since/MILLIS_TO_MINUTES)%60);
            int hours = (int) ((since/MILLIS_TO_HOURES));
            int millis= (int) (since%1000);

            ((MainActivity) mContext).updateTimerText(String.format(Locale.ENGLISH,"%02d:%02d:%02d:%03d"
                    , hours, minutes, seconds, millis));

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
