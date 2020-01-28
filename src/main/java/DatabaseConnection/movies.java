package DatabaseConnection;


import Utilities.movDat_parser;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class movies {
    private int id;
    private String title;
    private String director;
    private String cast;
    private String plot;
    private float rating;

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int compareTo(movies mov)
    {
        int res=0;
        if (rating < mov.rating) {res=-1;  }
        if (rating > mov.rating){res=1;}
        return res;
    }
}
