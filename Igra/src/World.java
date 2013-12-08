import java.util.ArrayList;
import java.util.Stack;


public class World {
	
	private float s_posX = 0, s_posY = 0, s_posZ = 0;
	private Stack stack = new Stack();
	private ArrayList<Cube> cubes = new ArrayList();
	float size = 2f;
	Diamonds diamonds;
	
	public World(Diamonds diamonds) {
		// bad, speed, time, good, final
		this.diamonds = diamonds;
		
		addCube("start","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","right");
		add("bad");
		addCube("right","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","LR");
		add("speed");
		push();
		addCube("right","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","front");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		addCube("front","left");
		add("bad");
		addCube("left","front");
		addCube("front","front");
		addCube("front","right");
		add("time");
		addCube("right","front");
		addCube("front","front");
		addCube("front","front");
		add("good");
		addCube("front","front");
		addCube("front","END");
		add("bad");
		pop();
		addCube("left","front");
		addCube("front","front");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("good");
		addCube("front","front");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("time");
		addCube("front","front");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","front");
		addCube("front","UD");
		push();
		addCube("down","front");
		addCube("front","front");
		addCube("front","front");
		add("time");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("good");
		addCube("front","right");
		addCube("right","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		addCube("front","down");
		addCube("down","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("good");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","down");
		addCube("down","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","down");
		addCube("down","front");
		addCube("front","front");
		add("time");
		addCube("front","front");
		addCube("front","down");
		addCube("down","front");
		addCube("front","front");
		add("good");
		addCube("front","front");
		addCube("front","front");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		add("bad");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		add("time");
		addCube("front","left");
		addCube("left","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		add("good");
		addCube("front","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		addCube("front","END");
		add("time");
		pop();
		addCube("up","front");
		addCube("front","right");
		add("time");
		addCube("right","front");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		add("bad");
		addCube("front","front");
		add("good");
		addCube("front","front");
		addCube("front","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		addCube("front","down");
		addCube("down","front");
		addCube("front","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		add("bad");
		addCube("front","down");
		addCube("down","front");
		addCube("front","front");
		add("time");
		addCube("front","front");
		addCube("front","front");
		add("good");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		addCube("front","up");
		add("bad");
		addCube("up","front");
		addCube("front","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		addCube("front","up");
		addCube("up","front");
		addCube("front","front");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		add("bad");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		add("bad");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		add("good");
		addCube("front","right");
		addCube("right","front");
		addCube("front","front");
		add("time");
		addCube("front","left");
		addCube("left","front");
		addCube("front","front");
		addCube("front","front");
		add("speed");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("speed");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("speed");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("speed");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		addCube("front","front");
		add("speed");
		addCube("front","front");
		add("speed");
		addCube("front","front");
		add("speed");
		addCube("front","END");
		add("final");
	}
	
	public ArrayList getCubes() {
		return cubes;
	}
	
	private void push() {
		float split[] = {s_posX, s_posY, s_posZ};
		stack.push(split);
	}
	
	private void pop() {
		float split[] = (float[])stack.pop();
		s_posX = split[0]; 
		s_posY = split[1];
		s_posZ = split[2];
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
			  else if(next=="LR")
				  cubes.add(new Cube(new int[]{0,0,1,0,1,1}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="UD")
				  cubes.add(new Cube(new int[]{0,1,1,1,0,0}, new float[]{s_posX,s_posY,s_posZ}));
			  else if(next=="END")
				  cubes.add(new Cube(new int[]{0,1,1,1,1,1}, new float[]{s_posX,s_posY,s_posZ}));
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
	
	private void add(String type) {
		// bad, speed, time, good, final
		float v[] = {s_posX, s_posY, s_posZ};
		diamonds.addDiamond(v, type);
	}

}
