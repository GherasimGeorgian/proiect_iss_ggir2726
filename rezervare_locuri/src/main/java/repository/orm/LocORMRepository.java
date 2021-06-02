package repository.orm;

import domain.DatePersonale;
import domain.Loc;
import domain.StareLoc;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.ILocRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocORMRepository implements ILocRepository {
    SessionFactoryInit sessionFactory;

    public LocORMRepository(SessionFactoryInit sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(Loc entity) {
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
    public Loc findOne(Long id_param) {

        sessionFactory.initialize();
        Loc loc = null;
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    loc =
                            session.createQuery("from Loc b where b.id like :id", Loc.class).setParameter("id", id_param).setMaxResults(1).uniqueResult();
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
        return loc;
    }
    @Override
    public Iterable<Loc> findAll() {
        sessionFactory.initialize();

        List<Loc> locuri = new ArrayList<>();
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    locuri =
                            session.createQuery("from Loc",Loc.class).list();
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
        return locuri;

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
                            (long)session.createQuery("from Loc ORDER BY id DESC").setMaxResults(1).uniqueResult();;
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
        sessionFactory.initialize();

        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    Loc locS = (Loc) session.get(Loc.class, id);
                    session.delete(locS);
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
    public void update(Loc entity,Loc newentity) {
        sessionFactory.initialize();
        try(Session session = sessionFactory.getSessionFactory().openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Loc loc = session.load( Loc.class, entity.getId());
                loc.setStare(newentity.getStare());
                session.update(loc);
                tx.commit();

            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }


}
