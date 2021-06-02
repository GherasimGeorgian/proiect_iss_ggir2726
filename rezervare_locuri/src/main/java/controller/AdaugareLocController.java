package controller;

import domain.Loc;
import domain.Reprezentatie;
import domain.StareLoc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

public class AdaugareLocController {
    private Service service;
    private Reprezentatie repr;
    public void setService(Service service){
        this.service = service;

    }
    public void setReprezentatie(Reprezentatie repr){
        this.repr =repr;
    }
    @FXML
    Button btnAdauga;

    @FXML
    javafx.scene.control.TextField txtRand;

    @FXML
    javafx.scene.control.TextField txtLoja;
    @FXML
    javafx.scene.control.TextField txtNumar;
    @FXML
    javafx.scene.control.TextField txtPret;

    @FXML
    public void initialize() {
        btnAdauga.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    long maxId = service.getMaxIdLoc();

                    Loc locNew = new Loc(maxId,Integer.parseInt(txtRand.getText()),Integer.parseInt(txtLoja.getText()),Integer.parseInt(txtNumar.getText()),Float.valueOf(txtPret.getText()), StareLoc.LIBER,repr.getId());


                    service.adaugaLoc(locNew);
                    Stage stagex = (Stage) btnAdauga.getScene().getWindow();
                    stagex.close();
                } catch (Exception e) {

                }
            }
        });
    }
}
