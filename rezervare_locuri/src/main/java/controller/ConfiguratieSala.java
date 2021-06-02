package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;
import utils.events.ChangeEvent;
import utils.events.ChangeEventType;
import utils.observer.Observer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfiguratieSala  implements Observer<ChangeEvent> {
    private Service service;
    private Reprezentatie reprezentatie;
    private Admin adminLogat;
    private boolean adminON = false;
    public ConfiguratieSala(){

    }
    private Client clientLogat;
    private Loc loc_selectat = null;
    private Integer big_x_pos_intr= 0;
    private DatePersonale datePersonale=null;
    public void setService(Service service){
        this.service = service;
        service.addObserver(this);

        initializareLocuri();

        if(adminON == false) {
            generateInsertDataButton();
            generateRezervaButton();
        }else{
            genereazaButtonStergereLoc();
            genereazaButonAdaugaLoc();
        }

    }
    public void genereazaButonAdaugaLoc(){
        Button btn = new Button("Adauga un loc");
        btn.wrapTextProperty().setValue(true);
        btn.setPrefWidth(100);
        btn.setPrefHeight(50);
        btn.setLayoutX(big_x_pos_intr+300);
        btn.setLayoutY(200);

        btn.setStyle("-fx-background-color: #3379FF;-fx-text-fill: black;");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {




                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/views/adauga_loc.fxml"));
                        AnchorPane root = fxmlLoader.load();

                        AdaugareLocController ctrl = fxmlLoader.getController();
                        ctrl.setReprezentatie(reprezentatie);
                        ctrl.setService(service);


                        Stage stagePageUser = new Stage();
                        Scene scene = new Scene(root, 600, 400);

                        stagePageUser.setTitle("Adaugare loc");
                        stagePageUser.setScene(scene);
                        stagePageUser.setResizable(false);
                        stagePageUser.show();
                    } catch (Exception ex) {

                    }



            }
        });

        anchorPane.getChildren().add(btn);
    }
    public void genereazaButtonStergereLoc(){

        Button btn = new Button("Sterge locul");
        btn.wrapTextProperty().setValue(true);
        btn.setPrefWidth(100);
        btn.setPrefHeight(50);
        btn.setLayoutX(big_x_pos_intr+300);
        btn.setLayoutY(100);

        btn.setStyle("-fx-background-color: #3379FF;-fx-text-fill: black;");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(loc_selectat != null) {
                    for (Map.Entry<Long, Button> entry : locuriButoane.entrySet()) {
                        Loc locBtn = service.getLoc(entry.getKey());
                        String id_btn = Long.toString(locBtn.getId());
                        Button btnn = (Button) anchorPane.lookup("#" + id_btn);
                        anchorPane.getChildren().remove(btnn);
                    }

                    service.stergeLoc(loc_selectat);

                    locuriButoane.clear();
                    initializareLocuri();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Trebuie sa selectezi un loc pentru al sterge!");
                    alert.showAndWait();
                }
            }
        });

        anchorPane.getChildren().add(btn);
    }
    @Override
    public void update(ChangeEvent event) {
        ChangeEventType t = event.getType();
        //daca se adauga o rezervare
        if(t == ChangeEventType.L_ADD){
            for (Map.Entry<Long, Button> entry : locuriButoane.entrySet()) {
                Loc locBtn = service.getLoc(entry.getKey());
                String id_btn = Long.toString(locBtn.getId());
                Button btnn = (Button) anchorPane.lookup("#" + id_btn);
                anchorPane.getChildren().remove(btnn);
            }
            locuriButoane.clear();
            initializareLocuri();
        }

        if(t == ChangeEventType.R_ADD){
            for (Map.Entry<Long, Button> entry : locuriButoane.entrySet()) {
                Loc locBtn = service.getLoc(entry.getKey());
                String id_btn = Long.toString(locBtn.getId());
                Button btnn = (Button)anchorPane.lookup("#" + id_btn);
                if(locBtn.getStare() == StareLoc.REZERVAT){
                    btnn.setStyle("-fx-background-color: #FF3633;-fx-text-fill: white;");
                    btnn.setText("Numarul: "+locBtn.getNumar()+'\n' +"Pret: "+ locBtn.getPret() + '\n' + "Stare: " + locBtn.getStare().toString());
                } else{
                    btnn.setStyle("-fx-background-color: #37FF33;-fx-text-fill: black;");
                    btnn.setText("Numarul: "+locBtn.getNumar()+'\n' +"Pret: "+ locBtn.getPret() + '\n' + "Stare: " + locBtn.getStare().toString());
                }


            }
        }


    }
    public void setReprezentatie(Reprezentatie reprezentatie){
        this.reprezentatie = reprezentatie;
    }
    public void setUser(Client client){
        this.clientLogat = client;
    }
    public void setAdmin(Admin admin){
        this.adminLogat = admin;
        this.adminON = true;
    }

    public Map<Long,Button> locuriButoane = new HashMap<>();

    public void generateRezervaButton(){
        Button btn = new Button("Rezerva locul");
        btn.wrapTextProperty().setValue(true);
        btn.setPrefWidth(100);
        btn.setPrefHeight(50);
        btn.setLayoutX(big_x_pos_intr+300);
        btn.setLayoutY(100);

        btn.setStyle("-fx-background-color: #3379FF;-fx-text-fill: black;");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                datePersonale = service.getDatePersonaleByClient(clientLogat);
                if (loc_selectat !=null) {
                    if (loc_selectat.getStare() == StareLoc.LIBER) {
                        if (datePersonale != null) {
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/views/confirmare_rezervare.fxml"));
                                AnchorPane root = fxmlLoader.load();

                                ConfirmareRezervareController ctrl = fxmlLoader.getController();
                                ctrl.setLoc(loc_selectat);
                                ctrl.setDatePersonale(datePersonale);
                                ctrl.setUser(clientLogat);
                                ctrl.setService(service);


                                Stage stagePageUser = new Stage();
                                Scene scene = new Scene(root, 600, 400);

                                stagePageUser.setTitle("Confirmare");
                                stagePageUser.setScene(scene);
                                stagePageUser.setResizable(false);
                                stagePageUser.show();
                            } catch (Exception ex) {

                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("Trebuie sa-ti introduci datele personale!");
                            alert.showAndWait();
                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Locul selectat este rezervat!");
                        alert.showAndWait();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Trebuie sa selectezi un loc!");
                    alert.showAndWait();
                }
            }
        });

        anchorPane.getChildren().add(btn);
    }
    public void generateInsertDataButton(){
        Button btn = new Button("Introdu datele personale");
        btn.wrapTextProperty().setValue(true);
        btn.setPrefWidth(100);
        btn.setPrefHeight(50);
        btn.setLayoutX(big_x_pos_intr+150);
        btn.setLayoutY(100);

        btn.setStyle("-fx-background-color: #3379FF;-fx-text-fill: black;");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/date_personale.fxml"));
                    AnchorPane root = fxmlLoader.load();

                    DatePersonaleController ctrl = fxmlLoader.getController();
                    ctrl.setUser(clientLogat);
                    ctrl.setService(service);



                    Stage stagePageUser = new Stage();
                    Scene scene = new Scene(root, 600, 400);

                    stagePageUser.setTitle("DatePersonale");
                    stagePageUser.setScene(scene);
                    stagePageUser.setResizable(false);
                    stagePageUser.show();
                }catch(IOException ex){

                }

            }
        });

        anchorPane.getChildren().add(btn);
    }

    public void initializareLocuri(){
        locuriButoane.clear();
        big_x_pos_intr=0;
        int i=0;
        int y;
        int last=1;
        Iterable<Loc> locuri  = service.getLocuribyReprezentatie(reprezentatie.getId());
        for(Loc loc : locuri){
            if(last!=loc.getRandul()){
                i=0;
            }
            y = loc.getRandul();
            last = y;
            Button btn = new Button("Numarul: "+loc.getNumar()+'\n' +"Pret: "+ loc.getPret() + '\n' + "Stare: " + loc.getStare().toString());
            btn.wrapTextProperty().setValue(true);
            btn.setPrefWidth(100);
            btn.setPrefHeight(100);
            btn.setLayoutX(100 + i);
            if(big_x_pos_intr<100+i){
                big_x_pos_intr = 100+i;
            }
            btn.setLayoutY(0 + (y-1) * 120);
            if(loc.getStare() == StareLoc.LIBER){
                btn.setStyle("-fx-background-color: #37FF33;-fx-text-fill: black;");

            }
            else{
                btn.setStyle("-fx-background-color: #FF3633;-fx-text-fill: white;");
            }
            btn.setId(Long.toString(loc.getId()));
            anchorPane.getChildren().add(btn);
            locuriButoane.put(loc.getId(),btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for(Button btnX: locuriButoane.values()){
                        if(btnX.getText().contains("LIBER")){
                            btnX.setStyle("-fx-background-color: #37FF33;-fx-text-fill: black;");
                        }
                        else{
                            btnX.setStyle("-fx-background-color: #FF3633;-fx-text-fill: white;");
                        }
                    }
                    btn.setStyle("-fx-background-color: yellow;-fx-text-fill: black;");
                    Long id_loc = loc.getId();
                    loc_selectat = service.getLoc(id_loc);


                }
            });
            i += 120;

        }

    }
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public void initialize() {

    }
}
