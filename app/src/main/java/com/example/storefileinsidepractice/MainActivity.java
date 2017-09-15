package com.example.storefileinsidepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private final static String FileName = "students";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_write = (Button)findViewById(R.id.button_write);
        Button button_read = (Button)findViewById(R.id.button_read);

        button_write.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OutputStream outputStream = null;

                        try {
                            FileOutputStream fileOutputStream = openFileOutput(FileName,MODE_PRIVATE);
                            outputStream = new BufferedOutputStream(fileOutputStream);
                            String infor = "Name:ZhangSan   NO:001";
                            try {
                                outputStream.write(infor.getBytes(StandardCharsets.UTF_8));
                            }
                            finally {
                                if(outputStream != null){
                                    outputStream.close();
                                }
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        button_read.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InputStream inputStream = null;
                        try{
                            FileInputStream fileInputStream = openFileInput(FileName);
                            inputStream = new BufferedInputStream(fileInputStream);

                            int index;
                            StringBuilder stringBuilder = new StringBuilder("");
                            try{
                                while ((index=inputStream.read()) != -1){
                                    stringBuilder.append((char)index);
                                }
                                Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                            }
                            finally {
                                if(inputStream != null){
                                    inputStream.close();
                                }
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();

                        }
                    }
                }
        );
    }
}
