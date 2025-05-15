package com.example.retailRevamp.MultiThreading;

class SharedResource{
    private int data;
    private boolean hasData;

    public synchronized void produce(int value){
        while(hasData){
            try{
//                System.out.println("Waiting : Produced content : "+value);
                wait();
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
        hasData=true;
        data=value;
        System.out.println("Produced content : "+value);
        notifyAll(); // wake up all consumers
    }
    public synchronized int consume(){
        while(!hasData){
            try{
//                System.out.println("Waiting Consume content : "+data);
                wait();
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
        hasData=false;
        System.out.println("Consumed content : "+data+" of Thread: "+Thread.currentThread().getName());
        notifyAll(); // wake up all producers
        return data;
    }
}
class Producer implements Runnable{
    SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable{
    SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            int value=resource.consume();
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource=new SharedResource();
        Thread producerThread =new Thread(new Producer(resource));
        Thread consumerThread1 =new Thread(new Consumer(resource),"Consumer1");
        Thread consumerThread2 =new Thread(new Consumer(resource),"Consumer2");
        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}
