package com.example.centos.ircbot;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class hello_bot extends AppCompatActivity {
    private static final int SERVERPORT = 6667;
    private static final String SERVER_IP = "chat.freenode.com";
    public static String EXTRA_MESSAGE = "172.22.26.226";
    public  String json_data="";
    boolean d;Button b;
    EditText server_name,host_name,port,nick,real_name,field;
    void toast(String data)
    {
        Toast.makeText(hello_bot.this, data,
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_bot);

    }

    public void set_remote_host(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Remote Host IP");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText(EXTRA_MESSAGE);
        input.setSelectAllOnFocus(true);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EXTRA_MESSAGE = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    class Task implements Runnable {

        //@Override
        String json_data;
        Task(String json_data)
        {
            this.json_data=json_data;
        }
        public void run() {
            try {
                Socket soc = new Socket(EXTRA_MESSAGE, 2004);
                String e=json_data.toString();
                //Toast.makeText(hello_bot.this, e,
                        //Toast.LENGTH_LONG).show();
                DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
                dout.writeUTF(json_data);
                dout.flush();
                dout.close();
                soc.close();
            } catch (Exception e) {

                Toast.makeText(hello_bot.this, e.toString(),
                        Toast.LENGTH_LONG).show();
            }

        }
    }
    /*-------------run a command-------------------------*/
    private boolean executeCommand(){
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 "+EXTRA_MESSAGE+"");
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue "+mExitValue);
            if(mExitValue==0){
                return true;
            }else{
                return false;
            }
        }
        catch (InterruptedException ignore)
        {
            ignore.printStackTrace();
            System.out.println(" Exception:"+ignore);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(" Exception:"+e);
        }
        return false;
    }


    public void start_bot(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Set title for AlertDialog
        builder.setTitle("Server Information");

        //Set body message of Dialog
        server_name=(EditText)findViewById(R.id.editText);
        host_name=(EditText)findViewById(R.id.editText2);
        port=(EditText)findViewById(R.id.editText3);
        nick=(EditText)findViewById(R.id.editText4);
        real_name=(EditText)findViewById(R.id.editText5);
        field=(EditText)findViewById(R.id.editText6);
        json_data="Server Name:"+server_name.getText()+"\nHostname: "+host_name.getText()+"\nPort: "+port.getText()+"\nNick: "+nick.getText()+"\nReal Name: "+real_name.getText()+"\n";
        builder.setMessage(json_data);

        //// Is dismiss when touching outside?

        builder.setCancelable(true);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(hello_bot.this, "Positive Button clicked!", Toast.LENGTH_SHORT).show();
                        //----------------------------------------------------------------------
                        d=executeCommand();
                        if (d)
                        {

                            /*Toast.makeText(hello_bot.this, "Connected",
                                    Toast.LENGTH_LONG).show();*/
                            try {
                                new Thread(new Task(json_data)).start();
                                field.append("\n"+json_data);
                                field.append(Html.fromHtml("<font color='green'>Connected</font>"));
                            }
                            catch (Exception e)
                            {
                                toast("Connection Failed");
                                field.append("\n"+json_data);
                                field.append(Html.fromHtml("<font color='red'>Service Stopped</font>"));
                            }

                            /*Intent i=new Intent(hello_bot.this,web.class);
                            i.putExtra(EXTRA_MESSAGE, "http://34.214.108.17");
                            startActivity(i);*/
                        }
                        else {
                            Toast.makeText(hello_bot.this, "Server Down",
                                    Toast.LENGTH_LONG).show();
                        }
                        //----------------------------------------------------------------------
                    }
                });

        //Negative Button
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Usually, negative use to close Dialog
                        //So, do nothing here, just dismiss it
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}