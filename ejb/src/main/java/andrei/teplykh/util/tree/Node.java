package andrei.teplykh.util.tree;

import lombok.Data;
import lombok.NoArgsConstructor;
import andrei.teplykh.util.OperandInfo;

@Data
@NoArgsConstructor
public class Node {
    private OperandInfo data;
    private Node leftChild;
    private Node rightChild;
    private boolean isOrderBy;

    public Node(final OperandInfo data) {
        this.data = data;
    }

    public Node(final OperandInfo data, final boolean isOrderBy) {
        this.data = data;
        this.isOrderBy = isOrderBy;
    }
}
