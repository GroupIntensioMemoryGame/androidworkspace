package com.example.view;

import com.simonsays.controller.SimonSaysController;
import com.simonsays.model.*;
import com.example.memorygame.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	SimonSaysController sscgame = new SimonSaysController();
	Player play;
	
	
	
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
    			final TextView tvlogins = (TextView) findViewById(R.id.edittextusername);
    			String temp = tvlogins.getText().toString();
    			play = new Player(temp);
    			optionsSetup();
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
        
    }
    
    public void createnewuserSetup()
    {
        setContentView(R.layout.createuserui);

        final Button bcreate = (Button) findViewById(R.id.createbutton);
        bcreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	loginSetup();
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
    	setContentView(R.layout.gameui);
    	GridView gameview = (GridView) findViewById(R.id.gamegridview);
    	sscgame.play(play, -1);
    	gameview.setAdapter(new ImageAdapter(this));
    	
    }
}
