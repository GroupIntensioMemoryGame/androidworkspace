package com.example.view;

import java.util.ArrayList;

import com.example.memorygame.R;
import com.simonsays.model.GameObject;
import com.simonsays.model.SimonSays;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	// Final Static Variables
	final public int CIRCLE = 0;
	final public int TRIANGLE = 1;
	final public int SQUARE = 2;
	
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
        return null;
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
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        
        imageView.setImageResource(currentShapes[position]);
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
    		R.drawable.sr, R.drawable.sb,
    		R.drawable.sg, R.drawable.sp,
    		R.drawable.so, R.drawable.sy,
    		
    		R.drawable.tr, R.drawable.tb,
    		R.drawable.tg, R.drawable.tp,
    		R.drawable.to, R.drawable.ty,
    		
    		R.drawable.cr, R.drawable.cb,
    		R.drawable.cg, R.drawable.cp,
    		R.drawable.co, R.drawable.cy
    };
    private Integer[] currentShapes;
}