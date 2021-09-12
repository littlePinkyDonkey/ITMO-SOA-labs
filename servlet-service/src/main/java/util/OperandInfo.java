package util;

import com.google.gson.JsonSyntaxException;
import lombok.Data;
import util.enums.FilterOperands;

@Data
public class OperandInfo {
    private FilterOperands filterOperands;
    private String operator;
    private String filterValue;

    public static OperandInfo initOperand(String filter) {
        OperandInfo operandInfo = new OperandInfo();

        int operatorPosition;
        if (filter.contains(">")) {
            operatorPosition = filter.indexOf(">");
        } else if (filter.contains("<")) {
            operatorPosition = filter.indexOf("<");
        } else if (filter.contains("=")) {
            operatorPosition = filter.indexOf("=");
        } else {
            operandInfo.setFilterOperands(FilterOperands.of(filter));
            if (operandInfo.getFilterOperands() == null) {
                throw new JsonSyntaxException("");
            }
            return operandInfo;
        }

        operandInfo.setFilterOperands(FilterOperands.of(filter.substring(0, operatorPosition).trim()));
        operandInfo.setOperator(String.valueOf(filter.charAt(operatorPosition)));
        operandInfo.setFilterValue(filter.substring(operatorPosition + 1).trim());

        if (operandInfo.getFilterOperands() == null) {
            throw new JsonSyntaxException("");
        }

        return operandInfo;
    }
}
