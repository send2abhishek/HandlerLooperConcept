package com.attra.handlerlooperconcept;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import static com.attra.handlerlooperconcept.MainActivity.TAG;

public class MyHandler extends Handler {

    @Override
    public void handleMessage(Message msg) {


        String music=msg.obj.toString();
        Log.d(TAG,"Song came  ..."+ music);
        DownloadSong(music);

    }

    private void DownloadSong(String music){

        try {
            Log.d(TAG,"Thread Name- "+ Thread.currentThread().getName());
            Thread.sleep(4000);

//            Message message=new Message();
////            Bundle bundle=new Bundle();
////            bundle.putString("MyMsg","Song is downloading -"+ music);
////            message.setData(bundle);
////            handler.sendMessage(message);

            Log.d(TAG,"Song Downloaded ..."+ music);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
