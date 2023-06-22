package com.blog;

public class BlogPost {
    int _id;
    String _name;
    int _ownerId;
    String[] _content;

    public BlogPost(int id, int owner, String name, String[] content) {
        _id = id; _ownerId = owner; _name = name; _content = content;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public int getOwnerId() {
        return _ownerId;
    }

    public String[] getContent() {
        return _content;
    }
}
