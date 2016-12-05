package com.example.wangyunwen.finallab;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;

public class ItemDetails extends AppCompatActivity {
    TextView timer, content;
    int year, month, day, hour, minute, second;
    private MyTimer myTimer;

    public void putTest() {
        year = 0;
        month = 2;
        day = 3;
        hour = 13;
        minute = 32;
        second = 60;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        putTest();

        timer = (TextView) findViewById(R.id.timer);
        long diff = 0;
        try {
            diff = calculateRemainTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myTimer = new MyTimer(diff, 1000);
        myTimer.start();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private long calculateRemainTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        Date curDate = new Date(System.currentTimeMillis());
        Date expDate = dateFormat.parse(date);
        long diff = expDate.getTime() - curDate.getTime();
        return diff;
    }

    public class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timer.setText("done");
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void onTick(long millisUntilFinished) {
            //Log.i("MainActivity", millisUntilFinished + "");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(second-1 >= 0) {
                second--;
            } else {
                second = 59;
                if(minute-1 >= 0) {
                    minute--;
                } else {
                    minute = 59;
                    if(hour-1 >= 0) {
                        hour--;
                    } else {
                        hour = 23;
                        if(day-1 > 0) {
                            day--;
                        } else {
                            day = 30;
                            if (month-1 > 0) {
                                month--;
                            } else {
                                month = 12;
                                year--;
                            }
                        }
                    }
                }
            }

            String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

            timer.setText(time);
        }
    }

}
