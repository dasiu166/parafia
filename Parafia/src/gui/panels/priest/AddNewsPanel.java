package gui.panels.priest;

import gui.Events;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Date;

import javax.swing.JScrollPane;

import obsluga.Actuals;
import obsluga.Priest;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class AddNewsPanel extends JPanel implements ActionListener {
	private JTextField textTitle;
	private JEditorPane editorContent;
	private JButton btnSubmit;
	private JButton btnReset;
	private Events events = Events.getInstance();

	/**
	 * Create the panel.
	 */
	public AddNewsPanel(JFrame owner) {
		setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 100, 0)));
		
		
		btnSubmit = new JButton("Dodaj");
		btnSubmit.setIcon(new ImageIcon(AddNewsPanel.class.getResource("/icons/Add-icon.png")));
		btnSubmit.addActionListener(this);
		
		btnReset = new JButton("Wyczy\u015B\u0107");
		btnReset.setIcon(new ImageIcon(AddNewsPanel.class.getResource("/icons/reset.png")));
		btnReset.addActionListener(this);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset)
							.addGap(17))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSubmit))
					.addContainerGap())
		);
		
		editorContent = new JEditorPane();
		scrollPane.setViewportView(editorContent);
		
		JLabel lblNowaAktualno = new JLabel("Nowa Aktualno\u015B\u0107");
		lblNowaAktualno.setForeground(new Color(0, 100, 0));
		lblNowaAktualno.setHorizontalAlignment(SwingConstants.CENTER);
		lblNowaAktualno.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblTitle_ = new JLabel("Tytu\u0142:");
		lblTitle_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		
		JLabel lblContent = new JLabel("Tre\u015B\u0107:");
		lblContent.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNowaAktualno, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(21)
							.addComponent(lblTitle_, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textTitle, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addGap(84))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNowaAktualno)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTitle_)
								.addComponent(textTitle))
							.addGap(15))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblContent)
							.addGap(6))))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object z = arg0.getSource();
		Actuals akt = new Actuals();
		Date d = new Date();
		Priest p = new Priest();
		p=events.getPriest();
		d.getDate();
		if(z==btnSubmit){
			if(textTitle.getText().length()<1){
				JOptionPane.showMessageDialog(null, "Za krótki tytuł (min. 1 znak)");
				return;
			} else akt.setSubject(textTitle.getText());
			
			
			if(editorContent.getText().length()<1){
				JOptionPane.showMessageDialog(null, "Za krótki wpis (min. 1 znak)");
				return;
			} else akt.setDescribe(editorContent.getText());
			
			akt.setAddDate(d);
			akt.setPriestPesel(p.getPesel());
			try {
				events.dodajAktualnosc(akt);
				if(events.getLastErr().equals("OK+")){
					JOptionPane.showMessageDialog(null, "News dodany");
				} else {
					JOptionPane.showMessageDialog(null, "Blad dodawania\n" +
							events.getLastErrData());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(z==btnReset){
			textTitle.setText("");
			editorContent.setText("");
		}
		
	}
}
