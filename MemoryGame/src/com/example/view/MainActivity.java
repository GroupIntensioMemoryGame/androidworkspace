package com.example.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.simonsays.controller.SimonSaysController;
import com.simonsays.model.*;
import com.example.memorygame.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements ISimonSaysObserver {

	SimonSaysController sscgame = new SimonSaysController();
	Player pcurrentplayer;
	
	int numberofobjects = 0;
	int objectsize = 0;
	int countn = 0;
	int presscount = 0;
	int sequenceSize = 1;
	int duration = 0;
	boolean gridlayout = true;
	
	GridView gameview;
	ImageAdapter iagame;
	Timer timer;
	TimerTask timertask = new TimerTask() 
	{
		@Override
		public void run()
		{
//			setTimeText("Time: " + duration);
			duration++;
		}
	};
	
	//This helps animate each shape in the sequence
	//countn increments up for the size of the sequence, starting from 0, and animates that number in the sequence
	//The caller of AnimatorRunnable sets countn back to 0 once it reaches the end of the sequence
	final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(final Message msg)
		{
			super.handleMessage(msg);
    		AnimationDrawable ad1 = (AnimationDrawable) gameview.getChildAt(sscgame.getGame().getComputerSequence().get(countn)).getBackground();
    		countn++;
        	ad1.stop();
        	ad1.start();
        	gameview.refreshDrawableState();
		}
	};
	
	//This runs the handler, which animates one of the shapes in the sequence
	private class AnimatorRunnable implements Runnable
	{
		@Override
		public void run() 
		{
			handler.sendMessage(new Message());
		}
	};
	
	//Sets up the Activity, calls loginSetup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSetup();
    }

    //Inflate the menu
    //This adds items to the action bar if they are present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //Sets the ui to loginui, and sets the values inside loginui to the appropriate value.
    public void loginSetup()
    {
        setContentView(R.layout.loginui);
        
    	//This is the TextView for errors
		final TextView tverrormessage = (TextView) findViewById(R.id.editTextErrorMessage);
		tverrormessage.setText("");
		
		//Login Button
    	final Button blogin = (Button) findViewById(R.id.loginbutton);
    	blogin.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			final TextView tvlogins = (TextView) findViewById(R.id.loginusertext);
    			String input = tvlogins.getText().toString();
    			
    			// Checks for a valid user name
    			pcurrentplayer = sscgame.login(input);
    			
    			//Play will equal null if the username is not allowed
    			if(pcurrentplayer != null)
    			{
    				optionsSetup();
    			}
    			else
    			{
    				tverrormessage.setText("Username does not exist");
    			}
    		}
    	});
    	
    	// High Score list
    	final TextView tvuser1 = (TextView) findViewById(R.id.highscoreuser1);
    	final TextView tvuser2 = (TextView) findViewById(R.id.highscoreuser2);
    	final TextView tvuser3 = (TextView) findViewById(R.id.highscoreuser3);
    	final TextView tvuser4 = (TextView) findViewById(R.id.highscoreuser4);
    	final TextView tvuser5 = (TextView) findViewById(R.id.highscoreuser5);
    	final TextView tvsequences1 = (TextView) findViewById(R.id.highscoresequences1);
    	final TextView tvsequences2 = (TextView) findViewById(R.id.highscoresequences2);
    	final TextView tvsequences3 = (TextView) findViewById(R.id.highscoresequences3);
    	final TextView tvsequences4 = (TextView) findViewById(R.id.highscoresequences4);
    	final TextView tvsequences5 = (TextView) findViewById(R.id.highscoresequences5);
    	final TextView tvtime1 = (TextView) findViewById(R.id.highscoretime1);
    	final TextView tvtime2 = (TextView) findViewById(R.id.highscoretime2);
    	final TextView tvtime3 = (TextView) findViewById(R.id.highscoretime3);
    	final TextView tvtime4 = (TextView) findViewById(R.id.highscoretime4);
    	final TextView tvtime5 = (TextView) findViewById(R.id.highscoretime5);
    	final TextView tvobjects1 = (TextView) findViewById(R.id.highscoreobjects1);
    	final TextView tvobjects2 = (TextView) findViewById(R.id.highscoreobjects2);
    	final TextView tvobjects3 = (TextView) findViewById(R.id.highscoreobjects3);
    	final TextView tvobjects4 = (TextView) findViewById(R.id.highscoreobjects4);
    	final TextView tvobjects5 = (TextView) findViewById(R.id.highscoreobjects5);
    	Scanner highscores = new Scanner(sscgame.getHighScores());
    	tvuser1.setText(highscores.next());
    	tvsequences1.setText(highscores.next());
    	tvtime1.setText(highscores.next());
    	tvobjects1.setText(highscores.next());
    	tvuser2.setText(highscores.next());
    	tvsequences2.setText(highscores.next());
    	tvtime2.setText(highscores.next());
    	tvobjects2.setText(highscores.next());
    	tvuser3.setText(highscores.next());
    	tvsequences3.setText(highscores.next());
    	tvtime3.setText(highscores.next());
    	tvobjects3.setText(highscores.next());
    	tvuser4.setText(highscores.next());
    	tvsequences4.setText(highscores.next());
    	tvtime4.setText(highscores.next());
    	tvobjects4.setText(highscores.next());
    	tvuser5.setText(highscores.next());
    	tvsequences5.setText(highscores.next());
    	tvtime5.setText(highscores.next());
    	tvobjects5.setText(highscores.next());
    	highscores.close();
    	
    	//Create New User Button
    	final Button bcreateuser = (Button) findViewById(R.id.createnewuserbutton);
        bcreateuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	createnewuserSetup();
            }
        });
    }
    
    public void createnewuserSetup()
    {
        setContentView(R.layout.createuserui);
        
        //Create Button
        final Button bcreate = (Button) findViewById(R.id.createbutton);
        bcreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final TextView tvcreate = (TextView) findViewById(R.id.createnewusertext);
    			String usernameinput = tvcreate.getText().toString();
    			
    			pcurrentplayer = sscgame.createUser(usernameinput);
    			if(pcurrentplayer != null)
    			{
    				optionsSetup();
    			}
    			else
    			{
    				final TextView tvcreateuserfail = (TextView) findViewById(R.id.editTextCreateUserFail);
    				tvcreateuserfail.setText("Username must be unique and must contain fewer than twelve characters in length.");
    			}
            }
        });
        
        //Cancel Button
        final Button bcancel = (Button) findViewById(R.id.cancelbutton);
        bcancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	loginSetup();
            }
        });
    }
    
    public void optionsSetup()
    {
		setContentView(R.layout.optionsui);

		final TextView tvusername = (TextView) findViewById(R.id.optionsusernametext);
		tvusername.setText(pcurrentplayer.getName());
		
		final Spinner snumberofobjects = (Spinner) findViewById(R.id.numberofobjectsdropdown);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.number_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		snumberofobjects.setAdapter(adapter);
		
		snumberofobjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        numberofobjects = pos + 4;
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		final Spinner ssize = (Spinner) findViewById(R.id.objectsizespinner);
		ArrayAdapter<CharSequence> asize = ArrayAdapter.createFromResource(this,
		        R.array.size_array, android.R.layout.simple_spinner_item);
		asize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ssize.setAdapter(asize);

		ssize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        objectsize = pos;
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
        final Button blogout = (Button) findViewById(R.id.logoutbutton);
        blogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	pcurrentplayer = null;
            	loginSetup();
            }
        });
        
        final Button bplay = (Button) findViewById(R.id.playbutton);
        bplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playSetup();
            }
        });
        
        final RadioButton rbgridlayout = (RadioButton) findViewById(R.id.radiobuttongridlayout);
        rbgridlayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	gridlayout = true;
            }
        });
        
        final RadioButton rbdiamondlayout = (RadioButton) findViewById(R.id.radiobuttondiamondlayout);
        rbdiamondlayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	gridlayout = false;
            }
        });
    }
    
    public void playSetup()
    {
    	ArrayList<Boolean> bacolors = new ArrayList<Boolean>();
        final CheckBox cbred = (CheckBox) findViewById(R.id.checkboxredcolor);
        final CheckBox cbblue = (CheckBox) findViewById(R.id.checkboxbluecolor);
        final CheckBox cbgreen = (CheckBox) findViewById(R.id.checkboxgreencolor);
        final CheckBox cbpurple = (CheckBox) findViewById(R.id.checkboxpurplecolor);
        final CheckBox cborange = (CheckBox) findViewById(R.id.checkboxorangecolor);
        final CheckBox cbyellow = (CheckBox) findViewById(R.id.checkboxyellowcolor);
    	bacolors.add(cbred.isChecked());
    	bacolors.add(cbblue.isChecked());
    	bacolors.add(cbgreen.isChecked());
    	bacolors.add(cbpurple.isChecked());
    	bacolors.add(cborange.isChecked());
    	bacolors.add(cbyellow.isChecked());
    	ArrayList<Boolean> bashapes = new ArrayList<Boolean>();
        final CheckBox cbsquare = (CheckBox) findViewById(R.id.checkboxsquareshape);
        final CheckBox cbtriangle = (CheckBox) findViewById(R.id.checkboxtriangleshape);
        final CheckBox cbcircle = (CheckBox) findViewById(R.id.checkboxcircleshape);
    	bashapes.add(cbsquare.isChecked());
    	bashapes.add(cbtriangle.isChecked());
    	bashapes.add(cbcircle.isChecked());
    	
    	ArrayList<Integer> alcolors = new ArrayList<Integer>();
    	ArrayList<Integer> alshapes = new ArrayList<Integer>();
    	for(int i = 0; i < bacolors.size(); i++)
    	{
    		if(bacolors.get(i))
    		{
    			alcolors.add(i);
    		}
    	}
    	for(int i = 0; i < bashapes.size(); i++)
    	{
    		if(bashapes.get(i))
    		{
    			alshapes.add(i);
    		}
    	}
    	
    	if(alcolors.size() == 0 || alshapes.size() == 0)
    	{
    		return;
    	}
    	
    	setContentView(R.layout.gameui);
    	gameview = (GridView) findViewById(R.id.gamegridview);
    	iagame = new ImageAdapter(this);
    	
    	sscgame.play(pcurrentplayer, alshapes, alcolors, numberofobjects, this);
    	
    	int temp = 0;
    	if(gridlayout)
    	{
    		temp = (int)Math.ceil(Math.sqrt(numberofobjects));
    	}
    	else
    	{ 	
    		temp = (((int)Math.ceil(Math.sqrt(numberofobjects)))*2) - 1;
    	}
    	gameview.setNumColumns(temp);
    	
    	for(int i = 0; i < numberofobjects; i++)
    	{
    		iagame.addShape(sscgame.getGameObject(i));
    	}
    	gameview.setAdapter(iagame);
    	
		presscount = 0;
		sequenceSize = 1;
    	gameview.setOnItemClickListener(new OnItemClickListener() 
    	{
            @SuppressLint("NewApi")
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {
            	sscgame.compareSequence(position);
            	presscount++;
//            	AnimationDrawable frameAnimation = (AnimationDrawable)v.getBackground();
//            	frameAnimation.stop();
//            	frameAnimation.start();
            	if(presscount == sequenceSize)
            	{
            		showSequence(sscgame.getGame().getComputerSequence());
            		presscount = 0;
            		sequenceSize += 1;
            	}
        	}
        });
    	
		showSequence(sscgame.getGame().getComputerSequence());
		timer = new Timer();
		
		try
		{
			timer.scheduleAtFixedRate(timertask, 1000, 1000);
		}
		catch(Exception e)
		{
			
		}
    }
    
    public void setTimeText(String a)
    {
    	final TextView tv = (TextView) findViewById(R.id.gameviewtextview);
    	tv.setText("Time: " + duration);
    }

    public void showSequence(final ArrayList<Integer> sequence)
    {
		for(int i = 0; i < sequence.size(); i++)
		{
			handler.postDelayed(new AnimatorRunnable(), 1000 * (i+1));
		}
		countn = 0;
    }

	public void update(SimonSays ss) {
		if(ss.getIsActive())
		{
			// Display end of round message and wait for user
			// Show sequence
			//showSequence();
		}
		else
		{
			timer.cancel();
			timer = null;
			ss.getScore().setTime(duration);
			duration = 0;
			
			// Store score
			sscgame.storeScore(ss.getScore());
			
			// Display score
			
			// Exit game
			optionsSetup();
		}
	}
    
}