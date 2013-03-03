package com.example.npolygon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Polygon {
	
	int mSides;
	FloatBuffer mVertexBuffer;
	
	public Polygon (int sides){
		
		this.mSides = sides;
		float[] vertices = new float[3*(mSides+2)];
		vertices[0] = vertices[1] = vertices[2] = 0; //origin
		for(int i=1; i<=mSides+1;i++)
		{
			double angle = 2*Math.PI*i/mSides;
			vertices[3*i] = (float)Math.cos(angle);
			vertices[3*i+1] = (float)Math.sin(angle);
			vertices[3*i+2] = 0;
		}
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length*4);
		byteBuf.order(ByteOrder.nativeOrder());
		mVertexBuffer = byteBuf.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
		
	}
	
	
	
	public void draw(GL10 gl){
		
		gl.glFrontFace(GL10.GL_CCW);
		gl.glColor4f(0.0f,1.0f,0.5f,1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);	
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, mSides+2);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);	
		gl.glEnable(GL10.GL_CULL_FACE); 
		gl.glCullFace(GL10.GL_BACK);
		
	}
	

}
