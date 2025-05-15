package com.example.retailRevamp.MultiThreading;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        runnableCheck();
        runnableMannualCheck();
//        futureCheck();
    }

    private static void futureCheck() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
//        Runnable runnable=()->"HelloWorld class is called";
        Callable<?> callable = () -> "HelloWorld class is called";
        Future<?> future = executorService.submit(callable);
        System.out.println(future.get());
        while (!future.isDone()) {
            System.out.println("Waiting...");
        }
        executorService.shutdown();
    }

    private static void runnableCheck() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
//                result final;
            });
        }
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        //when all operations are done;
        //find total time taken in operation
        while (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
            System.out.println("Waiting....");
        }
        System.out.print("Total time taken ");
        System.out.println(startTime - System.currentTimeMillis());
        System.out.println("Is shutdown: " + executorService.isShutdown());

    }

    private static void runnableMannualCheck() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        int threadCount = 9;
        Thread[] threads = new Thread[threadCount];

        for (int i = 1; i <= threadCount; i++) {
            int finalI = i;
            Runnable task = () -> {
                long result = factorial(finalI);
                System.out.println("Factorial of " + finalI + " is: " + result);
            };
            threads[i - 1] = new Thread(task);
            threads[i - 1].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Total time taken: " + totalTime + " ms");
        System.out.println("All threads finished.");

    }

    private static long factorial(int fact) {
        long result = 1;
        for (int i = fact; i > 0; i--) {
            result *= i;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
