package com.example.memorygame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginui); 
        final Button bcreateuser = (Button) findViewById(R.id.button2);
        bcreateuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.createuserui);
            }
        });
        

        final Button blogin = (Button) findViewById(R.id.button1);
        blogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.optionsui);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
