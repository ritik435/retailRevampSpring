package com.example.retailRevamp.MultiThreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance=100;
    private ReentrantLock lock=new ReentrantLock();

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw amount: "+amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(amount<=balance) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " is proceeding to withdraw amount: " + amount);
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " is successfully withdraw amount newBalance: " + balance);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + " Insufficient Balance : " + balance);
                }
            }else{
                System.out.println(Thread.currentThread().getName() + " is not able to aquire the lock");
            }
        } catch (InterruptedException e) {
        }
    }
}
