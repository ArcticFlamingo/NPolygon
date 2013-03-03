package com.example.npolygon;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;


public class MyRenderer implements Renderer {
	
	PolygonColor mPolygonColor;
	private float rotationAngle = 0.0f;	
	float rotationFactor = 1.0f;
	
	public void increaseRotationAngle(float rotationAngle) {
		this.rotationAngle += rotationAngle;
	}

	
	@Override
	public void onDrawFrame(GL10 gl) {	
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		increaseRotationAngle(rotationFactor);
		gl.glRotatef(rotationAngle, 0.0f, 0.0f, 1.0f);
		mPolygonColor.draw(gl);
		rotationFactor = 1.0f; //resets rotationFactor after touch event changes it
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		//if user changes orientation of their phone this will use best possible area
		if(height == 0){
			height = 1; 
				}
				int delta;
				if(width <= height){
					delta = (height-width)/2;	
					gl.glViewport(0, delta, width, width); 
				} else{
					delta = (width-height) /2;
					gl.glViewport(delta, 0, height, height); 
				}
				gl.glMatrixMode(GL10.GL_PROJECTION);		
				gl.glLoadIdentity();
				gl.glOrthof(-1.0f, 1.0f, -1.0f, 1.0f, -0.5f, 0.5f);
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f	);					
		gl.glEnable(GL10.GL_DEPTH_TEST);		
		gl.glDepthFunc(GL10.GL_LEQUAL);
		mPolygonColor = new PolygonColor(6);

	}
	
	public PolygonColor getPolygonColor(){
		return mPolygonColor;
	}
	
	public void setPolygonColor(PolygonColor newPolygonColor){
		mPolygonColor = newPolygonColor;
	}

}
