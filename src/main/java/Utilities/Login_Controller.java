package Utilities;

import DatabaseConnection.users;
import DatabaseConnection.userController;

public class Login_Controller {
    userController ctrl = new userController();
    users usr = new users();
    Encryption_Provider encrypt = new Encryption_Provider();
    usrDat_parser dat = new usrDat_parser();
    public String username = "EV";


    public boolean validate_login(String username, String password){

        boolean truepass = false;
        //Setup starts the Hibernate session, while query finds the user
        ctrl.setup();
        ctrl.query(username);
        truepass = encrypt.checkPassword(password, dat.getPassword());

        return truepass;

    }

    public boolean validate_admin(String username){
        ctrl.setup();
        ctrl.query(username);

        boolean valid = dat.isIs_admin();

        return valid;
    }


    public static void main(String args[]){
        Login_Controller lg = new Login_Controller();

       boolean validated= lg.validate_admin("User");
        System.out.println(validated);
    }

}
