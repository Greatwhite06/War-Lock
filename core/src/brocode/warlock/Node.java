package brocode.warlock;

public class Node {
    public Node left;
    public Node right;
    int val;

    public Node(int val)
    {
        this.val = val;
        left = right = null;
    }
}
