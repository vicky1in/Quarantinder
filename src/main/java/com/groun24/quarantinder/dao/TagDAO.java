package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.Tag;

import org.springframework.stereotype.Repository;

@Repository(value = "TagDAO")
public class TagDAO extends BaseDAO<Tag> {

    public TagDAO() {
        super(Tag.class);
    }

    public List<Tag> get() {
        return queryDatabase("FROM Tag");  
    }
    
    public Tag get(String name) {
        final String queryString = String.format("FROM Tag WHERE name=\'%s\'", name);
        List<Tag> list = queryDatabase(queryString);  
        if (list.isEmpty()) {
            return null;
        }
        else {
            return list.get(0);
        }
    }
}
