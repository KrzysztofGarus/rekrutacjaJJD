package pl.com.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                List<Block> innerBlocks = ((CompositeBlock) block).getBlocks();
                for (Block innerBlock : innerBlocks) {
                    if (innerBlock.getColor().equals(color)) {
                        return Optional.of(innerBlock);
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                List<Block> innerBlocks = ((CompositeBlock) block).getBlocks();
                for (Block innerBlock : innerBlocks) {
                    if (innerBlock.getMaterial().equals(material)) {
                        result.add(innerBlock);
                    }
                }
            }
            if (block.getMaterial().equals(material)) {
                result.add(block);
            }
        }
        return result;
    }

    @Override
    public int count() {
        int count = 0;
        for (Block block : blocks) {
            count++;
            if (block instanceof CompositeBlock) {
                List<Block> innerBlocks = ((CompositeBlock) block).getBlocks();
                count += innerBlocks.size() - 1;
            }
        }
        return count;
    }
}






