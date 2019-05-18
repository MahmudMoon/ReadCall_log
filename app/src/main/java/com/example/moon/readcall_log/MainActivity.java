  package com.example.moon.readcall_log;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

  public class MainActivity extends AppCompatActivity {

      private static final String TAG = "MyTag";
      StringBuilder sbfr = new StringBuilder();
      ArrayList<Calls_data> arrayList = new ArrayList<>();

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getCallList();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();



    }

      private void getCallList() {
          if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_CALL_LOG)!=
                  PackageManager.PERMISSION_GRANTED){
              ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},101);
          }else {
              String log_path = "content://call_log/calls";
              Cursor managedCursor = getContentResolver().query(Uri.parse(log_path), null, null, null, null);
              managedCursor.moveToFirst();

              String test = "";
              do{
                  Log.i(TAG, "getCallList: "+managedCursor.getString(0));
                  Date callDayTime = new Date(Long.valueOf(managedCursor.getString(0)));
                  String duration = managedCursor.getString(10);
                  String callType = managedCursor.getString(7);
                  String number  = managedCursor.getString(13);
                  int dircode = Integer.parseInt(callType);
                  String dir = "";
                  switch (dircode) {
                      case CallLog.Calls.OUTGOING_TYPE:
                          dir = "OUTGOING";
                          break;

                      case CallLog.Calls.INCOMING_TYPE:
                          dir = "INCOMING";
                          break;

                      case CallLog.Calls.MISSED_TYPE:
                          dir = "MISSED";
                          break;
                  }
                  arrayList.add(new Calls_data(callDayTime,duration,number,dir));

              }while (managedCursor.moveToNext());

              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),arrayList);
                      ((ListView)findViewById(R.id.list)).setAdapter(customAdapter);
                  }
              });

              managedCursor.close();
          }
      }



}
