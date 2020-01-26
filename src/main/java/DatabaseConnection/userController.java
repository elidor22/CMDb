package DatabaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

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
        int id = 1;
        users usr = session.get(users.class, id);
        System.out.println("Username " + usr.getUsername());
        System.out.println("Password " + usr.getPassword());
        System.out.println("Is admin " + usr.isIs_admin());
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

    public void query(String username){
        String hql = "from users where username like :keyword";
        Session session = sessionFactory.openSession();
        String keyword = username;
        Query query = session.createQuery(hql);
        query.setParameter("keyword", "%" + keyword + "%");

        List<users> usr = query.list();
        for (users user : usr) {
            System.out.println("Username " + user.getUsername());
            System.out.println("Password " + user.getPassword());
            System.out.println("Is admin " + user.isIs_admin());

        }
        session.close();

    }

    protected void update(String username, String password, boolean isAdmin) {
        // code to modify a user account
        users usr = new users();
        Encryption_Provider encrypt = new Encryption_Provider();
        String pass = encrypt.hashPassword(password);

        usr.setId(1);
        usr.setUsername(username);
        usr.setPassword(pass);
        usr.setIs_admin(isAdmin);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(usr);

        session.getTransaction().commit();
        session.close();
    }


    public static void main(String args[]){
        userController ctrl = new userController();
        ctrl.setup();
        //ctrl.create("User55", "hashed_one");
        ctrl.query("ev");
        //ctrl.update("EV","saltednow",true);
        //ctrl.read();

    }
}
