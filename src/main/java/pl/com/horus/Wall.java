package pl.com.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure, CompositeBlock {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }


    // zwraca pierwszy znaleziony element o danym kolorze
    // jesli podczas przeszukiwania natrafi na element typu CompositeBlock,
    // to przeszukuje go w celu znalezienia pasującego koloru
    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks){
            if (block instanceof CompositeBlock){
                Optional<Block> innerBlock = ((CompositeBlock) block).getBlocks().stream()
                       .filter(block1 -> getColor().equals(color)).findFirst();
                if (innerBlock.isPresent()){
                    return innerBlock;
                }
            }
            if (block.getColor().equals(color)){
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    // zwraca listę elementów
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>(0);
        for (Block block : blocks){
            if (block instanceof CompositeBlock){
                result.addAll(((CompositeBlock) block).getBlocks().stream()
                        .filter(block1 -> getMaterial().equals(material)).toList());
            }
            if (block.getMaterial().equals(material)){
                result.add(block);
            }
        }
        return result;
    }

    @Override
    public int count() {
        int count = 0;
        for (Block block: blocks) {
            count++;
            if (block instanceof CompositeBlock){
                count += ((CompositeBlock) block).getBlocks().size() - 1;
            }
        }
        return count;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }

    @Override
    public List<Block> getBlocks() {
        return null;
    }
}

interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

interface Block {
    String getColor();
    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
