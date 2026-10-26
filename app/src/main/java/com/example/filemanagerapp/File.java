package com.example.filemanagerapp;

public class File {
    private final long id;
    private final String name;

    public File(long id, String name, int quantity, String category) {
        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }
    public String getName() { return name; }
}
