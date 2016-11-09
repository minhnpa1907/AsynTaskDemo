package com.minhnpa.asyntaskdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SlowTask.SlowTaskCallBack {
    EditText editText;
    Button btnSlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSlow = (Button) findViewById(R.id.btnSlow);
        btnSlow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSlow) {
            SlowTask backgroundTask = new SlowTask(this);
            backgroundTask.setSlowTaskCallBack(this);
            backgroundTask.execute("MMMMMM");
        }
    }

    @Override
    public void onSlowTaskCompleted(String s) {
        editText.setText("Result: " + s);
    }
}
