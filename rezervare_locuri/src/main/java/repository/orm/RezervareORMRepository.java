package repository.orm;

import domain.Client;
import domain.Reprezentatie;
import domain.Rezervare;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.IRezervareRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RezervareORMRepository implements IRezervareRepository {
    SessionFactoryInit sessionFactory;

    public RezervareORMRepository(SessionFactoryInit sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(Rezervare entity) {
        sessionFactory.initialize();


        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    session.save(entity);

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


    }
    @Override
    public long getMaxId(){
        sessionFactory.initialize();
        long maxid=-1;
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    maxid =
                            (long)session.createQuery("from Rezervare ORDER BY id DESC").setMaxResults(1).uniqueResult();;
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
        return maxid;

    }
    @Override
    public void delete(Long id){

    }
    @Override
    public void update(Rezervare entity,Rezervare newEntity) {

    }
    @Override
    public Rezervare findOne(Long id_param) {
        return null;
    }
    @Override
    public Iterable<Rezervare> findAll() {
        sessionFactory.initialize();

        List<Rezervare> rezervarii = new ArrayList<>();
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    rezervarii =
                            session.createQuery("from Rezervare").list();
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
        return rezervarii;

    }


}
