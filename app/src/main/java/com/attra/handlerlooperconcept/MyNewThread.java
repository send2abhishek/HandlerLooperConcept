package com.attra.handlerlooperconcept;


import android.os.Looper;

import android.util.Log;
import static com.attra.handlerlooperconcept.MainActivity.TAG;


public class MyNewThread extends Thread {


    public MyHandler handler;



    @Override
    public void run() {


        Looper.prepare();
        handler=new MyHandler();
        Looper.loop();
        Log.d(TAG,"All download complete ...");


    }



}
