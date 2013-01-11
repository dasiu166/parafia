package gui.panels.priest;

import gui.Events;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import obsluga.*;
import pomoce.Pomoc;

import java.util.*;

public class OrderDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 7706823427108133572L;
	private final JPanel contentPanel = new JPanel();
	private JButton okAbort;
	private JButton okAccept;
	private JButton cancelButton;
	private JEditorPane editorRepeat;
	private boolean accept = false;
	private boolean abort = false;
	private Order order;
	private LinkedList<obsluga.Event> eventList;

	
	/**
	 * Create the dialog.
	 */
	public OrderDialog(JFrame owner, Order order, LinkedList<obsluga.Event> le) {
		super(owner, "Rozpatrzenie Wniosku", true);
		setResizable(false);
		eventList=le;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				accept = false;
				abort = false;
			}
		});
		this.order = order;
		
		setBounds(100, 100, 490, 418);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFrom_ = new JLabel("Od:");
		lblFrom_.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrom_.setBounds(54, 11, 150, 14);
		contentPanel.add(lblFrom_);
		
		JLabel lblFrom = new JLabel("Brak Danych Zamawiaj¹cego");;
		if(order.getSender()!=null) 
			lblFrom = new JLabel(order.getSender().getName()+" "+order.getSender().getSurName());

		lblFrom.setBounds(214, 11, 220, 14);
		contentPanel.add(lblFrom);

		JLabel lblStatus_ = new JLabel("Status:");
		lblStatus_.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus_.setBounds(54, 86, 150, 14);
		contentPanel.add(lblStatus_);

		JLabel lblStatus = new JLabel(order.getStatus());
		lblStatus.setBounds(214, 86, 220, 14);
		contentPanel.add(lblStatus);
		
		JLabel lblType_ = new JLabel("Typ:");
		lblType_.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType_.setBounds(54, 61, 150, 14);
		contentPanel.add(lblType_);
		
		JLabel lblType = new JLabel(Pomoc.validateEventName(eventList,order.getEvent()));
		lblType.setBounds(214, 61, 220, 14);
		contentPanel.add(lblType);

		JLabel lblDate_ = new JLabel("Data:");
		lblDate_.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_.setBounds(54, 36, 150, 14);
		contentPanel.add(lblDate_);

		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		JLabel lblDate = new JLabel(formatter.format(order.getBeginDate()));
		lblDate.setBounds(214, 36, 220, 14);
		contentPanel.add(lblDate);
		
		JLabel lblMessage = new JLabel("Wiadomo\u015B\u0107:");
		lblMessage.setBounds(10, 106, 100, 14);
		contentPanel.add(lblMessage);
		
		JScrollPane scrollMessage = new JScrollPane();
		scrollMessage.setBounds(10, 123, 464, 100);
		contentPanel.add(scrollMessage);
		
		JEditorPane editorMessage = new JEditorPane();
		editorMessage.setEditable(false);
		editorMessage.setText(order.getDescribe());
		scrollMessage.setViewportView(editorMessage);
		
		JLabel lblRepeat = new JLabel("Odpowied\u017A:");
		lblRepeat.setBounds(10, 234, 100, 14);
		contentPanel.add(lblRepeat);
		
		JScrollPane scrollRepeat = new JScrollPane();
		scrollRepeat.setBounds(10, 250, 464, 100);
		contentPanel.add(scrollRepeat);
		
		editorRepeat = new JEditorPane();
		scrollRepeat.setViewportView(editorRepeat);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			okAbort = new JButton("Odrzu\u0107");
			okAbort.setActionCommand("OK");
			okAbort.addActionListener(this);
			buttonPane.add(okAbort);
		}
		{
			okAccept = new JButton("Akceptuj");
			okAccept.setActionCommand("OK");
			buttonPane.add(okAccept);
			okAccept.addActionListener(this);
			getRootPane().setDefaultButton(okAccept);
		}
		{
			cancelButton = new JButton("Anuluj");
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(this);
			buttonPane.add(cancelButton);
		}
	}
	
	public Order getOrder(){
		return order;
	}
	
	public boolean isAccepted(){
		return accept;
	}
	
	public boolean isAborted(){
		return abort;
	}
	
	public void setFocus(){
		editorRepeat.requestFocusInWindow();
	}
	
	public void setEventList(LinkedList<obsluga.Event> le){
		eventList = le;
		System.out.print("ORDERDIALOG LISTEVENT "+le.size());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == okAccept){
			accept = true;
			order.setStatus("Zaakceptowany");
			order.setDescribe(editorRepeat.getText());
		}else 
			accept = false;
		
		if(z == okAbort){
			abort = true;
			order.setStatus("Odrzucony");
			order.setDescribe(editorRepeat.getText());
		}else
			abort = false;
		
		setVisible(false);
	}
}
