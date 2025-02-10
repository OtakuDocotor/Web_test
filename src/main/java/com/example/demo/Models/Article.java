package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _Id;
    private String _Text,_Anons,_Title;
    private int views;
    public Long get_Id() {
        return _Id;
    }
    public void set_Id(Long _Id) {
        this._Id = _Id;
    }
    public String get_Title() {
        return _Title;
    }
    public void set_Title(String _Title) {
        this._Title = _Title;
    }
    public String get_Anons() {
        return _Anons;
    }
    public void set_Anons(String _Anons) {
        this._Anons = _Anons;
    }
    public String get_Text() {
        return _Text;
    }
    public void set_Text(String _Text) {
        this._Text = _Text;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public Article(String title,String anons,String full_text)
    {
        this._Title=title;
        this._Anons=anons;
        this._Text=full_text;
        this.views=0;
    }
    public Article()
    {
    }
}
