package gui.panels.parishioner;

import gui.Events;
import gui.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.util.*;
import java.io.*;
import obsluga.*;
import pomoce.Pomoc;
import stale.KindQuery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NewOrderPanel extends JPanel implements ActionListener {
	private JComboBox comboOrderType;
	private JComboBox cbPriestList;
	private JTextPane textMessage;
	private JDateChooser dateStart;
	private JComboBox comboHour;
	private JButton btnSend;
	private JButton btnReset;
	private Events events = Events.getInstance();
	/**
	 * Create the panel.
	 */
	public NewOrderPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblOrderType_ = new JLabel("Typ Zamowienia:");
		lblOrderType_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboOrderType = new JComboBox();
		/*Grzesiek*/
		
		JLabel lblDateStart_ = new JLabel("Data:");
		lblDateStart_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dateStart = new JDateChooser(true);
		
		btnSend = new JButton("Wy\u015Blij");
		btnSend.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		JLabel lblMessage_ = new JLabel("Wiadomo\u015B\u0107:");
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblHour_ = new JLabel("Godzina");
		
		comboHour = new JComboBox();
		comboHour.addItem("<Wybierz>");
		comboHour.addItem("08:00");
		comboHour.addItem("10:00");
		comboHour.addItem("12:00");
		comboHour.addItem("14:00");
		comboHour.addItem("16:00");
		
		JLabel lblKsidz = new JLabel("Ksi\u0105dz:");
		
		cbPriestList = new JComboBox();
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(190)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addComponent(lblMessage_, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))))
					.addGap(40))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOrderType_, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboOrderType, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDateStart_, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHour_)
								.addComponent(lblKsidz))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dateStart, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(comboHour, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbPriestList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderType_, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboOrderType, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKsidz)
						.addComponent(cbPriestList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addComponent(lblMessage_, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDateStart_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboHour, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHour_))
							.addGap(12)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReset)
						.addComponent(btnSend))
					.addGap(29))
		);
		
		textMessage = new JTextPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(textMessage, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(textMessage, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	
	public void setOrderList(LinkedList<Event> le){
		
		Iterator<Event> it = le.iterator();
		String kindOrder[] = new String[le.size()+1];
		kindOrder[0] = "<wybierz Typ>";
		int index=1;
		  while(it.hasNext()){
			  Event ee = it.next();
			  kindOrder[index] = ee.getId()+"  "+ee.getName();
			  index++;
		  }
		
		comboOrderType.setModel(new DefaultComboBoxModel(kindOrder));
	}
	
	public void setPriestList(LinkedList<Priest> lp){
		Iterator<Priest> it = lp.iterator();
		String strPriest[] = new String[lp.size()+1];
		strPriest[0] = "<wybierz ksiedza>";
		int index=1;
		while(it.hasNext()){
			Priest prr = it.next();
			strPriest[index] =prr.getName()+" "+prr.getSurName();
			index++;
		}
		
		cbPriestList.setModel(new DefaultComboBoxModel(strPriest));
		
	}
	
	public int getSelectedOrderItemIndex(){
		return comboOrderType.getSelectedIndex();
	}
	
	public int getSelectedPriestItemIndex(){
		return cbPriestList.getSelectedIndex();
	}
	
	public String getSelectedOrderItem(){
		return (String)comboOrderType.getSelectedItem();
	}
	
	public Date getDateStart(){
		return (!dateStart.isEmpty())?dateStart.getDate():null;
	}
	
	public int getSelectedHourIndex(){
		return comboHour.getSelectedIndex();
	}
	
	public String getSelectedHour(){
		return (String)comboHour.getSelectedItem();
	}
	
	public String getMessage(){
		return textMessage.getText();
	}
	
	public void reset(){
		comboOrderType.setSelectedIndex(0);
		cbPriestList.setSelectedIndex(0);
		dateStart.setEmpty();
		comboHour.setSelectedIndex(0);
		textMessage.setText("");
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object z = arg0.getSource();
		
		if(z==btnSend){
			//tymczasowo ustwiam ksiedza na sztywno, alepozniej musi byc pobierany z listy
			Priest p = new Priest();
			
			try{
			p.setPesel(Pomoc.validatePriestName(events.getClient().getPriestList(), 
					this.getSelectedPriestItemIndex()));
			} catch (IndexOutOfBoundsException s){
				JOptionPane.showMessageDialog(null, "Wybierz ksiedza");
				return;
			}
			
			//JOptionPane.showMessageDialog(null, p.getPesel());
			
			Order o = new Order();
			o.setSender(events.getParishioner());
			o.setExecutor(p);
			
			
			
			if(getMessage().length()<10){
				JOptionPane.showMessageDialog(null, "Za krótki opis - co najmniej 10 znaków");
				return;
			} else o.setDescribe(getMessage());//wiadomosc
			
			
			
			if(getDateStart()==null){
				JOptionPane.showMessageDialog(null, "Podaj date");
				return;
			} else {
			o.setBeginDate(Pomoc.podajDate(getDateStart()
					.toLocaleString().substring(0,10)+" "+getSelectedHour()));
			//JOptionPane.showMessageDialog(null, o.getBeginDate().toLocaleString());
			o.setEndDate(Pomoc.podajDate(getDateStart()
					.toLocaleString().substring(0,10)+" "+getSelectedHour()));
			}
			
			
			
			if(getSelectedOrderItemIndex()<1){
				JOptionPane.showMessageDialog(null, "Wybierz typ zamowienia");
				return;
			} else
			o.setEvent(getSelectedOrderItem().substring(0, 1));
			//JOptionPane.showMessageDialog(null,getSelectedOrderItem().substring(0, 1) );

			try{
			
			events.zlozZamowienie(o);
			JOptionPane.showMessageDialog(null, events.getLastErrData());
			reset();
			
			} catch (IOException e){
				
			} catch (ClassNotFoundException ee){
				
			}
		
		}else if(z==btnReset){
			reset();
		}
		
	}
}
