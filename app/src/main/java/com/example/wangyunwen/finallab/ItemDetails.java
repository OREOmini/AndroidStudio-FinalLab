package com.example.wangyunwen.finallab;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ItemDetails extends AppCompatActivity {
    TextView timer, content;
    int year, month, day, hour, minute, second;
    String con;
    long leftTime;
    private MyTimer myTimer;

    public void putTest() {
        year = 2017;
        month = 12;
        day = 6;
        hour = 23;
        minute = 30;
        second = 60;
        con = "homework";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        putTest();

        timer = (TextView) findViewById(R.id.timer);
        content = (TextView) findViewById(R.id.content);
        content.setText(con);

        try {
            leftTime = calculateRemainTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myTimer = new MyTimer(leftTime, 1000);
        Log.i("------", leftTime+"");
        myTimer.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTimer.cancel();
    }

    private long calculateRemainTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Log.i("expDate", day+"");
        String date = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        Date curDate = new Date(System.currentTimeMillis());
        Date expDate = dateFormat.parse(date);
        Log.i("expDate", expDate.toString());
        Log.i("curDate", curDate.toString());

        Long diff = expDate.getTime() - curDate.getTime();

        return diff;
    }

    public class MyTimer extends CountDownTimer {
        int year, month, day, hour, minute, second;
        CountDownTimer countDownTimer;

        public void setExpDate(Date date) {
            try {
                leftTime = calculateRemainTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timer.setText("done");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //Log.i("MainActivity", millisUntilFinished + "");

            leftTime -= 1000;

            String formatType = "HH时mm分ss秒";
            Date tempdate = new Date(millisUntilFinished);
            SimpleDateFormat format = new SimpleDateFormat(formatType);
            format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            String time = (tempdate.getYear()-70) + "年" + (tempdate.getMonth()) + "月" +
                    (tempdate.getDay()-4) + "日"+format.format(millisUntilFinished);
            //Log.i("----", tempdate.toString());

            //Log.i("------", tempdate.getYear()+" "+tempdate.getMonth()+" "+tempdate.getDay());
            //String time = new SimpleDateFormat(formatType).format(date);
            //String time = (tempdate.getYear()- 70) + "年" + (tempdate.getMonth()) + "月" +
                    //(tempdate.getDay()-4) + "日"+ (tempdate.getHours() - 8) + new SimpleDateFormat(formatType).format(tempdate);
//            int y, m, d, h, mi, s;
//            y = (int ) (millisUntilFinished / (31536000*1000));
//            m = (int)

//            if(second-1 >= 0) {
//                second--;
//            } else {
//                second = 59;
//                if(minute-1 >= 0) {
//                    minute--;
//                } else {
//                    minute = 59;
//                    if(hour-1 >= 0) {
//                        hour--;
//                    } else {
//                        hour = 23;
//                        if(day-1 > 0) {
//                            day--;
//                        } else {
//                            day = 30;
//                            if (month-1 > 0) {
//                                month--;
//                            } else {
//                                month = 12;
//                                year--;
//                            }
//                        }
//                    }
//                }
//            }
//
//            String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

            timer.setText(time);
        }


    }

}
