import java.util.ArrayList;


public class Diamonds {

	private ArrayList<Diamond> diamonds = new ArrayList();
	private Diamond d;
	private ArrayList<float[]> vs = new ArrayList();
	
	public Diamonds() {
	}
	
	public ArrayList<Diamond> getDiamonds() {
		return diamonds;
	}
	
	public ArrayList getVS() {
		return vs;
	}
	
	public void addDiamond(float v[], String type) {
		vs.add(v);
		  d = new Diamond(v, type);
		  d.setAlpha(1.0f);
		  diamonds.add(d);
	}
}
