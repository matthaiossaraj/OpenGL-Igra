import org.lwjgl.opengl.GL11;

public class Ship extends Model3D {
	
	float size = 2f;
	float shipSizeX = 0.3f;
	float shipSizeY = 0.2f;
	
	public void render3D() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		
	    //renderModel();
		 
		GL11.glPopMatrix();
	}
	
	public float[][] getBounds() {
		float bounds[][] = new float[2][2];
		
		GL11.glVertex3f(-shipSizeX*size, -shipSizeY*size,  -0.2f);
    	GL11.glVertex3f( shipSizeX*size, -shipSizeY*size,  -0.2f);  
	    GL11.glVertex3f( shipSizeX*size,  shipSizeY*size,  -0.2f);
	    GL11.glVertex3f(-shipSizeX*size,  shipSizeY*size,  -0.2f);
		
		// x
		bounds[0][0] = -shipSizeX*size;
		bounds[1][0] = shipSizeX*size;
		
		// y
		bounds[0][1] = -shipSizeY*size;
		bounds[1][1] = shipSizeY*size;
		
		return bounds;
	}
	  
	private void renderModel() {
		/*GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glColor3f(0.1f, 0.1f, 0.4f);
		GL11.glVertex3f( 0.0f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f( 0.4f, -0.2f, -0.4f);
		GL11.glVertex3f( 0.0f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f(-0.4f, -0.2f, -0.4f);
		
		GL11.glColor3f(0.7f, 0.7f, 0.9f);
		GL11.glVertex3f( 0.03f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f( 0.2f, -0.1f, -0.3f);
		GL11.glVertex3f(-0.03f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f(-0.2f, -0.1f, -0.3f);
		
		GL11.glColor3f(0.1f, 0.1f, 0.3f);
		GL11.glVertex3f( 0.0f, -0.3f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.75f); GL11.glVertex3f( 0.33f, -0.05f, 0.7f);
		GL11.glVertex3f( 0.0f, -0.3f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.75f); GL11.glVertex3f(-0.33f, -0.05f, 0.7f);
		GL11.glEnd();*/
		
		/*float shipSizeX = 1f;
		float shipSizeY = 1f;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Main.m_Textures.get(4)); // "ship.png"
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Main.allocFloats(new float[] { 1.0f, 1.0f, 0.5f, 0.8f}));
	    GL11.glColor4f(0.4f, 0.3f, 1.0f, 0.8f);
	    
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f(-shipSizeX*size, -shipSizeY*size,  0.2f);
    	GL11.glTexCoord2f(-4.0f, 0.0f); GL11.glVertex3f( shipSizeX*size, -shipSizeY*size,  0.2f);  
	    GL11.glTexCoord2f(-4.0f, 4.0f); GL11.glVertex3f( shipSizeX*size,  shipSizeY*size,  0.2f);
	    GL11.glTexCoord2f( 0.0f, 4.0f); GL11.glVertex3f(-shipSizeX*size,  shipSizeY*size,  0.2f);
	    GL11.glEnd();
	    
	    GL11.glDisable(GL11.GL_BLEND);*/
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.1f, 0.1f, 0.4f);
		GL11.glVertex3f(-shipSizeX*size, -shipSizeY*size,  -0.2f);
    	GL11.glVertex3f( shipSizeX*size, -shipSizeY*size,  -0.2f);  
	    GL11.glVertex3f( shipSizeX*size,  shipSizeY*size,  -0.2f);
	    GL11.glVertex3f(-shipSizeX*size,  shipSizeY*size,  -0.2f);
	    GL11.glEnd();
	}
}
