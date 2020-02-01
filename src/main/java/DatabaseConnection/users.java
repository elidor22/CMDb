package DatabaseConnection;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class users {
    private int id;
    private String username;
    private String password;
    private boolean is_admin;
    private String user;
    private String url_Icon;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl_Icon() {
        return url_Icon;
    }

    public void setUrl_Icon(String url_Icon) {
        this.url_Icon = url_Icon;
    }
}
