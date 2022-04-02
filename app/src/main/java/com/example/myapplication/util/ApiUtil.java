package com.example.myapplication.util;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.MyApplication;
import com.example.myapplication.model.ResultModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtil {

    public static <T> void request(Call<T> call, ApiAction<T> action) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.body() != null)
                    if (response.body() instanceof ResultModel) {
                        if (((ResultModel) response.body()).getCode() == 200) {
                            action.onSuccess(response.body());
                        } else {
                            Toast.makeText(MyApplication.instance, ((ResultModel) response.body()).getMsg(), Toast.LENGTH_SHORT).show();
                            action.onFailed(new RuntimeException(((ResultModel) response.body()).getMsg()));
                        }
                    } else {
                        Toast.makeText(MyApplication.instance, response.message(), Toast.LENGTH_SHORT).show();
                        action.onFailed(new RuntimeException(response.message()));
                    }
                else {
                    try {
                        String msg = response.errorBody().string();
                        Toast.makeText(MyApplication.instance, msg, Toast.LENGTH_SHORT).show();
                        action.onFailed(new RuntimeException(msg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                action.onFailed(t);
            }
        });
    }

}
