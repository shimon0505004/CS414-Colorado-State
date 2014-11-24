package cs414.a5.nlighth1.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Nathan Lighthart
 */
public class CompleteDeliverOrderUI {

    private UIController controller;
    private JFrame frame;
    private JComboBox<String> orderComboBox;
    private JScrollPane scrollPane;
    private JList<String> itemList;
    private JPanel buttonPanel;
    private JButton actionButton;
    private JButton closeButton;

    private boolean isDeliveryMenu;

    public CompleteDeliverOrderUI(UIController controller) {
        this.controller = controller;
    }

    public void setIsDeliveryOption(boolean isDelivery) {
        isDeliveryMenu = isDelivery;
        if(isDeliveryMenu) {
            frame.setTitle("Deliver Order");
            actionButton.setText("Deliver Order");
        } else {
            frame.setTitle("Complete Order");
            actionButton.setText("Complete Order");
        }
    }

    public void init() {
        frame = new JFrame("Complete Order");
        orderComboBox = new JComboBox<>();
        scrollPane = new JScrollPane();
        itemList = new JList<>();
        buttonPanel = new JPanel();
        actionButton = new JButton("Complete Order");
        closeButton = new JButton("Close");

        layoutComponents();

        addListeners();

        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
    }

    public void setVisible(boolean visible) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }

    public void updateOrders() {
        if(isDeliveryMenu) {
            setOrderList(controller.getCompletedButUnderliveredOrders());
        } else {
            setOrderList(controller.getIncompleteOrders());
        }
    }

    public void setOrderList(Iterable<String> orders) {
        orderComboBox.removeAllItems();
        for(String order : orders) {
            orderComboBox.addItem(order);
        }
    }

    private void layoutComponents() {
        frame.setLayout(new BorderLayout());
        frame.add(orderComboBox, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        scrollPane.setViewportView(itemList);
        itemList.setModel(new DefaultListModel<String>());
        itemList.setEnabled(false);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttonPanel.add(actionButton);
        buttonPanel.add(closeButton);
    }

    private void addListeners() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.closeCompleteDeliverOrder();
            }
        });
        orderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemsAction();
            }
        });
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderAction();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.closeCompleteDeliverOrder();
            }
        });
    }

    private void updateItemsAction() {
        DefaultListModel<String> model = (DefaultListModel<String>) itemList.getModel();
        model.removeAllElements();
        if(orderComboBox.getModel().getSize() == 0) {
            return;
        }
        String orderString = (String) orderComboBox.getSelectedItem();
        int orderID = controller.getOrderID(orderString);

        Iterable<String> orderItems = controller.getItemsForOrder(orderID);
        for(String item : orderItems) {
            model.addElement(item);
        }
    }

    private void orderAction() {
        if(orderComboBox.getModel().getSize() == 0) {
            return;
        }
        String orderString = (String) orderComboBox.getSelectedItem();
        int orderID = controller.getOrderID(orderString);
        if(isDeliveryMenu) {
            deliverOrderAction(orderID);
        } else {
            completeOrderAction(orderID);
        }
    }

    private void completeOrderAction(int orderID) {
        if(controller.completeOrder(orderID)) {
            JOptionPane.showMessageDialog(null, "Order " + orderID + " marked as completed.");
            updateOrders();
        } else {
            JOptionPane.showMessageDialog(null, "Order " + orderID + " cannot be marked as completed. Current employee does not have priviledge to complete order.");
        }
    }

    private void deliverOrderAction(int orderID) {
        if(controller.deliverOrder(orderID)) {
            JOptionPane.showMessageDialog(null, "Order " + orderID + " marked as delivered.");
            updateOrders();
        } else {
            JOptionPane.showMessageDialog(null, "Order " + orderID + " cannot be marked as delivered. Current employee does not have priviledge to complete order.");
        }
    }

    // Used to view the interface with nothing working
    public static void main(String[] args) {
        final CompleteDeliverOrderUI view = new CompleteDeliverOrderUI(null);

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
