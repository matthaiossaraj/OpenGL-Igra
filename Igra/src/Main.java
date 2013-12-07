import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.nio.IntBuffer;

public class Main extends BaseWindow {

  BitmapText text;
  public static String title = "FRIkopter";
  public static IntBuffer m_Textures;
  // gameplay holds the game status:
  // 0 - Initialization, 1 - Main Menu, 2 - Play the Game, 3 - Game Over
  public static int gameplay = 1;
  int health = 100;
  public static Timer timer;
  static int definedPlayTime = 10000;
  public static int playTime = definedPlayTime, healthTime = 10, firstTime = 1000, period = 1000;
  public static int totalTime = playTime+healthTime; 

  float posX = 0, posY = 0, posZ = 0, rotX = 0, rotY = 0, rotZ = 0, scale = 1f;
  float s_posX = 0, s_posY = 0, s_posZ = 0;
  boolean flag_wireframe = false, flag_lights = true, flag_fog = true;
  
  public static ArrayList<Cube> cubes;
  
  float NormalSpeed = 0.05f;
  float FastSpeed   = 0.1f;
  int currentCube = 0;
  float size=1;
  
  // for all additional objects (Elements=Osebki/Predmeti; for instance "Diamonds")
  Diamond d;
  ArrayList<Diamond> diamonds = new ArrayList();
  float[] v = new float[3];
  ArrayList<float[]> vs = new ArrayList();
  String action = "", display = "";
  int occurrenceTime = 0;
  int currentdiamond = -1;
  float startZ = -1.9f;
  
  Ship ship;
  World world = new World();
  
  protected void setupView() {
	  super.setupView();
	  
	  text = new BitmapText();
	
	  // wireframe display
	  if (flag_wireframe) GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
   
	  // smooth shading - Gouraud
	  GL11.glShadeModel(GL11.GL_SMOOTH);
    
	  // lights
	  if (flag_lights) {
/*		  GL11.glEnable(GL11.GL_LIGHTING);
	      GL11.glEnable(GL11.GL_LIGHT0);
	      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, allocFloats(new float[] { 0.2f, 0.2f, 0.2f, 0.0f}));
	      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE , allocFloats(new float[] { 1.0f, 1.0f, 1.0f, 0.0f}));
	      GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_LINEAR_ATTENUATION , 7f);
	      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, allocFloats(new float[] { 0f, 0f, 6.0f, 0f}));
	      
	      GL11.glEnable(GL11.GL_LIGHT1);
	      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, allocFloats(new float[] { 0.5f, 0.5f, 0.5f, 0.0f}));
	      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE , allocFloats(new float[] { 1.0f, 1.0f, 1.0f, 0.0f}));
	      GL11.glLightf(GL11.GL_LIGHT1, GL11.GL_LINEAR_ATTENUATION , 3f);
	      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, allocFloats(new float[] { 0f, 0f, 11.0f, 0f}));
*/	      
	      GL11.glEnable(GL11.GL_LIGHT2);
	      GL11.glLight(GL11.GL_LIGHT2, GL11.GL_AMBIENT, allocFloats(new float[] { 0.3f, 0.5f, 0.3f, 0.0f}));
	      GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE , allocFloats(new float[] { 0.7f, 0.7f, 0.7f, 0.0f}));
	      GL11.glLightf(GL11.GL_LIGHT2, GL11.GL_LINEAR_ATTENUATION , 2f);
	      GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, allocFloats(new float[] { 4f, 4f, 18.0f, 0f}));
	      
	      GL11.glEnable(GL11.GL_LIGHT3);
	      GL11.glLight(GL11.GL_LIGHT3, GL11.GL_AMBIENT, allocFloats(new float[] { 0.3f, 0.3f, 0.4f, 0.0f}));
	      GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE , allocFloats(new float[] { 0.6f, 0.6f, 0.6f, 0.0f}));
	      GL11.glLightf(GL11.GL_LIGHT3, GL11.GL_LINEAR_ATTENUATION , 5f);
	      GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, allocFloats(new float[] { 2f, 4f, 28.0f, 0f}));
	  }
    
	  // fog
	  if (flag_fog) {
	      GL11.glEnable(GL11.GL_FOG);
	      GL11.glFog(GL11.GL_FOG_COLOR,allocFloats(new float[] { 0.5f,0.5f,0.5f,0.0f }));
	      GL11.glFogi(GL11.GL_FOG_MODE,GL11.GL_EXP);
	      GL11.glFogf(GL11.GL_FOG_DENSITY,0.01f);
	  }
 
	  // mapping from normalized to window coordinates
	  GL11.glViewport(0, 0, 1024, 768);

	  // setup projection matrix stack
	  GL11.glMatrixMode(GL11.GL_PROJECTION);
	  GL11.glLoadIdentity();
	  GLU.gluPerspective(45, 1024 / (float)768, 1.0f, 500.0f);

	  setCameraMatrix();    
	}
    
  protected void setCameraMatrix() { //Setting camera - like in "1.person"
	  // model view stack
	  GL11.glMatrixMode(GL11.GL_MODELVIEW);
	  GL11.glLoadIdentity();
	  // setup view space;
	  // translate to 0,2,4 and rotate 30 degrees along x
	  GL11.glTranslatef(0, 0, startZ);
//	  GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
  }

  // can be used for 3D model initialization
  protected void initializeModels() {
	  gameplay = 1;
	  health = 100;
	  playTime = definedPlayTime;
	  healthTime = 10;
	  totalTime = playTime+healthTime;
	  posX = 0; posY = 0; posZ = 0;
	  rotX = 0; rotY = 0; rotZ = 0; scale = 1f;
	  s_posX = 0; s_posY = 0; s_posZ = 0;
	  diamonds.clear();
	  vs.clear();
	  NormalSpeed = 0.01f;
	  FastSpeed = 0.08f;
	  action = "";
	  display = "";
	  occurrenceTime = 0;
	  currentdiamond = -1;
	  
	  cubes = world.getCubes();
	  
	  m_Textures= Texture.loadTextures2D(new String[] { "ceiling.jpg", "checker2.jpg", "floor.png", "wall.jpg" });
	  
	  // BAD DIAMOND (takes 30% health)
	  v = new float[] {0.0f, 0.0f, -6.0f};
	  vs.add(v);
	  d = new Diamond(v, "bad");
	  d.setAlpha(1.0f); //set transparency
	  diamonds.add(d);
	  
	  // SPEED DIAMOND (increases the speed(s) for 2x), hidden in SECRET chamber
	  v = new float[] {-2.0f, 0.0f, -12.0f};
	  vs.add(v);
	  d = new Diamond(v, "speed");
	  d.setAlpha(1.0f); //set transparency
	  diamonds.add(d);
	  
 	  // BAD DIAMOND (takes 30% health)
	  v = new float[] {10.0f, 0.0f, -12.0f};
	  vs.add(v);
	  d = new Diamond(v, "bad");
	  d.setAlpha(1.0f); //set transparency
	  diamonds.add(d);
	  
	  // TIME DIAMOND (additional time - 15 seconds), hidden in SECRET chamber
	  v = new float[] {10.0f, 0.0f, -20.0f};
	  vs.add(v);
	  d = new Diamond(v, "time");
	  d.setAlpha(1.0f); //set transparency
	  diamonds.add(d);
	  
	  // GOOD DIAMOND (gives/recovers health to 100%)
	  v = new float[] {4.0f, 4.0f, -18.0f};
	  vs.add(v);
	  d = new Diamond(v, "good");
	  d.setAlpha(1.0f); //set transparency
	  diamonds.add(d);

	  // LEVEL DIAMOND (completes the game)
	  v = new float[] {-2.0f, 4.0f, -28.0f};
	  vs.add(v);
	  d = new Diamond(v, "final");
	  d.setAlpha(1.0f); //set transparency
	  diamonds.add(d);
	  
	  //ship = new Ship();
  }
  
  // Resets the view of current frame
  protected void resetView() {
	  // clear color and depth buffer
	  GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
  }
  
  // Renders current frame
  protected void renderFrame() {
	  // textures - enable 2D textures
	  GL11.glEnable(GL11.GL_TEXTURE_2D);
	  //select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
	  GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );
	    
	  GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	  
	  // enable depth buffer (off by default)
	  GL11.glEnable(GL11.GL_DEPTH_TEST); 
	  // enable culling of back sides of polygons
	  //GL11.glEnable(GL11.GL_CULL_FACE);
	  
	  for(int i=0;i<cubes.size();i++) {
		  Cube cube = cubes.get(i);
		  cube.setPosition((posX+cube.translation[0]), (posY+cube.translation[1]), (posZ+cube.translation[2]));
		  cube.setRotation(rotX, rotY, rotZ);
		  cube.setScaling(scale, scale, scale); 
		  cube.render3D();
	  }
	  
	  for (int j=0;j<diamonds.size();j++) {
		  Diamond d = diamonds.get(j);
		  d.setPosition((posX), (posY), (posZ));
		  d.setRotation(rotX, rotY, rotZ);
		  d.setScaling(scale, scale, scale);
		  d.render3D();
	  }
	  
	  //ship.render3D();
	  
	  // HUD & Text render
	  startHUD();
	  GL11.glEnable(GL11.GL_BLEND);
	  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	  
	  // Time
	  GL11.glPopMatrix();
	  GL11.glPushMatrix();
	  GL11.glLoadIdentity();
	  GL11.glTranslatef(30, 720, 0);
	  if (playTime < 10) {
		  GL11.glColor4f(1, 0, 0, 1);
		  text.renderString("TIME:", 22);
	  } else {
		  GL11.glColor4f(0.7f, 0.7f, 0.7f, 1);
		  text.renderString("TIME:", 22);
		  GL11.glColor4f(1, 1, 1, 1);
	  }
	  GL11.glTranslatef(60, 0, 0);
	  text.renderString(" "+playTime, 25);
	  
	  // Health
	  GL11.glPopMatrix();
	  GL11.glPushMatrix();
	  GL11.glLoadIdentity();
	  GL11.glTranslatef(840, 720, 0);
	  if (health < 50) {
		  GL11.glColor4f(1, 0, 0, 1);
		  text.renderString("HEALTH:", 22);
	  } else {
		  GL11.glColor4f(0.7f, 0.7f, 0.7f, 1);
		  text.renderString("HEALTH:", 22);
		  GL11.glColor4f(0, 1, 0, 1);
	  }
	  GL11.glTranslatef(90, 0, 0);
	  text.renderString(" "+health, 25);
	  
	  // Info about the Collected Objects/Diamonds
	  if (!action.equals("") && (!action.equals("final"))) {
		  if (Math.abs(playTime-occurrenceTime) <= 2) {
			  GL11.glPopMatrix();
			  GL11.glPushMatrix();
			  GL11.glLoadIdentity();
			  GL11.glTranslatef(350, 350, 0);
			  if (action.equals("bad")) {
				  GL11.glColor4f(1, 0, 0, 1);
			  } else {
				  GL11.glColor4f(1, 1, 1, 1);
			  }
			  text.renderString(display, 28);
//			  System.out.println(">>> playTime="+playTime+",occurenceTime="+occurrenceTime+", action="+action+", display="+display+"!");
		  } else {
			  occurrenceTime = 0;
			  action = "";
			  display = "";
		  }
	  }

	  GL11.glDisable(GL11.GL_BLEND);
	  endHUD();
	  super.renderFrame();
  }
  
  protected void renderFrameMainMenu() {
	  startHUD();
	  // Whole panel - kind of Blue
	  GL11.glColor4f(0.1f, 0.1f, 0.4f, 1);
      GL11.glBegin(GL11.GL_QUADS);
      GL11.glVertex2f(   0,  0);
      GL11.glVertex2f(1024,  0);
      GL11.glVertex2f(1024,768);
      GL11.glVertex2f(   0,768);
      GL11.glEnd();
      
   	  // Title of the game
	  GL11.glTranslatef(150, 440, 0);
      GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
      text.renderString(title,130);

      // "Play Game" button
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GL11.glColor4f(1.0f, 1.0f, 1.0f, 1);
      GL11.glTranslatef(385, 270, 0);
      text.renderString("Play Game",50);

      // "Cancel" button
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GL11.glColor4f(0.7f, 0.7f, 0.7f, 1);
      GL11.glTranslatef(450, 140, 0);
      text.renderString("Cancel",40);
      endHUD();
  }
  
  protected void renderFrameGameOver() {
	  startHUD();
  	  // Whole panel - kind of Red
  	  GL11.glColor4f(0.7f, 0.0f, 0.0f, 1);
	  GL11.glBegin(GL11.GL_QUADS);
	  GL11.glVertex2f(   0,  0);
	  GL11.glVertex2f(1024,  0);
	  GL11.glVertex2f(1024,768);
	  GL11.glVertex2f(   0,768);
	  GL11.glEnd();
  	
	  // Game Over
	  GL11.glTranslatef(230, 440, 0);
	  GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
	  text.renderString("Game Over",100);
	
	  // "Back to Main Menu" button
	  GL11.glPopMatrix();
	  GL11.glPushMatrix();
	  GL11.glLoadIdentity();
	  GL11.glColor4f(1.0f, 1.0f, 1.0f, 1);
	  GL11.glTranslatef(335, 280, 0);
	  text.renderString("Back to Main Menu",35);
	  endHUD();
  }
  
  protected void renderFrameLevelCompleted() {
	  startHUD();
  	  // Whole panel - kind of Green
  	  GL11.glColor4f(0.0f, 0.6f, 0.0f, 1);
	  GL11.glBegin(GL11.GL_QUADS);
	  GL11.glVertex2f(   0,  0);
	  GL11.glVertex2f(1024,  0);
	  GL11.glVertex2f(1024,768);
	  GL11.glVertex2f(   0,768);
	  GL11.glEnd();
  	
	  // Level Completed
	  GL11.glTranslatef(160, 440, 0);
	  GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
	  text.renderString("Level Completed",75);
	
	  // "Back to Main Menu" button
	  GL11.glPopMatrix();
	  GL11.glPushMatrix();
	  GL11.glLoadIdentity();
	  GL11.glColor4f(1.0f, 1.0f, 1.0f, 1);
	  GL11.glTranslatef(335, 280, 0);
	  text.renderString("Back to Main Menu",35);
	  endHUD();
  } 
  
  protected float cos(float f) { // cos in degrees
	  double f_d = Math.toRadians(f);
	  float res = (float)Math.cos(f_d);
	  return res;
  }
  
  protected float sin(float f) { // sin in degrees
	  double f_d = Math.toRadians(f);
	  float res = (float)Math.sin(f_d);
	  return res;
  }
  
  protected void startHUD() {
	  GL11.glMatrixMode(GL11.GL_PROJECTION);
	  GL11.glPushMatrix();
	  GL11.glLoadIdentity();
	  GL11.glOrtho(0, 1024, 0, 768, -1, 1); //0*0 - left bottom, 1024*768 - right upper
	    
	  GL11.glMatrixMode(GL11.GL_MODELVIEW);
	  GL11.glPushMatrix();
	  GL11.glLoadIdentity();
	  GL11.glDisable(GL11.GL_LIGHTING);
  }
	  
  protected void endHUD() {	
	  GL11.glMatrixMode(GL11.GL_PROJECTION);
	  GL11.glPopMatrix();
	  GL11.glMatrixMode(GL11.GL_MODELVIEW);
	  GL11.glPopMatrix();
	  GL11.glEnable(GL11.GL_LIGHTING);
  }
  
 
  
  protected void processInputMainMenu(){
	  if(Mouse.getY()>280 && Mouse.getY()<305 && Mouse.getX()>385 && Mouse.getX()<670) {
		  if(Mouse.isButtonDown(0)){
			  Mouse.setCursorPosition(-1, -1);
			  Display.update();
			  gameplay = 0;
		  }
	  }
	  if(Mouse.getY()>150 && Mouse.getY()<170 && Mouse.getX()>450 && Mouse.getX()<602) {
		  if(Mouse.isButtonDown(0)){
			  BaseWindow.isRunning = false;
		  }
	  }
	  if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
		  BaseWindow.isRunning = false;
	  }
	  super.processInputMainMenu();
  }
  
  protected void processInputGameOver() {
	  if(Mouse.getY()>285 && Mouse.getY()<305 && Mouse.getX()>335 && Mouse.getX()<707) {
		  if(Mouse.isButtonDown(0)){
			  Mouse.setCursorPosition(-1, -1);
			  Display.update();
			  gameplay = 0;
		  }
	  }
	  if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
		  BaseWindow.isRunning = false;
	  }
	  super.processInputGameOver();
  }
  
  protected void processInputLevelCompleted() {
	  if(Mouse.getY()>285 && Mouse.getY()<305 && Mouse.getX()>335 && Mouse.getX()<707) {
		  if(Mouse.isButtonDown(0)){
			  Mouse.setCursorPosition(-1, -1);
			  Display.update();
			  gameplay = 0;
		  }
	  }
	  if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
		BaseWindow.isRunning = false;
	  }
	  super.processInputLevelCompleted();
  }
  
  protected void checkLocationAndCollision() {
	  if ((1.0 < posX) && (posX < 3.0) && (-5.0 < posY) && (posY < -3.0) && (27.0 < posZ)) { // Reached the end of the Game/Level
		  gameplay = 4;
	  } else { // Still to play
		  if (anyDiamondInCurrentLocation() && (currentdiamond+1 > 0)) {
			  d = diamonds.get(currentdiamond);
	    	  action = d.description;
		      occurrenceTime = playTime;
//			  if (d.getAlpha() == 1) {
			      if (action.equals("final")) {
			    	  gameplay = 4;
			      } else if (action.equals("bad")) {
			    	  health -= 30;
			    	  display = " Lost -30% Health "; // display is exactly 18-characters long
			      } else if (action.equals("good")) {
			    	  health = 100;
			    	  display = "Gained 100% Health"; // display is exactly 18-characters long
			      } else if (action.equals("time"))  {
			    	  playTime += 15;
			    	  occurrenceTime += 15;
			    	  display = "Gained 15 seconds "; // display is exactly 18-characters long
			      } else if (action.equals("speed")) {
			    	  NormalSpeed *= 1.5;
			    	  FastSpeed *= 1.5;
			    	  display = "Gained +50% speed "; // display is exactly 18-characters long
			      }
//			      System.out.println("playTime="+playTime+", occurenceTime="+occurrenceTime+", Desc/Action="+action+", Display="+display+"!");
//		  		  d.setAlpha(0.0f);
		  		  vs.remove(currentdiamond);
		  		  diamonds.remove(currentdiamond);
		  		  currentdiamond = -1;
//			  }
		  }
	  }
  }
  
  protected boolean anyDiamondInCurrentLocation() {
	  float delta=0.8f;
	  for (int i=0; i<vs.size(); i++) {
		  v = vs.get(i);
		  //X~v[0]; Y~v[1]; Z~v[2]
//		  System.out.println("X~v[0]="+v[0]+", Y~v[1]="+v[1]+", Z~v[2]="+v[2]+"!");
//		  System.out.println("posX  ="+posX+", posY  ="+posY+", posZ  ="+posZ+"!");
		  if ((v[0]-delta < posX*(-1) ) && (posX*(-1) <v[0]+delta) &&
			  (v[1]-delta < posY*(-1) ) && (posY*(-1) <v[1]+delta) &&
			  (v[2]-delta < posZ*(-1) ) && (posZ*(-1) <v[2]+delta) ) {
			  currentdiamond = i;
//			  System.out.println("Current VS[i] is the currentdiamond... i="+i+"!");
			  return true;
		  }
	  }
	  return false;
  }
  
  protected float[] getCubeCoordinates(int i) {
	  //System.out.println("Got i="+i+"!");
	  /*for(int j=0;j<cubes.size();j++) {
		  getCubeCoordinates(j);
	  } */
	  
	  Cube kocka = cubes.get(i);
	  //System.out.println("#cubes="+cubes.size()+", m_nX="+kocka.m_nX+", m_nY="+kocka.m_nY+", m_nZ="+kocka.m_nZ+", m_rX="+kocka.m_rX+", m_rY="+kocka.m_rY+", m_rZ="+kocka.m_rZ+", m_sX="+kocka.m_sX+", m_sY="+kocka.m_sY+", m_sZ="+kocka.m_sZ+"!");
	  float[] vertex = new float[3];
	  vertex = new float[]{ kocka.m_nX,  kocka.m_nY, kocka.m_nZ};
	  return vertex;
  }
  
  private static final int setNewTime() {
	  playTime--;
	  totalTime=playTime+healthTime;
	  return totalTime;
  }
  
  public static void main(String[] args) {
	  timer = new Timer();
	  timer.scheduleAtFixedRate(new TimerTask() {
		  public void run() {
			  setNewTime();
		  }
	  }, firstTime, period);
	  (new Main()).execute();
  }
  
  protected void execute() {
	  try {
		  initDisplay();
	  } catch (LWJGLException e) {
	      System.err.println("Can't open display.");
	      System.exit(0);
	  }
	  BaseWindow.isRunning = true;
	  mainLoop();
	  Display.destroy();
	  System.exit(0);
  }
  
  protected void mainLoop() {
	  // setup camera and lights
	  setupView();
	  
	  while (BaseWindow.isRunning) {
		  switch (gameplay) {
		  case 0: // Initialization
			  initializeModels();
			  setupView();
			  gameplay = 2;
			  break;
		  case 1: // Main Menu
			  resetView();
			  renderFrameMainMenu();
			  processInputMainMenu();
			  break;
		  case 2: // Play the Game
			  resetView();
			  renderFrame();
			  if (health < 1) {
				  gameplay = 3;
			  }
			  if (playTime < 0) {
				  if ((health > 0)) {
					  playTime += 1;
					  health -= 10;
				  } else {
					  gameplay = 3;
				  }
			  }
			  processInput();
			  break;
		  case 3: // Game Over
			  resetView();
			  renderFrameGameOver();
			  processInputGameOver();
			  break;
		  case 4: // Level Completed
			  resetView();
			  renderFrameLevelCompleted();
			  processInputLevelCompleted();
			  break;
		  }
		  Display.update();
	  }
  }
  
  protected int getCurrentCube() {
	  boolean[] detect = {false, false, false};
	  for(int i=0;i<cubes.size();i++) {
		  float p[] = cubes.get(i).getPosition();
		  
		  if((p[0] + posX) <= 0 && Math.abs(p[0] + posX) <= 1*size)
			  detect[0] = true;
		  if((posY+p[1]) <= 0.6*size)
			  detect[1] = true;
		  if((posZ + p[2]) >= 0 && (posZ + p[2]) <= 2*size) {
			  detect[2] = true;
		  }
		  if(detect[0] && detect[1] && detect[2])
			  currentCube = i ;
		  detect[0] = false;
		  detect[1] = false;
		  detect[2] = false;
	  }
	  return currentCube;
  }
  
  protected boolean collisionRotation(float rY) {
	  float tail = 1;
	  float p[] = cubes.get(currentCube).getPosition();
	  float distZ = Math.abs(Math.abs(posZ+startZ) - Math.abs(p[2]));
	  if(distZ < 1 && !cubes.get(currentCube).opened(2))
		  return true;
	  float distX = Math.abs(Math.abs(posX) - Math.abs(p[0]));
	  if(distX > 0.43f && !cubes.get(currentCube).opened(1))
		  return true;
	  float distY = Math.abs(Math.abs(posY) - Math.abs(p[1]));
	  if(distY > 0.55f && !cubes.get(currentCube).opened(4))
		  return true;
	  return false;
  }
  
  protected boolean collision(float pX, float pY, float pZ) {
	  float p[] = cubes.get(currentCube).getPosition();
	  float distZ = Math.abs(Math.abs(pZ+startZ) - Math.abs(p[2]));
	  if(pZ > posZ && distZ < 1 && !cubes.get(currentCube).opened(2))
		  return true;
	  else if(pZ < posZ  && (pZ+startZ) < Math.abs(p[2]) && !cubes.get(currentCube).opened(0))
		  return true;
	  float distX = Math.abs(Math.abs(pX) - Math.abs(p[0]));
	  if(pX < posX && distX > 0.43f && !cubes.get(currentCube).opened(1))
		  return true;
	  else if(pX > posX  && distX > 0.43f && !cubes.get(currentCube).opened(3))
		  return true;
	  float distY = Math.abs(Math.abs(pY) - Math.abs(p[1]));
	  if(pY < posY && distY > 0.55f && !cubes.get(currentCube).opened(4))
		  return true;
	  else if(pY > posY  && distY > 0.55f && !cubes.get(currentCube).opened(5))
		  return true;
	  return false;
  }
  
  // Processes Keyboard and Mouse input and spawns actions
  protected void processInput() {
//	  System.out.println("posX="+posX+", posY="+posY+", posZ="+posZ+"; rotX="+rotX+", rotY="+rotY+", rotZ="+rotZ+", speed="+speed+", scale="+scale+"!");
	  
	  // check 'our' location and potential collision with the objects/diamonds
	  // This routine SHOULD BE EXTENDED with the Collision Detection with the cubes
	  checkLocationAndCollision();
	  float  pX = 0, pY = 0, pZ = 0, rY = 0;
	  
	  if (Keyboard.isKeyDown(Keyboard.KEY_1)) setCameraMatrix();
	  	  
	  if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
		  getCurrentCube();
		  if(rotY != 0 || rotX != 0) {
			  pY=sin((rotX%360))*NormalSpeed;
			  float point = cos((rotX%360))*NormalSpeed;
			  pZ=cos((rotY%360))*point;
			  pX=sin((rotY%360))*point;
		  }  
		  else
			  //posZ+=NormalSpeed;
			  pZ=NormalSpeed;
		  if(!collision(posX - pX, posY + pY, posZ + pZ)) {
			  posY+=pY;
			  posZ+=pZ;
			  posX-=pX;
		  }
		  
		  int c = getCurrentCube();
		  float s[] = cubes.get(c).getPosition();
		  System.out.println("-------------------------");
		  System.out.println("pos: "+posX+"  "+posY+"  "+posZ);
		  System.out.println("new: "+(posX-pX)+"  "+(posY+pY)+"  "+(posZ+pZ));
		  System.out.println("pC: "+s[0]+"  "+s[1]+"  "+s[2]);
		  System.out.println("cube: "+c);
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
		  getCurrentCube();
		  if(rotY != 0 || rotX != 0) {
			  pY=sin((rotX%360))*NormalSpeed;
			  float point = cos((rotX%360))*NormalSpeed;
			  pZ=cos((rotY%360))*point;
			  pX=sin((rotY%360))*point;
		  }  
		  else
			  pZ=NormalSpeed;
		  if(!collision(posX + pX, posY - pY, posZ - pZ)) {
			  posY-=pY;
			  posZ-=pZ;
			  posX+=pX;
		  }
		  
		  int c = getCurrentCube();
		  float s[] = cubes.get(c).getPosition();
		  System.out.println("-------------------------");
		  System.out.println("pos: "+posX+"  "+posY+"  "+posZ);
		  System.out.println("new: "+(posX+pX)+"  "+(posY-pY)+"  "+(posZ-pZ));
		  System.out.println("pC: "+s[0]+"  "+s[1]+"  "+s[2]);
		  System.out.println("cube: "+c);
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
		  getCurrentCube();
		  if(rotY != 0) {
			  pZ=sin((rotY%360))*NormalSpeed;
			  pX=cos((rotY%360))*NormalSpeed;
		  }  
		  else
			  pX+=NormalSpeed;
		  if(!collision(posX + pX, posY, posZ + pZ)) {
			  posZ+=pZ;
			  posX+=pX;
		  }
		  
		  int c = getCurrentCube();
		  float s[] = cubes.get(c).getPosition();
		  System.out.println("-------------------------");
		  System.out.println("pos: "+posX+"  "+posY+"  "+posZ);
		  System.out.println("new: "+(posX+pX)+"  "+posY+"  "+(posZ+pZ));
		  System.out.println("pC: "+s[0]+"  "+s[1]+"  "+s[2]);
		  System.out.println("cube: "+c);
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
		  getCurrentCube();
		  if(rotY != 0) {
			  pZ=sin((rotY%360))*NormalSpeed;
			  pX=cos((rotY%360))*NormalSpeed;
		  }  
		  else
			  pX=NormalSpeed;
		  if(!collision(posX - pX, posY, posZ - pZ)) {
			  posZ-=pZ;
			  posX-=pX;
		  }
		  
		  int c = getCurrentCube();
		  float s[] = cubes.get(c).getPosition();
		  System.out.println("-------------------------");
		  System.out.println("pos: "+posX+"  "+posY+"  "+posZ);
		  System.out.println("new: "+(posX-pX)+"  "+posY+"  "+(posZ-pZ));
		  System.out.println("pC: "+s[0]+"  "+s[1]+"  "+s[2]);
		  System.out.println("cube: "+c);
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
		  rY=FastSpeed*4;
		  if(!collisionRotation(rotY-rY)) {
			  rotY-=rY;
		  }
		  
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
		  rY=FastSpeed*4;
		  if(!collisionRotation(rotY+rY)) {
			  rotY+=rY;
		  }
		  
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
		  pY=NormalSpeed;
		  if(!collision(posX, posY-pY, posZ)) {
			  posY-=pY;
		  }
		  
		  int c = getCurrentCube();
		  float s[] = cubes.get(c).getPosition();
		  System.out.println("-------------------------");
		  System.out.println("pos: "+posX+"  "+posY+"  "+posZ);
		  System.out.println("new: "+posX+"  "+(posY-pY)+"  "+posZ);
		  System.out.println("pC: "+s[0]+"  "+s[1]+"  "+s[2]);
		  System.out.println("cube: "+c);
	  }
	  if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
		  pY=NormalSpeed;
		  if(!collision(posX, posY+pY, posZ)) {
			  posY+=pY;
		  }
		  
		  int c = getCurrentCube();
		  float s[] = cubes.get(c).getPosition();
		  System.out.println("-------------------------");
		  System.out.println("pos: "+posX+"  "+posY+"  "+posZ);
		  System.out.println("new: "+posX+"  "+(posY+pY)+"  "+posZ);
		  System.out.println("pC: "+s[0]+"  "+s[1]+"  "+s[2]);
		  System.out.println("cube: "+c);
	  }
	  if (Display.isCloseRequested()) {
	      BaseWindow.isRunning = false;
	  }
      super.processInput();
  }
}
