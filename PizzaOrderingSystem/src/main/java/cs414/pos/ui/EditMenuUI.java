package cs414.pos.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Nathan Lighthart
 */
public class EditMenuUI {
	private JFrame frame;
	private JButton cancelButton;
	private JComboBox<String> menuComboBox;
	private JComboBox<String> menuItemComboBox;
	private JLabel menuItemLabel;
	private JLabel menuLabel;
	private JTextField name;
	private JLabel nameLabel;
	private JTextField price;
	private JLabel priceLabel;
	private JButton saveButton;
	private UIController controller;
	private boolean syncing;

	public EditMenuUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		syncing = true;
		frame = new JFrame("Edit Menu");
		menuLabel = new JLabel("Menu:");
		nameLabel = new JLabel("Name:");
		menuComboBox = new JComboBox<>();
		menuItemLabel = new JLabel("Menu Item:");
		menuItemComboBox = new JComboBox<>();
		name = new JTextField();
		priceLabel = new JLabel("Price:");
		price = new JTextField();
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");

		layoutComponents();

		addListeners();

		frame.pack();
		frame.setResizable(false);
		syncing = false;
	}

	public void setMenus(Iterable<String> menus) {
		syncing = true;

	}

	private void layoutComponents() {
		frame.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints;
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(5, 0, 3, 0);
		frame.add(menuLabel, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(2, 2, 3, 0);
		frame.add(nameLabel, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(2, 0, 3, 3);
		frame.add(menuComboBox, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(2, 0, 3, 0);
		frame.add(menuItemLabel, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(2, 0, 3, 3);
		frame.add(menuItemComboBox, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 3, 0);
		frame.add(name, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(2, 2, 3, 0);
		frame.add(priceLabel, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 3, 0);
		frame.add(price, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new Insets(2, 2, 5, 0);
		frame.add(saveButton, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new Insets(2, 0, 5, 0);
		frame.add(cancelButton, gridBagConstraints);
	}

	private void addListeners() {
		menuComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!syncing) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			}
		});
		menuItemComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!syncing) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			}
		});
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
	}

	private void setComboBoxItems(JComboBox<String> comboBox, Iterable<String> items) {
		comboBox.removeAllItems();
		for(String item : items) {
			comboBox.addItem(item);
		}
	}

}
