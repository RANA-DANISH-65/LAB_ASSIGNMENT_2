package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;

public class AdmissionForm extends Application {

    ArrayList<Candidate> candidates = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        TextField fatherNameField = new TextField();
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Karachi", "Islamabad");
        TextArea addressField = new TextArea();
        TextField emailField = new TextField();

        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        nameField.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff;");
        fatherNameField.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff;");
        cityComboBox.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff;");
        addressField.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff;");
        emailField.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff;");

        maleButton.setStyle("-fx-text-fill: #ff0000;");
        femaleButton.setStyle("-fx-text-fill: #ff0000;");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Father's Name:"), 0, 1);
        grid.add(fatherNameField, 1, 1);

        grid.add(new Label("City:"), 0, 2);
        grid.add(cityComboBox, 1, 2);

        grid.add(new Label("Address:"), 0, 3);
        grid.add(addressField, 1, 3);

        grid.add(new Label("Email:"), 0, 4);
        grid.add(emailField, 1, 4);

        grid.add(new Label("Gender:"), 0, 5);
        grid.add(new VBox(5, maleButton, femaleButton), 1, 5);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white;");
        Text feedbackText = new Text();
        feedbackText.setFill(Color.RED);
        grid.add(submitButton, 1, 6);
        grid.add(feedbackText, 1, 7);

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String city = cityComboBox.getValue();
            String address = addressField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : (femaleButton.isSelected() ? "Female" : "");

            if (name.isEmpty() || fatherName.isEmpty() || city == null || address.isEmpty() || email.isEmpty() || gender.isEmpty()) {
                feedbackText.setText("Please fill out all fields!");
                feedbackText.setFill(Color.RED);
                return;
            }

            candidates.add(new Candidate(name, fatherName, city, address, email, gender));
            feedbackText.setText("Form submitted successfully!");
            feedbackText.setFill(Color.GREEN);

            nameField.clear();
            fatherNameField.clear();
            cityComboBox.setValue(null);
            addressField.clear();
            emailField.clear();
            genderGroup.selectToggle(null);
        });

        Scene scene = new Scene(grid, 400, 400);
        scene.setFill(Color.BLACK);
        stage.setTitle("Admission Form");
        stage.setScene(scene);
        stage.show();
    }

    static class Candidate {
        private String name, fatherName, city, address, email, gender;

        public Candidate(String name, String fatherName, String city, String address, String email, String gender) {
            this.name = name;
            this.fatherName = fatherName;
            this.city = city;
            this.address = address;
            this.email = email;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Name: " + name +
                    "\nFather's Name: " + fatherName +
                    "\nCity: " + city +
                    "\nAddress: " + address +
                    "\nEmail: " + email +
                    "\nGender: " + gender;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
