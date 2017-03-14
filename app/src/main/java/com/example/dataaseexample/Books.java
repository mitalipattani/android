package com.example.dataaseexample;

/**
 * Created by naimesh on 3/13/2017. object model
 */
public class Books {
    private int id;
    private String title;
    private String author;

    public Books(){}

    public Books(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    //getters & setters
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author
                + "]";
    }
}

