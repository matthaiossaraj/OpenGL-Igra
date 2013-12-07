import org.lwjgl.opengl.GL11;

	public class Diamond extends Model3D {
		float col1_R, col1_G, col1_B;
		float col2_R, col2_G, col2_B;
		float col_A;
		float polarni = 0.27f, napored = 0.18f, ekvator = 0.14f;
		float[] v;
		String description;

		public Diamond(float[] vertex, String description) {
			this.v = vertex;
			this.description = description;
		}
		
		public void setAlpha(float alpha) {
			col_A = alpha;
//			render3D();
		}
		
		public float getAlpha() {
			return col_A;
		}
		
		public String getDescription() {
			return this.description;
		}

		public void render3D() {
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
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
			GL11.glPopMatrix();;
	}
	
	private void renderModel() {
		float[] v1 = new float[]{v[0]-napored,  v[1], 	   	   v[2]};
		float[] v2 = new float[]{v[0]-ekvator,  v[1],		   v[2]+ekvator};
		float[] v3 = new float[]{v[0],          v[1], 	   	   v[2]+napored};
		float[] v4 = new float[]{v[0]+ekvator,  v[1], 	       v[2]+ekvator};
		float[] v5 = new float[]{v[0]+napored,  v[1], 	   	   v[2]};
		float[] v6 = new float[]{v[0]+ekvator,  v[1], 	   	   v[2]-ekvator};
		float[] v7 = new float[]{v[0],  	    v[1],	 	   v[2]-napored};
		float[] v8 = new float[]{v[0]-ekvator,  v[1], 	   	   v[2]-ekvator};
		float[] v0 = new float[]{v[0],  	    v[1]+polarni,  v[2]};
		float[] v9 = new float[]{v[0], 		    v[1]-polarni,  v[2]};
		
		if (description=="bad"){
			col1_R = 0.0f; col1_G = 0.0f; col1_B = 0.0f;
			col2_R = 1.0f; col2_G = 1.0f; col2_B = 0.0f;
		} else if (description=="good") {
			col1_R = 1.0f; col1_G = 1.0f; col1_B = 1.0f;
			col2_R = 0.0f; col2_G = 1.0f; col2_B = 0.0f;
		} else if (description=="time") {
			col1_R = 1.0f; col1_G = 1.0f; col1_B = 1.0f;
			col2_R = 1.0f; col2_G = 0.0f; col2_B = 1.0f;
		} else if (description=="speed") {
			col1_R = 1.0f; col1_G = 1.0f; col1_B = 1.0f;
			col2_R = 1.0f; col2_G = 0.0f; col2_B = 0.0f;
		} else if (description=="final") {
			col1_R = 1.0f; col1_G = 1.0f; col1_B = 1.0f;
			col2_R = 0.0f; col2_G = 0.0f; col2_B = 1.0f;
		}
		
		GL11.glEnable(GL11.GL_BLEND); //Enable blending
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); //Set blending function
		GL11.glBegin(GL11.GL_TRIANGLES); // draw independent triangles
		GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v1[0], v1[1], v1[2]); GL11.glVertex3f(v2[0], v2[1], v2[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v2[0], v2[1], v2[2]); GL11.glVertex3f(v3[0], v3[1], v3[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v3[0], v3[1], v3[2]); GL11.glVertex3f(v4[0], v4[1], v4[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v4[0], v4[1], v4[2]); GL11.glVertex3f(v5[0], v5[1], v5[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v5[0], v5[1], v5[2]); GL11.glVertex3f(v6[0], v6[1], v6[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);		    
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v6[0], v6[1], v6[2]); GL11.glVertex3f(v7[0], v7[1], v7[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v7[0], v7[1], v7[2]); GL11.glVertex3f(v8[0], v8[1], v8[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v8[0], v8[1], v8[2]); GL11.glVertex3f(v1[0], v1[1], v1[2]); GL11.glVertex3f(v0[0], v0[1], v0[2]);

	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v1[0], v1[1], v1[2]); GL11.glVertex3f(v2[0], v2[1], v2[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v2[0], v2[1], v2[2]); GL11.glVertex3f(v3[0], v3[1], v3[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v3[0], v3[1], v3[2]); GL11.glVertex3f(v4[0], v4[1], v4[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v4[0], v4[1], v4[2]); GL11.glVertex3f(v5[0], v5[1], v5[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v5[0], v5[1], v5[2]); GL11.glVertex3f(v6[0], v6[1], v6[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);		    
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v6[0], v6[1], v6[2]); GL11.glVertex3f(v7[0], v7[1], v7[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glColor4f(col2_R, col2_G, col2_B, col_A);
	    GL11.glVertex3f(v7[0], v7[1], v7[2]); GL11.glVertex3f(v8[0], v8[1], v8[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glColor4f(col1_R, col1_G, col1_B, col_A);
	    GL11.glVertex3f(v8[0], v8[1], v8[2]); GL11.glVertex3f(v1[0], v1[1], v1[2]); GL11.glVertex3f(v9[0], v9[1], v9[2]);
	    GL11.glEnd();
		GL11.glDisable(GL11.GL_BLEND);
	}
}
