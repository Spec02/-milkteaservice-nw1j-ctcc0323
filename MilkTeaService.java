import java.awt.*;
import java.awt.event.*;

public class MilkTeaService extends Frame implements ActionListener {
    private Choice flavorChoice, sizeChoice, paymentMethodChoice;
    private TextField quantityField, paymentField, totalField, changeField;
    private Checkbox addon1, addon2, addon3;
    private Button calculateButton, payButton, exitButton;
    private TextArea receiptArea;
    private double totalAmount = 0;

    public MilkTeaService() {
        // Frame settings
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setSize(400, 600);
        setTitle("Milk Tea Service");

        // Set background color
        setBackground(new Color(255, 228, 196)); // Bisque

        // Font for labels and buttons
        Font font = new Font("Arial", Font.BOLD, 14);

        // Flavor selection
        Label flavorLabel = new Label("Select Flavor:");
        flavorLabel.setFont(font);
        add(flavorLabel);
        flavorChoice = new Choice();
        flavorChoice.add("Chocolate");
        flavorChoice.add("Cookie Crumble");
        flavorChoice.add("Strawberry");
        flavorChoice.add("Cheese");
        flavorChoice.add("Matcha");
        add(flavorChoice);

        // Size selection
        Label sizeLabel = new Label("Select Size:");
        sizeLabel.setFont(font);
        add(sizeLabel);
        sizeChoice = new Choice();
        sizeChoice.add("Small - 20 PHP");
        sizeChoice.add("Medium - 30 PHP");
        sizeChoice.add("Large - 40 PHP");
        add(sizeChoice);

        // Add-ons
        Label addonLabel = new Label("Add-ons (10-15 PHP):");
        addonLabel.setFont(font);
        add(addonLabel);
        addon1 = new Checkbox("Pearls - 10 PHP", false);
        addon2 = new Checkbox("Pudding - 15 PHP", false);
        addon3 = new Checkbox("Grass Jelly - 15 PHP", false);
        add(addon1);
        add(addon2);
        add(addon3);

        // Quantity
        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setFont(font);
        add(quantityLabel);
        quantityField = new TextField(5);
        add(quantityField);

        // Payment method
        Label paymentMethodLabel = new Label("Payment Method:");
        paymentMethodLabel.setFont(font);
        add(paymentMethodLabel);
        paymentMethodChoice = new Choice();
        paymentMethodChoice.add("Cash");
        paymentMethodChoice.add("Paymaya");
        add(paymentMethodChoice);

        // Calculate button
        calculateButton = new Button("Calculate Total");
        calculateButton.setFont(font);
        calculateButton.setBackground(new Color(144, 238, 144)); // Light green
        calculateButton.addActionListener(this);
        add(calculateButton);

        // Total amount
        Label totalLabel = new Label("Total Amount:");
        totalLabel.setFont(font);
        add(totalLabel);
        totalField = new TextField(10);
        totalField.setEditable(false);
        add(totalField);

        // Payment field
        Label paymentLabel = new Label("Enter Payment:");
        paymentLabel.setFont(font);
        add(paymentLabel);
        paymentField = new TextField(10);
        add(paymentField);

        // Change amount
        Label changeLabel = new Label("Change:");
        changeLabel.setFont(font);
        add(changeLabel);
        changeField = new TextField(10);
        changeField.setEditable(false);
        add(changeField);

        // Pay button
        payButton = new Button("Pay");
        payButton.setFont(font);
        payButton.setBackground(new Color(173, 216, 230)); // Light blue
        payButton.addActionListener(this);
        add(payButton);

        // Receipt area
        receiptArea = new TextArea(10, 30);
        add(receiptArea);

        // Exit button
        exitButton = new Button("Exit");
        exitButton.setFont(font);
        exitButton.setBackground(new Color(255, 160, 122)); // Light salmon
        exitButton.addActionListener(this);
        add(exitButton);

        // Closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            calculateTotal();
        } else if (e.getSource() == payButton) {
            processPayment();
        } else if (e.getSource() == exitButton) {
            System.out.println("Thank you for using the Milk Tea Service!");
            System.exit(0);
        }
    }

    private void calculateTotal() {
        String selectedSize = sizeChoice.getSelectedItem();
        int sizePrice = 0;
        switch (selectedSize) {
            case "Small - 20 PHP":
                sizePrice = 20;
                break;
            case "Medium - 30 PHP":
                sizePrice = 30;
                break;
            case "Large - 40 PHP":
                sizePrice = 40;
                break;
        }

        int addonPrice = 0;
        if (addon1.getState()) addonPrice += 10;
        if (addon2.getState()) addonPrice += 15;
        if (addon3.getState()) addonPrice += 15;

        int quantity = 1;
        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            quantity = 1;
        }

        totalAmount = (sizePrice + addonPrice) * quantity;
        totalField.setText(String.valueOf(totalAmount));
    }

    private void processPayment() {
        double payment = 0;
        try {
            payment = Double.parseDouble(paymentField.getText());
        } catch (NumberFormatException e) {
            payment = 0;
        }

        double change = payment - totalAmount;
        changeField.setText(String.valueOf(change));

        String receipt = "Receipt\n";
        receipt += "Flavor: " + flavorChoice.getSelectedItem() + "\n";
        receipt += "Size: " + sizeChoice.getSelectedItem() + "\n";
        if (addon1.getState()) receipt += "Add-on: " + addon1.getLabel() + "\n";
        if (addon2.getState()) receipt += "Add-on: " + addon2.getLabel() + "\n";
        if (addon3.getState()) receipt += "Add-on: " + addon3.getLabel() + "\n";
        receipt += "Quantity: " + quantityField.getText() + "\n";
        receipt += "Total: " + totalAmount + " PHP\n";
        receipt += "Payment Method: " + paymentMethodChoice.getSelectedItem() + "\n";
        receipt += "Payment: " + payment + " PHP\n";
        receipt += "Change: " + change + " PHP\n";
        receipt += "Thank you for your purchase!";
        receiptArea.setText(receipt);
    }

    public static void main(String[] args) {
        new MilkTeaService();
    }
}
