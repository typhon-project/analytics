package analytics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JScrollPane;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichFilterFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.CommonReviews;
import ac.york.typhon.analytics.commons.datatypes.Customer;
import ac.york.typhon.analytics.commons.datatypes.Product;
import ac.york.typhon.analytics.commons.datatypes.Review;
import ac.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.examples.finalEvaluation.demo.DemoUI;

public class FinalReviewDemoAnalytics implements IAnalyzer {

	public void analyze(DataStream<Event> eventsStream) throws Exception {

		// care about reviews
		eventsStream.map(new MapFunction<Event, DeserializedPostEvent>() {

			@Override
			public DeserializedPostEvent map(Event value) throws Exception {
				//System.err.println(value.getClass());
				return (DeserializedPostEvent) value;
			}
		}).filter(new RichFilterFunction<DeserializedPostEvent>() {

			private Graph graph;

			@Override
			public void open(Configuration parameters) throws Exception {

				super.open(parameters);
				graph = FinalReviewDemoAnalyticsRunner.ui.graph;
			}

			@Override
			public boolean filter(DeserializedPostEvent event) throws Exception {

				String query = event.getPreEvent().getQuery().toLowerCase();

				if (query.contains("insert review")) {
					//
					query = event.getPreEvent().getQuery().substring(query.indexOf("{\"query\":\"") + 10,
							query.lastIndexOf("\"}"));

					if (event.getPreEvent().isAuthenticated()) {

						FinalReviewDemoAnalyticsRunner.ui.logmodel.addRow(new Object[] { query });
						JScrollPane logscroll = FinalReviewDemoAnalyticsRunner.ui.log;
						Thread.sleep(10);
						logscroll.getVerticalScrollBar().setValue(logscroll.getVerticalScrollBar().getMaximum());
						// table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
						//
						return true;
					} else {

						FinalReviewDemoAnalyticsRunner.ui.rlogmodel.addRow(new Object[] { query });
						JScrollPane rlogscroll = FinalReviewDemoAnalyticsRunner.ui.rlog;
						Thread.sleep(10);
						rlogscroll.getVerticalScrollBar().setValue(rlogscroll.getVerticalScrollBar().getMaximum());

						return false;
					}
				} else if (query.contains("insert customer")) {

					try {

						String rq = event.getJsonquery().getResolvedQuery();
						String name = rq.substring(rq.indexOf("name: ") + 7, rq.indexOf("\" }"));

						Node n = graph.addNode(name);
						n.setAttribute("ui.label", name);

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

				return false;
			}
		}).map(new RichMapFunction<DeserializedPostEvent, Integer>() {

			// user has common reviews with user2 for products p
			HashMap<String, HashMap<String, HashSet<String>>> common;
			private DemoUI ui;
			private Graph graph;

			@Override
			public void open(Configuration parameters) throws Exception {
				super.open(parameters);

				ui = FinalReviewDemoAnalyticsRunner.ui;

				graph = FinalReviewDemoAnalyticsRunner.ui.graph;

				common = new HashMap<String, HashMap<String, HashSet<String>>>();

			}

			@Override
			public Integer map(DeserializedPostEvent value) throws Exception {

				Review reviewc = (Review) ((InsertCommand) value.getCommands().get(0)).getInsertedEntities().get(0);
				System.out.println("new review author uuid: " + reviewc.getAuthor().getUUID());
				System.out.println("new review uuid: " + reviewc.getUUID());

				// check if any other customers have reviewed the same product
				List<Entity> productreviews = new PolyStoreUtils(ClassLoader.getSystemClassLoader()).queryPolyStore(
						"from Product p select p.@id, p.reviews where p.@id == #" + reviewc.getProduct().getUUID(),
						false);
				// System.out.println(product);

				int commonreviewc = 0;
				for (Entity p : productreviews) {
					Product pr = ((Product) p);
					Review r = pr.getReviews().get(0);
					// ignore my own review i just added

					//
					System.err.println(">" + reviewc.getUUID());
					System.err.println(">>" + r.getUUID());
					//
					if (reviewc.getUUID() != null && r.getUUID() != null && !reviewc.getUUID().equals(r.getUUID())) {

						List<Entity> otherreview = new PolyStoreUtils(ClassLoader.getSystemClassLoader())
								.queryPolyStore("from Review r select r.author where r.@id == #" + r.getUUID(), false);

						// System.out.println(">>>"+reviewer);

						Customer otherreviewer = ((Review) otherreview.get(0)).getAuthor();

						List<Entity> commonreviews = null;

						//
						System.err.println(">>>" + reviewc.getAuthor().getUUID());
						System.err.println(">>>>" + otherreviewer.getUUID());

						// ignore reviews i have made on this product
						if (reviewc.getAuthor().getUUID() != null && otherreviewer.getUUID() != null
								&& !reviewc.getAuthor().getUUID().equals(otherreviewer.getUUID())) {

							if (reviewc.getAuthor().getUUID().compareTo(otherreviewer.getUUID()) < 0) {
								commonreviews = new PolyStoreUtils(ClassLoader.getSystemClassLoader())
										.queryPolyStore("from CommonReviews c select c.@id, c.count where c.source == #"
												+ reviewc.getAuthor().getUUID() + ", c.target == #"
												+ otherreviewer.getUUID(), false);

								int count = 0;
								if (!commonreviews.isEmpty()) {

									count = ((CommonReviews) commonreviews.get(0)).getCount();
									// XXX i know there is enormous code replication and it should be consolidated
									// into a method
									System.err.println("count was: " + count);
									if (common.get(reviewc.getAuthor().getUUID()) == null)
										common.put(reviewc.getAuthor().getUUID(),
												new HashMap<String, HashSet<String>>());
									if (common.get(reviewc.getAuthor().getUUID()).get(otherreviewer.getUUID()) == null)
										common.get(reviewc.getAuthor().getUUID()).put(otherreviewer.getUUID(),
												new HashSet<String>());
									HashSet<String> products = common.get(reviewc.getAuthor().getUUID())
											.get(otherreviewer.getUUID());
									System.err.println("cashe shows: " + products);
									products.add(reviewc.getProduct().getUUID());
									System.err.println("added product: " + reviewc.getProduct().getUUID());
									System.err.println(products.size() + " : " + count);
									if (products.size() > count)
										count++;
								} else {
									//
									System.err.println("EMPTY count was: " + count);
									if (common.get(reviewc.getAuthor().getUUID()) == null)
										common.put(reviewc.getAuthor().getUUID(),
												new HashMap<String, HashSet<String>>());
									if (common.get(reviewc.getAuthor().getUUID()).get(otherreviewer.getUUID()) == null)
										common.get(reviewc.getAuthor().getUUID()).put(otherreviewer.getUUID(),
												new HashSet<String>());
									HashSet<String> products = common.get(reviewc.getAuthor().getUUID())
											.get(otherreviewer.getUUID());
									System.err.println("cashe shows: " + products);
									products.add(reviewc.getProduct().getUUID());
									System.err.println("added product: " + reviewc.getProduct().getUUID());
									System.err.println(products.size() + " : " + count);
									count++;
								}
								if (!commonreviews.isEmpty()) {
									new PolyStoreUtils(ClassLoader.getSystemClassLoader()).queryPolyStore(
											"update CommonReviews cr where cr.@id == #" + commonreviews.get(0).getUUID()
													+ " set {count: " + count + "}",
											true);
									Edge edge = graph
											.getEdge(reviewc.getAuthor().getUUID() + "::" + otherreviewer.getUUID());
									edge.setAttribute("ui.label", count);
								} else {
									new PolyStoreUtils(ClassLoader.getSystemClassLoader())
											.queryPolyStore("insert CommonReviews {count: " + count + ", source: #"
													+ reviewc.getAuthor().getUUID() + ", target: #"
													+ otherreviewer.getUUID() + "}", true);
									Edge edge = graph.addEdge(
											reviewc.getAuthor().getUUID() + "::" + otherreviewer.getUUID(),
											ui.getNameFromId(reviewc.getAuthor().getUUID()),
											ui.getNameFromId(otherreviewer.getUUID()));
									edge.setAttribute("ui.label", count);
								}
								System.err.println(">>>>>inserting edge: " + reviewc.getAuthor().getUUID() + " -> "
										+ otherreviewer.getUUID() + " :: " + count);
								commonreviewc = commonreviewc + count;

							} else {
								commonreviews = new PolyStoreUtils(ClassLoader.getSystemClassLoader())
										.queryPolyStore("from CommonReviews c select c.count where c.source == #"
												+ otherreviewer.getUUID() + ", c.target == #"
												+ reviewc.getAuthor().getUUID(), false);

								int count = 0;
								if (!commonreviews.isEmpty()) {

									count = ((CommonReviews) commonreviews.get(0)).getCount();
									//
									System.err.println("count was: " + count);
									if (common.get(otherreviewer.getUUID()) == null)
										common.put(otherreviewer.getUUID(), new HashMap<String, HashSet<String>>());
									if (common.get(otherreviewer.getUUID()).get(reviewc.getAuthor().getUUID()) == null)
										common.get(otherreviewer.getUUID()).put(reviewc.getAuthor().getUUID(),
												new HashSet<String>());
									HashSet<String> products = common.get(otherreviewer.getUUID())
											.get(reviewc.getAuthor().getUUID());
									System.err.println("cashe shows: " + products);
									products.add(reviewc.getProduct().getUUID());
									System.err.println("added product: " + reviewc.getProduct().getUUID());
									System.err.println(products.size() + " : " + count);
									if (products.size() > count)
										count++;
								} else {
									//
									System.err.println("EMPTY2 count was: " + count);
									if (common.get(otherreviewer.getUUID()) == null)
										common.put(otherreviewer.getUUID(), new HashMap<String, HashSet<String>>());
									if (common.get(otherreviewer.getUUID()).get(reviewc.getAuthor().getUUID()) == null)
										common.get(otherreviewer.getUUID()).put(reviewc.getAuthor().getUUID(),
												new HashSet<String>());
									HashSet<String> products = common.get(otherreviewer.getUUID())
											.get(reviewc.getAuthor().getUUID());
									System.err.println("cashe shows: " + products);
									products.add(reviewc.getProduct().getUUID());
									System.err.println("added product: " + reviewc.getProduct().getUUID());
									System.err.println(products.size() + " : " + count);
									count++;
								}
								if (!commonreviews.isEmpty()) {
									new PolyStoreUtils(ClassLoader.getSystemClassLoader()).queryPolyStore(
											"update CommonReviews cr where cr.@id == #" + commonreviews.get(0).getUUID()
													+ " set {count: " + count + "}",
											true);
									Edge edge = graph
											.getEdge(otherreviewer.getUUID() + "::" + reviewc.getAuthor().getUUID());
									edge.setAttribute("ui.label", count);
								} else {
									new PolyStoreUtils(ClassLoader.getSystemClassLoader())
											.queryPolyStore("insert CommonReviews {count: " + count + ", source: #"
													+ otherreviewer.getUUID() + ", target: #"
													+ reviewc.getAuthor().getUUID() + "}", true);
									Edge edge = graph.addEdge(
											otherreviewer.getUUID() + "::" + reviewc.getAuthor().getUUID(),
											ui.getNameFromId(reviewc.getAuthor().getUUID()),
											ui.getNameFromId(otherreviewer.getUUID()));
									edge.setAttribute("ui.label", count);
								}
								System.err.println(">>>>>>inserting edge: " + otherreviewer.getUUID() + " -> "
										+ reviewc.getAuthor().getUUID() + " :: " + count);
								commonreviewc = commonreviewc + count;
							}

							System.out.println(commonreviews);

						}
					}
				}
				System.err.println(common);
				return commonreviewc;
			}
		})
				// get all other customers who have reviewed this product

				//

				.print();

	}

}
