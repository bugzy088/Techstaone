import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class CollectStra extends JPanel {

	private JFrame frame;
	private JPanel panel;
	private JList catalog;
	private JList trade;
	private JButton addcat;
	private JButton addTra;
	private JButton searchCat;
	private JButton searchTra;
	private JButton delCat;
	private JButton delTra;
	private JTextField catText;
	private JTextField traText;
	private JLabel title;
	private JLabel catLab;
	private JLabel traLab;
	private DefaultListModel catModel;
	private DefaultListModel traModel;
	private ArrayList<String> collection;
	private ArrayList<String> forTrade;
	private int numItems;
	private int numTradeItems;

	public CollectStra() {

		this.frame = new JFrame("CollectStra");
		this.frame.setSize(800, 600);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new JPanel();
		this.frame.add(panel);
		components(panel);
		this.frame.setVisible(true);
		this.collection = new ArrayList<String>();
		this.forTrade = new ArrayList<String>();
		this.numItems = 0;
		this.numTradeItems = 0;
	}

	public void components(JPanel panel) {
		this.panel.setLayout(null);
		this.catModel = new DefaultListModel();
		this.traModel = new DefaultListModel();

		// Collection/Catalog list parameters
		this.catalog = new JList(catModel);
		this.catalog.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.catalog.setSelectedIndex(0);
		this.catalog.setVisibleRowCount(5);
		JScrollPane catScrollPane = new JScrollPane(this.catalog);
		this.catalog.setBounds(475, 100, 300, 400);
		this.panel.add(catalog);
		

		// Trade list parameters
		this.trade = new JList(traModel);
		this.trade.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.trade.setSelectedIndex(0);
		this.trade.setVisibleRowCount(5);
		JScrollPane tradeScrollPane = new JScrollPane(this.trade);
		this.trade.setBounds(10, 100, 300, 400);
		this.panel.add(trade);

		//add to Collection list and search
		this.catText = new JTextField(20);
		this.catText.setBounds(575, 70, 200, 25);
		this.panel.add(catText);
		this.catText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				collection.add(catText.getText());
					catModel.addElement(collection.get(numItems));
					// Collections.sort(collection);
					catText.setText("");
					numItems++;
					
			}
		});
		this.traText = new JTextField(20);
		this.traText.setBounds(10, 70, 200, 25);
		this.panel.add(traText);

		// Labels
		this.title = new JLabel("CollectStra");
		this.title.setBounds(370, 20, 100, 20);
		this.panel.add(title);
		this.catLab = new JLabel("Collection");
		this.catLab.setBounds(600, 500, 100, 20);
		this.panel.add(catLab);
		this.traLab = new JLabel("Trader");
		this.traLab.setBounds(135, 500, 100, 20);
		this.panel.add(traLab);
		
		//Add buton and enter key work to add to collection
		this.addcat = new JButton("Add");
		this.addcat.addActionListener(new addCatListen());
		this.addcat.setBounds(675, 30, 100, 40);
		this.panel.add(addcat);

		this.searchCat = new JButton("Search");
		this.addcat.addActionListener(new addCatListen());
		this.searchCat.setBounds(575, 30, 100, 40);
		this.panel.add(searchCat);

		//Trade button takes element from colection and adds it to list
		this.addTra = new JButton("Trade");
		this.addTra.addActionListener(new addTraListen());
		this.addTra.setBounds(8, 30, 100, 40);
		this.panel.add(addTra);

		this.searchTra = new JButton("Search");
		this.addcat.addActionListener(new addCatListen());
		this.searchTra.setBounds(108, 30, 100, 40);
		this.panel.add(searchTra);

		//Deletes Item from both collection list and array
		this.delCat = new JButton("Delete");
		this.delCat.addActionListener(new removeFromCollection());
		this.delCat.setBounds(475, 55, 100, 40);
		this.panel.add(delCat);

		//Removes from Trade and forTrade array
		this.delTra = new JButton("Remove");
		this.delTra.addActionListener(new removeFromTrade());
		this.delTra.setBounds(208, 55, 100, 40);
		this.panel.add(delTra);
	}

	class addCatListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			collection.add(catText.getText());
			catModel.addElement(collection.get(numItems));
			// Collections.sort(collection);
			catText.setText("");
			numItems++;
		}
	}
	
	class addTraListen implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (a.getActionCommand().equalsIgnoreCase("Trade")) {
				String move = (String) catalog.getSelectedValue();
				forTrade.add(move);
				traModel.addElement(forTrade.get(numTradeItems));
				numTradeItems++;
			}
		}
	}
	class removeFromCollection implements ActionListener {
		public void actionPerformed(ActionEvent b) {
			if (b.getActionCommand().equals("Delete")) {
				String delete = (String) catalog.getSelectedValue();
				collection.remove(delete);
				catModel.removeElement(delete);
				numItems--;
			}
		}
	}
	
	class removeFromTrade implements ActionListener {
		public void actionPerformed(ActionEvent c) {
			if (c.getActionCommand().equals("Remove")) {
				String delete = (String) trade.getSelectedValue();
				forTrade.remove(delete);
				traModel.removeElement(delete);
				numTradeItems--;
			}
		}
	}

	public static void main(String[] args) {
		CollectStra obj = new CollectStra();
	}
}