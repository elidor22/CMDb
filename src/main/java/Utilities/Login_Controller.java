package Utilities;

import DatabaseConnection.users;
import DatabaseConnection.userController;

public class Login_Controller {
    userController ctrl = new userController();
    Encryption_Provider encrypt = new Encryption_Provider();
    usrDat_parser dat = new usrDat_parser();
    public String username = "EV";


    /**
     * All password are stored in an hashed form so
     * it's impossible to validate them without a
     * middleware that calls encryption provider validator and returns the desired result
     * */
    public boolean validate_login( String password, String saltedpass){

        boolean truepass = false;
        //Setup starts the Hibernate session, while query finds the user
        //ctrl.setup();
       // ctrl.query(user, username);
        truepass = encrypt.checkPassword(password, saltedpass);
        System.out.println(truepass);

        return truepass;

    }


    /**Validates if the logged user is administrator or not
     * then returns a boolean to be used in advance
     * */
    public boolean validate_admin(String username, String user){
        ctrl.setup();
        ctrl.query(username, user);

        boolean valid = dat.isIs_admin();
        System.out.println("Username is "+dat.getUsername());
        return valid;
    }

//Testing main
    public static void main(String args[]){
        Login_Controller lg = new Login_Controller();

       boolean validated= lg.validate_admin("EV","el");
        System.out.println(validated);
    }

}
