package com.codecool.javatries;

import java.util.HashMap;

public class TrieDataNode {
    private char data;
    private HashMap<Character, TrieDataNode> childrens;
    boolean isWord;
    // TODO add further members as needed

    /**
     * Initializes a TrieDataNode with its data
     * @param data The data in this node
     */
    public TrieDataNode(char data) {
        this.data = data;
        this.childrens = new HashMap<>();
        this.isWord = false;
    }

    public char getData() {
        return data;
    }

    public HashMap<Character, TrieDataNode> getChildren() {
        return childrens;
    }

    @Override
    public String toString() {
        return Character.toString(data);
    }

}
