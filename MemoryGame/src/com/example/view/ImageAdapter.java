package com.example.view;

import java.util.ArrayList;

import com.example.memorygame.R;
import com.simonsays.model.GameObject;
import com.simonsays.model.SimonSays;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
	private ArrayList<Integer> shapeView;

    public ImageAdapter(Context c) {
        mContext = c;
        shapeView = new ArrayList<Integer>();
    }

    public int getCount() {
        return currentShapes.length;
    }

    public Object getItem(int position) {
    	return currentShapes[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(16, 16, 16, 16);
        } else {
            imageView = (ImageView) convertView;
        }
        
        imageView.setBackgroundResource(currentShapes[position]);
        return imageView;
    }
    
    public void prepare()
    {
        currentShapes = new Integer[shapeView.size()];
        for(int i = 0 ; i < shapeView.size(); i++)
        {
        	currentShapes[i] = shapeView.get(i);
        }
    }
    
    public void addShape(GameObject go)
    {
    	int temp = ((go.getShape() * 6) + go.getColor());
    	shapeView.add(availableShapes[temp]);
    }
    
    // references to our images
    private Integer[] availableShapes = {
    		R.drawable.asr, R.drawable.asb,
    		R.drawable.asg, R.drawable.asp,
    		R.drawable.aso, R.drawable.asy,
    		
    		R.drawable.atr, R.drawable.atb,
    		R.drawable.atg, R.drawable.atp,
    		R.drawable.ato, R.drawable.aty,
    		
    		R.drawable.acr, R.drawable.acb,
    		R.drawable.acg, R.drawable.acp,
    		R.drawable.aco, R.drawable.acy,
    };
    private Integer[] currentShapes;
}