import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TravelGUI {
    private TravelManager manager = new TravelManager(); // starts empty, no default trips
    private DefaultListModel<String> tripListModel = new DefaultListModel<>();
    private JList<String> tripList = new JList<>(tripListModel);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Tourism Destination and Travel Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        updateTripList();
        frame.add(new JScrollPane(tripList), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Trip");
        addButton.addActionListener(e -> showAddTripDialog());

        JComboBox<String> sortOptions = new JComboBox<>(new String[]{"Sort by Country/City", "Sort by Price", "Sort by Date"});
        sortOptions.addActionListener((ActionEvent e) -> {
            switch (sortOptions.getSelectedIndex()) {
                case 0 -> manager.sortTripsByCountryCity();
                case 1 -> manager.sortTripsByPrice();
                case 2 -> manager.sortTripsByStartDate();
            }
            updateTripList();
        });

        JPanel topPanel = new JPanel();
        topPanel.add(addButton);
        topPanel.add(sortOptions);
        frame.add(topPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void updateTripList() {
        tripListModel.clear();
        for (int i = 0; i < manager.getTrips().size(); i++) {
            tripListModel.addElement(manager.getTrips().get(i).toString());
        }
    }

    private void showAddTripDialog() {
        JFrame formFrame = new JFrame("Add New Trip");
        formFrame.setSize(400, 400);
        formFrame.setLayout(new GridLayout(9, 2));

        JTextField nameField = new JTextField();
        JComboBox<String> countryField = new JComboBox<>(new String[]{"Australia", "Austria", "Bulgaria", "Canada", "France", "Italy", "Japan", "Spain", "UK", "USA"});
        JComboBox<String> cityField = new JComboBox<>();
        JTextField descriptionField = new JTextField();
        JTextField startDateField = new JTextField("dd/MM/yyyy");
        JTextField endDateField = new JTextField("dd/MM/yyyy");
        JTextField priceField = new JTextField();
        JTextField seatsField = new JTextField();

        HashMap<String, String[]> citiesMap = new HashMap<>() {{
        	put("Australia", new String[]{"Brisbane", "Melbourne", "Sydney"});
        	put("Austria", new String[]{"Innsbruck", "Salzburg", "Vienna"});
        	put("Bulgaria", new String[]{"Plovdiv", "Sofia", "Varna"});
        	put("Canada", new String[]{"Montreal", "Toronto", "Vancouver"});
        	put("France", new String[]{"Lyon", "Nice", "Paris"});
        	put("Italy", new String[]{"Milan", "Rome", "Venice"});
        	put("Japan", new String[]{"Kyoto", "Osaka", "Tokyo"});
        	put("Spain", new String[]{"Barcelona", "Madrid", "Valencia"});
        	put("UK", new String[]{"Liverpool", "London", "Manchester"});
        	put("USA", new String[]{"Chicago", "Los Angeles", "New York"});

        }};

        countryField.addActionListener(e -> {
            String selectedCountry = (String) countryField.getSelectedItem();
            cityField.removeAllItems();
            for (String city : citiesMap.get(selectedCountry)) {
                cityField.addItem(city);
            }
        });
        countryField.setSelectedIndex(0);

        formFrame.add(new JLabel("Trip Name:")); formFrame.add(nameField);
        formFrame.add(new JLabel("Country:")); formFrame.add(countryField);
        formFrame.add(new JLabel("City:")); formFrame.add(cityField);
        formFrame.add(new JLabel("Description:")); formFrame.add(descriptionField);
        formFrame.add(new JLabel("Start Date:")); formFrame.add(startDateField);
        formFrame.add(new JLabel("End Date:")); formFrame.add(endDateField);
        formFrame.add(new JLabel("Price:")); formFrame.add(priceField);
        formFrame.add(new JLabel("Seats:")); formFrame.add(seatsField);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String country = (String) countryField.getSelectedItem();
                String city = (String) cityField.getSelectedItem();
                String description = descriptionField.getText().trim();
                LocalDate start = LocalDate.parse(startDateField.getText().trim(), formatter);
                LocalDate end = LocalDate.parse(endDateField.getText().trim(), formatter);
                double price = Double.parseDouble(priceField.getText().trim());
                int seats = Integer.parseInt(seatsField.getText().trim());

                if (end.isBefore(start)) {
                    JOptionPane.showMessageDialog(formFrame, "End date must be after the start date.", "Date Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                manager.addTrip(new Trip(name, new Destination(country, city, description), start, end, price, seats));
                updateTripList();
                formFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formFrame, "Invalid input: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        formFrame.add(new JLabel(""));
        formFrame.add(submit);

        formFrame.setVisible(true);
    }
}
