package com.example.myapplication.util;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtil {
    public static CountDownTimer sendEmailCodeTimer(TextView tvSendEmail) {
        tvSendEmail.setEnabled(false);
        return new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String value = String.valueOf((int) (millisUntilFinished / 1000));
                tvSendEmail.setText(value);
            }

            @Override
            public void onFinish() {
                tvSendEmail.setText("发送验证码");
                tvSendEmail.setEnabled(true);
            }
        };
    }
}
