package DatabaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class userController {
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

    void read(){
        Session session = sessionFactory.openSession();
        int id = 3;
        users usr = session.get(users.class, id);
        System.out.println("Username " + usr.getUsername());
        System.out.println("Password " + usr.getPassword());
        session.close();

    }
    protected void create(String username, String password) {
        // code to save a book
        users usr = new users();
        Encryption_Provider encrypt = new Encryption_Provider();
        String pass = encrypt.hashPassword(password);

        usr.setUsername(username);
        usr.setPassword(pass);


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(usr);

        session.getTransaction().commit();
        session.close();
    }


    public static void main(String args[]){
        userController ctrl = new userController();
        ctrl.setup();
        ctrl.create("User55", "hashed_one");
        ctrl.read();

    }
}
