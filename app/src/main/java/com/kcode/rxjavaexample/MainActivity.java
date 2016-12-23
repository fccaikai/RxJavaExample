package com.kcode.rxjavaexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final static String[] names = {"张三", "李四", "王五", "田七"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        first();
        try {
            map();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void first() {

        Observable.from(names)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);
                    }
                });
    }

    private void map() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("name", "张三");
        object.put("age", 19);

        Observable.just(object.toString())
                .map(new Func1<String, List<User>>() {
                    @Override
                    public List<User> call(String s) {

                        List<User> users = new ArrayList<User>();

                        try {
                            JSONObject obj = new JSONObject(s);
                            User user = new User();
                            user.setAge(obj.optInt("age"));
                            user.setName(obj.optString("name"));
                            users.add(user);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return users;
                    }
                })
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        for (User user : users) {
                            Log.d(TAG, user.toString());
                        }
                    }
                });
    }
}
