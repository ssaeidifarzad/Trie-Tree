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

    private boolean validityCheck(char[] chars) {
        for (char c : chars) {
            if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c == 32))) {
                System.out.println("Invalid character");
                return false;
            }
        }
        return true;
    }

    public boolean searchWord(String word) throws NullPointerException {
        if (root == null) throw new NullPointerException("Empty tree");
        Node iterator = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (iterator.getNode(c) == null)
                return false;
            iterator = iterator.getNode(c);
        }
        return iterator.isWord();
    }
}

class Node {

    private Node[] children;
    private boolean isWord;

    public Node(boolean isWord) {
        this.isWord = isWord;
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
    }

    public Node getNode(char c) {
        if (children == null)
            return null;
        if (c == 32)
            return children[26];
        return children[c % 26];
    }

}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}