package DatabaseConnection;

import javax.persistence.*;

@Entity
@Table(name = "userfavourites")
public class userfavourites {
    private int user_id;
    private int movie_id;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
