package DatabaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbTest {

    SessionFactory sessionFactory;

    protected void setup() {
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

    protected void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }

    protected void read() {
        // code to get a movie by id
        Session session = sessionFactory.openSession();

        int id = 1;
        movies mov = session.get(movies.class, id);

        System.out.println("Title: " + mov.getTitle());
        System.out.println("Cast: " + mov.getCast());
        System.out.println("The director is " + mov.getDirector());

        session.close();
    }

    public static void main(String args[]){
        DbTest dbTest = new DbTest();
        dbTest.setup();
       dbTest.read();
      //  dbTest.exit();


    }

}
