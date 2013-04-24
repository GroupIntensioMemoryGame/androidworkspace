package com.example.view;

import java.util.ArrayList;

import com.simonsays.controller.SimonSaysController;
import com.simonsays.model.*;
import com.example.memorygame.R;

import android.os.Bundle;
import android.app.Activity;
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
import android.widget.Toast;

public class MainActivity extends Activity {

	SimonSaysController sscgame = new SimonSaysController();
	Player play;
	int numberofobjects = 0;
	int objectsize = 0;
	boolean gridlayout = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    	final Button blogin = (Button) findViewById(R.id.loginbutton);
    	blogin.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			// get text from login
    			// create player from login string
    			final TextView tvlogins = (TextView) findViewById(R.id.loginusertext);
    			String input = tvlogins.getText().toString();
    			play = new Player(input);
    			optionsSetup();
    			final TextView tvusername = (TextView) findViewById(R.id.optionsusernametext);
    			tvusername.setText(input);
    		}
    	});
    	
    	final Button bcreateuser = (Button) findViewById(R.id.createnewuserbutton);
        bcreateuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	createnewuserSetup();
            }
        });
    }
    
    public void optionsSetup()
    {
		setContentView(R.layout.optionsui);
		
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
    
    public void createnewuserSetup()
    {
        setContentView(R.layout.createuserui);

        final Button bcreate = (Button) findViewById(R.id.createbutton);
        bcreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final TextView tvcreate = (TextView) findViewById(R.id.createnewusertext);
    			String input = tvcreate.getText().toString();
    			play = new Player(input);
    			optionsSetup();
    			final TextView tvusername = (TextView) findViewById(R.id.optionsusernametext);
    			tvusername.setText(input);
            }
        });

        final Button bcancel = (Button) findViewById(R.id.cancelbutton);
        bcancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	loginSetup();
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
    	
    	sscgame.play(play, alshapes, alcolors, numberofobjects);
    	
    	GridView gameview = (GridView) findViewById(R.id.gamegridview);
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
    	
    	ImageAdapter iagame = new ImageAdapter(this);
    	for(int i = 0; i < numberofobjects; i++)
    	{
    		iagame.addShape(sscgame.getGameObject(i));
    	}
    	iagame.prepare();
    	gameview.setAdapter(iagame);
    }
    
}
