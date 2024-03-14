package com.fullstack1.peliculas;

public class Pelicula {
    private int _id;
    private String _title;
    private int _year;
    private String _director;
    private String _genre;
    private String _synopsis;

    public Pelicula(int id, String title, int year, String director, String genre, String synopsis) {
        this._id = id;
        this._title = title;
        this._year = year;
        this._director = director;
        this._genre = genre;
        this._synopsis = synopsis;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    public String get_director() {
        return _director;
    }

    public void set_director(String _director) {
        this._director = _director;
    }

    public String get_genre() {
        return _genre;
    }

    public void set_genre(String _genre) {
        this._genre = _genre;
    }

    public String get_synopsis() {
        return _synopsis;
    }

    public void set_synopsis(String _synopsis) {
        this._synopsis = _synopsis;
    }

}
