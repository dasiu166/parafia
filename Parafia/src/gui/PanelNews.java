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

public class PanelNews extends JPanel implements ActionListener {

//######################### CONSTRUCTOR #########################
	private static final long serialVersionUID = 1L;
	private LoginDialog dialogLogowania;
	private JFrame owner;
	JPanel panel;
	int newsNumber = 0;
	MigLayout ml;
	boolean reset=false;
	final JScrollPane scrollPane;

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
		
		//addNews("Aktualnosc 1", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:red; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 1</p>");
		//addNews("test News 2", new Date(), "Zdzichu Kowal", 56,"<p style=\"color:yellow; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 2</p>");
		//addNews("Aktualnosc 3", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:green; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 3</p>");
		//addNews("test News 4", new Date(), "Zdzichu Kowal", 97,"<p style=\"color:blue; margin:0px; padding:0px;\"><b>Lorem ipsum</b> -<br /> Zawarto\u015B\u0107 aktualno\u015Bci 4<br /> linijka 3<br />linijka 4<br />linijka 5</p>");
		setLayout(groupLayout);
		
		//JOptionPane.showMessageDialog(null, ((MigLayout)panel.getLayout()).getRowConstraints());

	}
//######################### FUNCTIONS #########################
	/**
	 * @param constraint - ograniczenie "[[100px:]100px[,grow]]"
	 * @return void - dodaje domyœlne rozmiary w pionie dla nowego newsu
	 */
	private synchronized void addRowConstraint(String constraint){
		String rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		if(rowConstraints.equals("[]"))
			((MigLayout)panel.getLayout()).setRowConstraints( constraint );
		else
			((MigLayout)panel.getLayout()).setRowConstraints( rowConstraints+constraint );
	}
	
	/**
	 * @param subiect - Temat / Tytu³ newsu
	 * @param data - data dodania newsu
	 * @param ksiadz - Imie i Nazwisko ksiêdza dodaj¹cego news
	 * @param contentHeight - wysokoœæ[px] zawartoœci newsu (min 100px)
	 * @param content - Zawartoœæ newsu w kodzie html
	 * @return <b>void</b> - tworzy nowego newsa na koñcu listy newsów (newsy nale¿y dodawaæ od najnowszego - do najsterszego);
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
		addRowConstraint("[100px:"+(contentHeight+44)+"px]");
		final JPanel panel_news = new JPanel();
		panel_news.setMinimumSize(new Dimension(10, 100));
		panel_news.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_news.setAutoscrolls(true);
		panel.add(panel_news, "cell 0 "+(newsNumber-1)+",grow");
				
		final JEditorPane dtrpnContent = new JEditorPane();
		dtrpnContent.setContentType("text/html");
		dtrpnContent.setText(content);
		dtrpnContent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dtrpnContent.setEditable(false);		

		JLabel lblTitle = new JLabel(subiect);
		lblTitle.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 18));
		
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		JLabel lblDate = new JLabel(formatter.format(data)); //data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900)+" "+data.getHours()+":"+data.getMinutes()
		
		JLabel lblKsName = new JLabel("Ks. "+ksiadz);
		GroupLayout gl_panel_news = new GroupLayout(panel_news);
		gl_panel_news.setHorizontalGroup(
			gl_panel_news.createParallelGroup(Alignment.TRAILING)
				.addComponent(dtrpnContent, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
				.addGroup(gl_panel_news.createSequentialGroup()
					.addGap(5)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_news.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblDate)
						.addComponent(lblKsName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(5))
		);
		gl_panel_news.setVerticalGroup(
			gl_panel_news.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_news.createSequentialGroup()
					.addGroup(gl_panel_news.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_news.createSequentialGroup()
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(lblKsName))
						.addGroup(gl_panel_news.createSequentialGroup()
							.addGap(10)
							.addComponent(lblTitle)))
					.addGap(1)
					.addComponent(dtrpnContent, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
					.addGap(1))
		);
		panel_news.setLayout(gl_panel_news);
	}

	
	/**
	 * @param news
	 * @return <b>void</b> - tworzy nowego newsa na koñcu listy newsów (newsy nale¿y dodawaæ od najnowszego - do najsterszego);
	 */
	public void addNews(News news){
		addNews(news.getSubiect(), news.getData(), news.getKsiadz(), news.getContentHeight(), news.getContent());
	}
	
	//@Override
	public void actionPerformed(ActionEvent e) {
		if(dialogLogowania==null)
			dialogLogowania = new LoginDialog(owner);
		dialogLogowania.setVisible(true);
		dialogLogowania.setFocus();
		
		if(dialogLogowania.isOK()){
			JOptionPane.showMessageDialog(owner, "Login: "+dialogLogowania.getLogin()+"\nHas³o: "+dialogLogowania.getPassword());
		}		
	}
}

class News{
	/**
	 * @param subiect - Temat / Tytu³ newsu
	 * @param data - data dodania newsu
	 * @param ksiadz - Imie i Nazwisko ksiêdza dodaj¹cego news
	 * @param contentHeight - wysokoœæ[px] zawartoœci newsu (min 100px)
	 * @param content - Zawartoœæ newsu w kodzie html
	 * @return <b>void</b> - tworzy nowego newsa na koñcu listy newsów (newsy nale¿y dodawaæ od najnowszego - do najsterszego);
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
	 * @return <b>String</b> - Temat / Tytu³ newsu
	 */
	public String getSubiect() {
		return subiect;
	}
	/**
	 * ustawia Temat newsa
	 * @param subiect - Temat / Tytu³ newsu
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
	 * ustawia Imie i Nazwisko ksiêdza dodaj¹cego news
	 * @return <b>String</b> - Imie i Nazwisko ksiêdza dodaj¹cego news
	 */
	public String getKsiadz() {
		return ksiadz;
	}
	/**
	 * pobiera Imie i Nazwisko ksiêdza dodaj¹cego news
	 * @param ksiadz - Imie i Nazwisko ksiêdza dodaj¹cego news
	 */
	public void setKsiadz(String ksiadz) {
		this.ksiadz = ksiadz;
	}
	/**
	 * ustawia wysokoœæ[px] zawartoœci newsu (min 100px)
	 * @return <b>int</b> - wysokoœæ[px] zawartoœci newsu (min 100px)
	 */
	public int getContentHeight() {
		return contentHeight;
	}
	/**
	 * pobiera wysokoœæ[px] zawartoœci newsu (min 100px)
	 * @param contentHeight - wysokoœæ[px] zawartoœci newsu (min 100px)
	 */
	public void setContentHeight(int contentHeight) {
		this.contentHeight = contentHeight;
	}
	/**
	 * ustawia zawartoœæ newsu w kodzie html
	 * @return <b>String</b> - Zawartoœæ newsu w kodzie html
	 */
	public String getContent() {
		return content;
	}
	/**
	 * pobiera zawartoœæ newsu w kodzie html
	 * @param content - Zawartoœæ newsu w kodzie html
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
			if(i%5==1)addNews(new News("Aktualnosc 1", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:red; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 1</p>"));
			if(i%5==2)addNews(new News("test News 2", new Date(), "Zdzichu Kowal", 56,"<p style=\"color:yellow; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 2</p>"));
			if(i%5==3)addNews(new News("Aktualnosc 3", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:green; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 3</p>"));
			if(i%5==4)addNews(new News("test News 4", new Date(), "Zdzichu Kowal", 97,"<p style=\"color:blue; margin:0px; padding:0px;\"><b>Lorem ipsum</b> -<br /> Zawarto\u015B\u0107 aktualno\u015Bci 4<br /> linijka 3<br />linijka 4<br />linijka 5</p>"));
			if(i%5==0)addNews(new News("Aktualnosc 5", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:orange; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 5</p>"));
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
