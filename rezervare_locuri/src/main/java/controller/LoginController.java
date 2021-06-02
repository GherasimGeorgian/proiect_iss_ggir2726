package controller;

import domain.Admin;
import domain.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.Service;

public class LoginController {

    private Service service;
    private Client clientLogat = null;
    private Admin adminLogat = null;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    TextField txtUserName;
    @FXML
    PasswordField txtPass;

    //buttons
    @FXML
    Button btnLogin;

    @FXML
    CheckBox cbAdmin;

    @FXML
    public void initialize() {
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                if (cbAdmin.isSelected() == false) {
                    //cazul client
                    try {
                        String username = txtUserName.getText();
                        String passwd = txtPass.getText();
                        clientLogat = service.loginClient(username, passwd);


                        if (clientLogat != null) {


                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("/views/reprezentatii.fxml"));
                            AnchorPane root = fxmlLoader.load();

                            ReprezentatieController ctrl = fxmlLoader.getController();
                            ctrl.setUser(clientLogat);
                            ctrl.setService(service);

                            Stage stagePageUser = new Stage();
                            Scene scene = new Scene(root, 1000, 562);

                            stagePageUser.setTitle("UserPage");
                            stagePageUser.setScene(scene);
                            stagePageUser.setResizable(false);
                            stagePageUser.show();
                            Stage stagex = (Stage) btnLogin.getScene().getWindow();
                            stagex.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Somethg");
                            alert.setHeaderText("Authentication failure");
                            alert.setContentText("Wrong username or password");
                            alert.showAndWait();
                        }


                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Somethg");
                        alert.setHeaderText("Authentication failure");
                        alert.setContentText("Wrong username or password");
                        alert.showAndWait();
                    }


                }
                else{
                    //cazul admin
                    try {
                        String username = txtUserName.getText();
                        String passwd = txtPass.getText();
                        adminLogat = service.loginAdmin(username, passwd);


                        if (adminLogat != null) {


                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("/views/reprezentatii.fxml"));
                            AnchorPane root = fxmlLoader.load();

                            ReprezentatieController ctrl = fxmlLoader.getController();
                            ctrl.setAdmin(adminLogat);
                            ctrl.setService(service);

                            Stage stagePageUser = new Stage();
                            Scene scene = new Scene(root, 1000, 562);

                            stagePageUser.setTitle("AdminPage");
                            stagePageUser.setScene(scene);
                            stagePageUser.setResizable(false);
                            stagePageUser.show();
                            Stage stagex = (Stage) btnLogin.getScene().getWindow();
                            stagex.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Somethg");
                            alert.setHeaderText("Authentication failure");
                            alert.setContentText("Wrong username or password");
                            alert.showAndWait();
                        }


                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Somethg");
                        alert.setHeaderText("Authentication failure");
                        alert.setContentText("Wrong username or password");
                        alert.showAndWait();
                    }

                }
            }
        });
    }
}
