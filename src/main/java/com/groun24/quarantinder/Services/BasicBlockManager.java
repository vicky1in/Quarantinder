package com.groun24.quarantinder.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.groun24.quarantinder.dao.BlockDAO;
import com.groun24.quarantinder.Modal.Block;
import com.groun24.quarantinder.Modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BasicBlockManager implements BlockManager {

    @Autowired
    BlockDAO blockDAO;

    @Transactional
    public List<Block> get() {
        return blockDAO.get();
    }

    @Transactional
    @Override
    public Block get(int blockID) {
        return blockDAO.get(blockID);
    }

    @Transactional
    @Override
    public Block get(User blocker, User blockee) {
        return blockDAO.get(blocker, blockee);
    }

    @Transactional
    @Override
    public List<Block> get(User blocker) {
        return blockDAO.get(blocker);
    }

    @Transactional
    @Override
    public void save(Block block) {
        if (block.getBlocker().equals(block.getBlockee())) {
            return;
        }
        Block existingBlock = get(block.getBlocker(), block.getBlockee());
        if (existingBlock == null) {
            blockDAO.save(block);
        }
    }

    @Transactional
    @Override
    public void delete(int blockID) {
        if (blockDAO.get(blockID) != null) {
            blockDAO.delete(blockID);
        }
    }
    
}
