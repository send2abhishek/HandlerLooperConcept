package com.attra.handlerlooperconcept;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;
    public static final String TAG="Demo";
    MyNewThread thread;

    private String[] songs={"Song1,Song2,Song3,Song4,Song5"};

    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            String data=intent.getStringExtra("action_KEY");
            Toast.makeText(MainActivity.this,""+data,Toast.LENGTH_LONG).show();
            displayProgressBar(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.activity_main_text);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
//        handler=new Handler(getMainLooper()){
//
//            @Override
//            public void handleMessage(Message msg) {
//
//                String data=msg.getData().getString("MyMsg");
//                Toast.makeText(MainActivity.this,""+data,Toast.LENGTH_LONG).show();
//                Log.d(TAG,""+data);
//                displayProgressBar(false);
//            }
//        };

        thread=new MyNewThread();
        thread.setName("Worker Thread");
        thread.start();

        IntentFilter filter=new IntentFilter("Com.EXMAPLE.THREAD.ACTION");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);
    }

    public void runCode(View view) {

        displayProgressBar(true);
        Log.d(TAG,"Code About To Run...");

        for (String values:songs) {


            Message msg=Message.obtain();
            msg.obj=values;
            thread.handler.sendMessage(msg);


        }


    }


    public void displayProgressBar(boolean display) {
        if (display) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
