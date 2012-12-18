package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class PAktualnosci extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DialogLogowania dialogLogowania;
	private JFrame owner;

	/**
	 * Create the panel.
	 */
	public PAktualnosci(JFrame owner) {
		this.owner = owner;
		setLayout(null);
		
		JButton bLogowania = new JButton("Logowanie");
		bLogowania.addActionListener(this);
		bLogowania.setBounds(83, 46, 89, 23);
		add(bLogowania);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(dialogLogowania==null)
			dialogLogowania = new DialogLogowania(owner);
		dialogLogowania.setVisible(true);
		dialogLogowania.setFocus();
		
		if(dialogLogowania.isOK()){
			JOptionPane.showMessageDialog(owner, "Login: "+dialogLogowania.getLogin()+"\nHas³o: "+dialogLogowania.getPassword());
		}		
	}
}
