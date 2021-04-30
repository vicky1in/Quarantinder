package com.groun24.quarantinder.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Locale;

import com.groun24.quarantinder.Modal.Tag;
import com.groun24.quarantinder.Services.TagManager;


@Repository
public class TagFormatter implements Formatter<Tag> {

    @Autowired
    TagManager tagManager;

    @Override
    public String print(Tag tag, Locale locale) {
        return tag.getName();
    }

    @Override
    public Tag parse(String tagID, Locale locale) throws ParseException {
        return tagManager.get(Integer.parseInt(tagID));
    }
}
