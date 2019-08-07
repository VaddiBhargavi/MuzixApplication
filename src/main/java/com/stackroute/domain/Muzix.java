package com.stackroute.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Muzix {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int rating;
    private String comments;
    //Default constructor
    public Muzix() {
    }
    //parameterised constructor
    public Muzix(int id, String name, int rating, String comments) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.comments = comments;
    }
    //setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Muzix{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}
