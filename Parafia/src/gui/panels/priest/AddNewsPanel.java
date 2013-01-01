package gui.panels.priest;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.Font;

public class AddNewsPanel extends JPanel {
	private JTextField textTitle;

	/**
	 * Create the panel.
	 */
	public AddNewsPanel(JFrame owner) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);
		
		JLabel lblTitle_ = new JLabel("Tytu\u0142:");
		lblTitle_.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle_.setBounds(110, 55, 100, 14);
		add(lblTitle_);
		
		textTitle = new JTextField();
		textTitle.setBounds(220, 52, 200, 20);
		add(textTitle);
		textTitle.setColumns(10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editorPane.setBounds(30, 108, 438, 278);
		add(editorPane);
		
		JLabel lblContent = new JLabel("Tre\u015B\u0107:");
		lblContent.setBounds(30, 95, 46, 14);
		add(lblContent);
		
		JLabel lblWidoczny = new JLabel("Widoczny:");
		lblWidoczny.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidoczny.setBounds(110, 80, 100, 14);
		add(lblWidoczny);
		
		ButtonGroup visibleButtonGroup = new ButtonGroup();
		
		JRadioButton rdbtnTak = new JRadioButton("Tak");
		visibleButtonGroup.add(rdbtnTak);
		rdbtnTak.setSelected(true);
		rdbtnTak.setBounds(220, 79, 46, 23);
		add(rdbtnTak);
		
		JRadioButton rdbtnNie = new JRadioButton("Nie");
		visibleButtonGroup.add(rdbtnNie);
		rdbtnNie.setBounds(268, 79, 46, 23);
		add(rdbtnNie);
		
		JLabel lblNowaAktualno = new JLabel("Nowa Aktualno\u015B\u0107");
		lblNowaAktualno.setHorizontalAlignment(SwingConstants.CENTER);
		lblNowaAktualno.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNowaAktualno.setBounds(30, 22, 438, 20);
		add(lblNowaAktualno);
		
		JButton btnSubmit = new JButton("Dodaj");
		btnSubmit.setBounds(379, 397, 89, 23);
		add(btnSubmit);
		
		JButton btnReset = new JButton("Wyczy\u015B\u0107");
		btnReset.setBounds(280, 397, 89, 23);
		add(btnReset);

	}
}
