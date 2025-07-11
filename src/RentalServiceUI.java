import com.rentals.*;
import com.services.RentalService;
import com.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RentalServiceUI extends JFrame {

    private final RentalService rentalService;

    public RentalServiceUI() {
        this.rentalService = new RentalService();
        setTitle("Vehicle Rental Service");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel createUserPanel = createCreateUserPanel();
        JPanel addVehiclePanel = createAddVehiclePanel();
        JPanel rentPanel = createRentPanel();
        JPanel viewUsersPanel = createViewUsersPanel();
        JPanel viewAllVehiclesPanel = createViewAllVehiclesPanel();
        JPanel viewRentedVehiclesPanel = createViewRentedVehiclesPanel();


        cardPanel.add(createUserPanel, "Create User");
        cardPanel.add(addVehiclePanel, "Add Vehicle");
        cardPanel.add(rentPanel, "Rent");
        cardPanel.add(viewUsersPanel, "View Users");
        cardPanel.add(viewAllVehiclesPanel, "View All Vehicles");
        cardPanel.add(viewRentedVehiclesPanel, "View Rented Vehicles");


        add(cardPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem createUserItem = new JMenuItem("Create User");
        JMenuItem addVehicleItem = new JMenuItem("Add Vehicle");
        JMenuItem rentItem = new JMenuItem("Rent");
        JMenuItem viewUsersItem = new JMenuItem("View Users");
        JMenuItem viewAllVehiclesItem = new JMenuItem("View All Vehicles");
        JMenuItem viewRentedVehiclesItem = new JMenuItem("View Rented Vehicles");


        createUserItem.addActionListener(_ -> cardLayout.show(cardPanel, "Create User"));
        addVehicleItem.addActionListener(_ -> cardLayout.show(cardPanel, "Add Vehicle"));
        rentItem.addActionListener(_ -> {
            updateRentPanel(rentPanel);
            cardLayout.show(cardPanel, "Rent");
        });
        viewUsersItem.addActionListener(_ -> {
            updateUsersPanel(viewUsersPanel);
            cardLayout.show(cardPanel, "View Users");
        });
        viewAllVehiclesItem.addActionListener(_ -> {
            updateAllVehiclesPanel(viewAllVehiclesPanel);
            cardLayout.show(cardPanel, "View All Vehicles");
        });
        viewRentedVehiclesItem.addActionListener(_ -> {
            updateRentedVehiclesPanel(viewRentedVehiclesPanel);
            cardLayout.show(cardPanel, "View Rented Vehicles");
        });

        menu.add(createUserItem);
        menu.add(addVehicleItem);
        menu.add(rentItem);
        menu.add(viewUsersItem);
        menu.add(viewAllVehiclesItem);
        menu.add(viewRentedVehiclesItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void updateRentedVehiclesPanel(JPanel viewRentedVehiclesPanel) {
        viewRentedVehiclesPanel.removeAll();
        JTextArea rentedVehiclesArea = new JTextArea(20, 40);
        rentedVehiclesArea.setEditable(false);
        Map<User, Rentable> rented = rentalService.getRented();
        if (rented.isEmpty()) {
            rentedVehiclesArea.append("No vehicles are currently rented.");
        } else {
            for (Map.Entry<User, Rentable> entry : rented.entrySet()) {
                rentedVehiclesArea.append("User: " + entry.getKey().getName() + " -> Vehicle: " + entry.getValue().toString() + " ");
            }
        }
        JScrollPane scrollPane = new JScrollPane(rentedVehiclesArea);
        viewRentedVehiclesPanel.add(scrollPane);
        viewRentedVehiclesPanel.revalidate();
        viewRentedVehiclesPanel.repaint();

    }

    private JPanel createViewRentedVehiclesPanel() {
        JPanel panel = new JPanel();
        updateRentedVehiclesPanel(panel);
        return panel;
    }

    private void updateAllVehiclesPanel(JPanel viewAllVehiclesPanel) {
        viewAllVehiclesPanel.removeAll();
        JTextArea allVehiclesArea = new JTextArea(20, 40);
        allVehiclesArea.setEditable(false);
        for (Rentable rentable : rentalService.getAllRentals()) {
            allVehiclesArea.append(rentable.toString() + " ");
        }
        JScrollPane scrollPane = new JScrollPane(allVehiclesArea);
        viewAllVehiclesPanel.add(scrollPane);
        viewAllVehiclesPanel.revalidate();
        viewAllVehiclesPanel.repaint();
    }

    private JPanel createViewAllVehiclesPanel() {
        JPanel panel = new JPanel();
        updateAllVehiclesPanel(panel);
        return panel;
    }


    private void updateUsersPanel(JPanel viewUsersPanel) {
        viewUsersPanel.removeAll();
        JTextArea usersArea = new JTextArea(20, 40);
        usersArea.setEditable(false);
        for (User user : rentalService.getUsers()) {
            usersArea.append(user.toString() + " ");
        }
        JScrollPane scrollPane = new JScrollPane(usersArea);
        viewUsersPanel.add(scrollPane);
        viewUsersPanel.revalidate();
        viewUsersPanel.repaint();
    }

    private void updateRentPanel(JPanel rentPanel) {
        rentPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        rentPanel.add(new JLabel("Select User:"), gbc);
        gbc.gridx = 1;
        JComboBox<User> userComboBox = new JComboBox<>(rentalService.getUsers().toArray(new User[0]));
        rentPanel.add(userComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        rentPanel.add(new JLabel("Select Vehicle:"), gbc);
        gbc.gridx = 1;
        JComboBox<Rentable> vehicleComboBox = new JComboBox<>(rentalService.getAvailableRentals().toArray(new Rentable[0]));
        rentPanel.add(vehicleComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        rentPanel.add(new JLabel("Days:"), gbc);
        gbc.gridx = 1;
        JTextField daysField = new JTextField(5);
        rentPanel.add(daysField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton rentButton = new JButton("Rent Vehicle");
        rentButton.addActionListener(_ -> {
            User selectedUser = (User) userComboBox.getSelectedItem();
            Rentable selectedVehicle = (Rentable) vehicleComboBox.getSelectedItem();
            int days;
            try {
                days = Integer.parseInt(daysField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for days.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedUser != null && selectedVehicle != null) {
                String result = rentalService.rent(selectedUser, ((Vehicle) selectedVehicle).getId(), days);
                JOptionPane.showMessageDialog(this, result);
                updateRentPanel(rentPanel);
            }
        });
        rentPanel.add(rentButton, gbc);
        rentPanel.revalidate();
        rentPanel.repaint();

    }

    private JPanel createViewUsersPanel() {
        JPanel panel = new JPanel();
        updateUsersPanel(panel);
        return panel;
    }


    private JPanel createRentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        updateRentPanel(panel);
        return panel;
    }

    private JPanel createAddVehiclePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Vehicle Type:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> vehicleTypeComboBox = new JComboBox<>(new String[]{"Car", "Truck", "Two Wheeler"});
        panel.add(vehicleTypeComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Brand:"), gbc);
        gbc.gridx = 1;
        JTextField brandField = new JTextField(20);
        panel.add(brandField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Model:"), gbc);
        gbc.gridx = 1;
        JTextField modelField = new JTextField(20);
        panel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Registration No:"), gbc);
        gbc.gridx = 1;
        JTextField regNoField = new JTextField(20);
        panel.add(regNoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Base Rate:"), gbc);
        gbc.gridx = 1;
        JTextField baseRateField = new JTextField(20);
        panel.add(baseRateField, gbc);

        // Car specific
        JLabel luxuryLabel = new JLabel("Is Luxury:");
        JCheckBox luxuryCheckBox = new JCheckBox();
        // Truck specific
        JLabel weightLabel = new JLabel("Weight Load:");
        JTextField weightField = new JTextField(20);
        // TwoWheeler specific
        JLabel ccLabel = new JLabel("CC:");
        JTextField ccField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(luxuryLabel, gbc);
        gbc.gridx = 1;
        panel.add(luxuryCheckBox, gbc);


        vehicleTypeComboBox.addActionListener(_ -> {
            String selectedType = (String) vehicleTypeComboBox.getSelectedItem();
            panel.remove(luxuryLabel);
            panel.remove(luxuryCheckBox);
            panel.remove(weightLabel);
            panel.remove(weightField);
            panel.remove(ccLabel);
            panel.remove(ccField);

            gbc.gridx = 0;
            gbc.gridy = 5;

            if ("Car".equals(selectedType)) {
                panel.add(luxuryLabel, gbc);
                gbc.gridx = 1;
                panel.add(luxuryCheckBox, gbc);
            } else if ("Truck".equals(selectedType)) {
                panel.add(weightLabel, gbc);
                gbc.gridx = 1;
                panel.add(weightField, gbc);
            } else if ("Two Wheeler".equals(selectedType)) {
                panel.add(ccLabel, gbc);
                gbc.gridx = 1;
                panel.add(ccField, gbc);
            }
            panel.revalidate();
            panel.repaint();
        });


        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addVehicleButton = new JButton("Add Vehicle");
        addVehicleButton.addActionListener(_ -> {
            try {
                String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
                String brand = brandField.getText();
                String model = modelField.getText();
                int regNo = Integer.parseInt(regNoField.getText());
                int baseRate = Integer.parseInt(baseRateField.getText());

                Vehicle vehicle = null;
                if ("Car".equals(vehicleType)) {
                    boolean isLuxury = luxuryCheckBox.isSelected();
                    vehicle = new Car(regNo, brand, model, baseRate, isLuxury);
                } else if ("Truck".equals(vehicleType)) {
                    int weightLoad = Integer.parseInt(weightField.getText());
                    vehicle = new Truck(regNo, brand, model, baseRate, weightLoad);
                } else if ("Two Wheeler".equals(vehicleType)) {
                    int cc = Integer.parseInt(ccField.getText());
                    vehicle = new TwoWheeler(regNo, brand, model, baseRate, cc);
                }

                if (vehicle != null) {
                    rentalService.addRentals(vehicle);
                    JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
                    brandField.setText("");
                    modelField.setText("");
                    regNoField.setText("");
                    baseRateField.setText("");
                    weightField.setText("");
                    ccField.setText("");
                    luxuryCheckBox.setSelected(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for numeric fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(addVehicleButton, gbc);
        return panel;
    }


    private JPanel createCreateUserPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("User Name:"), gbc);
        gbc.gridx = 1;
        JTextField userNameField = new JTextField(20);
        panel.add(userNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        JTextField userIdField = new JTextField(20);
        panel.add(userIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        JTextField ageField = new JTextField(20);
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createUserButton = new JButton("Create User");
        createUserButton.addActionListener(_ -> {
            try {
                String name = userNameField.getText();
                int id = Integer.parseInt(userIdField.getText());
                int age = Integer.parseInt(ageField.getText());
                User user = new User(id, name, age);
                rentalService.addUser(user);
                JOptionPane.showMessageDialog(this, "User created successfully!");
                userNameField.setText("");
                userIdField.setText("");
                ageField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID and Age.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(createUserButton, gbc);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RentalServiceUI().setVisible(true));
    }
}
