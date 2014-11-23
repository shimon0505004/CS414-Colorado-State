package cs414.a5.eid.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
    private JList<String> menuItemList;
    private JScrollPane orderScrollPane;
    private JList<String> orderList;
    private JLabel totalLabel;
    private JLabel totalAmount;
    private JButton addButton;
    private JButton removeButton;
    private JButton payButton;
    private JButton closeButton;

    public PlaceOrderUI(UIController controller) {
        this.controller = controller;
    }

    public void init() {
        frame = new JFrame("Place Order");
        orderPanel = new JPanel();
        optionsPanel = new JPanel();
        menuComboBox = new JComboBox<>();
        menuItemsScrollPane = new JScrollPane();
        menuItemList = new JList<>();
        orderScrollPane = new JScrollPane();
        orderList = new JList<>();
        totalLabel = new JLabel("Total:");
        totalAmount = new JLabel();
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        payButton = new JButton("Pay");
        closeButton = new JButton("Close");

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

    public void updateOrder() {
        setOrderItems(controller.getOrderItems());
        totalAmount.setText("$" + controller.getTotal());

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
        menuItemList.setModel(model);
    }

    public void setOrderItems(Iterable<String> items) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String item : items) {
            model.addElement(item);
        }
        orderList.setModel(model);
    }

    private void layoutComponents() {
        frame.setLayout(new BorderLayout());
        frame.add(menuComboBox, BorderLayout.NORTH);
        frame.add(menuItemsScrollPane, BorderLayout.CENTER);
        menuItemsScrollPane.setViewportView(menuItemList);
        menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(orderPanel, BorderLayout.EAST);

        orderPanel.setLayout(new BorderLayout());
        orderPanel.add(orderScrollPane, BorderLayout.CENTER);
        orderScrollPane.setViewportView(orderList);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setModel(new DefaultListModel<String>());
        orderPanel.add(optionsPanel, BorderLayout.SOUTH);

        GridLayout optionsLayout = new GridLayout(3, 2);
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
        optionsPanel.add(closeButton);
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
                addItemAction();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemAction();
            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payAction();
            }
        });
        closeButton.addActionListener(new ActionListener() {
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

    private void addItemAction() {
        String itemString = verifySelectedItem();
        if(itemString == null) {
            return; // cancel
        }
        String itemName = controller.getItemName(itemString);
        String quantityString = JOptionPane.showInputDialog("Enter quantity:");
        if(quantityString == null) {
            return; // cancel
        }
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch(NumberFormatException ex) {
            // quantity is still 0
        }
        if(quantity <= 0) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid quantity");
            return;
        }

        controller.addOrderItem(itemName, quantity);
        updateOrder();
    }

    private void removeItemAction() {
        String orderItemString = verifySelectedOrderItem();
        if(orderItemString == null) {
            return; // cancel
        }
        String itemName = controller.getOrderItemName(orderItemString);

        String quantityString = JOptionPane.showInputDialog("Enter quantity:");
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch(NumberFormatException ex) {
            // quantity is still 0
        }
        if(quantity <= 0) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid quantity. Quantity can not be 0 or less.");
            return;
        }
        if(quantity > controller.getCurrentCountOfOrderItem(itemName)) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid quantity. Number of items to remove is greater than actual number of items present in current order by " + (quantity - controller.getCurrentCountOfOrderItem(itemName)) + ".");
            return;
        }
        controller.removeOrderItem(itemName, quantity);
        updateOrder();
    }

    private void payAction() {
        if(orderList.getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(frame, "There is nothing ordered so no payment can be made.");
            return;
        }
        int result = JOptionPane.showConfirmDialog(frame, "Would you like to use a membership ID?", "Question", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.CANCEL_OPTION) {
            return; // cancel
        }

        String membershipID = null;
        if(result == JOptionPane.YES_OPTION) {
            membershipID = JOptionPane.showInputDialog(frame, "Please enter membership ID:");
        }

        Object[] deliveryOptions = {"In House", "Take Away", "Delivery"};
        int deliveryOption = JOptionPane.showOptionDialog(frame, "Please select and order type", "Question",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, deliveryOptions, deliveryOptions[0]);
        if(deliveryOption == JOptionPane.CLOSED_OPTION) {
            return; // cancel
        }

        String address = null;
        if(deliveryOption == 2) {
            address = JOptionPane.showInputDialog(frame, "Please enter delivery address:");
            if(address == null) {
                return; // cancel
            }
        }

        Object[] paymentOptions = {"Cash", "Credit Card"};
        int paymentOption = JOptionPane.showOptionDialog(frame, "Please select and order type", "Question",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, paymentOptions, paymentOptions[0]);
        if(paymentOption == JOptionPane.CLOSED_OPTION) {
            return; // cancel
        }

        String cardNumber = null;
        String expiration = null;
        String cv2 = null;

        if(paymentOption == 1) {
            cardNumber = JOptionPane.showInputDialog(frame, "Please enter card number:");
            if(cardNumber == null) {
                return; // cancel
            }
            expiration = JOptionPane.showInputDialog(frame, "Please enter expiration date:");
            if(expiration == null) {
                return; // cancel
            }
            cv2 = JOptionPane.showInputDialog(frame, "Please enter cv2:");
            if(cv2 == null) {
                return; // cancel
            }
        }

        String amountString = JOptionPane.showInputDialog(frame, "Please enter amount to pay:");
        if(amountString == null) {
            return; //cancel
        }
        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount");
            return;
        }

        boolean success = controller.payOrder(membershipID, deliveryOption, address, paymentOption, cardNumber, expiration, cv2, amount);

        if(!success) {
            JOptionPane.showMessageDialog(frame, "Error completing order please try again");
            return;
        }

        controller.placeOrder();
        double changeAmount = controller.getOrderChange();
        if(changeAmount > 0.0) {
            JOptionPane.showMessageDialog(frame, "Here is your change $" + changeAmount);
        }
        JOptionPane.showMessageDialog(null, "Order successfully placed.");

        controller.closePlaceOrder();

        if(membershipID != null && success) {
            int point = controller.getMemberPoints(membershipID);
            JOptionPane.showMessageDialog(frame, "Dear Customer with membership number: " + membershipID + " your current reward points is: " + point);

            /**
             * implementation of free pizza offer
             */
            if(point > controller.getRequiredPointForFreePizzaCertificate()) {
                int wantFreePizzaResult = JOptionPane.showConfirmDialog(frame, "Dear Customer with membership number: " + membershipID + " you are eligible to receive a free pizza Certificate. Would you like to use it now?", "Question", JOptionPane.YES_NO_OPTION);
                if(wantFreePizzaResult == JOptionPane.CANCEL_OPTION) {
                    return; // cancel
                } else if(wantFreePizzaResult == JOptionPane.OK_OPTION) {
                    controller.setMemberPoints(membershipID, (point - controller.getRequiredPointForFreePizzaCertificate()));
                    JOptionPane.showMessageDialog(frame, "Enjoy your house special pizza! Your updated rewards point is: " + controller.getMemberPoints(membershipID));
                }
            }
        }
    }

    private String verifySelectedItem() {
        String item = menuItemList.getSelectedValue();
        if(item == null) {
            JOptionPane.showMessageDialog(frame, "Please select an item.");
        }
        return item;
    }

    private String verifySelectedOrderItem() {
        String item = orderList.getSelectedValue();
        if(item == null) {
            JOptionPane.showMessageDialog(frame, "Please select an order item.");
        }
        return item;
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
