package tuner.controller;
import tuner.model.InstrumentModel;
import tuner.view.InstrumentView;
import tuner.view.JFrameView;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

public class InstrumentController extends AbstractController {
	public InstrumentController(){
		setModel(new InstrumentModel());
		setView(new InstrumentView((InstrumentModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
		}
	
	public ArrayList<InstrumentModel> instruments = new ArrayList<InstrumentModel>();
	
	public static TunerController tuner = new TunerController();
	public static JTextField InstrumentName = new JTextField(); 
	public static InstrumentModel curInstrument;
	
	public static DefaultListModel instrumentListModel = new DefaultListModel();
	public static JList instrumentList = new JList(instrumentListModel);	
	
	public static String getInstrName() {
		return InstrumentName.getText();
	}
	
	public static void newString(String strValue) {
		JTextField newString = new JTextField();
		newString.setText(strValue);
		InstrumentView.stringPanel.add(newString, null);
		
		InstrumentView.stringPanel.validate();
	}
	
	public static void remString() {
		int index = InstrumentView.stringPanel.getComponentCount() - 1;
		curInstrument.remTone(index);
		InstrumentView.stringPanel.remove(index);
		InstrumentView.stringPanel.validate();
	}
	
	public static void addString(String strValue) {
		curInstrument.addTone(strValue);
		
		newString(strValue);
	}
	
	public static void setInstrName(String name) {
		InstrumentName.setText(name);
	}
	
	public static void remInstr(int index) {
		instrumentListModel.remove(index);
	}
	
	public static void changeInstr(InstrumentModel newInstr) {
		InstrumentView.stringPanel.removeAll();
		
		for(String tone : newInstr.getTones()) {
			newString(tone);
		}
	}
	
	public static int getSelectedIndex() {
		return instrumentList.getSelectedIndex();
	}
	
	public static void addInstrument(String name) {
		instrumentListModel.addElement(name);
		curInstrument = new InstrumentModel();
		curInstrument.setName(name);
	}
	
	public void operation(String option) {
		if (option == "Save") {
			String name = getInstrName();
			addInstrument(name);
		}
		else if (option == "Delete") {
			int selectedIndex = getSelectedIndex();
			remInstr(selectedIndex);
		}
		else if (option == "New") {
			setInstrName("NewInstrument");
		}
		else if (option == "+") {
			addString("Z");
		}
		else if (option == "-") {
			remString();
		}
	}
}

