import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MilkTeaService extends JFrame implements ActionListener {
    private JComboBox<String> flavorChoice, sizeChoice, paymentMethodChoice;
    private JCheckBox addon1, addon2, addon3;
    private JTextField quantityField, paymentField, totalField, changeField;
    private JButton addFlavorButton, calculateButton, payButton, exitButton;
    private JTextArea receiptArea;
    private JScrollPane receiptScrollPane;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private double totalAmount = 0;

    public MilkTeaService() {
        // Frame settings
        setLayout(null);
        setSize(500, 750);
        setTitle("Milk Tea Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize and add components
        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        JLabel flavorLabel = new JLabel("Select Flavor:");
        flavorLabel.setBounds(20, 20, 100, 25);
        add(flavorLabel);

        flavorChoice = new JComboBox<>(new String[] {"Chocolate", "Cookie Crumble", "Strawberry", "Cheese", "Matcha"});
        flavorChoice.setBounds(150, 20, 200, 25);
        add(flavorChoice);

        JLabel sizeLabel = new JLabel("Select Size:");
        sizeLabel.setBounds(20, 60, 100, 25);
        add(sizeLabel);

        sizeChoice = new JComboBox<>(new String[] {"Small - 29 PHP", "Medium - 39 PHP", "Large - 49 PHP"});
        sizeChoice.setBounds(150, 60, 200, 25);
        add(sizeChoice);

        JLabel addonLabel = new JLabel("Choose Add-ons:");
        addonLabel.setBounds(20, 100, 100, 25);
        add(addonLabel);

        addon1 = new JCheckBox("Pearls - 10 PHP");
        addon1.setBounds(150, 100, 200, 25);
        add(addon1);

        addon2 = new JCheckBox("Pudding - 15 PHP");
        addon2.setBounds(150, 130, 200, 25);
        add(addon2);

        addon3 = new JCheckBox("Grass Jelly - 15 PHP");
        addon3.setBounds(150, 160, 200, 25);
        add(addon3);

        JLabel quantityLabel = new JLabel("Enter Quantity:");
        quantityLabel.setBounds(20, 200, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(150, 200, 200, 25);
        add(quantityField);

        addFlavorButton = new JButton("Add Flavor");
        addFlavorButton.setBounds(150, 240, 200, 25);
        addFlavorButton.addActionListener(this);
        add(addFlavorButton);

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(20, 280, 100, 25);
        add(paymentLabel);

        paymentMethodChoice = new JComboBox<>(new String[] {"Cash", "Maya", "Gcash"});
        paymentMethodChoice.setBounds(150, 280, 200, 25);
        add(paymentMethodChoice);

        calculateButton = new JButton("Calculate Total");
        calculateButton.setBounds(150, 320, 200, 25);
        calculateButton.addActionListener(this);
        add(calculateButton);

        JLabel totalLabel = new JLabel("Show Total Amount:");
        totalLabel.setBounds(20, 360, 150, 25);
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(150, 360, 200, 25);
        totalField.setEditable(false);
        add(totalField);

        JLabel paymentAmountLabel = new JLabel("Enter Payment Amount:");
        paymentAmountLabel.setBounds(20, 400, 150, 25);
        add(paymentAmountLabel);

        paymentField = new JTextField();
        paymentField.setBounds(150, 400, 200, 25);
        add(paymentField);

        JLabel changeLabel = new JLabel("Change:");
        changeLabel.setBounds(20, 440, 100, 25);
        add(changeLabel);

        changeField = new JTextField();
        changeField.setBounds(150, 440, 200, 25);
        changeField.setEditable(false);
        add(changeField);

        payButton = new JButton("Pay");
        payButton.setBounds(150, 480, 200, 25);
        payButton.addActionListener(this);
        add(payButton);

        JLabel receiptLabel = new JLabel("Receipt:");
        receiptLabel.setBounds(20, 520, 100, 25);
        add(receiptLabel);

        receiptArea = new JTextArea();
        receiptArea.setEditable(false);

        receiptScrollPane = new JScrollPane(receiptArea);
        receiptScrollPane.setBounds(20, 550, 430, 150);
        add(receiptScrollPane);

        exitButton = new JButton("Exit");
        exitButton.setBounds(390, 20, 80, 25);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFlavorButton) {
            addFlavorToOrder();
        } else if (e.getSource() == calculateButton) {
            calculateTotal();
        } else if (e.getSource() == payButton) {
            processPayment();
        } else if (e.getSource() == exitButton) {
            System.out.println("Thank you for using the Milk Tea Service!");
            System.exit(0);
        }
    }

    private void addFlavorToOrder() {
        String selectedFlavor = (String) flavorChoice.getSelectedItem();
        String selectedSize = (String) sizeChoice.getSelectedItem();
        int sizePrice = 0;
        switch (selectedSize) {
            case "Small - 29 PHP":
                sizePrice = 29;
                break;
            case "Medium - 39 PHP":
                sizePrice = 39;
                break;
            case "Large - 49 PHP":
                sizePrice = 49;
                break;
        }

        int addonPrice = 0;
        if (addon1.isSelected()) addonPrice += 10;
        if (addon2.isSelected()) addonPrice += 15;
        if (addon3.isSelected()) addonPrice += 15;

        int quantity = 1;
        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            quantity = 1;
        }

        orderItems.add(new OrderItem(selectedFlavor, selectedSize, sizePrice, addonPrice, quantity));

        // Clear selections for new order
        flavorChoice.setSelectedIndex(0);
        sizeChoice.setSelectedIndex(0);
        addon1.setSelected(false);
        addon2.setSelected(false);
        addon3.setSelected(false);
        quantityField.setText("");
    }

    private void calculateTotal() {
        totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += (item.sizePrice + item.addonPrice) * item.quantity;
        }
        totalField.setText(String.valueOf(totalAmount));
    }

    private void processPayment() {
        double payment = 0;
        try {
            payment = Double.parseDouble(paymentField.getText());
        } catch (NumberFormatException e) {
            payment = 0;
        }

        if (payment < totalAmount) {
            receiptArea.setText("The payment is not enough.");
            changeField.setText("");
            return;
        }

        double change = payment - totalAmount;
        changeField.setText(String.valueOf(change));

        StringBuilder receipt = new StringBuilder();
        for (OrderItem item : orderItems) {
            receipt.append(item.quantity)
                   .append(" - ")
                   .append(item.flavor)
                   .append(" (")
                   .append(item.size)
                   .append(") - ")
                   .append((item.sizePrice + item.addonPrice) * item.quantity)
                   .append(" PHP\n");
        }
        receipt.append("\nTotal: ").append(totalAmount).append(" PHP\n")
               .append("Cash: ").append(payment).append(" PHP\n")
               .append("Change: ").append(change).append(" PHP\n")
               .append("\nThank you for your purchase!");

        receiptArea.setText(receipt.toString());
    }

    class OrderItem {
        String flavor;
        String size;
        int sizePrice;
        int addonPrice;
        int quantity;

        OrderItem(String flavor, String size, int sizePrice, int addonPrice, int quantity) {
            this.flavor = flavor;
            this.size = size;
            this.sizePrice = sizePrice;
            this.addonPrice = addonPrice;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        new MilkTeaService();
    }
}
