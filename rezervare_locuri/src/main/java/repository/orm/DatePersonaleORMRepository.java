package repository.orm;

import domain.Client;
import domain.DatePersonale;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.IDatePersonaleRepository;

import java.util.ArrayList;
import java.util.List;

public class DatePersonaleORMRepository implements IDatePersonaleRepository {
    SessionFactoryInit sessionFactory;

    public DatePersonaleORMRepository(SessionFactoryInit sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(DatePersonale entity) {
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
    public void update(DatePersonale entity,DatePersonale newEntity) {

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
                            (long)session.createQuery("from DatePersonale ORDER BY id DESC").setMaxResults(1).uniqueResult();;
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
    public DatePersonale findOne(Long id_param) {
        return null;
    }
    @Override
    public Iterable<DatePersonale> findAll() {
        sessionFactory.initialize();

        List<DatePersonale> datepersonalelist = new ArrayList<>();
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    datepersonalelist =
                            session.createQuery("from DatePersonale").list();
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
        return datepersonalelist;

    }


}
