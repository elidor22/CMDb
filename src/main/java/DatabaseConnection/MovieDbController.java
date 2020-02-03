package DatabaseConnection;

import Utilities.MovieSorter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class MovieDbController {

    SessionFactory sessionFactory;
    public static List<movies> list;

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

    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }

    public  void read() {
        // code to get a movie by id
        //Throws exception if there are duplicates so the id must be a primary key
        Session session = sessionFactory.openSession();

        int id = 2;
        movies mov = session.get(movies.class, id);

        System.out.println("Title: " + mov.getTitle());
        System.out.println("Cast: " + mov.getCast());
        System.out.println("The director is " + mov.getDirector());

        session.close();
    }


    //Creates a movie entry in the DB
    public void create(String title, String author,String cast, String plot, float rating, String coverUrl) {

        movies movies = new movies();
        movies.setTitle(title);
        movies.setDirector(author);
        movies.setCast(cast);
        movies.setPlot(plot);
        movies.setRating(rating);
        movies.setCoverURL(coverUrl);


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(movies);

        session.getTransaction().commit();
        session.close();
    }

    //Searches an entry based on the given parameters

    public void query(String title){
        String hql = "from movies where title like :keyword";
        Session session = sessionFactory.openSession();
        String keyword = title;
        Query query = session.createQuery(hql);
        query.setParameter("keyword", "%" + keyword + "%");

        List<movies> movies = query.list();
        MovieSorter sorter = new MovieSorter();
        list = sorter.sorted(movies);

        session.close();

    }


//A main class used to debug the class during it's development
    public static void main(String args[]){
        MovieDbController movieDbController = new MovieDbController();
        movieDbController.setup();
        //movieDbController.read();
       // movieDbController.query("The punisher");
        //Just example data to test code functionality
       //dbTest.create("The punisher", "Marvel Entertainment", "JOhn Bernthal", "A guy getting revenge,", 9.17f);



    }

}
