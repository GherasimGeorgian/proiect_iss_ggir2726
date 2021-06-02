package controller;

import domain.Admin;
import domain.Client;
import domain.DatePersonale;
import domain.Reprezentatie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import service.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReprezentatieAdaugareController {

    private Service service;

    @FXML
    Button btnSalveaza;

    @FXML
    javafx.scene.control.TextField txtNume;

    @FXML
    javafx.scene.control.TextField txtRating;

    @FXML
    DatePicker dpdata;


    public void setService(Service service){
        this.service = service;

    }

    @FXML
    public void initialize() {
        btnSalveaza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    long maxId = service.getMaxIdReprezentatie();
                    LocalDate localDate = dpdata.getValue();

                    Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
                    Reprezentatie reprezentatie = new Reprezentatie(maxId,txtNume.getText(),Integer.parseInt(txtRating.getText()),timestamp);


                    service.adaugaReprezentatie(reprezentatie);
                    Stage stagex = (Stage) btnSalveaza.getScene().getWindow();
                    stagex.close();
                } catch (Exception e) {

                }
            }
        });
    }
}
