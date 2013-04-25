package com.example.view;

import java.util.ArrayList;

import com.simonsays.controller.SimonSaysController;
import com.simonsays.model.*;
import com.example.memorygame.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements ISimonSaysObserver {

	SimonSaysController sscgame = new SimonSaysController();
	Player play;
	int numberofobjects = 0;
	int objectsize = 0;
	boolean gridlayout = true;
	
	GridView gameview;
	ImageAdapter iagame;
	Handler handler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        loginSetup();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
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
    			//SimonSaysController sscgame checks for username, and denies access until a reasonable name is given.
    			if(sscgame.login(input))
    			{
    				optionsSetup();
    				final TextView tvusername = (TextView) findViewById(R.id.optionsusernametext);
    				tvusername.setText(input);
    			}
    			else
    			{
    				tverrormessage.setText("Username does not exist");
    			}
    		}
    	});
    	
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
    			
    			play = sscgame.createUser(usernameinput);
    			if(play != null)
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
		tvusername.setText(play.getName());
		
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
            	play = null;
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
    	
    	sscgame.play(play, alshapes, alcolors, numberofobjects, this);
    	
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
    	
    	iagame.prepare();
    	gameview.setAdapter(iagame);
    	
    	final TextView tvgameviewdebug = (TextView) findViewById(R.id.gameviewtextview);
    	tvgameviewdebug.setText("Sequence: " + sscgame.getGame().getComputerSequence().toString());
    	gameview.setOnItemClickListener(new OnItemClickListener() 
    	{
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {            	
            	sscgame.compareSequence(position);
            	tvgameviewdebug.setText("Sequence: " + sscgame.getGame().getComputerSequence().toString());
            	settingsAndPlaceTaskStart();
        	}
        });
    }

    public void showSequence(ArrayList<Integer> sequence)
    {
//    	for(int i = 0; i < sequence.size(); i++)
//    	{
//    		iagame.greyShape(sequence.get(i));
//    		try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		iagame.revertShape(sequence.get(i));
//    	}
    }
    
//    http://stackoverflow.com/questions/9505109/update-current-activity-view-behind-while-showing-loading-dialog
    private void settingsAndPlaceTaskStart() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
            	for(int i = 0; i < sscgame.getGame().getComputerSequence().size(); i++)
            	{
            		iagame.greyShape(sscgame.getGame().getComputerSequence().get(i));
            		for(int j = 0; j < 100000000; j++)
            		{
            			
            		}
            		iagame.revertShape(sscgame.getGame().getComputerSequence().get(i));
            	}
				return null;
            }

            protected void onProgressUpdate(Void... param) {
                //Prepare message
            }
        }.execute();
    }

	public void update(SimonSays ss) {
		if(ss.getIsActive())
		{
			// Display end of round message and wait for user
			// Show sequence
		}
		else
		{
			// Store score
			sscgame.storeScore(ss.getScore());
			
			// Display score
			
			// Exit game
			//setContentView(R.layout.optionsui);
			optionsSetup();
		}
	}
    
}