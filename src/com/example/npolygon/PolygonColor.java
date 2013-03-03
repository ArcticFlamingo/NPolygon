package com.example.npolygon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class PolygonColor {
	
	int mSides;
	int vCount;
	int vSize;
	float [] vertices;
	Boolean filled = true;
	FloatBuffer mVertexBuffer;
	
	public PolygonColor (int sides){
		this.mSides = sides;
		vCount = sides+2; //num of vertices
		vSize = 7; //(x,y,z,r,g,b,a)
		int bCount = vCount*vSize*4; // each float is 4 bytes
		vertices = new float[vCount*vSize];
		vertices[0] = 0.0f;
		vertices[1] = 0.0f;
		vertices[2] = 0.0f;//center vertex
		vertices[3] = 0.5f;
		vertices[4] = 0.5f;
		vertices[5] = 0.0f; //black
		vertices[6] = 1.0f;
		float a = 1.0f;  //ambient always opaque
		for(int i=1; i<vCount; i++){			
			double angle = 2*i*Math.PI/sides;
			vertices[vSize*i] = (float) Math.cos(angle);
			vertices[vSize*i+1] = (float) Math.sin(angle);
			vertices[vSize*i+2] = 0.0f;	
			//assign random colors
			vertices[vSize*i+3] = (float) Math.random();
			vertices[vSize*i+4] = (float) Math.random();
			vertices[vSize*i+5] = (float) Math.random();
			vertices[vSize*i+6] = a;
			
		}
		//allocate a buffer with space for n+2 vertices plus
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(bCount);
		byteBuf.order(ByteOrder.nativeOrder());
		mVertexBuffer = byteBuf.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);

		
		
	}
	
	public void setFilled(Boolean f){
		filled = f;
	}
	
	
	
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		mVertexBuffer.position(0);
		gl.glVertexPointer(3, GL10.GL_FLOAT, vSize*4, mVertexBuffer);	
		mVertexBuffer.position(3);
		gl.glColorPointer(4, GL10.GL_FLOAT, vSize*4, mVertexBuffer);
		
		if(filled){
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, vCount);
		}else{
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 1, vCount-1);
		}
		
		 //Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);	
	}
	

}
