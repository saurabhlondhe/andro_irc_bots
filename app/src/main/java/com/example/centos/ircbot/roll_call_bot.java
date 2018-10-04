package com.example.centos.ircbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class roll_call_bot extends AppCompatActivity {
    EditText server_name,host_name,port,nick,real_name,field;
    Button btn;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call_bot);
        server_name=(EditText)findViewById(R.id.editText);
        host_name=(EditText)findViewById(R.id.editText2);
        port=(EditText)findViewById(R.id.editText3);
        nick=(EditText)findViewById(R.id.editText4);
        real_name=(EditText)findViewById(R.id.editText5);
        field=(EditText)findViewById(R.id.editText6);
        btn=(Button)findViewById(R.id.b1);


    }
    private void hide_things()
    {
        if (flag) {
            server_name.setVisibility(View.GONE);
            host_name.setVisibility(View.GONE);
            port.setVisibility(View.GONE);
            nick.setVisibility(View.GONE);
            real_name.setVisibility(View.GONE);
            TextView s = (TextView) findViewById(R.id.textView);
            s.setVisibility(View.GONE);
            s = (TextView) findViewById(R.id.textView);
            s.setVisibility(View.GONE);
            s = (TextView) findViewById(R.id.textView2);
            s.setVisibility(View.GONE);
            s = (TextView) findViewById(R.id.textView3);
            s.setVisibility(View.GONE);
            s = (TextView) findViewById(R.id.textView4);
            s.setVisibility(View.GONE);
            s = (TextView) findViewById(R.id.textView5);
            s.setVisibility(View.GONE);
            getSupportActionBar().hide();
        }
        else {
            server_name.setVisibility(View.VISIBLE);
            host_name.setVisibility(View.VISIBLE);
            port.setVisibility(View.VISIBLE);
            nick.setVisibility(View.VISIBLE);
            real_name.setVisibility(View.VISIBLE);
            TextView s = (TextView) findViewById(R.id.textView);
            s.setVisibility(View.VISIBLE);
            s = (TextView) findViewById(R.id.textView);
            s.setVisibility(View.VISIBLE);
            s = (TextView) findViewById(R.id.textView2);
            s.setVisibility(View.VISIBLE);
            s = (TextView) findViewById(R.id.textView3);
            s.setVisibility(View.VISIBLE);
            s = (TextView) findViewById(R.id.textView4);
            s.setVisibility(View.VISIBLE);
            s = (TextView) findViewById(R.id.textView5);
            s.setVisibility(View.VISIBLE);
            getSupportActionBar().show();
        }
    }
    public void start_roll_call_bot(View view) {
        if (flag)
        {
            hide_things();
            btn.setText("Stop Bot");
            //new Thread(new Task()).start();
            flag=false;
        }
        else
            {
                hide_things();
                btn.setText("Start Bot");
                flag=true;
            }


    }

    class Task implements Runnable {


        public void run() {

        }

    }


}
