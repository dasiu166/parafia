package gui.panels;

import gui.CardLayoutExp;
import gui.Events;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import obsluga.Parishioner;
import obsluga.Priest;
import stale.KindRestriction;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private CardLayout cl;
    private LoginDialog dialogLogowania;
    private Events event = Events.getInstance();
	/**
	 * Create the panel.
	 */
	public LoginPanel(final CardLayoutExp owner) {
        setSize(150, 70);
        cl = new CardLayout();
        setLayout(cl);
        final JPanel ja = this;
        							// Panel przed Zalogowaniem
        JPanel unlogged = new JPanel();
        add(unlogged, "unlogged");
        unlogged.setLayout(new BorderLayout(0, 0));
        
        JButton bZaloguj = new JButton("Zaloguj");
        bZaloguj.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/login.png")));
        unlogged.add(bZaloguj);
        
        							// Panel po zalogowaniu
        JPanel logged = new JPanel();
        add(logged, "logged");
        logged.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblJesteZalogowany = new JLabel("Jeste\u015B zalogowany jako:");
        lblJesteZalogowany.setHorizontalAlignment(SwingConstants.CENTER);
        logged.add(lblJesteZalogowany);
        
        final JLabel lblUserName = new JLabel("Nazwa U\u017Cytkownika");
        lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
        logged.add(lblUserName);
        
        JButton bLogOut = new JButton("Wyloguj");
        bLogOut.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/logouticon.png")));
        // akcja po wci�ni�ciu przycisku ### WYLOGUJ ###
        bLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(event.wyloguj()){
        			owner.resetSettings();
	        		cl.show(ja, "unlogged");
	        		//JOptionPane.showMessageDialog(null, "Wylogowano");
        		} else {
        			JOptionPane.showMessageDialog(null, event.getUser().getData(), "B��d", JOptionPane.ERROR_MESSAGE);
        			//JOptionPane.showMessageDialog(null, "B��d wylogowania", "B��d", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        logged.add(bLogOut);
        
        // akcja po wci�ni�ciu przycisku ### ZALOGUJ ###
        bZaloguj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(dialogLogowania==null)
        			dialogLogowania = new LoginDialog(null);
        		dialogLogowania.setVisible(true);
        		dialogLogowania.setFocus();
        		
        		if(dialogLogowania.isOK()){
        			
        			if(event.zaloguj(dialogLogowania.getLogin(), dialogLogowania.getPassword())){
        				
        				if(event.getRestriction()==KindRestriction.LOGED_R){
        				Parishioner p = event.getParishioner();
        				lblUserName.setText(p.getName()+" "+p.getSurName());
        				}
        				
        				if (event.getRestriction()>=KindRestriction.WORKS_R){
        					Priest pr = event.getPriest();
        					lblUserName.setText(pr.getName()+" "+pr.getSurName());
        				}
        				//JOptionPane.showMessageDialog(null, "Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\nAdres: "+p.getAdress().getCity()+"\nPesel: "+p.getPesel());
        				owner.loginUser();
        				cl.show(ja, "logged");
        				//JOptionPane.showMessageDialog(null, "Zosta�e� pomy�lnie zalogowany");
        			} else {
        				JOptionPane.showMessageDialog(null, event.getUser().getData());
        			}
        		}
        	}
        });
	}
}
