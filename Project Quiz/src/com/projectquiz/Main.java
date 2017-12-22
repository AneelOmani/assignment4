package com.projectquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	Button javaQuiz,CQuiz;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        javaQuiz=(Button)findViewById(R.id.button1);
        CQuiz=(Button)findViewById(R.id.button2);
        javaQuiz.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View v) {
	        Intent intent=new Intent(Main.this,Java_Quiz.class);
	        startActivity(intent);
	        finish();
			}
		});
       CQuiz.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View v) {
	        Intent intent=new Intent(Main.this,C_Quiz.class);
	        startActivity(intent);
	        finish();
			}
		});
    }
}