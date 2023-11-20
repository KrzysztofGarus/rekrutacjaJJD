package pl.com.horus;

import java.util.List;

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
