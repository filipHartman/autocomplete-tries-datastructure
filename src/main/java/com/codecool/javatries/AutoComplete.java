package com.codecool.javatries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AutoComplete {
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
        HashMap<Character, TrieDataNode> children = root.getChildren();

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

            children = current.getChildren();
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
        HashMap<Character, TrieDataNode> children = root.getChildren();
        TrieDataNode lastCommonNode = root;
        StringBuilder wordPrefix = new StringBuilder();
        for(int i = 0; i < baseChars.length(); i++) {
            char letter = baseChars.charAt(i);

            if(Character.isUpperCase(letter)) {
                if(children.get(letter) != null) {
                    lastCommonNode = children.get(letter);
                } else if(children.get(Character.toLowerCase(letter)) != null) {
                    lastCommonNode = children.get(Character.toLowerCase(letter));
                }
                else {
                    return new ArrayList<>();
                }

            } else {
                if(children.get(letter) != null) {
                    lastCommonNode = children.get(letter);
                } else if(children.get(Character.toUpperCase(letter)) != null) {
                    lastCommonNode = children.get(Character.toUpperCase(letter));
                }
                else {
                    return new ArrayList<>();
                }
            }
            wordPrefix.append(lastCommonNode.getData());
            children = lastCommonNode.getChildren();
        }

        getRestLetters(words, wordPrefix.toString(), lastCommonNode);

        Collections.sort(words);
        return words;
    }

    private void getRestLetters(List<String> words, String word, TrieDataNode currentNode) {
        if(currentNode.isWord) {
            words.add(word);
        }
        for(TrieDataNode child : currentNode.getChildren().values()) {
            if(child != null) {
                getRestLetters(words, word + child.getData(), child);
            }
        }
    }

    /**
     * Removes a word from the Trie
     * @return true if the removal was successful
     */
    public boolean removeWord(String wordToRemove) {
        // TODO -- Optional homework
        HashMap<Character, TrieDataNode> children = root.getChildren();
        for(int i = 0; i < wordToRemove.length(); i++) {
            char letter = wordToRemove.charAt(i);
            if(children.get(letter) != null) {
                if(children.size() == 1) {
                    children.put(letter, null);
                    return true;
                } else {
                    children = children.get(letter).getChildren();
                }
            } else {
                return false;
            }
        }
        return false;
    }

}
