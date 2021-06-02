import controller.LoginController;
import domain.Loc;
import domain.Reprezentatie;
import domain.Rezervare;
import domain.StareLoc;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.*;
import repository.orm.*;
import service.Service;

import java.sql.Timestamp;
import java.time.Instant;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        SessionFactoryInit sessionFactory = new SessionFactoryInit();




        IClientRepository clientRepository = new ClientORMRepository(sessionFactory);
        IAdminRepository adminRepository = new AdminORMRepository(sessionFactory);
        ILocRepository locRepository = new LocORMRepository(sessionFactory);
        IReprezentatieRepository reprezentatieRepository = new ReprezentatieORMRepository(sessionFactory);
        IDatePersonaleRepository datePersonaleRepository = new DatePersonaleORMRepository(sessionFactory);
        IRezervareRepository rezervareRepository = new RezervareORMRepository(sessionFactory);
        reprezentatieRepository.findAll().forEach(System.out::println);
        Service service = new Service(clientRepository,adminRepository,locRepository,reprezentatieRepository,datePersonaleRepository,rezervareRepository);


//        Loc loc12 = new Loc((long)12,1,1,1,(float)32.4,StareLoc.REZERVAT,2);
//        Loc loc13 = new Loc((long)13,1,2,2,(float)43.4,StareLoc.REZERVAT,2);
//        Loc loc14 = new Loc((long)14,1,3,3,(float)25.5,StareLoc.REZERVAT,2);
//        Loc loc15 = new Loc((long)15,1,4,4,(float)53.4,StareLoc.REZERVAT,2);
//        Loc loc16 = new Loc((long)16,2,1,5,(float)45.4,StareLoc.REZERVAT,2);
//        Loc loc17 = new Loc((long)17,2,2,6,(float)46.4,StareLoc.REZERVAT,2);
//        Loc loc18 = new Loc((long)18,2,3,7,(float)47.4,StareLoc.REZERVAT,2);
//        Loc loc19 = new Loc((long)19,2,4,8,(float)48.4,StareLoc.REZERVAT,2);
//        Loc loc20 = new Loc((long)20,3,1,9,(float)12.4,StareLoc.REZERVAT,2);
//        Loc loc21 = new Loc((long)21,3,2,10,(float)113.4,StareLoc.REZERVAT,2);
//        Loc loc22 = new Loc((long)22,4,3,11,(float)13.4,StareLoc.REZERVAT,2);
//        Loc loc23 = new Loc((long)23,4,4,12,(float)21.4,StareLoc.REZERVAT,2);
//        locRepository.save(loc12);
//        locRepository.save(loc13);
//        locRepository.save(loc14);
//        locRepository.save(loc15);
//        locRepository.save(loc16);
//        locRepository.save(loc17);
//        locRepository.save(loc18);
//        locRepository.save(loc19);
//        locRepository.save(loc20);
//        locRepository.save(loc21);
//        locRepository.save(loc22);
//        locRepository.save(loc23);
        //locRepository.findAll().forEach(System.out::println);
        //adminRepository.findAll().forEach(System.out::println);
        // clientRepository.findAll().forEach(System.out::println);

        //datePersonaleRepository.findAll().forEach(System.out::println);
     //   Rezervare rezervare2 = new Rezervare((long)2,(long)1,(long)1,(long)1,Timestamp.from(Instant.now()),(long)1);
        //service.salvare_rezervare(rezervare2);
        // rezervareRepository.save(rezervare2);
       // service.actualizareStareLocREZERVAT((long)1);
        //locRepository.findAll().forEach(System.out::println);
//        service.actualizareStareLocLiber(1);
//        service.actualizareStareLocLiber(2);
//        service.actualizareStareLocLiber(3);
//        service.actualizareStareLocLiber(4);
//        service.actualizareStareLocLiber(5);
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/login.fxml"));
        AnchorPane root=loader.load();


        LoginController ctrl=loader.getController();
        ctrl.setService(service);

        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setTitle("LoginPage");
        primaryStage.show();

    }
}
