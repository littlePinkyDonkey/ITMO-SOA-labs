package andrei.teplykh.converter.converters;

import andrei.teplykh.expetions.UserDataException;
import andrei.teplykh.util.OperandInfo;

import javax.ws.rs.ext.ParamConverter;

public class SortingConverter implements ParamConverter<OperandInfo> {
    @Override
    public OperandInfo fromString(final String s) {
        try {
            return OperandInfo.initOperand(s);
        } catch (UserDataException e) {
            return null;
        }
    }

    @Override
    public String toString(final OperandInfo operandInfo) {
        return null;
    }
}
