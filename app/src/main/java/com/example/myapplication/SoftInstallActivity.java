package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

//教程软件安装界面
public class SoftInstallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_install);
        //点击进入Microsoft网址
        TextView Microfsft = (TextView) findViewById(R.id.main6_edittext2);
        Microfsft.setMovementMethod(LinkMovementMethod.getInstance());
        //点击进入office网址
        TextView Office = (TextView) findViewById(R.id.main6_edittext3);
        Office.setMovementMethod(LinkMovementMethod.getInstance());
        //点击进入Arcgis网址
        TextView ArcGis = (TextView) findViewById(R.id.main6_edittext6);
        ArcGis.setMovementMethod(LinkMovementMethod.getInstance());
        //点击进入ENVI网址
        TextView ENVI = (TextView) findViewById(R.id.main6_edittext7);
        ENVI.setMovementMethod(LinkMovementMethod.getInstance());
        //点击进入Visual Studio网址
        TextView VisualStudio = (TextView) findViewById(R.id.main6_edittext10);
        //点击进入NYSQL网址
        VisualStudio.setMovementMethod(LinkMovementMethod.getInstance());
        TextView MYSQL = (TextView) findViewById(R.id.main6_edittext11);
        MYSQL.setMovementMethod(LinkMovementMethod.getInstance());
    }
}