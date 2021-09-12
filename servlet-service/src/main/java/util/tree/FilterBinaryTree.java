package util.tree;

import lombok.Data;
import util.OperandInfo;

import java.util.HashMap;
import java.util.Map;

@Data
public class FilterBinaryTree {
    private Node root = new Node();
//    private List<String> queryParams = new ArrayList<>();
    private Map<String, String> params = new HashMap<>();
    private StringBuilder query = new StringBuilder("FROM DRAGONS d where ");

    public void buildTree(OperandInfo orderOperand, OperandInfo[] filterOperands) {
        if (orderOperand != null) {
            root.setRightChild(new Node(orderOperand, true));
            addNodes(filterOperands, 0);
        } else {
            root.setRightChild(new Node(filterOperands[0]));
            addNodes(filterOperands, 1);
        }
    }

    private void addNodes(OperandInfo[] filterOperands, int index) {
        Node curRoot = root;
        for (int i = index; i < filterOperands.length; i++) {
            if (i != filterOperands.length - 1) {
                Node leftChild = new Node();

                curRoot.setLeftChild(leftChild);
                curRoot = leftChild;

                curRoot.setRightChild(new Node(filterOperands[i]));
            } else {
                curRoot.setLeftChild(new Node(filterOperands[i]));
            }
        }
    }

    public void parseQueryParams(Node root) {
        if (root == null) {
            return;
        }

        if (root.getLeftChild() != null) {
            parseQueryParams(root.getLeftChild());
        }

        if (root.getRightChild() != null) {
            parseQueryParams(root.getRightChild());
        }

        OperandInfo operandInfo = root.getData();

        if (root.getData() != null) {
            if (root.isOrderBy()) {
                query = query.delete(query.length() - 5, query.length());
                query.append(String.format(" order by %s", operandInfo.getFilterOperands().getFiledName()));
//                queryParams.add(String.format("order by %s", operandInfo.getFilterOperands().getFiledName()));
            } else {
                params.put(operandInfo.getFilterOperands().getOperand(), operandInfo.getFilterValue());
                query.append(String.format("%s%s:%s and ", operandInfo.getFilterOperands().getFiledName(), operandInfo.getOperator(), operandInfo.getFilterOperands().getOperand()));
//                queryParams.add(String.format("%s%s%s ", operandInfo.getFilterOperands().getFiledName(), operandInfo.getOperator(), operandInfo.getFilterValue()));
            }
        }
    }
}
