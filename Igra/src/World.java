import java.util.ArrayList;


public class World {
	
	private float s_posX = 0, s_posY = 0, s_posZ = 0;
	public static ArrayList<Cube> cubes = new ArrayList();
	float size = 2f;
	
	public World() {
		/*addCube("start","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","right");
		  addCube("right","right");
		  addCube("right","right");
		  addCube("right","right");
		  addCube("right","right");
		  addCube("right","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","up");
		  addCube("up","up");	  
		  addCube("up","left");
		  addCube("left","left");
		  addCube("left","left");
		  addCube("left","left");
		  addCube("left","left");
		  addCube("left","left");
		  addCube("left","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","front");
		  addCube("front","front");*/
		addCube("start","front");
		addCube("front","front");
		addCube("front","right");
		addCube("right","right");
		addCube("right","right");
		addCube("right","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","left");
		addCube("left","left");
		addCube("left","left");
		addCube("left","left");
		addCube("left","left");
	}
	
	public ArrayList getCubes() {
		return cubes;
	}
	
	private void addCube(String direction, String next) {
		  // front, right, back, left, top, bottom / x, y, z
		  if(direction=="front") {
			  s_posZ-=2*size;
			  if(next=="front")
				  cubes.add(new Cube(new int[]{0,1,0,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="right")
				  cubes.add(new Cube(new int[]{0,0,1,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="left")
				  cubes.add(new Cube(new int[]{0,1,1,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="up")
				  cubes.add(new Cube(new int[]{0,1,1,1,0,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="down")
				  cubes.add(new Cube(new int[]{0,1,1,1,1,0}, new float[]{s_posX,s_posY,s_posZ}));
		  }
	  	  if(direction=="back") {
	  		  s_posZ+=2*size;
	  		  if(next=="back")
	  			  cubes.add(new Cube(new int[]{0,1,0,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="right")
	  			  cubes.add(new Cube(new int[]{1,0,0,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="left")
	  			  cubes.add(new Cube(new int[]{1,1,0,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="up")
	  			  cubes.add(new Cube(new int[]{1,1,0,1,0,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="down")
	  			  cubes.add(new Cube(new int[]{1,1,0,1,1,0}, new float[]{s_posX,s_posY,s_posZ}));
	  	  }
	  	  if(direction=="right") {
	  		  s_posX+=2*size;
	  		  if(next=="front")
	  			  cubes.add(new Cube(new int[]{1,1,0,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="back")
	  			  cubes.add(new Cube(new int[]{0,1,1,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="right")
	  			  cubes.add(new Cube(new int[]{1,0,1,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="up")
	  			  cubes.add(new Cube(new int[]{1,1,1,0,0,1}, new float[]{s_posX,s_posY,s_posZ}));
	  		  else if(next=="down")
	  			  cubes.add(new Cube(new int[]{1,1,1,0,1,0}, new float[]{s_posX,s_posY,s_posZ}));
		  }
		  if(direction=="left") {
			  s_posX-=2*size;
			  if(next=="front")
				  cubes.add(new Cube(new int[]{1,0,0,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="back")
				  cubes.add(new Cube(new int[]{0,0,1,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="left")
			  	  cubes.add(new Cube(new int[]{1,0,1,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="up")
			  	  cubes.add(new Cube(new int[]{1,0,1,1,0,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="down")
			  	  cubes.add(new Cube(new int[]{1,0,1,1,1,0}, new float[]{s_posX,s_posY,s_posZ}));
		  }
		  if(direction=="up") {
			  s_posY+=2*size;
			  if(next=="front")
				  cubes.add(new Cube(new int[]{1,1,0,1,1,0}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="back")
				  cubes.add(new Cube(new int[]{0,1,1,1,1,0}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="right")
				  cubes.add(new Cube(new int[]{1,0,1,1,1,0}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="left")
				  cubes.add(new Cube(new int[]{1,1,1,0,1,0}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="up")
				  cubes.add(new Cube(new int[]{1,1,1,1,0,0}, new float[]{s_posX,s_posY,s_posZ}));
		  }
		  if(direction=="down") {
			  s_posY-=2*size;
			  if(next=="front")
				  cubes.add(new Cube(new int[]{1,1,0,1,0,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="back")
				  cubes.add(new Cube(new int[]{0,1,1,1,0,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="right")
				  cubes.add(new Cube(new int[]{1,0,1,1,0,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="left")
				  cubes.add(new Cube(new int[]{1,1,1,0,0,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="down")
				  cubes.add(new Cube(new int[]{1,1,1,1,0,0}, new float[]{s_posX,s_posY,s_posZ}));
		  }
		  if(direction=="start")
			  cubes.add(new Cube(new int[]{1,1,0,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
	  }

}
