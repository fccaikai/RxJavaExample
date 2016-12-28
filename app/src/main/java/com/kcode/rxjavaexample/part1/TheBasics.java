package com.kcode.rxjavaexample.part1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.kcode.rxjavaexample.R;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by caik on 2016/12/23.
 */

public class TheBasics extends AppCompatActivity {

    private static final String TAG = "TheBasics";
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observablesimple);
        mTextView = (TextView) findViewById(R.id.textView);
        ObservableCreate();
    }

    private void ObservableCreate() {

        //创建一个被观察者
        Observable<String> observable = Observable.create(subscriber -> {
            subscriber.onNext("Hello World");
            subscriber.onCompleted();
        });

        //创建一个观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
                mTextView.setText(s);
            }
        };

        //建立订阅关系
        observable.subscribe(observer);
    }

    private void just() {
        Observable.just("Hello World")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mTextView.setText(s);
                    }
                });
    }

    private void action() {
        Observable.just("Hello World")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mTextView.setText(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //onError
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        //onCompleted
                    }
                });
    }

    private void map() {
        Observable.just("Hello World")
                .map(s -> { return s + "--map";})
                .subscribe(result -> mTextView.setText(result));
    }

    private void range() {
        Observable.range(0,10)
                .subscribe(integer -> Log.d("RxJava","" + integer),
                        throwable -> Log.d("RxJava",throwable.toString()));
    }

}
