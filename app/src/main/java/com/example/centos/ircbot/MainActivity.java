package com.example.centos.ircbot;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String a="msg";
    public static final String EXTRA_MESSAGE = "https://saurabhlondhe.github.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hello_bot(View view) {
        Intent i=new Intent(this,hello_bot.class);
        startActivity(i);
    }

    public void roll_call_bot(View view) {
        Intent i=new Intent(this,roll_call_bot.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.settings){
            // do something
        }
        return super.onOptionsItemSelected(item);
    }

    public void go_to_settings(MenuItem item) {
        Intent i_=new Intent(this,settings.class);
        //i_.putExtra(EXTRA_MESSAGE,"https://saurabhlondhe.github.io");
        startActivity(i_);
    }
}
