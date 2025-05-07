package com.example.retailRevamp.MultiThreading;

public class World implements Runnable{

    @Override
    public void run() {
        for(; ;) {
            System.out.println("World");
        }
    }
}
