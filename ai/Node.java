package ai;

public class Node {
    public int col, row;
    public boolean open, checked, solid;
    public Node parent;
    public int gCost, hCost, fCost;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}