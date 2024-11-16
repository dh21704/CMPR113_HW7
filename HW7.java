package hw7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HW7 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Santa Ana College");

        // Create UI elements for the login window
        Label lblUsername = new Label("Username:");
        TextField txtUsername = new TextField();
        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();
        Button btnLogin = new Button("Login");

        // Layout for the login window
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        // Add the username label to the first row and first column
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 2);

        // Login button action
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if ("jsim".equals(username) && "sac".equals(password)) {
                primaryStage.hide();
                // Open second window
                openSecondWindow(primaryStage);
            } else {
                showAlert("Invalid Credentials", "Wrong username or password");
            }
        });

        // Set the scene for the login window
        Scene scene = new Scene(gridPane, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openSecondWindow(Stage primaryStage) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("CA", "NY", "AZ");
        comboBox.setValue("CA");

        // Create the second window layout
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label lblName = new Label("Name:");
        TextField txtName = new TextField();
        Label lblAge = new Label("Age:");
        TextField txtAge = new TextField();
        Label lblAddress = new Label("Address:");
        TextField txtAddress = new TextField();
        Label lblCity = new Label("City:");
        TextField txtCity = new TextField();
        Label lblZip = new Label("Zip:");
        TextField txtZip = new TextField();

        // Create radio buttons
        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");

        // Group the radio buttons together so only one can be selected
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        // Adding an image
        Image image = new Image("file:sac.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(200);

        // Create buttons for submit and close
        Button btnSubmit = new Button("Submit");
        Button btnClose = new Button("Close");

        // Submit button action to display data in a message box
        btnSubmit.setOnAction(e -> {
            String name = txtName.getText();
            String age = txtAge.getText();
            String address = txtAddress.getText();
            String city = txtCity.getText();
            String zip = txtZip.getText();
            String selectedState = comboBox.getValue();
            
            String gender = "Not specified";
            
            if(maleButton.isSelected())
            {
             gender = "Male";   
                
            }
            else if (femaleButton.isSelected())
            {
                gender = "Female";
            }

            // Show confirmation alert before submitting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Submission");
            alert.setHeaderText("Please confirm your details");
            alert.setContentText("Name: " + name + "\nAge: " + age + "\nAddress: " + address
                    + "\nCity: " + city + "\nState: " + selectedState + "\nZip: " + zip);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // If user clicks yes
                    showAlert("Submitted Information", "Name: " + name + "\nAge: " + age + "\nAddress: " + address
                            + "\nCity: " + city + "\nState: " + selectedState + "\nZip: " + zip);

                    String filePath = "C:\\Users\\Danny\\Desktop\\ClassExercise6Text.txt";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        writer.write(name);
                        writer.newLine();
                        writer.write(age);
                        writer.newLine();
                        writer.write(address);
                        writer.newLine();
                        writer.write(city);
                        writer.newLine();
                        writer.write(selectedState);
                        writer.newLine();
                        writer.write(zip);
                        writer.newLine();

                        showAlert("Information submitted to text document", "Your details have been saved.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // User clicked cancel
                    showAlert("Submission cancelled", "Your submission has been cancelled");
                }
            });
        });

        btnClose.setOnAction(e -> primaryStage.close());

        // Layout for the second window
        gridPane.add(lblName, 0, 0);
        gridPane.add(txtName, 1, 0);
        gridPane.add(lblAge, 0, 1);
        gridPane.add(txtAge, 1, 1);
        gridPane.add(lblAddress, 0, 2);
        gridPane.add(txtAddress, 1, 2);
        gridPane.add(lblCity, 0, 3);
        gridPane.add(txtCity, 1, 3);
        gridPane.add(lblZip, 0, 4);
        gridPane.add(txtZip, 1, 4);

        gridPane.add(new Label("State from ComboBox:"), 0, 5);  // Label for ComboBox
        gridPane.add(comboBox, 1, 5);

        gridPane.add(maleButton, 0, 6);
        gridPane.add(femaleButton, 1, 6);

        gridPane.add(imageView, 1, 7);  // Display the image
        gridPane.add(btnSubmit, 0, 8);
        gridPane.add(btnClose, 1, 8);

        // Set the scene for the second window
        Scene scene = new Scene(gridPane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Information");
        primaryStage.show();
    }

    // Method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
