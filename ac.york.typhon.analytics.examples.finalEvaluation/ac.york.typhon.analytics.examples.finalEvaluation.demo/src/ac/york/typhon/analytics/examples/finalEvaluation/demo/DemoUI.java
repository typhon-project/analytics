package ac.york.typhon.analytics.examples.finalEvaluation.demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;

public class DemoUI {

	public static final String path = "C:/Users/kb634/Desktop/eclipse2019-typhon/workspace";

	public JFrame root;

	public JTable logtable;
	public DefaultTableModel logmodel;
	public JScrollPane log;

	public JTable rlogtable;
	public DefaultTableModel rlogmodel;
	public JScrollPane rlog;

	public Graph graph;

	JComboBox<String> customermenu = new JComboBox<>();
	JComboBox<String> productmenu = new JComboBox<>();

	public void createUI() {

		root = new JFrame("Typhon Review Demo -- Analytics Component");// creating instance of JFrame
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout gl = new GridLayout(1, 2);
		root.setLayout(gl);
		root.setSize(1900, 1000);
		root.setVisible(true);

		// col 1

		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new BorderLayout());
		root.add(leftpanel);

		// left panel

		JPanel demobuttonspanel = new JPanel();
		demobuttonspanel.setLayout(new GridLayout(3, 2));
		leftpanel.add(demobuttonspanel, BorderLayout.NORTH);

		JLabel customerlabel = new JLabel("Select Customer:");
		customerlabel.setFont(new Font("Arial", Font.PLAIN, 20));
		demobuttonspanel.add(customerlabel);

		JLabel productlabel = new JLabel("Select Product:");
		productlabel.setFont(new Font("Arial", Font.PLAIN, 20));
		demobuttonspanel.add(productlabel);

		customermenu = new JComboBox<>();
		customermenu.setFont(new Font("Arial", Font.PLAIN, 20));
		customermenu.setEditable(false);
		demobuttonspanel.add(customermenu);

		productmenu = new JComboBox<>();
		productmenu.setFont(new Font("Arial", Font.PLAIN, 20));
		productmenu.setEditable(false);
		demobuttonspanel.add(productmenu);

		JButton addreview = new JButton("Send Random Review");
		addreview.setFont(new Font("Arial", Font.PLAIN, 30));
		// b.setBounds(130,100,100, 40);
		addreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("sending review ...");
					new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {

							try {
								addReview();
							} catch (Exception e) {
								System.err.println("ERROR IN CLICKING ADD REVIEW BUTTON INNER:");
								e.printStackTrace();
							}
							return null;

						}

					}.execute();

				} catch (Exception e1) {
					System.err.println("ERROR IN CLICKING ADD RANDOM REVIEW BUTTON:");
					e1.printStackTrace();
				}
			}
		});
		demobuttonspanel.add(addreview);

		addreview = new JButton("Send Specific Review");
		addreview.setFont(new Font("Arial", Font.PLAIN, 30));
		// b.setBounds(130,100,100, 40);
		addreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("sending review ...");
					new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {

							try {
								addReview(customermenu.getSelectedItem().toString(),
										productmenu.getSelectedItem().toString());
							} catch (Exception e) {
								System.err.println("ERROR IN CLICKING ADD SPECIFIC REVIEW BUTTON INNER:");
								e.printStackTrace();
							}
							return null;

						}

					}.execute();

				} catch (Exception e1) {
					System.err.println("ERROR IN CLICKING ADD REVIEW BUTTON:");
					e1.printStackTrace();
				}
			}
		});
		demobuttonspanel.add(addreview);

		//

		logtable = new JTable(new DefaultTableModel(new Object[] { "REVIEW QUERY LOG" }, 0)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		logmodel = (DefaultTableModel) logtable.getModel();
		log = new JScrollPane(logtable);
		logtable.setRowHeight(logtable.getRowHeight() + 5);
		logtable.setFont(new Font("Arial", Font.PLAIN, 15));
		// b.setBounds(130,100,100, 40);
		leftpanel.add(log, BorderLayout.CENTER);

		rlogtable = new JTable(new DefaultTableModel(new Object[] { "REJECTED REVIEW QUERY LOG" }, 0)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		rlogmodel = (DefaultTableModel) rlogtable.getModel();
		rlog = new JScrollPane(rlogtable);
		rlogtable.setRowHeight(rlogtable.getRowHeight() + 5);
		rlogtable.setFont(new Font("Arial", Font.PLAIN, 15));
		// b.setBounds(130,100,100, 40);
		leftpanel.add(rlog, BorderLayout.SOUTH);

// col 2 

		System.setProperty("org.graphstream.ui", "swing");
		graph = new SingleGraph("Common Reviews");

		SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();

		graph.setAttribute("ui.stylesheet",
				"url('file:///" + path + "/ac.york.typhon.analytics.examples.finalEvaluation.demo/stylesheet.css')");

		View view = viewer.addDefaultView(false); // false indicates "no JFrame".
		root.add((Component) view);

		//

		root.revalidate();

		try {
			init(5, 5);
		} catch (Exception e1) {
			System.err.println("ERROR IN INIT: ");
			e1.printStackTrace();
		}

	}

	private List<String> customers = new LinkedList<String>();
	private List<String> products = new LinkedList<String>();
	private PolyStoreUtils pu = new PolyStoreUtils();

	List<String> customernames = Arrays.asList("Alice", "Bob", "Charlie", "Dave", "Eve");
	List<String> productnames = Arrays.asList("Laptop", "TV", "Car", "Groceries", "Book");

	public String getIdFromName(String name) {

		if (customernames.indexOf(name) >= 0)
			return customers.get(customernames.indexOf(name));
		if (productnames.indexOf(name) >= 0)
			return products.get(productnames.indexOf(name));
		else
			return null;

	}

	public String getNameFromId(String id) {

		if (customers.indexOf(id) >= 0)
			return customernames.get(customers.indexOf(id));
		if (products.indexOf(id) >= 0)
			return productnames.get(products.indexOf(id));
		else
			return null;

	}

	public void init(int customers, int products) throws Exception {

		System.out.println(pu.resetDatabases());

		List<List<String>> boundrows = new LinkedList<>();
		for (int i = 0; i < customers; i++) {
			boundrows.add(java.util.Collections.singletonList(customernames.get(i)));
			customermenu.addItem(customernames.get(i));
		}

		String query = "insert Customer { name: ??name }";
		String ret = pu.queryPolyStoreRaw(query, true, true, true, "string", "name", boundrows);

		ret = ret.replace("[", "").replace("]", "").replace("\"", "");
		String[] split = ret.split(",");
		this.customers = Arrays.asList(split);
		System.out.println(this.customers);

		boundrows.clear();
		for (int i = 0; i < products; i++) {
			boundrows.add(java.util.Collections.singletonList(productnames.get(i)));
			productmenu.addItem(productnames.get(i));
		}

		query = "insert Product { name: ??name }";
		ret = pu.queryPolyStoreRaw(query, true, true, true, "string", "name", boundrows);

		ret = ret.replace("[", "").replace("]", "").replace("\"", "");
		split = ret.split(",");
		this.products = Arrays.asList(split);
		System.out.println(this.products);

	}

	public void addReview(String c, String p) throws Exception {

		System.out.println(c + " : " + p);
		System.out.println("author: #" + customers.get(customernames.indexOf(c)) + ", product: #"
				+ products.get(productnames.indexOf(p)));
		System.out.println(pu.queryPolyStoreRaw("insert Review {author: #" + customers.get(customernames.indexOf(c))
				+ ", product: #" + products.get(productnames.indexOf(p)) + "}", true, true, false, null, null, null));

	}

	public void addReview() throws Exception {

		int a = (int) Math.floor(customers.size() * Math.random());
		int p = (int) Math.floor(products.size() * Math.random());

		System.out.println(a + " : " + p);
		System.out.println("author: #" + customers.get(a) + ", product: #" + products.get(p));
		System.out.println(pu.queryPolyStoreRaw(
				"insert Review {author: #" + customers.get(a) + ", product: #" + products.get(p) + "}", true, true,
				false, null, null, null));

	}

	public static void main(String[] args) throws Exception {

		new DemoUI().createUI();

//		demo_exec e = new demo_exec();
//		e.init(10, 10);
//		for (int i = 0; i < 20; i++)
//			e.addReview();

	}

}
