package edu.umb.cs681.hw07;

public class RunnableConcurrentSingleton implements Runnable {
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }
}