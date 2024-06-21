import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

public class WelcomePage extends JFrame implements ActionListener {
    private JButton nextButton;

    public WelcomePage() {
        setTitle("Java TeaHouse: MilkTea Services");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for logo and shop name
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(Color.WHITE);

        // Load logo image
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/resources/logo.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledLogo);

        // Create rounded logo
        ImageIcon roundedLogoIcon = new ImageIcon(getRoundedImage(scaledIcon.getImage()));
        JLabel logoLabel = new JLabel(roundedLogoIcon);
        logoPanel.add(logoLabel);

        // Shop name label
        JLabel nameLabel = new JLabel("Java TeaHouse: MilkTea Services", JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logoPanel.add(nameLabel);

        add(logoPanel, BorderLayout.CENTER);

        // Next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            new MilkTeaService();
            dispose(); // Close the WelcomePage frame after opening MilkTeaService
        }
    }

    // Method to create rounded image
    private Image getRoundedImage(Image img) {
        BufferedImage roundedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = roundedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create rounded rectangle
        int diameter = Math.min(img.getWidth(null), img.getHeight(null));
        Shape roundedRectangle = new Ellipse2D.Float(0, 0, diameter, diameter);
        g2.setClip(roundedRectangle);
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        return roundedImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}
