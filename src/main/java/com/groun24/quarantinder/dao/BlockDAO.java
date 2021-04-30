package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.Block;
import com.groun24.quarantinder.Modal.User;

import org.springframework.stereotype.Repository;

@Repository(value = "BlockDAO")
public class BlockDAO extends BaseDAO<Block> {

    public BlockDAO() {
        super(Block.class);
    }
    
    public List<Block> get() {
        return queryDatabase("FROM Block");  
    } 

    public Block get(User blocker, User blockee) {
        final String queryString = String.format("FROM Block WHERE blocker = %d AND blockee = %d", blocker.getId(), blockee.getId());
        final List<Block> list = queryDatabase(queryString);
        if (list.isEmpty()) {
            return null;
        }
        else {
            return list.get(0);
        }
    }

    public List<Block> get(User blocker) {
        String queryString = String.format("FROM Block WHERE blocker = %d", blocker.getId());
        return queryDatabase(queryString);
    }
}
