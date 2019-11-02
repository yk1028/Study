package com.yk1028.websocket.domain;

public class Message {

    private String contents;

    public Message() {
    }

    public Message(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
