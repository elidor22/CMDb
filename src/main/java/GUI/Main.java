package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


//Clases imported to get the saved data
import DatabaseConnection.*;
import Utilities.*;

public class Main  {
    movies movies = new movies();
    List<movies> ls;
    JFrame f = new JFrame();
    List<users> usr ;
    userController ctrl = new userController();
    Login_Controller logctrl = new Login_Controller();
    usrDat_parser parser = new usrDat_parser();
    MovieDbController db = new MovieDbController();
    boolean validated;

  //panel1
  JPanel p1= new JPanel();
  JButton searchB;
  JButton addMovie = new JButton("Add new movie");
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
  JLabel userIcon;
  JButton createUser;
    Font titleF = new Font(Font.MONOSPACED,Font.PLAIN,50);
    Font castF = new Font(Font.MONOSPACED,Font.PLAIN,24);
    Font f30 = new Font(Font.MONOSPACED,Font.PLAIN,30);
    Font plotF = new Font(Font.MONOSPACED,Font.PLAIN, 20);
    Font serif20 = new Font(Font.SERIF,Font.PLAIN,20);
    Font f25 = new Font(Font.MONOSPACED,Font.PLAIN,25);
    Font f35 = new Font(Font.MONOSPACED, Font.PLAIN,35);
    Font serif18 = new Font(Font.SERIF,Font.PLAIN,18);

  //directorF, ratingF, p1UsernameF

  //panel2
   JPanel p2 = new JPanel();
   JTextField user = new JTextField();
   JTextField username=new JTextField();
   JPasswordField password= new JPasswordField();
   JButton loginConfirm;
   JLabel userL;
   JLabel usernameL;
   JLabel passwordL;
   JButton back = new JButton("Back");


   //panel3
   JPanel p3 = new JPanel();
    JLabel p3Title;
    JLabel p3Cast;
    JLabel p3Director;
    JLabel p3Rating;
    JLabel p3Plot;
    JTextField titleField;
    JTextArea castArea;
    JTextField directorField;
    JTextField ratingField;
    JTextArea plotArea;
    JButton p3Upload = new JButton();
    JButton p3Back= new JButton();

public Main() throws IOException {

    f.add(p1);
    f.add(p2);

    //panel1
    title = new JLabel("Joker");   // movie title label
    title.setBounds(80,40,400,50);
    title.setFont(titleF);

    cast = new JTextArea("feat:Joaquin Phoenix, Frances Conroy");     //cast information area
    cast.setBounds(80,100,320,65);
    cast.setFont(castF);
    cast.setEditable(false);
    cast.setOpaque(false);
    cast.setLineWrap(true);
    cast.setWrapStyleWord(true);

    director = new JLabel("Director:Todd Phillips");  //director name label
    director.setBounds(80,175,400,35);
    director.setFont(f30);

    rating = new JLabel("Rating:8.6/10");         //rating label
    rating.setBounds(80,215,260,40);
    rating.setFont(f30);

    plot = new JTextArea("Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City. Arthur wears two masks -- the one he paints for his day job as a clown, and the guise he projects in a futile attempt to feel like he's part of the world around him. Isolated, bullied and disregarded by society, Fleck begins a slow descent into madness as he transforms into the criminal mastermind known as the Joker.");
    plot.setBounds(80,260,380,550);  //plot area
    plot.setFont(plotF);
    plot.setOpaque(false);
    plot.setLineWrap(true);
    plot.setWrapStyleWord(true);

    localUsername = new JLabel("Local Name");  // username on panel 1
    localUsername.setBounds(1220,200,250,40);
    localUsername.setFont(f25);

    movieIcon = new JLabel();   //movie icon link
    movieIcon.setBounds(500,107,620,500);
   // movieIcon.setIcon(new ImageIcon(new URL("https://cmdbcit.blob.core.windows.net/cmdb2/Ghostbusters_logo.png")));
    coverResizer("https://comicfrontline.com/wp-content/uploads/2019/10/The-Joker.jpg");


    next = new JButton("Next");  //next button
    next.setBounds(840,630,120,30);
    next.setFont(serif20);

    prev = new JButton("Prev");  //previous button
    prev.setBounds(690,630,120,30);
    prev.setFont(serif20);

    //TODO: userIcon
    if(validated) {

        userIcon = new JLabel();
        userIcon.setBounds(1220,40,250,200);
        setUsericon();
        p1.add(userIcon);
    }

    login = new JButton("Log in");   // panel 1 log in button
    login.setBounds(1220,250,200,40);
    login.setFont(serif20);

    addMovie.setBounds(1220,300,200,40);
    addMovie.setFont(serif20);

    search = new JTextField();   // movie name search field
    search.setBounds(500,50,510,32);

    searchB = new JButton("Search");  //button for searching
    searchB.setBounds(1020,50,100,30);
    searchB.setFont(serif20);

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
    p1.setSize(1500,780);
    p1.setLayout(null);
    p1.setVisible(true);
    p2.setVisible(false);
    //panel3();
    //panel2
    loginpanel();
    search();
    addmovies();
   //Jframe
    f.setSize(1500,780);
    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setResizable(false);
    try {
        URL url = new URL("http://www.google.com");
        URLConnection connection = url.openConnection();
        connection.connect();
        JOptionPane.showMessageDialog(f,"You're connected and ready to go");
    } catch (MalformedURLException e) {
        JOptionPane.showMessageDialog(f,"Check your internet connection");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(f,"Failed to connect to the internet");
    }
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

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                p2.setVisible(false);
                p1.setVisible(true);
            }
        });
        //login();
    }

void panel2(){
    f.add(p2);
    user = new JTextField();       // log in user
    user.setBounds(200,40,220,40);
    user.setFont(serif20);

    username = new JTextField();  // local user
    username.setBounds(200,120,220,40);
    username.setFont(serif20);

    password = new JPasswordField();   // password input field
    password.setBounds(200,200,220,40);
    password.setFont(serif20);

    userL = new JLabel("User");
    userL.setBounds(20,40,180,40);
    userL.setFont(f35);

    usernameL = new JLabel("Username");
    usernameL.setBounds(20,120,180,40);
    usernameL.setFont(f35);

    passwordL = new JLabel("Password");
    passwordL.setBounds(20,200,180,40);
    passwordL.setFont(f35);

    createUser = new JButton("Create User");
    createUser.setBounds(20,280,120,40);
    createUser.setFont(serif18);

    loginConfirm = new JButton("Log in");  // panel 2 login button
    loginConfirm.setBounds(170,280,120,40);
    loginConfirm.setFont(serif18);

    back = new JButton("Back");   //takes you to panel 1
    back.setBounds(320,280,100,40);
    back.setFont(serif18);

    p2.add(createUser);
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

    loginpanel();
    loginController();

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
                coverResizer(movies.getCoverURL());
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
            //upload();
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
                coverResizer(movies.getCoverURL());
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
                    coverResizer(movies.getCoverURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    }




   void loginController(){

        loginConfirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ctrl.setup();
                ctrl.query(username.getText(), user.getText());
                usr=ctrl.usercl;
                //Validate if the user information is valid in order to register it

                if(logctrl.validate_login(password.getText(), parser.getPassword())){
                    JOptionPane.showMessageDialog(f,"You're logged in "+parser.getUsername());
                    localUsername.setText(parser.getUsername());
                    p1.add(addMovie);
                    validated = true;
                }
                    else
                JOptionPane.showMessageDialog(f, "Try to enter a valid username/password");
            }

        });

    }


    void upload(){

    FileChooser fileChooser= new FileChooser();
    fileChooser.choose();
    }
    void coverResizer(String URL) throws MalformedURLException {
        ImageIcon MI = new ImageIcon(new URL(URL));
        Image img = MI.getImage();
        Image newImg = img.getScaledInstance(455, movieIcon.getHeight(),Image.SCALE_AREA_AVERAGING);
        ImageIcon image = new ImageIcon(newImg);
        movieIcon.setIcon(image);
    }

    void panel3(){
        p3Title = new JLabel("Movie title:");
        p3Title.setBounds(150,150,150,30);

        p3Cast = new JLabel("Cast:");
        p3Cast.setBounds(150,190,150,30);

        p3Director = new JLabel("Director:");
        p3Director.setBounds(150,300,150,30);

        p3Rating = new JLabel("Rating:");
        p3Rating.setBounds(150,340,150,30);

        p3Plot = new JLabel("Plot:");
        p3Plot.setBounds(150,380,150,30);

        titleField = new JTextField();
        titleField.setBounds(400,150,250,30);

        castArea = new JTextArea();
        castArea.setBounds(400,190,250,100);

        directorField = new JTextField();
        directorField.setBounds(400,300,250,30);

        ratingField = new JTextField();
        ratingField.setBounds(400,340,250,30);

        plotArea = new JTextArea();
        plotArea.setBounds(400,380,250,300);

        p3Upload = new JButton("Upload");
        p3Upload.setBounds(700,200,200,30);

        p3Back = new JButton("Next");
        p3Back.setBounds(700,240,200,30);




        p3.add(p3Title);
        p3.add(p3Cast);
        p3.add(p3Director);
        p3.add(p3Rating);
        p3.add(p3Plot);
        p3.add(titleField);
        p3.add(castArea);
        p3.add(directorField);
        p3.add(ratingField);
        p3.add(plotArea);
        p3.add(p3Upload);
        p3.add(p3Back);
        p3.setBounds(100,100,1300,750);
        p3.setBackground(Color.gray);
        p3.setLayout(null);
        p3.setVisible(true);
        f.add(p3);

        addmovies();
    }



    void addmovies(){

        addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               p1.setVisible(false);
               panel3();
            }
        });
    p3Upload.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        upload();
            db.setup();
            String rat = ratingField.getText();
            float rating = Float.valueOf(rat);
        db.create(titleField.getText(),directorField.getText(),p3Cast.getText(),plotArea.getText(),rating,fileUploader.url);

        }
    });

    p3Back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            p3.setVisible(false);
            p1.setVisible(true);
        }
    });

    }

    void setUsericon() throws MalformedURLException {
    userIcon.setIcon(new ImageIcon(new URL(parser.getUrl_Icon())));
    }
}
