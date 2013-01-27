package gui;



import gui.panels.LoginDialog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import stale.KindRestriction;

public class PanelNews extends JPanel implements ActionListener {

//######################### CONSTRUCTOR #########################
	private static final long serialVersionUID = 1L;
	private LoginDialog dialogLogowania;
	private JFrame owner;
	private JPanel panel;
	private int newsNumber = 0;
	private MigLayout ml;
	private boolean reset=false;
	private final JScrollPane scrollPane;
	private Events events = Events.getInstance();

	/**
	 * Create the panel.
	 */
	public PanelNews(JFrame owner) {
		this.owner = owner;

		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
		);
		
		panel = new JPanel();
		panel.setFont(new Font("Tekton Pro", Font.BOLD, 14));
		scrollPane.setViewportView(panel);
		ml = new MigLayout("", "[475.00px:475.00px,grow]", "[]");
		panel.setLayout(ml);
		
		//addNews("Aktualnosc 1", new Date(), "Zdzichu Kasprowicz", 56,"Aktualność1\nline2\nline3\nline4\nline5");
		//addNews("test News 2", new Date(), "Zdzichu Kowal", 56,"<p style=\"color:yellow; margin:0px; padding:0px;\"><b>Lorem ipsum - Zawarto\u015B\u0107 aktualno\u015Bci 2");
		//addNews("Aktualnosc 3", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:green; margin:0px; padding:0px;\"><b>Lorem ipsum - Zawarto\u015B\u0107 aktualno\u015Bci 3");
		//addNews("test News 4", new Date(), "Zdzichu Kowal", 97,"<p style=\"color:blue; margin:0px; padding:0px;\"><b>Lorem ipsum -\n Zawarto\u015B\u0107 aktualno\u015Bci 4\n linijka 3\nlinijka 4\nlinijka 5");
		setLayout(groupLayout);
		
		//JOptionPane.showMessageDialog(null, ((MigLayout)panel.getLayout()).getRowConstraints());

	}
//######################### FUNCTIONS #########################
	/**
	 * @param constraint - ograniczenie "[[100px:]100px[,grow]]"
	 * @return void - dodaje domyślne rozmiary w pionie dla nowego newsu
	 */
	private synchronized void addRowConstraint(String constraint){
		String rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		if(rowConstraints.equals("[]"))
			((MigLayout)panel.getLayout()).setRowConstraints( constraint );
		else
			((MigLayout)panel.getLayout()).setRowConstraints( rowConstraints+constraint );
	}
	
	/**
	 * @param constraint - ograniczenie "[[100px:]100px[,grow]]"
	 * @return void - dodaje domy�lne rozmiary w pionie dla nowego newsu
	 */
	private synchronized void editRowConstraint(int constraint, int number){
		//JOptionPane.showMessageDialog(null, ((MigLayout)panel.getLayout()).getRowConstraints());
		String rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		String constraints[] = rowConstraints.substring(1, rowConstraints.length()-1).replace("][", "##").split("##");
		constraints[number] = "100px:"+Integer.toString(constraint)+"px";
		rowConstraints = "";
		for(int i=0;i<constraints.length;i++){
			rowConstraints +="["+constraints[i]+"]";
		}
		((MigLayout)panel.getLayout()).setRowConstraints( rowConstraints );
		//JOptionPane.showMessageDialog(null, ((MigLayout)panel.getLayout()).getRowConstraints());
		panel.repaint();
	}
	
	/**
	 * @param subiect - Temat / Tytuł newsu
	 * @param data - data dodania newsu
	 * @param ksiadz - Imie i Nazwisko księdza dodającego news
	 * @param contentHeight - wysokość[px] zawartości newsu (min 100px)
	 * @param content - Zawartość newsu w kodzie html
	 * @return <b>void</b> - tworzy nowego newsa na ko�ńcu listy newsów (newsy należy dodawać od najnowszego - do najsterszego);
	 */
	public void cleanList(){
		newsNumber=0;
		reset=false;
		panel.removeAll();
		//scrollPane.setViewportView(panel);
		((MigLayout)panel.getLayout()).setRowConstraints("[100px:"+(0)+"px]");
		
	}
	
	public void addNews(String subiect, Date data, String ksiadz, int contentHeight, String content){
		
		newsNumber++;
		final int newsNumber_ = newsNumber-1;
		addRowConstraint("[100px:"+(contentHeight+44)+"px]");
		final JPanel panel_news = new JPanel();
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClick(newsNumber_);
			}
		};		
		panel_news.addMouseListener(mouseAdapter);
		panel_news.setMinimumSize(new Dimension(10, 100));
		panel_news.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_news.setAutoscrolls(true);
		panel.add(panel_news, "cell 0 "+(newsNumber-1)+",grow");

		JLabel lblTitle = new JLabel(subiect);
		lblTitle.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 18));
		
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		JLabel lblDate = new JLabel(formatter.format(data)); //data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900)+" "+data.getHours()+":"+data.getMinutes()
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblKsName = new JLabel("Ks. "+ksiadz);
		lblKsName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnDelete = new JButton("");
		if(events.getRestriction()>KindRestriction.LOGED_R)
			btnDelete.setVisible(true);
		else
			btnDelete.setVisible(false);
		
		// ################# USUWANIE NEWSA ########################
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć News?", "Usówanie Newsa", JOptionPane.YES_NO_OPTION);
				if(answer == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(null, "Usunięto news nr"+newsNumber_,"Usówanie Newsa",JOptionPane.INFORMATION_MESSAGE);
					//Events events = Events.getInstance();
					//events.deleteNews(newsNumber_);
					// newsNumber_ - numer newsa na liście wyświetlanej (numerowane od 0);
					//subiect, data, ksiadz, contentHeight, content - informacje podawane podczas tworzenia
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(PanelNews.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose.gif")));
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.addMouseListener(mouseAdapter);
		editorPane.setText(content);
		editorPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editorPane.setEditable(false);
		
		GroupLayout gl_panel_news = new GroupLayout(panel_news);
		gl_panel_news.setHorizontalGroup(
			gl_panel_news.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_news.createSequentialGroup()
					.addGap(3)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
					.addGroup(gl_panel_news.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_news.createSequentialGroup()
							.addGap(72)
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_news.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblKsName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(2))
				.addComponent(editorPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
		);
		gl_panel_news.setVerticalGroup(
			gl_panel_news.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_news.createSequentialGroup()
					.addGroup(gl_panel_news.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_news.createSequentialGroup()
							.addGroup(gl_panel_news.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_news.createSequentialGroup()
									.addGap(1)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
							.addGap(2)
							.addComponent(lblKsName, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_news.createSequentialGroup()
							.addGap(7)
							.addComponent(lblTitle)))
					.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
					.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
		);
		panel_news.setLayout(gl_panel_news);
	}

	
	/**
	 * @param news
	 * @return <b>void</b> - tworzy nowego newsa na końcu listy newsów (newsy należy dodawać od najnowszego - do najsterszego);
	 */
	public void addNews(News news){
		addNews(news.getSubiect(), news.getData(), news.getKsiadz(), news.getContentHeight(), news.getContent());
	}
	
	public void onClick(int numer){
		//JOptionPane.showMessageDialog(null, ((JTextPane)((JPanel)panel.getComponent(numer)).getComponent(3)).getClass().getName());
		int i;
		for(i=0;i<((JPanel)panel.getComponent(numer)).getComponentCount();i++)
			if(((JPanel)panel.getComponent(numer)).getComponent(i).getClass().getName().compareTo("javax.swing.JEditorPane") == 0)
				break;
		int height = ((JEditorPane)((JPanel)panel.getComponent(numer)).getComponent(i)).getHeight();
		if(panel.getComponent(numer).getHeight()<=100){
			//JOptionPane.showMessageDialog(null, height);
			editRowConstraint(height+45,numer);
		}else{
			//JOptionPane.showMessageDialog(null, "FALSE");
			editRowConstraint(100,numer);
		}
		panel.resize(panel.getWidth()+1, panel.getHeight());
	}
	
	public void deleteEnable(boolean answer){
		if(((JPanel)panel.getComponent(0)).getComponentCount()>3){
			int i,j;
			for(j=0;j<((JPanel)panel.getComponent(0)).getComponentCount();j++)
				if(((JPanel)panel.getComponent(0)).getComponent(j).getClass().getName().compareTo("javax.swing.JButton") == 0)
					break;
			for(i=0;i<panel.getComponentCount();i++)
				((JButton)((JPanel)panel.getComponent(i)).getComponent(j)).setVisible(answer);
		}
	}
	
	
	//@Override
	public void actionPerformed(ActionEvent e) {
		if(dialogLogowania==null)
			dialogLogowania = new LoginDialog(owner);
		dialogLogowania.setVisible(true);
		dialogLogowania.setFocus();
		
		if(dialogLogowania.isOK()){
			JOptionPane.showMessageDialog(owner, "Login: "+dialogLogowania.getLogin()+"\nHasło: "+dialogLogowania.getPassword());
		}		
	}
}














class News{
	/**
	 * @param subiect - Temat / Tytuł newsu
	 * @param data - data dodania newsu
	 * @param ksiadz - Imie i Nazwisko księdza dodającego news
	 * @param contentHeight - wysokość[px] zawartości newsu (min 100px)
	 * @param content - Zawartość newsu w kodzie html
	 * @return <b>void</b> - tworzy nowego newsa na końcu listy newsów (newsy należy dodawać od najnowszego - do najsterszego);
	 */
	public News(String subiect, Date data, String ksiadz, int contentHeight, String content) {
		super();
		this.subiect = subiect;
		this.data = data;
		this.ksiadz = ksiadz;
		this.contentHeight = contentHeight;
		this.content = content;
	}
	
	private String subiect;
	private Date data;
	private String ksiadz;
	private int contentHeight;
	private String content;
	
	/**
	 * pobiera Temat newsa
	 * @return <b>String</b> - Temat / Tytuł newsu
	 */
	public String getSubiect() {
		return subiect;
	}
	/**
	 * ustawia Temat newsa
	 * @param subiect - Temat / Tytuł newsu
	 */
	public void setSubiect(String subiect) {
		this.subiect = subiect;
	}
	/**
	 * pobiera date dodania newsu
	 * @return <b>Date</b> - data dodania newsu
	 */
	public Date getData() {
		return data;
	}
	/**
	 * ustawia date dodania newsu
	 * @param data - data dodania newsu
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * ustawia Imie i Nazwisko księdza dodaj�cego news
	 * @return <b>String</b> - Imie i Nazwisko księdza dodaj�cego news
	 */
	public String getKsiadz() {
		return ksiadz;
	}
	/**
	 * pobiera Imie i Nazwisko księdza dodającego news
	 * @param ksiadz - Imie i Nazwisko księdza dodającego news
	 */
	public void setKsiadz(String ksiadz) {
		this.ksiadz = ksiadz;
	}
	/**
	 * ustawia wysokość[px] zawartości newsu (min 100px)
	 * @return <b>int</b> - wysokość[px] zawartości newsu (min 100px)
	 */
	public int getContentHeight() {
		return contentHeight;
	}
	/**
	 * pobiera wysokość[px] zawartości newsu (min 100px)
	 * @param contentHeight - wysokość[px] zawartości newsu (min 100px)
	 */
	public void setContentHeight(int contentHeight) {
		this.contentHeight = contentHeight;
	}
	/**
	 * ustawia zawartość newsu w kodzie html
	 * @return <b>String</b> - Zawartość newsu w kodzie html
	 */
	public String getContent() {
		return content;
	}
	/**
	 * pobiera zawartość newsu w kodzie html
	 * @param content - Zawartość newsu w kodzie html
	 */
	public void setContent(String content) {
		this.content = content;
	}	
}













class NewsList{	
	ArrayList<News> lista = new ArrayList<News>();
	
	public NewsList() {
		super();
	}
	
	public NewsList(ArrayList<News> lista) {
		super();
		this.lista = lista;
	}
	
	/**
	 * tworzy liste newsów do testów
	 * @param num - liczba newsów do wygenerowania
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void generateNewsList(int num) throws IOException, ClassNotFoundException {
		for(int i=1 ;i<=num;i++){
			if(i%5==1)addNews(new News("Aktualnosc 1", new Date(), "Zdzichu Kasprowicz", 56,"Lorem ipsum - Zawarto\u015B\u0107 aktualno\u015Bci 1"));
			if(i%5==2)addNews(new News("test News 2", new Date(), "Zdzichu Kowal", 56,"Lorem ipsum - Zawarto\u015B\u0107 aktualno\u015Bci 2"));
			if(i%5==3)addNews(new News("Aktualnosc 3", new Date(), "Zdzichu Kasprowicz", 56,"Lorem ipsum - Zawarto\u015B\u0107 aktualno\u015Bci 3"));
			if(i%5==4)addNews(new News("test News 4", new Date(), "Zdzichu Kowal", 97,"Lorem ipsum -\n Zawarto\u015B\u0107 aktualno\u015Bci 4\n linijka 3\nlinijka 4\nlinijka 5"));
			if(i%5==0)addNews(new News("Aktualnosc 5", new Date(), "Zdzichu Kasprowicz", 56,"Lorem ipsum - Zawarto\u015B\u0107 aktualno\u015Bci 5"));
		}
	}	
	
	public void addNews(News news){
		lista.add(news);
	}
	
	public News getNews(int index){
		return lista.get(index);
	}
	
	public ArrayList<News> getNewsList(){
		return lista;
	}
	
	public void cleanList(){
		lista.removeAll(lista);
	}
	
	public void addNewsListToPanel(PanelNews panelNews){
		ListIterator<News> iterator = lista.listIterator();
		while(iterator.hasNext()){
			panelNews.addNews(iterator.next());
		}
	}
	
	public boolean addNewsToPanel(PanelNews panelNews, int index){
		if(index<lista.size()){
			panelNews.addNews(lista.get(index));
			return true;
		}
		return false;
	}
}
