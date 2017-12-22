package com.projectquiz;

/**
 * Created by omani on 12/22/2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Environment;
import android.content.Context;
import android.widget.Toast;

import com.projectquiz.Model.Mcqs;

public class CreateQuiz extends Activity {
    EditText quest, optiona, optionb, optionc, optiond, correct;
    Button add, finish;
    TextView setque;
    int index = 0;
    String q="",A="",B="",C="",D="",Correct="";
    Mcqs[] m1 = new Mcqs[10];
    String  data="omani";
    final static String TAG =CreateQuiz.class.getName();
    final static String fileName = "data1";
    BufferedReader br = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quiz);

        add = (Button) findViewById(R.id.Add);
        finish = (Button) findViewById(R.id.Finish);
        quest = (EditText) findViewById(R.id.editText);
        optiona = (EditText) findViewById(R.id.editText2);
        optionb = (EditText) findViewById(R.id.editText3);
        optionc = (EditText) findViewById(R.id.editText4);
        optiond = (EditText) findViewById(R.id.editText5);
        correct = (EditText) findViewById(R.id.editText6);

        for ( int i=0; i<10; i++) {
            m1[i]=new Mcqs();
        }
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                q=quest.getText().toString().trim();
                A=optiona.getText().toString().trim();
                B=optionb.getText().toString().trim();
                C=optionc.getText().toString().trim();
                D=optiond.getText().toString().trim();
                Correct=correct.getText().toString().trim();
                m1[index].SetMcqs(q,A,B,C,D,Correct);
                index++;
                quest.setText("");
                optiona.setText("");
                optionb.setText("");
                optionc.setText("");
                optiond.setText("");
                correct.setText("");
            }
        });
        finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                q=quest.getText().toString();
                A=optiona.getText().toString();
                B=optionb.getText().toString();
                C=optionc.getText().toString();
                D=optiond.getText().toString();
                Correct=correct.getText().toString();
                m1[index].SetMcqs(q,A,B,C,D,Correct);
                index++;
                writeobject();
                Intent intent=new Intent(CreateQuiz.this,Main.class);
                startActivity(intent);
                finish();

            }
        });
    }
    void  writeobject()
    {
        FileOutputStream f=null;
        try {

            String fpath = "/sdcard/"+fileName+".txt";
          /*  File file = new File(fpath);
            File yourFile = new File(fpath);
            yourFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(yourFile,true);*/
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File(fpath)));

           // FileOutputStream f = new FileOutputStream(new File(fpath));
            //ObjectOutputStream o = new ObjectOutputStream(oFile);
            for ( int i=0; i<index; i++) {
                o.writeObject(m1[i]);
                //display in short period of time
                Toast.makeText(getApplicationContext(), "Your toast message.",
                        Toast.LENGTH_SHORT).show();
            }
            o.close();
           // f.close();

           // FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(fpath)));

            // Read objects
            Mcqs pr1 = (Mcqs) oi.readObject();
            //Mcqs pr2 = (Mcqs) oi.readObject();

            Toast.makeText(getApplicationContext(),pr1.toString(),
                    Toast.LENGTH_SHORT).show();

            oi.close();
          //  fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
