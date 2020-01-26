package DatabaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MovieDbController {

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

        int id = 2;
        movies mov = session.get(movies.class, id);

        System.out.println("Title: " + mov.getTitle());
        System.out.println("Cast: " + mov.getCast());
        System.out.println("The director is " + mov.getDirector());

        session.close();
    }


    protected void create(String title, String author,String cast, String plot, float rating) {
        // code to save a book
        movies movies = new movies();
        movies.setTitle(title);
        movies.setDirector(author);
        movies.setCast(cast);
        movies.setPlot(plot);
        movies.setRating(rating);


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(movies);

        session.getTransaction().commit();
        session.close();
    }

    public static void main(String args[]){
        MovieDbController movieDbController = new MovieDbController();
        movieDbController.setup();
        movieDbController.read();
        //Just example data to test code functionality
       //dbTest.create("The punisher", "Marvel Entertainment", "JOhn Bernthal", "A guy getting revenge,", 9.17f);



    }

}
