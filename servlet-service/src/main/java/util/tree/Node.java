package util.tree;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.OperandInfo;

@Data
@NoArgsConstructor
public class Node {
    private OperandInfo data;
    private Node leftChild;
    private Node rightChild;
    private boolean isOrderBy;

    public Node(OperandInfo data) {
        this.data = data;
    }

    public Node(OperandInfo data, boolean isOrderBy) {
        this.data = data;
        this.isOrderBy = isOrderBy;
    }
}
