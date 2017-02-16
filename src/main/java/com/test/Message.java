package com.test;

import java.io.Serializable;

/**
 * Created by aanu.oyeyemi on 1/6/17.
 * Project name -> GamePlayer
 */
public class Message implements Serializable{
    private   String link;
    private   String message;
    private  String message1;

    private boolean CONTINUES=true;

    public Message(){
    }

    public Message(String link, String message){
        this.link=link;
        this.message=message;
    }


    public  String getLink() {
        return link;
    }

    public  void setLink(String link) {
       this.link = link;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public  String getMessage() {
        return message;
    }

    public boolean isCONTINUES() {
        return CONTINUES;
    }

    public void setCONTINUES(boolean CONTINUES) {
        this.CONTINUES = CONTINUES;
    }


    public  void setMessage(String message){this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "link='" + link + '\'' +
                ", message='" + message + '\'' +
                ", message1='" + message1 + '\'' +
                ", CONTINUES=" + CONTINUES +
                '}';
    }
}
