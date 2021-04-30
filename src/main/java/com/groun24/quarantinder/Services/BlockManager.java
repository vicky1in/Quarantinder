package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.Block;
import com.groun24.quarantinder.Modal.User;

public interface BlockManager {

    public List<Block> get();
    
    public Block get(int blockID);

    public Block get(User blocker, User blockee);
    
    public List<Block> get(User blocker);

    public void save(Block block);
    
    public void delete(int blockID);
    
}
