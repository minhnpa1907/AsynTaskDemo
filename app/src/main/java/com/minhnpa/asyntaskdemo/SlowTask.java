package com.minhnpa.asyntaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class SlowTask extends AsyncTask<String, Long, String> {
    ProgressDialog dialog;
    SlowTaskCallBack slowTaskCallBack;

    public SlowTask(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("Slow", "Slow onPreExecute");
        dialog.setMessage("Loading... 0%");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        Log.i("Slow", "Slow doInBackground " + params[0]);
        for (int i = 1; i <= 10; i++) {
            try {
                publishProgress((long) i * 10);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(System.currentTimeMillis());
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        dialog.setMessage("Loading... " + values[0] + "%");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        Log.i("Slow", "Result: " + s);
        slowTaskCallBack.onSlowTaskCompleted(s);
    }

    public void setSlowTaskCallBack(SlowTaskCallBack slowTaskCallBack) {
        this.slowTaskCallBack = slowTaskCallBack;
    }

    interface SlowTaskCallBack {
        void onSlowTaskCompleted(String s);
    }
}
