package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {

    private LinkedList<FSElement> children;
    LinkedList<Directory> subDirectories = new LinkedList<Directory>();
    LinkedList<File> files = new LinkedList<File>();
    
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if(parent != null)
            parent.appendChild(this);
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            return this.children;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        if(this.children == null) {
            this.children = new LinkedList<FSElement>();
        }
        this.children.add(child);
    }

    //counts number of sub-directories in the directory.
    public int countChildren() {
        lock.lock();
        try {
            return this.children.size();
        } finally {
            lock.unlock();
        }

    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            for (FSElement element : getChildren()) {
                if (element.isDirectory())
                	subDirectories.add((Directory) element);
            }
            return subDirectories;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            for (FSElement element : getChildren()) {
                if (element instanceof File)
                    files.add((File) element);
            }
            return files;
        } finally {
            lock.unlock();
        }

    }

    //calculates size of files inside the directory
    public int getTotalSize() {
        lock.lock();
        try {
            int totalSize = 0;
            for(FSElement element : getChildren()) {
                if(element instanceof Directory)
                	totalSize += ((Directory) element).getTotalSize();
                else
                	totalSize += element.getSize();
            }
            return totalSize;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }

    }

}
