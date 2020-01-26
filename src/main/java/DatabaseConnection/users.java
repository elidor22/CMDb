package DatabaseConnection;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class users {
    private int id;
    private String username;
    private String password;

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
    Encryption_Provider encryption_provider = new Encryption_Provider();
        this.password = encryption_provider.hashPassword(password);
    }
}
