import org.lwjgl.opengl.GL11;

public class Ship {
	
	public void render3D() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		
	    renderModel();
		 
		GL11.glPopMatrix();
	}
	  
	private void renderModel() {
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glColor3f(0.1f, 0.1f, 0.4f);
		GL11.glVertex3f( 0.0f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f( 0.4f, -0.2f, -0.4f);
		GL11.glVertex3f( 0.0f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f(-0.4f, -0.2f, -0.4f);
		
		GL11.glColor3f(0.7f, 0.7f, 0.9f);
		GL11.glVertex3f( 0.03f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f( 0.2f, -0.1f, -0.3f);
		GL11.glVertex3f(-0.03f, -0.2f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.8f); GL11.glVertex3f(-0.2f, -0.1f, -0.3f);
		
		GL11.glColor3f(0.1f, 0.1f, 0.3f);
		GL11.glVertex3f( 0.0f, -0.3f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.75f); GL11.glVertex3f( 0.33f, -0.05f, 0.7f);
		GL11.glVertex3f( 0.0f, -0.3f, -1.1f); GL11.glVertex3f( 0.0f, -0.1f, -0.75f); GL11.glVertex3f(-0.33f, -0.05f, 0.7f);
		GL11.glEnd();
	}
}
