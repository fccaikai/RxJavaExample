package com.kcode.rxjavaexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kcode.rxjavaexample.part1.TheBasics;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final static String[] names = {"张三", "李四", "王五", "田七"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void theBasics(View view) {
        Intent intent = new Intent(this, TheBasics.class);
        startActivity(intent);
    }

}
