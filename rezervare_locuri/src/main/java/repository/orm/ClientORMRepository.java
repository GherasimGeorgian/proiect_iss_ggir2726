package repository.orm;

import domain.Admin;
import domain.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.IClientRepository;

import java.util.ArrayList;
import java.util.List;


public class ClientORMRepository implements IClientRepository {

    SessionFactoryInit sessionFactory;

    public ClientORMRepository(SessionFactoryInit sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Client loginClient(String username, String password) {
        sessionFactory.initialize();
        Client client =null;
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    client =
                            (Client)session.createQuery("from Client where username=:fn and password=:ln")
                                    .setParameter("fn", username)
                                    .setParameter("ln", password)
                                    .uniqueResult();
                }catch(Exception ex){
                    ex.printStackTrace();
                }


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }catch (Exception ex){

            ex.printStackTrace();
        }finally
        {
            sessionFactory.close();
        }
        return client;

    }
    @Override
    public void save(Client entity) {

    }
    @Override
    public Client findOne(Long id_param) {
        return null;
    }
    @Override
    public Iterable<Client> findAll() {
        sessionFactory.initialize();

        List<Client> clientii = new ArrayList<>();
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    clientii =
                            session.createQuery("from Client").list();
                }catch(Exception ex){
                    ex.printStackTrace();
                }


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }catch (Exception ex){

            ex.printStackTrace();
        } finally
        {
            sessionFactory.close();
        }
        return clientii;

    }
    @Override
    public void update(Client entity,Client newEntity) {

    }
    @Override
    public void delete(Long id){

    }



}