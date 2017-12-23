package com.projectquiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.projectquiz.Model.Mcqs;

public class Java_Quiz extends Activity {
	Button next,back,finish;
	RadioGroup rg;
	RadioButton rb1,rb2,rb3,rb4;
	TextView setque;
	int index=0;
	String MyCAns,UserCAns="";
	int totalCurrect,totalQues=6,totalSkeep,totalWrrong;
	final static String fileName = "data1";
	ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
	Mcqs[] m1 = new Mcqs[10];
	HashMap<String, String> map;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.java_quiz);
    
    back=(Button)findViewById(R.id.button1);
    next=(Button)findViewById(R.id.button2);
    finish=(Button)findViewById(R.id.button3);
    rg=(RadioGroup)findViewById(R.id.radioGroup1);
    rb1=(RadioButton)findViewById(R.id.radio0);
    rb2=(RadioButton)findViewById(R.id.radio1);
    rb3=(RadioButton)findViewById(R.id.radio2);
    rb4=(RadioButton)findViewById(R.id.radio3);
    rb1.setChecked(false);
    rb2.setChecked(false);
    rb3.setChecked(false);
    rb4.setChecked(false);

		for ( int i=0; i<10; i++) {
			m1[i]=new Mcqs();
		}

    setque=(TextView)findViewById(R.id.textView1);
    back.setVisibility(View.GONE);
    finish.setVisibility(View.GONE);
    set_Your_Ques();
    set_Ques_One();
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
	    RadioButton rb=(RadioButton)findViewById(checkedId)	;
	    UserCAns=rb.getText().toString().trim();
		}
	});
    
    back.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		if(UserCAns.equals("")){
   			totalSkeep++;
   		}else if(UserCAns.equals(MyCAns)){
   			totalCurrect++;
   		}else{
   			totalWrrong++;
   		}
   		UserCAns="";
   		rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        back();
    }
    });  
   
    next.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		
	back.setVisibility(View.VISIBLE);
    if(UserCAns.equals("")){
		totalSkeep++;
	}else if(UserCAns.equals(MyCAns)){
		totalCurrect++;
	}else{
		totalWrrong++;
	}
	UserCAns="";
	rb1.setChecked(false);
    rb2.setChecked(false);
    rb3.setChecked(false);
    rb4.setChecked(false);
   	next();
	}
    });
   
    finish.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
	Intent intent=new Intent(Java_Quiz.this,Finish_Quiz.class);
	intent.putExtra("totalquestion", "Total Ques : "+totalQues);
	intent.putExtra("totalcurrect", "Total Currect : "+totalCurrect);
	intent.putExtra("totalSkeep", "Total Skeep : "+totalSkeep);
	intent.putExtra("totalWrrong", "Total Wrrong : "+totalWrrong);
	startActivity(intent);
	finish();
		
	}
    });
}
    
    private void set_Ques_One() {
		map=MyArrList.get(index);
		setque.setText(map.get("Ques").toString().trim());
		rb1.setText(map.get("A1").toString().trim());
		rb2.setText(map.get("A2").toString().trim());
		rb3.setText(map.get("A3").toString().trim());
		rb4.setText(map.get("A4").toString().trim());
		MyCAns = map.get("CA1");
		//MyCAns=map.get("CA1").toString();
		//Toast.makeText(getApplicationContext(),"1"+index,2000).show();
	}
	public void next() {
	    if(index==5){
		back.setVisibility(View.GONE);
		next.setVisibility(View.GONE);
		finish.setVisibility(View.VISIBLE);
		}else{
		index++;
		//Toast.makeText(getApplicationContext(),""+index,2000).show();	
		map=MyArrList.get(index);
		setque.setText(map.get("Ques").toString().trim());
		rb1.setText(map.get("A1").toString().trim());
		rb2.setText(map.get("A2").toString().trim());
		rb3.setText(map.get("A3").toString().trim());
		rb4.setText(map.get("A4").toString().trim());
			MyCAns = map.get("CA1");
		}
		
	}
    public void back() {
    	if(index==0){
		back.setVisibility(View.GONE);
		finish.setVisibility(View.GONE);
    	}else{
    	index--;
    	map=MyArrList.get(index);
		setque.setText(map.get("Ques").toString().trim());
		rb1.setText(map.get("A1").toString().trim());
		rb2.setText(map.get("A2").toString().trim());
		rb3.setText(map.get("A3").toString().trim());
		rb4.setText(map.get("A4").toString().trim());
			MyCAns = map.get("CA1");
    	}
		
		
	}

    public void set_Your_Ques() {
		try
		{
		String fpath = "/sdcard/"+fileName+".txt";
		ObjectInputStream oi=null;
			try {
				oi = new ObjectInputStream(new FileInputStream(new File(fpath)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Read objects
			try {
				for(int i=0;i<10;i++) {
					m1[i] = (Mcqs) oi.readObject();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Mcqs pr2 = (Mcqs) oi.readObject();

		Toast.makeText(getApplicationContext(),m1[0].toString(),
				Toast.LENGTH_SHORT).show();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Q 1
    	map=new HashMap<String, String>();
		for(int i=0;i<m1.length;i++) {
			map.put("Ques", "Q1" + m1[i].getQuestion());
			map.put("A1", m1[i].getOptionA());
			map.put("A2", m1[i].getOptionB());
			map.put("A3", m1[i].getOptionC());
			map.put("A4", m1[i].getOptionD());
			map.put("CA1", m1[i].getCorrect());
			MyArrList.add(map);
			Toast.makeText(getApplicationContext(),m1[i].toString(),
					Toast.LENGTH_SHORT).show();
			map = new HashMap<String, String>();
		}
		//Q 2
    	/*
		map.put("Ques", "Q2. what is java2 ?");
		map.put("A1", "xyz1");
		map.put("A2", "xyz2");
		map.put("A3", "xyz3");
		map.put("A4", "xyz4");
		map.put("CA1","xyz2");
		MyArrList.add(map);
		//Q 1
    	map=new HashMap<String, String>();
		map.put("Ques", "Q3. what is java3 ?");
		map.put("A1", "mno1");
		map.put("A2", "mno2");
		map.put("A3", "mno3");
		map.put("A4", "mno4");
		map.put("CA1","mno4");
		MyArrList.add(map);
		//Q 1
    	map=new HashMap<String, String>();
		map.put("Ques", "Q4. what is java4 ?");
		map.put("A1", "opq1");
		map.put("A2", "opq2");
		map.put("A3", "opq3");
		map.put("A4", "opq4");
		map.put("CA1","opq1");
		MyArrList.add(map);
		//Q 1
    	map=new HashMap<String, String>();
		map.put("Ques", "Q5. what is java5 ?");
		map.put("A1", "www1");
		map.put("A2", "www2");
		map.put("A3", "www3");
		map.put("A4", "www4");
		map.put("CA1","www3");
		MyArrList.add(map);
		//Q 1
    	map=new HashMap<String, String>();
		map.put("Ques", "Q6. what is java6 ?");
		map.put("A1", "bbb1");
		map.put("A2", "bbb2");
		map.put("A3", "bbb3");
		map.put("A4", "bbb4");
		map.put("CA1","bbb1");
		MyArrList.add(map);*/
	}
    
}