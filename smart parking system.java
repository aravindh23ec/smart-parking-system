import java.awt.*;
import java.awt.event.*;

public class SmartParkingSystemAWT_2 {
    private static final int MAX_SPACES = 10; // Maximum parking slots
    private String[] parkingSlots;
    private List parkingList;
    private TextField vehicleNumberField;
    
    // Constructor to initialize parking slots
    public SmartParkingSystemAWT_2() {
        parkingSlots = new String[MAX_SPACES];
    }

    // Display available parking slots in the List component
    public void showAvailableSpaces() {
        parkingList.removeAll();
        for (int i = 0; i < MAX_SPACES; i++) {
            if (parkingSlots[i] == null) {
                parkingList.add("Slot " + (i + 1) + " is available");
            } else {
                parkingList.add("Slot " + (i + 1) + ": " + parkingSlots[i]);
            }
        }
    }

    // Park a vehicle
    public void parkVehicle(String vehicleNumber) {
        for (int i = 0; i < MAX_SPACES; i++) {
            if (parkingSlots[i] == null) { // Find the first empty slot
                parkingSlots[i] = vehicleNumber;
                showAvailableSpaces();
                return;
            }
        }
        showAvailableSpaces();
        parkingList.add("Parking is full. Cannot park the vehicle.");
    }

    // Remove a vehicle
    public void removeVehicle(String vehicleNumber) {
        for (int i = 0; i < MAX_SPACES; i++) {
            if (vehicleNumber.equals(parkingSlots[i])) { // Find the vehicle
                parkingSlots[i] = null; // Clear the slot
                showAvailableSpaces();
                return;
            }
        }
        showAvailableSpaces();
        parkingList.add("Vehicle " + vehicleNumber + " not found.");
    }

    // Main method to create the GUI and set up event handling
    public static void main(String[] args) {
        Frame frame = new Frame("Smart Parking System");
        SmartParkingSystemAWT_2 parkingSystem = new SmartParkingSystemAWT_2();

        // Create components
        parkingSystem.parkingList = new List();
        parkingSystem.parkingList.setSize(300, 150);
        Button showSpacesButton = new Button("Show Available Spaces");
        Button parkVehicleButton = new Button("Park Vehicle");
        Button removeVehicleButton = new Button("Remove Vehicle");
        Label vehicleNumberLabel = new Label("Enter Vehicle Number: ");
        parkingSystem.vehicleNumberField = new TextField();

        // Set layout
        frame.setLayout(new FlowLayout());

        // Add components to frame
        frame.add(vehicleNumberLabel);
        frame.add(parkingSystem.vehicleNumberField);
        frame.add(showSpacesButton);
        frame.add(parkVehicleButton);
        frame.add(removeVehicleButton);
        frame.add(parkingSystem.parkingList);

        // Set up button actions
        showSpacesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parkingSystem.showAvailableSpaces();
            }
        });

        parkVehicleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String vehicleNumber = parkingSystem.vehicleNumberField.getText();
                if (!vehicleNumber.isEmpty()) {
                    parkingSystem.parkVehicle(vehicleNumber);
                } else {
                    parkingSystem.parkingList.add("Please enter a vehicle number.");
                }
            }
        });

        removeVehicleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String vehicleNumber = parkingSystem.vehicleNumberField.getText();
                if (!vehicleNumber.isEmpty()) {
                    parkingSystem.removeVehicle(vehicleNumber);
                } else {
                    parkingSystem.parkingList.add("Please enter a vehicle number.");
                }
            }
        });

        // Set frame properties
        frame.setSize(400, 400);
        frame.setVisible(true);

        // Close window on exit
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
  }
