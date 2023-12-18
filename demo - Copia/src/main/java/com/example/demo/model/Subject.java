package com.example.demo.model;

import com.example.demo.controller.Observer;

public interface Subject{
    public void subscribe(Observer o);
    public void notif();

}
