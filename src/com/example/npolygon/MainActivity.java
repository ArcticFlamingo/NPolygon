package com.example.npolygon;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private GLSurfaceView mGLView;
	private MyRenderer mGLRenderer;
	
  

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLRenderer = new MyRenderer();
        mGLView = new MySurfaceView(this, mGLRenderer);
        setContentView(mGLView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mymenu, menu);
	    menu.setGroupCheckable(R.id.group1, true, true);
	    return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.item1:  //Filled
	    	item.setChecked(true);
	    	mGLRenderer.getPolygonColor().setFilled(true);	    		
	    	mGLView.requestRender();
	    	return true;
	    case R.id.item2:  //Wire frame
	    	item.setChecked(true);
	    	mGLRenderer.getPolygonColor().setFilled(false);
	    	mGLView.requestRender();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

    
    @Override
  	protected void onPause() {
  		// TODO Auto-generated method stub
  		super.onPause();
  		mGLView.onPause();
  	}

  	@Override
  	protected void onResume() {
  		// TODO Auto-generated method stub
  		super.onResume();
  		mGLView.onResume();
  	}
    
}
