package DatabaseConnection;

import Utilities.Encryption_Provider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;
import Utilities.usrDat_parser;
public class userController {
    SessionFactory sessionFactory;
    public List<users> usercl;


    /**
     * Just like book controller, utilizes Hibernate to return
     * application data
     * */
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

    //Read a user when the id is available, throws exception if the id is multiplicated
    void read(){
        Session session = sessionFactory.openSession();
        int id = 1;
        users usr = session.get(users.class, id);
        System.out.println("Username " + usr.getUsername());
        System.out.println("Password " + usr.getPassword());
        System.out.println("Is admin " + usr.isIs_admin());
        session.close();

    }

    //Create a new user
    public void create(String user,String username, String password) {
        users usr = new users();
        Encryption_Provider encrypt = new Encryption_Provider();
        String pass = encrypt.hashPassword(password);

        usr.setUsername(username);
        usr.setPassword(pass);
        usr.setUser(user);


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(usr);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Uses user and username to prevent problems
     * with similar words
     */
    public void query(String username, String user){
        String hql = "from users where username like:keyword and user like:user";
        Session session = sessionFactory.openSession();
        String keyword = username;
        Query query = session.createQuery(hql);
        query.setParameter("keyword",  "%"+ keyword + "%");
        query.setParameter("user","%"+user+"%");

        usrDat_parser dat_parser = new usrDat_parser();
        usercl=query.list();
        List<users> usr = query.list();
        for (users usr1 : usr) {
           // System.out.println("Username " + user.getUsername());
           // System.out.println("Password " + user.getPassword());
           // System.out.println("Is admin " + user.isIs_admin());
            dat_parser.setId(usr1.getId());
            dat_parser.setUsername(usr1.getUsername());
            dat_parser.setPassword(usr1.getPassword());
            dat_parser.setIs_admin(usr1.isIs_admin());

        }

        session.close();


    }

    protected void update(String username, String password, boolean isAdmin) {
        // code to modify a user account
        users usr = new users();
        Encryption_Provider encrypt = new Encryption_Provider();
        String pass = encrypt.hashPassword(password);

        usr.setId(13);
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
        //ctrl.query("EV");
        ctrl.update("Eminem","saltednow",true);
        //ctrl.read();

    }


}
