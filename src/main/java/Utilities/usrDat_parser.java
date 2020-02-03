package Utilities;


/**
 * As we need this information to persist between classes
 * we need some static variables, that will never change as long as we don't change those
 * and also will not interfere with the Hibernate sessions
 * */
public class usrDat_parser {
    private static int id;
    private static String username;
    private static String password;
    private static boolean is_admin;
    private String url_Icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getUrl_Icon() {
        return url_Icon;
    }

    public void setUrl_Icon(String url_Icon) {
        this.url_Icon = url_Icon;
    }
}
