package DatabaseConnection;


import javax.persistence.*;

@Entity
@Table(name = "favourites")
public class favourites {
    private int mov_id;
    private String usr_id;
    private int navId;


    public int getMov_id() {
        return mov_id;
    }

    public void setMov_id(int mov_id) {
        this.mov_id = mov_id;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    @Id
    @Column(name = "navId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getNavId() {
        return navId;
    }

    public void setNavId(int navId) {
        this.navId = navId;
    }
}
