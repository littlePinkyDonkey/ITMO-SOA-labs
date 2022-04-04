package andrei.teplykh.util;

import com.google.gson.JsonSyntaxException;
import andrei.teplykh.expetions.UserDataException;
import lombok.Data;
import andrei.teplykh.util.enums.FilterOperands;

import java.io.Serializable;

@Data
public class OperandInfo implements Serializable {
    private FilterOperands filterOperands;
    private String operator;
    private String filterValue;

    public static OperandInfo initOperand(final String filter) throws UserDataException {
        final OperandInfo operandInfo = new OperandInfo();

        final int operatorPosition;
        if (filter.contains(">")) {
            operatorPosition = filter.indexOf(">");
        } else if (filter.contains("<")) {
            operatorPosition = filter.indexOf("<");
        } else if (filter.contains("=")) {
            operatorPosition = filter.indexOf("=");
        } else {
            operandInfo.setFilterOperands(FilterOperands.of(filter));
            if (operandInfo.getFilterOperands() == null) {
                throw new UserDataException("Incorrect ordering parameter!");
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
