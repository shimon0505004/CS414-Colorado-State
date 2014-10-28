package cs414.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 * @author Nathan Lighthart
 */
public class PlaceOrderUI {
	private UIController controller;
	private JFrame frame;
	private JPanel orderPanel;
	private JPanel optionsPanel;
	private JComboBox<String> menuComboBox;
	private JScrollPane menuItemsScrollPane;
	private JList<String> menuItemsList;
	private JScrollPane orderScrollPane;
	private JList<String> orderList;
	private JLabel totalLabel;
	private JLabel totalAmount;
	private JButton addButton;
	private JButton removeButton;
	private JButton payButton;
	private JButton cancelButton;

	public PlaceOrderUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Place Order");
		orderPanel = new JPanel();
		optionsPanel = new JPanel();
		menuComboBox = new JComboBox<>();
		menuItemsScrollPane = new JScrollPane();
		menuItemsList = new JList<>();
		orderScrollPane = new JScrollPane();
		orderList = new JList<>();
		totalLabel = new JLabel("Total:");
		totalAmount = new JLabel();
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		payButton = new JButton("Pay");
		cancelButton = new JButton("Cancel");

		layoutComponents();

		addListeners();

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
	}

	public void setVisible(boolean visible) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}

	public void updateMenus() {
		setMenus(controller.getMenus());
	}

	public void setMenus(Iterable<String> menus) {
		menuComboBox.removeAllItems();
		for(String menu : menus) {
			menuComboBox.addItem(menu);
		}
	}

	public void setMenuItems(Iterable<String> items) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for(String item : items) {
			model.addElement(item);
		}
		menuItemsList.setModel(model);
	}

	private void layoutComponents() {
		frame.setLayout(new BorderLayout());
		frame.add(menuComboBox, BorderLayout.NORTH);
		frame.add(menuItemsScrollPane, BorderLayout.CENTER);
		menuItemsScrollPane.setViewportView(menuItemsList);
		menuItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.add(orderPanel, BorderLayout.EAST);

		orderPanel.setLayout(new BorderLayout());
		orderPanel.add(orderScrollPane, BorderLayout.CENTER);
		orderScrollPane.setViewportView(orderList);
		orderList.setSelectionModel(new DefaultListSelectionModel() {
			private static final long serialVersionUID = -2473659851927728063L;

			@Override
			public void setSelectionInterval(int index0, int index1) {
				super.setSelectionInterval(-1, -1); // prevent selection
			}
		});
		orderList.setModel(new DefaultListModel<String>());
		orderPanel.add(optionsPanel, BorderLayout.SOUTH);

		GridLayout optionsLayout = new GridLayout(3, 3);
		optionsLayout.setHgap(5);
		optionsLayout.setVgap(5);
		optionsPanel.setLayout(optionsLayout);
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		optionsPanel.add(totalLabel);
		totalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		optionsPanel.add(totalAmount);
		optionsPanel.add(addButton);
		optionsPanel.add(removeButton);
		optionsPanel.add(payButton);
		optionsPanel.add(cancelButton);
	}

	private void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closePlaceOrder();
			}
		});
		menuComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadItemsAction();
			}
		});
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.closePlaceOrder();
			}
		});
	}

	private void loadItemsAction() {
		if(menuComboBox.getModel().getSize() == 0) {
			return; // do nothing as nothing to load
		}
		String menu = (String) menuComboBox.getSelectedItem();
		Iterable<String> menuItems = controller.getFullMenuItems(menu);
		setMenuItems(menuItems);
	}

	// Used to view the interface with nothing working
	public static void main(String[] args) {
		final PlaceOrderUI view = new PlaceOrderUI(null);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
				view.frame.removeWindowListener(view.frame.getWindowListeners()[0]);
				view.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
	}
}
