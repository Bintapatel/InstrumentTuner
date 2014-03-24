package tuner.view;
import javax.swing.*; 
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*;
import tuner.controller.TunerController;
import tuner.controller.InstrumentController;
import tuner.model.InstrumentModel;
import tuner.model.ModelEvent;

public class InstrumentView extends JFrameView {
	
	public static JPanel stringPanel = new JPanel();

	public InstrumentView(InstrumentModel model, InstrumentController controller) { 

		super(model, controller); 

		InstrumentController.InstrumentName.setText("New Instrument"); 
		this.getContentPane().add(InstrumentController.InstrumentName, BorderLayout.NORTH); 
		
		stringPanel.setLayout(new GridLayout(1, 6));
		this.getContentPane().add(stringPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();     
		Handler handler = new Handler(); 
		JButton saveButton = new JButton("Save"); 
		saveButton.addActionListener(handler); 
		JButton deleteButton = new JButton("Delete"); 
		deleteButton.addActionListener(handler); 
		JButton newButton = new JButton("New"); 
		newButton.addActionListener(handler); 
		JPanel tunerPanel = new JPanel();
		tunerPanel.add(InstrumentController.tuner.frame.getContentPane());

		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(saveButton, c);
		
		c.gridx = 1;
		c.gridy = 0;
		buttonPanel.add(deleteButton, c);
		
		c.gridx = 2;
		c.gridy = 0;
		buttonPanel.add(newButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		buttonPanel.add(tunerPanel, c);
		
		JPanel stringerPanel = new JPanel();
		Handler otherHandler = new Handler();
		JButton addString = new JButton("+");
		addString.addActionListener(otherHandler);
		JButton remString = new JButton("-");
		remString.addActionListener(otherHandler);
		
		stringerPanel.setLayout(new GridLayout(2, 1));
		this.getContentPane().add(stringerPanel, BorderLayout.EAST);
		
		stringerPanel.add(addString, null);
		stringerPanel.add(remString, null);

		ListSelectionModel listSelectionModel = InstrumentController.instrumentList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
		
		InstrumentController.instrumentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		InstrumentController.instrumentList.setLayoutOrientation(JList.VERTICAL);
		this.getContentPane().add(InstrumentController.instrumentList, BorderLayout.WEST);
	
		pack();
	 }

	public void modelChanged(ModelEvent event) {
	String msg = event.getAmount() + "";
	InstrumentController.InstrumentName.setText(msg);
	 }

	class Handler implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			((InstrumentController)getController()).operation(e.getActionCommand()); 
	    } 
	}
	

	public class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
 
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting(); 
 
            if (lsm.isSelectionEmpty()) {
                System.out.println("empty");
            } else {
            	if(!isAdjusting) {
            		InstrumentController.curInstrument = (InstrumentModel)controller.instruments.get(lsm.getLeadSelectionIndex());
            		InstrumentController.changeInstr(InstrumentController.curInstrument);
	                
            	}
            }
        }
    }
	
	
	
	private static InstrumentController controller = new InstrumentController(); 
	
	

	public static void main(String [] args) { 
		InstrumentController.curInstrument = new InstrumentModel();
		
		InstrumentModel guitar = new InstrumentModel();
		InstrumentModel violin = new InstrumentModel();
		
		guitar.setName("Guitar");
		guitar.addTone("E");
		guitar.addTone("A");
		guitar.addTone("D");
		guitar.addTone("G");
		guitar.addTone("B");
		guitar.addTone("e");
		
		violin.setName("Violin");
		violin.addTone("G");
		violin.addTone("D");
		violin.addTone("A");
		violin.addTone("E");
		
		controller.instruments.add(guitar);
		controller.instruments.add(violin);
		
		for (InstrumentModel instr : controller.instruments) {
			InstrumentController.instrumentListModel.addElement(instr.getName());
		}
		
		try { InstrumentController.tuner.initializeTuner(); } catch (Exception e) {};
	}

}

