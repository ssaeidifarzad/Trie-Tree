import java.util.Scanner;

public class TrieTree {
    Node root;

    public void insertWord(String word) {
        if (root == null)
            root = new Node(false);
        if (searchWord(word)) {
            System.out.println("Word already exists");
            return;
        }
        Node iterator = root;
        char[] chars = word.toCharArray();
        if (validityCheck(chars)) {
            for (char c : chars) {
                if (iterator.getNode(c) == null)
                    iterator.insertNode(c, false);
                iterator.increaseSubTreeWordCount();
                iterator = iterator.getNode(c);
            }
            iterator.setWord(true);
        }
    }

    public boolean searchWord(String word) throws NullPointerException {
        if (root == null)
            throw new NullPointerException("Empty tree");
        Node iterator = root;
        char[] chars = word.toCharArray();
        if (!validityCheck(chars))
            return false;
        for (char c : chars) {
            if (iterator.getNode(c) == null)
                return false;
            iterator = iterator.getNode(c);
        }
        return iterator.isWord();
    }

    public void deleteWord(String word) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        if (!searchWord(word)) {
            System.out.println("Word doesn't exist");
            return;
        }
        char[] chars = word.toCharArray();
        deleteNodes(root, chars, 0);
    }

    public String[] autoComplete(String word) throws NullPointerException {
        if (root == null)
            throw new NullPointerException("Empty tree");
        char[] chars = word.toCharArray();
        Node iterator = root;
        for (char c : chars) {
            iterator = iterator.getNode(c);
            if (iterator == null)
                return new String[0];
        }
        String[] suggestions = new String[iterator.getSubTreeWordCount()];
        int[] index = {0};
        String[] wordToBeSuggested = {word};
        subTreeCollector(iterator, word, wordToBeSuggested, suggestions, index);
        return suggestions;
    }

    private void subTreeCollector(Node root, String mainWord, String[] wordToBeSuggested, String[] suggestions, int[] index) {
        if (root.isWord() && !wordToBeSuggested[0].equals(mainWord)) {
            suggestions[index[0]++] = wordToBeSuggested[0];
        }
        if (root.isEmpty())
            return;
        Node[] children = root.getChildren();
        for (int i = 0; i < 27; i++) {
            if (children[i] != null) {
                String temp = wordToBeSuggested[0];
                wordToBeSuggested[0] = wordToBeSuggested[0] + (i == 26 ? ' ' : ((char) (i + 97)));
                subTreeCollector(children[i], mainWord, wordToBeSuggested, suggestions, index);
                wordToBeSuggested[0] = temp;
            }
        }
    }

    private void deleteNodes(Node root, char[] chars, int i) {
        root.decreaseSubTreeWordCount();
        if (i == chars.length) {
            root.setWord(false);
            return;
        }
        if (root.getNode(chars[i]) == null)
            return;
        if (root.getNode(chars[i]).isEmpty())
            root.deleteChildNode(chars[i]);
        else
            deleteNodes(root.getNode(chars[i]), chars, i + 1);
    }

    private boolean validityCheck(char[] chars) {
        for (char c : chars) {
            if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c == 32))) {
                System.out.println("Invalid character");
                return false;
            }
        }
        return true;
    }
}

class Node {


    private Node[] children;
    private int childCount, subTreeWordCount;
    private boolean isWord;

    public Node(boolean isWord) {
        this.isWord = isWord;
        childCount = 0;
        subTreeWordCount = 0;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public void insertNode(char c, boolean isWord) {
        if (children == null)
            children = new Node[27];
        if (c >= 65 && c <= 90)
            c += 32;
        if (c == 32)
            children[26] = new Node(isWord);
        else
            children[(c + 7) % 26] = new Node(isWord);
        childCount++;
    }

    public Node getNode(char c) {
        if (children == null)
            return null;
        if (c >= 65 && c <= 90)
            c += 32;
        return c == 32 ? children[26] : children[(c + 7) % 26];
    }

    public void deleteChildNode(char c) {
        if (c == 32)
            children[26] = null;
        else {
            if (c >= 65 && c <= 90)
                c += 32;
            children[c % 26] = null;
        }
        childCount--;
        if (childCount == 0)
            children = null;
    }

    public Node[] getChildren() {
        return children;
    }

    public boolean isEmpty() {
        return childCount == 0;
    }

    public int getSubTreeWordCount() {
        return subTreeWordCount;
    }

    public void increaseSubTreeWordCount() {
        subTreeWordCount++;
    }

    public void decreaseSubTreeWordCount() {
        subTreeWordCount--;
    }
}

class Main {
    public static void main(String[] args) {
        TrieTree t = new TrieTree();
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}