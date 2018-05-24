package com.codecool.javatries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AutoComplete {
    private static final int UPPERCASE_INDEX_OFFSET = 26;
    private TrieDataNode root;

    /**
     * Starts a new Trie with dummy root data "-"
     */
    public AutoComplete() {
        root = new TrieDataNode('-');
    }

    /**
     * Adds a word to the Trie.
     */
    public void addWord(String wordToAdd) {
        HashMap<Character, TrieDataNode> children = root.getChildrens();

        for(int i = 0; i <wordToAdd.length(); i++) {
            char letter = wordToAdd.charAt(i);

            TrieDataNode current;
            if(children.get(letter) != null) {
                current = children.get(letter);
            }
            else {
                current = new TrieDataNode(letter);
                children.put(letter, current);
            }

            children = current.getChildrens();
            if(i == wordToAdd.length() - 1) {
                current.isWord = true;
            }
        }
    }

    /**
     * Returns the possible completions of baseChars String from the Trie.
     * @param baseChars The String to autocomplete.
     * @return possible completions. An empty list if there are none.
     */
    public List<String> autoComplete(String baseChars) {
        List<String> words = new ArrayList<>();
        HashMap<Character, TrieDataNode> children = root.getChildrens();
        TrieDataNode lastCommonNode = root;
        StringBuilder wordPrefix = new StringBuilder();
        for(int i = 0; i < baseChars.length(); i++) {
            char letter = baseChars.charAt(i);

            if(children.get(letter) != null) {
                lastCommonNode = children.get(letter);
            }
            else {
                return new ArrayList<>();
            }
            wordPrefix.append(lastCommonNode.getData());
            children = lastCommonNode.getChildrens();
        }





        if(lastCommonNode.isWord) {
            words.add(wordPrefix.toString());
        } else {
            getRestLetters(words, wordPrefix.toString(), lastCommonNode);
        }
        Collections.sort(words);
        return words;
    }

    private void getRestLetters(List<String> words, String word, TrieDataNode currentNode) {
        if(currentNode.isWord) {
            words.add(word);
        }
        for(TrieDataNode child : currentNode.getChildrens().values()) {
            if(child != null) {
                getRestLetters(words, word + child.getData(), child);
            }
        }
    }

    private int getIndexForLetter(char letter) {

        if(Character.isUpperCase(letter)) {
            return letter - 'A' + UPPERCASE_INDEX_OFFSET;
        }
        else {
            return letter - 'a';
        }
    }
    /**
     * Removes a word from the Trie
     * @return true if the removal was successful
     */
    public boolean removeWord(String wordToRemove) {
        // TODO -- Optional homework
        return false;
    }

}
