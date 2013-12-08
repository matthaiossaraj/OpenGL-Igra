import org.lwjgl.opengl.GL11;

public class Cube extends Model3D {
	
	int[] sides; // front, right, back, left, top, bottom
	float[] translation;
	float size = 2f;
	
	public Cube(int[] sides, float[] translation) {
		this.sides = sides;
		this.translation = translation;
	}
	
	public float[] getPosition() {
		return this.translation;
	}
	
	public float[][] getBounds() {
		float bounds[][] = new float[2][3];
		
		// x
		bounds[0][0] = -this.translation[0] + size;
		bounds[1][0] = -this.translation[0] - size;
		
		// y
		bounds[0][1] = -this.translation[1] + size;
		bounds[1][1] = -this.translation[1] - size;
				
		// z
		bounds[0][2] = -this.translation[2];
		bounds[1][2] = -this.translation[2] + 2*size;
		
		return bounds;
	}
	
	public boolean opened(int i) {
		if(this.sides[i] == 0)
			return true;
		else
			return false;
	}
	
	public void render3D() {
	    // model view stack 
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    
	    // save current matrix
	    GL11.glPushMatrix();

	    // TRANSLATE 
	    GL11.glTranslatef(m_nX, m_nY, m_nZ);

	    // ROTATE and SCALE
	    GL11.glTranslatef(-m_nX, -m_nY, -m_nZ);
	    if (m_rZ!=0)
	      GL11.glRotatef(m_rZ, 0, 0, 1);
	    if (m_rY!=0)
	      GL11.glRotatef(m_rY, 0, 1, 0);
	    if (m_rX!=0)
	      GL11.glRotatef(m_rX, 1, 0, 0);
	    if (m_sX!=1 || m_sY!=1 || m_sZ!=1)
	      GL11.glScalef(m_sX, m_sY, m_sZ);
	    GL11.glTranslatef(m_nX, m_nY, m_nZ);    

	    renderModel();
	    
	    // discard current matrix
	    GL11.glPopMatrix();
	}
	  
	private void renderModel() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Main.m_Textures.get(3)); // "wall.jpg"
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Main.allocFloats(new float[] { 1.0f, 1.0f, 0.5f, 0.8f}));
	    GL11.glColor3f(0.4f, 0.3f, 1.0f);
	    if(sides[0]==1) { // front
//	    	GL11.glColor3f(1, 0, 0); // red
	    	GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f(-1.0f*size, -1.0f*size,  1.0f*size);
	    	GL11.glTexCoord2f(-4.0f, 0.0f); GL11.glVertex3f( -1.0f*size, 1.0f*size,  1.0f*size);  
		    GL11.glTexCoord2f(-4.0f, 4.0f); GL11.glVertex3f( 1.0f*size,  1.0f*size,  1.0f*size);
		    GL11.glTexCoord2f( 0.0f, 4.0f); GL11.glVertex3f(1.0f*size,  -1.0f*size,  1.0f*size);
	    }
	    if(sides[1]==1) { // right
//	    	GL11.glColor3f(0, 1, 0); // green
		    GL11.glTexCoord2f(-4.0f, 0.0f); GL11.glVertex3f( 1.0f*size, -1.0f*size,  1.0f*size);
		    GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f( 1.0f*size,  1.0f*size,  1.0f*size); 
		    GL11.glTexCoord2f( 0.0f, 4.0f); GL11.glVertex3f( 1.0f*size,  1.0f*size, -1.0f*size); 
		    GL11.glTexCoord2f(-4.0f, 4.0f); GL11.glVertex3f( 1.0f*size, -1.0f*size, -1.0f*size);
	    }
		if(sides[2]==1) { // back
//			GL11.glColor3f(0, 0, 1); // blue
		    GL11.glTexCoord2f(-4.0f, 0.0f); GL11.glVertex3f(-1.0f*size, -1.0f*size, -1.0f*size); 		    
		    GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f( 1.0f*size, -1.0f*size, -1.0f*size);
		    GL11.glTexCoord2f( 0.0f, 4.0f); GL11.glVertex3f( 1.0f*size,  1.0f*size, -1.0f*size);
		    GL11.glTexCoord2f(-4.0f, 4.0f); GL11.glVertex3f(-1.0f*size,  1.0f*size, -1.0f*size);
		}
		if(sides[3]==1) { // left
//			GL11.glColor3f(1, 1, 0); // yellow
			GL11.glTexCoord2f(-4.0f, 4.0f); GL11.glVertex3f(-1.0f*size, -1.0f*size,  1.0f*size);
		    GL11.glTexCoord2f( 0.0f, 4.0f); GL11.glVertex3f(-1.0f*size, -1.0f*size, -1.0f*size); 
		    GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f(-1.0f*size,  1.0f*size, -1.0f*size); 
		    GL11.glTexCoord2f(-4.0f, 0.0f); GL11.glVertex3f(-1.0f*size,  1.0f*size,  1.0f*size);
		}
		GL11.glEnd();
		
		GL11.glColor3f(0.3f, 0.2f, 0.5f);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Main.m_Textures.get(0)); // "ceiling.jpg"
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Main.allocFloats(new float[] { 1.0f, 1.0f, 0.5f, 0.8f}));
		if(sides[4]==1) { // top
//			GL11.glColor3f(1, 0, 1); // purple
		    GL11.glTexCoord2f(-3.0f, 0.0f); GL11.glVertex3f(-1.0f*size,  1.0f*size,  1.0f*size);
			GL11.glTexCoord2f(-3.0f, 3.0f); GL11.glVertex3f(-1.0f*size,  1.0f*size, -1.0f*size); 
		    GL11.glTexCoord2f( 0.0f, 3.0f); GL11.glVertex3f( 1.0f*size,  1.0f*size, -1.0f*size);
		    GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f( 1.0f*size,  1.0f*size,  1.0f*size);
		}
		GL11.glEnd();
		
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, Main.m_Textures.get(2)); // "floor.png"
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Main.m_Textures.get(0)); // "ceiling.jpg"
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Main.allocFloats(new float[] { 1.0f, 1.0f, 0.5f, 0.8f}));
		if(sides[5]==1) { // bottom
//			GL11.glColor3f(0, 1, 1); // cyan
		    GL11.glTexCoord2f(-3.0f, 0.0f); GL11.glVertex3f(-1.0f*size, -1.0f*size,  1.0f*size);
		    GL11.glTexCoord2f( 0.0f, 0.0f); GL11.glVertex3f( 1.0f*size, -1.0f*size,  1.0f*size); 
		    GL11.glTexCoord2f( 0.0f, 3.0f); GL11.glVertex3f( 1.0f*size, -1.0f*size, -1.0f*size); 
		    GL11.glTexCoord2f(-3.0f, 3.0f); GL11.glVertex3f(-1.0f*size, -1.0f*size, -1.0f*size);
		}
	    GL11.glEnd();
	}
}

