package com.codecool.javatries;

import java.util.ArrayList;
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
        TrieDataNode[] childern = root.getChildrens();

        for(int i = 0; i <wordToAdd.length(); i++) {
            char letter = wordToAdd.charAt(i);
            int index = letter - 'a';
            TrieDataNode current;
            if(childern[index] != null) {
                current = childern[index];
            } else {
                current = new TrieDataNode(letter);
                childern[index] = current;
            }

            childern = current.getChildrens();
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
        TrieDataNode[] childeren = root.getChildrens();
        TrieDataNode lastCommonNode = root;

        for(int i = 0; i < baseChars.length(); i++) {
            char letter = baseChars.charAt(i);
            int index = letter - 'a';
            if(childeren[index] != null) {
                lastCommonNode = childeren[index];
            } else {
                return new ArrayList<>();
            }
            childeren = lastCommonNode.getChildrens();
        }


        if(lastCommonNode.isWord) {
            words.add(baseChars);
        } else {
            getRestLetters(words, baseChars, lastCommonNode);
        }

        return words;
    }

    private void getRestLetters(List<String> words, String word, TrieDataNode currentNode) {
        if(currentNode.isWord) {
            words.add(word);
        }
        for(TrieDataNode child : currentNode.getChildrens()) {
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
        return false;
    }

}
