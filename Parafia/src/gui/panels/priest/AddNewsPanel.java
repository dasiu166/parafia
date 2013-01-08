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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AddNewsPanel extends JPanel {
	private JTextField textTitle;

	/**
	 * Create the panel.
	 */
	public AddNewsPanel(JFrame owner) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblTitle_ = new JLabel("Tytu\u0142:");
		lblTitle_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblContent = new JLabel("Tre\u015B\u0107:");
		
		JLabel lblWidoczny = new JLabel("Widoczny:");
		lblWidoczny.setHorizontalAlignment(SwingConstants.RIGHT);
		
		ButtonGroup visibleButtonGroup = new ButtonGroup();
		
		JRadioButton rdbtnTak = new JRadioButton("Tak");
		visibleButtonGroup.add(rdbtnTak);
		rdbtnTak.setSelected(true);
		
		JRadioButton rdbtnNie = new JRadioButton("Nie");
		visibleButtonGroup.add(rdbtnNie);
		
		JLabel lblNowaAktualno = new JLabel("Nowa Aktualno\u015B\u0107");
		lblNowaAktualno.setHorizontalAlignment(SwingConstants.CENTER);
		lblNowaAktualno.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnSubmit = new JButton("Dodaj");
		
		JButton btnReset = new JButton("Wyczy\u015B\u0107");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNowaAktualno, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(108)
							.addComponent(lblTitle_, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(250)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(80)
									.addComponent(lblWidoczny, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(58)
									.addComponent(rdbtnNie, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(190)
									.addComponent(rdbtnTak, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE))))
					.addGap(27))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNowaAktualno)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTitle_))
						.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblContent))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblWidoczny))
								.addComponent(rdbtnNie)
								.addComponent(rdbtnTak))
							.addGap(6)
							.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReset)
						.addComponent(btnSubmit)))
		);
		setLayout(groupLayout);

	}
}
