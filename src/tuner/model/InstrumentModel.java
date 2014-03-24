package tuner.model;
import tuner.model.AbstractModel;
import java.util.ArrayList;

public class InstrumentModel extends AbstractModel {
	private String name;
	private int numTones;
	private ArrayList<String> tones;
	
	public InstrumentModel() {
		tones = new ArrayList<String>();
	}
	
	public String getName() { return name; }
	public void setName(String newName) { name = newName; }
	
	public int getNumTones() { return numTones; }
	
	public void addTone(String tone) { 
		numTones++; 
		tones.add(tone);
		}
	
	public void remTone(int index) { 
		numTones--; 
		System.out.println(tones.toString());
		tones.remove(index);
		}
	
	public ArrayList<String> getTones() { return tones; }
	
	@Override
	public boolean equals(Object object) {
		boolean sameSame = false;
		
		if (object != null && object instanceof InstrumentModel) {
			sameSame = this.name == ((InstrumentModel) object).name;
			System.out.println(this.name + " " + ((InstrumentModel) object).name);
		}
		
		return sameSame;
	}
}
