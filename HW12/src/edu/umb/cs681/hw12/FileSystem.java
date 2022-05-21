package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem implements Runnable {

    private LinkedList<Directory> directoryroot;
    private static FileSystem instance = null;

    void appendRootDir(Directory RootDirectory) {
        directoryroot = new LinkedList<Directory>();
        directoryroot.add(RootDirectory);
    }
    private FileSystem() {};

    public static FileSystem getFileSystem() {
        if(instance==null)
            instance = new FileSystem ();
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        return this.directoryroot;
    }

    @Override
    public void run() {

        System.out.println("\nThread "+Thread.currentThread().getId()+":");

    }

    public static void main(String[] args) {

        LocalDateTime localTime = LocalDateTime.now();

        FileSystem fs = new FileSystem();

        Directory root = new Directory(null, "root", 0, localTime);

        Directory applications = new Directory(root, "applications", 0, localTime);
        File photo = new File(applications, "photo", 55, localTime);
        File video = new File(applications, "video", 70, localTime);
        Thread t1 = new Thread(fs);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Directory "+root.getName()+" is added.");
        System.out.println(photo.getName()+" is added to the directory "+root.getName()+".");
        System.out.println(video.getName()+" is added to the directory "+root.getName()+".");
        System.out.println("Size of " + root.getName() + " dir: " + root.getTotalSize());
        System.out.println("No. of files: "+video.getParent().countChildren());


        Directory home = new Directory(root, "home", 0, localTime);
        File photo1 = new File(home, "photo1", 25, localTime);
        File video1 = new File(home, "video1", 170, localTime);
        Thread t2 = new Thread(fs);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        System.out.println("Directory "+home.getName()+" is added.");
        System.out.println(photo1.getName()+" is added to the directory "+home.getName()+".");
        System.out.println(video1.getName()+" is added to the directory "+home.getName()+".");
        System.out.println("Size of " + home.getName() + " dir: " + home.getTotalSize());
        System.out.println("No. of files: "+video1.getParent().countChildren());

        Directory code = new Directory(home, "code", 0, localTime);
        File photo2 = new File(code, "photo2", 70, localTime);
        File video2 = new File(code, "video2", 130, localTime);
        Thread t3 = new Thread(fs);
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        System.out.println("Directory "+code.getName()+" is added.");
        System.out.println(photo2.getName()+" is added to the directory "+code.getName()+".");
        System.out.println(video2.getName()+" is added to the directory "+code.getName()+".");
        System.out.println("Size of " + code.getName() + " dir: " + code.getTotalSize());
        System.out.println("No. of files: "+video2.getParent().getParent().countChildren());

    }

}
