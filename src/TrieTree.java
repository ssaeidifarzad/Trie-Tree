import java.util.Scanner;

public class TrieTree {

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
            children = new Node[26];
        children[c % 26] = new Node(isWord);
    }

    public Node getNode(char c) {
        if (children == null)
            return null;
        return children[c % 26];
    }

}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}