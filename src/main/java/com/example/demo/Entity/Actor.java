package com.example.demo.Entity;


import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String realname;
    private String headshot;

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

//    private static  Byte[] image;
//




//    public void setImage(Byte[] image) {
//        Actor.image = image;
//    }
//



   // private BufferedImage  image;
   @ManyToMany(mappedBy = "cast")
    private Set<Movie> movies = new HashSet<Movie> (  );


    public void addMovie(Movie movie)
    {
        movies.add ( movie );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

//    public Blob getImage() {
//        return image;
//    }
//
//    public void setImage(Blob image) {
//        this.image = image;
//    }

}
