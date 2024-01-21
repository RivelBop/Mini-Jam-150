package com.rivelbop.rivelgdx.utils;

import com.badlogic.gdx.files.FileHandle;

// Text class used to store string data from loaded txt file
public class Text {
    private String string;

    public Text() {
        string = new String("".getBytes());
    }

    public Text(byte[] data) {
        string = new String(data);
    }

    public Text(String string) {
        string = new String(string.getBytes());
    }

    public Text(FileHandle file) {
        string = new String(file.readBytes());
    }

    public Text(Text text) {
        string = new String(text.getString().getBytes());
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void clear() {
        string = new String("".getBytes());
    }
}