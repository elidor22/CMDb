package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


//Clases imported to get the saved data
import DatabaseConnection.MovieDbController;
import DatabaseConnection.movies;
import Utilities.MovieSorter;
public class Main  {
    movies movies = new movies();
    List<movies> ls;
    MovieSorter sorter;
    JFrame f = new JFrame();

  //panel1
  JPanel p1= new JPanel();
  JButton searchB;
  JTextField search;
  JButton next;
  JButton prev;
  JLabel title;
  JTextArea cast;
  JLabel director;
  JLabel rating;
  JTextArea plot;
  JLabel movieIcon;
  JButton login;
  JLabel localUsername;
  Font titleF = new Font(Font.MONOSPACED,Font.PLAIN,50);
  Font castF = new Font(Font.MONOSPACED,Font.PLAIN,25);
  Font f30 = new Font(Font.MONOSPACED,Font.PLAIN,30);
  Font plotF = new Font(Font.MONOSPACED,Font.PLAIN, 22);

  Font f35 = new Font(Font.MONOSPACED, Font.PLAIN,35);


  //directorF, ratingF, p1UsernameF

  //panel2
   JPanel p2 = new JPanel();
   JTextField user;
   JTextField username;
   JPasswordField password;
   JButton loginConfirm;
   JLabel userL;
   JLabel usernameL;
   JLabel passwordL;
   JButton back;


   //panel3


public Main() throws IOException {

    f.add(p1);
    f.add(p2);

    //panel1
    title = new JLabel("Joker");   // movie title label
    title.setBounds(700,170,150,50);
    title.setFont(titleF);

    cast = new JTextArea("cast");     //cast information area
    cast.setBounds(700,230,100,35);
    cast.setFont(castF);
    cast.setEditable(false);
    cast.setOpaque(false);
    cast.setLineWrap(true);
    cast.setWrapStyleWord(true);

    director = new JLabel("Director:Todd Phillips");  //director name label
    director.setBounds(700,270,400,35);
    director.setFont(f30);

    rating = new JLabel("Rating:8.6/10");         //rating label
    rating.setBounds(700,310,260,40);
    rating.setFont(f30);

    plot = new JTextArea("Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City. Arthur wears two masks -- the one he paints for his day job as a clown, and the guise he projects in a futile attempt to feel like he's part of the world around him. Isolated, bullied and disregarded by society, Fleck begins a slow descent into madness as he transforms into the criminal mastermind known as the Joker.");
    plot.setBounds(100,600,750,300);  //plot area
    plot.setFont(plotF);
    plot.setOpaque(false);
    plot.setLineWrap(true);
    plot.setWrapStyleWord(true);

    localUsername = new JLabel("Local Name");  // username on panel 1
    localUsername.setBounds(1200,200,250,40);
    localUsername.setFont(f35);

    movieIcon = new JLabel();   //movie icon link
    movieIcon.setIcon(new ImageIcon(new URL("https://cmdbcit.blob.core.windows.net/cmdb/anchorman-2.jpg")));
    movieIcon.setBounds(100,100,580,500);

    next = new JButton("Next");  //next button
    next.setBounds(700,350,120,30);
    next.setFont(castF);

    prev = new JButton("Prev");  //previous button
    prev.setBounds(700,390,120,30);
    prev.setFont(castF);

    login = new JButton("Log in");   // panel 1 log in button
    login.setBounds(1220,250,200,40);
    login.setFont(f30);

    search = new JTextField();   // movie name search field
    search.setBounds(150,40,550,32);

    searchB = new JButton("Search");  //button for searching
    searchB.setBounds(710,40,100,30);



    p1.add(search);
    p1.add(searchB);
    p1.add(title);
    p1.add(cast);
    p1.add(director);
    p1.add(rating);
    p1.add(plot);
    p1.add(localUsername);
    p1.add(login);
    p1.add(movieIcon);
    p1.add(next);
    p1.add(prev);
    p1.setSize(1500,950);
    p1.setLayout(null);
    p1.setVisible(true);

    //panel2



    loginpanel();
    search();
   //Jframe
    f.setSize(1500,950);
    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setResizable(false);
}

    public static void main(String[] args) throws IOException {
     Main mn = new Main();


    }

    void loginpanel(){
    login.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            panel2();
        }
    });

    }

void panel2(){
    f.add(p2);
    user = new JTextField();       // log in user
    user.setBounds(200,40,200,40);

    username = new JTextField();  // local user
    username.setBounds(200,120,200,40);

    password = new JPasswordField();   // password input field
    password.setBounds(200,200,200,40);

    userL = new JLabel("User");
    userL.setBounds(50,40,180,40);
    userL.setFont(f35);

    usernameL = new JLabel("Username");
    usernameL.setBounds(10,120,180,40);
    usernameL.setFont(f35);

    passwordL = new JLabel("Password");
    passwordL.setBounds(10,200,180,40);
    passwordL.setFont(f35);

    loginConfirm = new JButton("Log in");  // panel 2 login button
    loginConfirm.setBounds(150,280,150,40);

    back = new JButton("Back");   //takes you to panel 1
    back.setBounds(320,280,100,40);

    p2.add(loginConfirm);
    p2.add(userL);
    p2.add(usernameL);
    p2.add(passwordL);
    p2.add(username);
    p2.add(password);
    p2.add(user);
    p2.add(back);
    p2.setBounds(525,300,450,350);
    p2.setBackground(Color.gray);
    p2.setLayout(null);
    p1.setVisible(false);
    p2.setVisible(true);


}

void search(){
    MovieDbController db = new MovieDbController();
    db.setup();

    searchB.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            db.query(search.getText());

            ls = db.list;
            movies = ls.get(0);
            title.setText(movies.getTitle());
            cast.setText(movies.getCast());
            director.setText(movies.getDirector());
            rating.setText(String.valueOf(movies.getRating())+"/10");
            plot.setText(movies.getPlot());
            try {
                movieIcon.setIcon(new ImageIcon(new URL(movies.getCoverURL())));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    });
    movie_NavigatorButtons();

}


void movie_NavigatorButtons(){
    next.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int next=1;
        if(ls.size()>1&&next<=ls.size()-1){

            movies = ls.get(next);
            title.setText(movies.getTitle());
            cast.setText(movies.getCast());
            director.setText(movies.getDirector());
            rating.setText(String.valueOf(movies.getRating())+"/10");
            plot.setText(movies.getPlot());
            next++;
            try {
                movieIcon.setIcon(new ImageIcon(new URL(movies.getCoverURL())));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        }
    });
    prev.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int prev=ls.size()-2;
            if(ls.size()>1&&prev<=ls.size()-1){

                movies = ls.get(prev);
                title.setText(movies.getTitle());
                cast.setText(movies.getCast());
                director.setText(movies.getDirector());
                rating.setText(String.valueOf(movies.getRating())+"/10");
                plot.setText(movies.getPlot());
                prev--;
                try {
                    movieIcon.setIcon(new ImageIcon(new URL(movies.getCoverURL())));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    }

}
