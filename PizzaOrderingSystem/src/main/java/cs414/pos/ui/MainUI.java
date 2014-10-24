package cs414.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Nathan Lighthart
 */
public class MainUI {
	private UIController controller;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem editMenu;
	private JMenuItem placeOrder;
	private JMenuItem completeOrder;
	private JMenuItem about;

	public MainUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Pizza Ordering System");
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		editMenu = new JMenuItem("Edit Menu");
		placeOrder = new JMenuItem("Place Order");
		completeOrder = new JMenuItem("Complete Order");
		about = new JMenuItem("About");

		layoutComponents();

		addListeners();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public void setContent(JPanel panel) {
		frame.add(panel, BorderLayout.CENTER);
	}

	private void layoutComponents() {
		frame.setLayout(new BorderLayout());

		// Setup file menu
		fileMenu.add(editMenu);
		fileMenu.add(placeOrder);
		fileMenu.add(completeOrder);
		menuBar.add(fileMenu);

		// setup help menu
		helpMenu.add(about);
		menuBar.add(helpMenu);

		// finalize frame
		frame.setJMenuBar(menuBar);
		frame.setPreferredSize(new Dimension(800, 600));
	}

	private void addListeners() {
		editMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		placeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		completeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
	}

	public static void main(String[] args) {
		final UIController controller = new UIController();
		final MainUI view = new MainUI(controller);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
			}
		});
	}
}
