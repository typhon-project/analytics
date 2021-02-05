package ac.york.typhon.analytics.examples.finalEvaluation.demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;

public class DemoUI {

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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		root = new JFrame("TYPHON Final Review Demo :: Polystore Analytics");// creating instance of JFrame
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout gl = new GridLayout(1, 2);
		root.setLayout(gl);
		root.setSize(1300, 800);

	    // center the jframe on screen
	    root.setLocationRelativeTo(null);
		root.setVisible(true);

		// col 1

		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new BorderLayout());
		root.add(leftpanel);

		// left panel

		JPanel demobuttonspanel = new JPanel();
		demobuttonspanel.setLayout(new FlowLayout());
		leftpanel.add(demobuttonspanel, BorderLayout.NORTH);

		JLabel customerlabel = new JLabel("Customer:");
		demobuttonspanel.add(customerlabel);

		customermenu = new JComboBox<>();
		customermenu.setEditable(false);
		demobuttonspanel.add(customermenu);
		
		JLabel productlabel = new JLabel("Product:");
		demobuttonspanel.add(productlabel);

		productmenu = new JComboBox<>();
		productmenu.setEditable(false);
		demobuttonspanel.add(productmenu);

		JButton addreview = new JButton("Send Review");
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

		logtable = new JTable(new DefaultTableModel(new Object[] { "Accepted Queries" }, 0)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		logtable.setRowHeight(logtable.getRowHeight() + 6);
		logmodel = (DefaultTableModel) logtable.getModel();
		log = new JScrollPane(logtable);
		leftpanel.add(log, BorderLayout.CENTER);

		rlogtable = new JTable(new DefaultTableModel(new Object[] { "Rejected Queries" }, 0)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		rlogmodel = (DefaultTableModel) rlogtable.getModel();
		logtable.setRowHeight(logtable.getRowHeight() + 6);
		rlog = new JScrollPane(rlogtable);
		leftpanel.add(rlog, BorderLayout.SOUTH);

// col 2 

		System.setProperty("org.graphstream.ui", "swing");
		graph = new SingleGraph("Common Reviews");

		SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		
		graph.setAttribute("ui.stylesheet",
				"url('" + DemoUI.class.getResource("stylesheet.css") + "')");

		View view = viewer.addDefaultView(false); // false indicates "no JFrame".
		JPanel viewPanel = new JPanel();
		viewPanel.setLayout(new BorderLayout());
		viewPanel.add((Component) view);
		viewPanel.setBorder(new EtchedBorder());
		root.add(viewPanel);

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

		String ret = pu.queryPolyStoreRaw("insert Review {author: #" + customers.get(customernames.indexOf(c))
				+ ", product: #" + products.get(productnames.indexOf(p)) + "}", true, true, false, null, null, null);

		if (ret.contains("insert Review")) {
			System.out.println("adding to rejected:");
			System.out.println(ret);
			rlogmodel.addRow(new Object[] { ret });
			Thread.sleep(10);
			rlog.getVerticalScrollBar().setValue(rlog.getVerticalScrollBar().getMaximum());
		}

	}

	public void addReview() throws Exception {

		int a = (int) Math.floor(customers.size() * Math.random());
		int p = (int) Math.floor(products.size() * Math.random());

		System.out.println(a + " : " + p);
		System.out.println("author: #" + customers.get(a) + ", product: #" + products.get(p));

		String ret = pu.queryPolyStoreRaw(
				"insert Review {author: #" + customers.get(a) + ", product: #" + products.get(p) + "}", true, true,
				false, null, null, null);

		if (ret.contains("insert Review")) {
			System.out.println("adding to rejected:");
			System.out.println(ret);
			rlogmodel.addRow(new Object[] { ret });
			Thread.sleep(10);
			rlog.getVerticalScrollBar().setValue(rlog.getVerticalScrollBar().getMaximum());
		}

	}

	public static void main(String[] args) throws Exception {

		new DemoUI().createUI();

//		demo_exec e = new demo_exec();
//		e.init(10, 10);
//		for (int i = 0; i < 20; i++)
//			e.addReview();

	}

}
