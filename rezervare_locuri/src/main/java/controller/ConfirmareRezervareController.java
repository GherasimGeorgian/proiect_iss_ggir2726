package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.sql.Timestamp;
import java.time.Instant;

public class ConfirmareRezervareController {
    private Service service;
    private Client clientLogat;
    private DatePersonale datePersonale;
    private Loc locSelectat;
    private Reprezentatie reprezentatie;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label label5;
    @FXML
    Button btnConfirma;

    @FXML
    Button btnAnuleaza;


    public void setUser(Client client){
        this.clientLogat = client;
    }
    public void setService(Service service){
        this.service = service;
        informatiiRezervare();

    }
    public void setDatePersonale(DatePersonale datePersonale){
        this.datePersonale = datePersonale;

    }
    public void setLoc(Loc loc){
        this.locSelectat = loc;
    }
    public void informatiiRezervare(){
        reprezentatie = service.getReprezentatie(locSelectat.getId_reprezentatie());
        label1.setText("Date personale: Bilet rezervat pe numele :" + datePersonale.getNume() + " " + datePersonale.getPrenume() + " avand numarul de telefon:" + datePersonale.getNumar_telefon());
        label2.setText("Locul selectat: Randul:" + locSelectat.getRandul() + " Loja:" + locSelectat.getLoja() + " Numarul:"+ locSelectat.getNumar());
        label3.setText("Pret total:" + locSelectat.getPret());
        label4.setText("Reprezentatia: " + reprezentatie.getNume() + " cu rating de " + reprezentatie.getRating() + " stele");
        label5.setText("Data:" + reprezentatie.getDurata());
    }
    @FXML
    public void initialize() {
        btnConfirma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {




                try{
                    long id_rez = service.getMaxIdRezervare();
                    Rezervare rezervare = new Rezervare(id_rez, locSelectat.getId(), clientLogat.getId(), datePersonale.getId(), Timestamp.from(Instant.now()), locSelectat.getId_reprezentatie());
                    service.salvare_rezervare(rezervare);
                    Stage stagex = (Stage) btnConfirma.getScene().getWindow();
                    stagex.close();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Somethg");
                    alert.setHeaderText("Eroare");
                    alert.setContentText("Eroare");
                    alert.showAndWait();
                    Stage stagex = (Stage) btnConfirma.getScene().getWindow();
                    stagex.close();
                }



            }
        });
        btnAnuleaza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stagex = (Stage) btnAnuleaza.getScene().getWindow();
                stagex.close();

            }
        });
    }
}
