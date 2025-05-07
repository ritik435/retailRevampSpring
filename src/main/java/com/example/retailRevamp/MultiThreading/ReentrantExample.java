package com.example.retailRevamp.MultiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private Lock lock=new ReentrantLock();

    private void outer(){

        lock.lock();
        try{
            innerLock();
            System.out.println("OUTER METHOD");
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
    private void innerLock(){

        lock.lock();
        try{
//            innerLock();
            System.out.println("INNER METHOD");
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args){
        ReentrantExample reentrantExample=new ReentrantExample();
        reentrantExample.outer();
    }
}
