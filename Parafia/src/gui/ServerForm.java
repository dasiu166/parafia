package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import serwer.Serwer;

public class ServerForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnStart;
	private JTextPane textPane;
	private boolean isStarted = false;
	private LinkedList<String> logList = new LinkedList<String>();


	/**
	 * Create the frame.
	 */
	public ServerForm() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		
		JLabel lblLog = new JLabel("Log:");
		
		textPane = new JTextPane();
		
		
		textPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addComponent(lblLog)
						.addComponent(btnStart))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(btnStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLog)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public boolean isStarted(){
		return isStarted;
	}
	
	private boolean startServer(){
		
		isStarted = true;
		return true;
	}
	
	private boolean stopServer(){
		isStarted = false;
		return true;
	}
	
	public void loadLogList(){
		textPane.setText("");
		String text = "";
		ListIterator<String> iterator = logList.listIterator();
		while(iterator.hasNext()){
			text += iterator.next()+"\n";
		}
	}
	public void addLog(String log){
		logList.add(log);
		textPane.setText(textPane.getText()+log+"\n");
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == btnStart){
			if(isStarted()){
				if(stopServer())
					btnStart.setText("start");
			} else {
				if(startServer())
					btnStart.setText("stop");
			}
			
		}
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final ServerForm frame = new ServerForm();
					WindowListener l = new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							if(frame.isStarted())
								if(!frame.stopServer()){
									int odt = JOptionPane.showConfirmDialog(null, "B³¹d w zatrzymywaniu pracy servera!\nCzy zignorowaæ b³¹d i zamkn¹æ Aplikacje?","Server stop error",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
									if(odt == JOptionPane.NO_OPTION){
										return;
									}
								}
							System.exit(0);	
						}
					};
					frame.addWindowListener(l);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
