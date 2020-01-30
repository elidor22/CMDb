package Utilities;

import DatabaseConnection.users;
import DatabaseConnection.userController;

public class Login_Controller {
    userController ctrl = new userController();
    Encryption_Provider encrypt = new Encryption_Provider();
    usrDat_parser dat = new usrDat_parser();
    public String username = "EV";


    public boolean validate_login( String password, String saltedpass){

        boolean truepass = false;
        //Setup starts the Hibernate session, while query finds the user
        //ctrl.setup();
       // ctrl.query(user, username);
        truepass = encrypt.checkPassword(password, saltedpass);
        System.out.println(truepass);

        return truepass;

    }

    public boolean validate_admin(String username, String user){
        ctrl.setup();
        ctrl.query(username, user);

        boolean valid = dat.isIs_admin();
        System.out.println("Username is "+dat.getUsername());
        return valid;
    }


    public static void main(String args[]){
        Login_Controller lg = new Login_Controller();

       boolean validated= lg.validate_admin("EV","el");
        System.out.println(validated);
    }

}
