package gui;

import java.awt.EventQueue;
import java.awt.Font;
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
	private boolean isStarted = false;
	private LinkedList<String> logList = new LinkedList<String>();
	private Serwer serwer = new Serwer();
	private JButton btnDane;
	private JLabel lblSerwerStatus;
	private JLabel lblClientsNumb;
	private JLabel lblLogged;


	/**
	 * Create the frame.
	 */
	public ServerForm() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		
		lblSerwerStatus = new JLabel("Serwer wy\u0142aczony");
		
		btnDane = new JButton("Refresh");
		btnDane.addActionListener(this);
		
		lblClientsNumb = new JLabel("Liczba klient\u00F3w");
		
		lblLogged = new JLabel("W tym zalogowanych");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnStart)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblSerwerStatus)
							.addPreferredGap(ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
							.addComponent(btnDane)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLogged)
								.addComponent(lblClientsNumb))
							.addGap(81))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(lblSerwerStatus)
						.addComponent(btnDane))
					.addGap(18)
					.addComponent(lblClientsNumb)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLogged)
					.addContainerGap(313, Short.MAX_VALUE))
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
	
	public void clean(){
		serwer.stopSerwer();
		serwer.clean();
	}
	
	/*public void loadLogList(){
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
	}*/
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == btnStart){
			if(!isStarted()){
				//serwer=new Serwer();
				//if(stopServer())
					startServer();
					
					serwer = new Serwer();
					if(serwer.prepare()){
						serwer.startSerwer();
						serwer.start();
						btnStart.setText("STOP");
						lblSerwerStatus.setText("Serwer WLACZONY");
						//lblSerwerStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					this.repaint();
			} else {
				if(isStarted())
					stopServer();
					btnStart.setText("START");
					serwer.stopSerwer();
					serwer.clean();
					//btnStart.setText("stop");
					lblSerwerStatus.setText("Serwer WYLACZONY");
					//lblSerwerStatus.setFont(new Font("Arial", Font.BOLD, 12));
					
					//JOptionPane.showMessageDialog(null, serwer.getSerwerStatus());
					//System.out.println("Alive "+serwer.isAlive());
					
			}
			
		}if(z==btnDane){
			lblClientsNumb.setText("Liczba klientów: "+serwer.getNumberClients());
			lblLogged.setText("W tym zalogowanych: "+serwer.getLoginListSize());
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
								//frame.clean();
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
