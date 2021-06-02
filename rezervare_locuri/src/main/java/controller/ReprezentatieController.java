package controller;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;
import utils.events.ChangeEvent;
import utils.events.ChangeEventType;
import utils.observer.Observer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReprezentatieController  implements Observer<ChangeEvent> {
    private Service service;
    private Client clientLogat;
    private Admin adminLogat;
    private boolean adminON = false;
    public void setService(Service service){
        this.service = service;
        service.addObserver(this);
        initializareLocuri();
        if(adminON == true){
            generateButtonAddReprezentatie();
        }
    }
    Integer lastLayoutY = -1;
    @Override
    public void update(ChangeEvent event) {
        ChangeEventType t = event.getType();
        //daca se adauga o reprezentatie
        if(t == ChangeEventType.S_ADD){
            Reprezentatie reprezentatie =  (Reprezentatie) event.getData();
            Button btn=null;
            if(reprezentatie.getRating() == 4){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/4stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 5){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/5stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 3){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/3stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 1){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/1star.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 2){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/2stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }


            btn.wrapTextProperty().setValue(true);
            btn.setPrefWidth(600);
            btn.setPrefHeight(80);
            btn.setLayoutX(10);

            btn.setLayoutY(10+lastLayoutY);

            anchorPane.getChildren().add(btn);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (adminON == false) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("/views/configuratie.fxml"));
                            AnchorPane root = fxmlLoader.load();

                            ConfiguratieSala ctrl = fxmlLoader.getController();
                            ctrl.setUser(clientLogat);
                            ctrl.setReprezentatie(reprezentatie);
                            ctrl.setService(service);


                            Stage stagePageUser = new Stage();
                            Scene scene = new Scene(root, 1200, 562);

                            stagePageUser.setTitle("UserPage");
                            stagePageUser.setScene(scene);
                            stagePageUser.setResizable(false);
                            stagePageUser.show();
                        } catch (IOException ex) {

                        }
                    }else{
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("/views/configuratie.fxml"));
                            AnchorPane root = fxmlLoader.load();

                            ConfiguratieSala ctrl = fxmlLoader.getController();
                            ctrl.setAdmin(adminLogat);
                            ctrl.setReprezentatie(reprezentatie);
                            ctrl.setService(service);


                            Stage stagePageUser = new Stage();
                            Scene scene = new Scene(root, 1200, 562);

                            stagePageUser.setTitle("UserPage");
                            stagePageUser.setScene(scene);
                            stagePageUser.setResizable(false);
                            stagePageUser.show();
                        } catch (IOException ex) {

                        }
                    }
                }
            });
            lastLayoutY+=100;
        }


    }
    public void setUser(Client client){
        this.clientLogat = client;

    }
    public void setAdmin(Admin admin){
        this.adminLogat = admin;
        this.adminON = true;
    }
    public AnchorPane anchorPane;
    public void generateButtonAddReprezentatie(){
        Button btn = new Button("Adauga reprezentatie");
        btn.wrapTextProperty().setValue(true);
        btn.setPrefWidth(100);
        btn.setPrefHeight(100);
        btn.setLayoutX(700);

        btn.setLayoutY(10);

        anchorPane.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/add_reprezentatii.fxml"));
                    AnchorPane root = fxmlLoader.load();

                    ReprezentatieAdaugareController ctrl = fxmlLoader.getController();
                    ctrl.setService(service);

                    Stage stagePageUser = new Stage();
                    Scene scene = new Scene(root, 500, 562);

                    stagePageUser.setTitle("Adauga Reprezentatie Page");
                    stagePageUser.setScene(scene);
                    stagePageUser.setResizable(false);
                    stagePageUser.show();

                } catch (Exception ex) {

                }

            }
        });
    }

    public void initializareLocuri(){
        int i=0;
        int y;
        int last=1;
        Iterable<Reprezentatie> reprezentatii  = service.getReprezentatii();
        for(Reprezentatie reprezentatie : reprezentatii){
            Button btn=null;
            if(reprezentatie.getRating() == 4){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/4stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 5){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/5stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 3){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/3stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 1){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/1star.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }
            if(reprezentatie.getRating() == 2){
                File imageFile = new File("C:/Users/Ghera/Documents/GitHub/proiect_iss_ggir2726/2stars.jpg");
                Image imageDecline = new Image(imageFile.toURI().toString(),100, 30, false, false);
                btn = new Button(reprezentatie.getNume() + "\n va incepe la data/ora:" + reprezentatie.getDurata(), new ImageView(imageDecline));

            }


            btn.wrapTextProperty().setValue(true);
            btn.setPrefWidth(600);
            btn.setPrefHeight(80);
            btn.setLayoutX(10);

            btn.setLayoutY(10+i);

            anchorPane.getChildren().add(btn);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (adminON == false) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("/views/configuratie.fxml"));
                            AnchorPane root = fxmlLoader.load();

                            ConfiguratieSala ctrl = fxmlLoader.getController();
                            ctrl.setUser(clientLogat);
                            ctrl.setReprezentatie(reprezentatie);
                            ctrl.setService(service);


                            Stage stagePageUser = new Stage();
                            Scene scene = new Scene(root, 1200, 562);

                            stagePageUser.setTitle("UserPage");
                            stagePageUser.setScene(scene);
                            stagePageUser.setResizable(false);
                            stagePageUser.show();
                        } catch (IOException ex) {

                        }
                    }else{
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("/views/configuratie.fxml"));
                            AnchorPane root = fxmlLoader.load();

                            ConfiguratieSala ctrl = fxmlLoader.getController();
                            ctrl.setAdmin(adminLogat);
                            ctrl.setReprezentatie(reprezentatie);
                            ctrl.setService(service);


                            Stage stagePageUser = new Stage();
                            Scene scene = new Scene(root, 1200, 562);

                            stagePageUser.setTitle("UserPage");
                            stagePageUser.setScene(scene);
                            stagePageUser.setResizable(false);
                            stagePageUser.show();
                        } catch (IOException ex) {

                        }
                    }
                }
            });
            i += 100;
            lastLayoutY = i;

        }

    }
}
