package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.Tag;

public interface TagManager {

    public List<Tag> get();
    
    public Tag get(int tagID);

    public Tag get(String name);
    
    public void save(Tag tag);

    public void save(String name);

    public void delete(int tagID);  
}