package Utilities;



public class movDat_parser {

    private static int id;
    private static String title;
    private static String director;
    private static String cast;
    private static String plot;
    private static float rating;
    private String coverURL;



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

    public int compareTo(movDat_parser dat_parser)
    {
        int res=0;
        if (rating < dat_parser.rating) {res=-1;  }
        if (rating > dat_parser.rating){res=1;}
        return res;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}
