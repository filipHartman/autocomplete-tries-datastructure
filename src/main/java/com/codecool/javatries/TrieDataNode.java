package com.codecool.javatries;

public class TrieDataNode {
    public static final int ALPHABETIC_SIZE = 52;
    private char data;
    private TrieDataNode[] childrens;
    boolean isWord;
    // TODO add further members as needed

    /**
     * Initializes a TrieDataNode with its data
     * @param data The data in this node
     */
    public TrieDataNode(char data) {
        this.data = data;
        this.childrens = new TrieDataNode[ALPHABETIC_SIZE];
        this.isWord = false;
    }

    public char getData() {
        return data;
    }

    public TrieDataNode[] getChildrens() {
        return childrens;
    }

    public void addChildNode(int index, char data) {
        childrens[index] = new TrieDataNode(data);
    }

    @Override
    public String toString() {
        return Character.toString(data);
    }

}
