package repository.orm;

import domain.Loc;
import domain.Reprezentatie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.IReprezentatieRepository;

import java.util.ArrayList;
import java.util.List;

public class ReprezentatieORMRepository implements IReprezentatieRepository {
    SessionFactoryInit sessionFactory;

    public ReprezentatieORMRepository(SessionFactoryInit sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Long id){

    }
    @Override
    public void save(Reprezentatie entity) {
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
    public Reprezentatie findOne(Long id_param) {

        sessionFactory.initialize();
        Reprezentatie reprezentatie = null;
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    reprezentatie =
                            session.createQuery("from Reprezentatie b where b.id like :id", Reprezentatie.class).setParameter("id", id_param).setMaxResults(1).uniqueResult();
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
        return reprezentatie;
    }
    @Override
    public Iterable<Reprezentatie> findAll() {
        sessionFactory.initialize();

        List<Reprezentatie> reprezentatii = new ArrayList<>();
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    reprezentatii =
                            session.createQuery("from Reprezentatie",Reprezentatie.class).list();
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
        return reprezentatii;

    }
    @Override
    public void update(Reprezentatie entity,Reprezentatie newEntity) {

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
                            (long)session.createQuery("from Reprezentatie ORDER BY id DESC").setMaxResults(1).uniqueResult();;
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


}
