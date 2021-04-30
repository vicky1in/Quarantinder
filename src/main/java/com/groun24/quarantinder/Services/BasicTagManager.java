package com.groun24.quarantinder.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.groun24.quarantinder.dao.TagDAO;
import com.groun24.quarantinder.Modal.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicTagManager implements TagManager {

    @Autowired
    TagDAO tagDAO;

    @Transactional
    @Override
    public List<Tag> get() {
        return tagDAO.get();
    }
   
    @Transactional
    @Override
    public Tag get(int tagID) {
        return tagDAO.get(tagID);
    }

    @Transactional
    @Override
    public Tag get(String name) {
        return tagDAO.get(name);
    }

    @Transactional
    @Override
    public void save(Tag tag) {
        if (tagDAO.get(tag.getName()) == null) {
            tagDAO.save(tag);
        } 
    }

    @Transactional
    @Override
    public void save(String name) {
        if (tagDAO.get(name) == null) {
            Tag tag = new Tag();
            tag.setName(name);
            tagDAO.save(tag);
        } 
    }
    
    @Transactional
    @Override
    public void delete(int tagID) {
        tagDAO.delete(tagID);
    }
}