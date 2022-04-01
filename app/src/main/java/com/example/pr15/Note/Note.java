package com.example.pr15.Note;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Note implements Serializable {
    private String header;
    private String content;
    private Time time = new Time (System.currentTimeMillis());
    private Integer state = 0;
    private Date date = new Date (System.currentTimeMillis());;
    public Note(){
        this.header ="default";
        this.content = "default";
        this.state = 0;
        this.date = new Date (System.currentTimeMillis());
        this.time = new Time (System.currentTimeMillis());
    }
    public Note(String header, String content, Time time){
        this.header = header;
        this.time = time;
        this.content = content;
    }

    public  void setHeader (String h) { this.header = h; }
    public  void setContent (String content) { this.content = content; }
    public String getHeader() { return  this.header; }
    public String getContent() { return  this.content; }
    public  void setTime (Time time) { this.time = time; }
    public Time getTime() { return  this.time; }

    public int getState() {
        return this.state;
    }
    public  void  setState(int state) {
        this.state = state;
    }
    public Date getDate() {
        return this.date;
    }
    public  void  setDate(Date date) {
        this.date = date;
    }
}
