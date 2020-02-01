package DatabaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class fav_Controller {
    SessionFactory sessionFactory;
    public void setup() {
        // code to load Hibernate Session factory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void add(String usrId, int movId){
    favourites favourites = new favourites();

    favourites.setMov_id(movId);
    favourites.setUsr_id(usrId);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(favourites);

        session.getTransaction().commit();
        session.close();
    }

    public void query(String usrId){
        String hql = "from favourites where usr_id = usrId";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("usrId", "%" + usrId + "%");
        List<favourites> movies = query.list();
        favourites fv = new favourites();
        fv = movies.get(0);
        System.out.println(fv.getMov_id());
    }

    public static void main(String [] args){
        fav_Controller ctrl = new fav_Controller();
        ctrl.setup();
        //ctrl.add(1,2);
        ctrl.query(1+"");

    }
}
