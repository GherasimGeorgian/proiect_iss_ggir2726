package controller;

import domain.Client;
import domain.DatePersonale;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;


public class DatePersonaleController {
    private Service service;
    private Client clientLogat;
    public void setUser(Client client){
        this.clientLogat = client;
    }
    public void setService(Service service){
        this.service = service;
        initializareDatePers();

    }
    @FXML
    javafx.scene.control.TextField txtNume;

    @FXML
    javafx.scene.control.TextField txtPrenume;

    @FXML
    javafx.scene.control.TextField txtVarsta;

    @FXML
    javafx.scene.control.TextField txtNumarTelefon;

    @FXML
    javafx.scene.control.TextField txtMentiunii;

    @FXML
    Button btnSalveaza;

    @FXML
    public void initialize() {
        btnSalveaza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    long maxId = service.getMaxIdDatePersonale();
                    DatePersonale datePersonaleClient = new DatePersonale(maxId+1,clientLogat.getId(),txtNume.getText(),txtPrenume.getText(),txtNumarTelefon.getText(),txtMentiunii.getText(),Integer.parseInt(txtVarsta.getText()));
                    service.salveazaDatePersonale(datePersonaleClient);
                    btnSalveaza.setDisable(true);
                } catch (Exception e) {

                }
            }
        });
    }

    public void initializareDatePers(){
        DatePersonale datepersClient = service.getDatePersonaleByClient(clientLogat);
        if(datepersClient != null){
            txtNume.setText(datepersClient.getNume());
            txtPrenume.setText(datepersClient.getPrenume());
            txtVarsta.setText(datepersClient.getVarsta().toString());
            txtNumarTelefon.setText(datepersClient.getNumar_telefon());
            txtMentiunii.setText(datepersClient.getAlte_mentiuni());
            btnSalveaza.setDisable(true);
        }else{
            btnSalveaza.setDisable(false);
        }
    }
}
