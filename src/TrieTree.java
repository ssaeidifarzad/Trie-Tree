import java.util.Scanner;

public class TrieTree {
    private Node root;

    public void insertWord(String word) {
        if (root == null)
            root = new Node(false);
        Node iterator = root;
        char[] chars = word.toCharArray();
        if (validityCheck(chars)) {
            for (char c : chars) {
                if (iterator.getNode(c) == null)
                    iterator.insertNode(c, false);
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
            System.out.println("Word not found");
        }
        char[] chars = word.toCharArray();
        deleteNodes(root, chars, 0);
    }

    private void deleteNodes(Node root, char[] chars, int i) {
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
    private int childCount;
    private boolean isWord;

    public Node(boolean isWord) {
        this.isWord = isWord;
        childCount = 0;
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
            children[c % 26] = new Node(isWord);
        childCount++;
    }

    public Node getNode(char c) {
        if (children == null)
            return null;
        if (c >= 65 && c <= 90)
            c += 32;
        return c == 32 ? children[26] : children[c % 26];
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

    public boolean isEmpty() {
        return childCount == 0;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}