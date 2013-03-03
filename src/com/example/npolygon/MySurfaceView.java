package com.example.npolygon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

@SuppressLint("ViewConstructor")
public class MySurfaceView extends GLSurfaceView {
	//private static final float ROTATION_FACTOR = .5f;
	private MyRenderer renderer;
	private float prevX;
	private float prevY;

	public MySurfaceView(Context context){
		super(context);
	}

	public MySurfaceView(Context context, MyRenderer renderer ) {
		super(context);
		this.renderer = renderer;
		this.setRenderer(renderer);
		//this.setRenderMode(RENDERMODE_WHEN_DIRTY);
	}
	

	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_MOVE:
			float x = event.getX();
			float y = event.getY();	
			float dx = x-prevX;
			float dy = y-prevY;
			prevX = x;
			prevY = y;
			
			if(dy>0 && dx>0){ //southeast, speeds rotation
				renderer.rotationFactor = 3.0f;
			}else if(dy<0 && dx<0){ //northwest slows rotation
				renderer.rotationFactor = .2f;
			}
			this.requestRender();
		   }
		   return true;
		}

}
