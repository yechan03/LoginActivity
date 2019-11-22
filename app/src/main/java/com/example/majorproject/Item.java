package com.example.majorproject;

public class Item {
    public Item(String id, String content, String uid) {
        this.id = id;
        this.content = content;
        this.uid = uid;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String content;
    private String uid;
}
