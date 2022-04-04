package andrei.teplykh.converter.converters;

import com.google.gson.Gson;
import andrei.teplykh.expetions.UserDataException;
import andrei.teplykh.util.GsonProvider;
import andrei.teplykh.util.OperandInfo;

import javax.ws.rs.ext.ParamConverter;

public class FiltersConverter implements ParamConverter<OperandInfo[]> {

    @Override
    public OperandInfo[] fromString(final String s) {
        Gson gson = GsonProvider.gson;
        String[] filters = gson.fromJson(s, String[].class);
        OperandInfo[] operands = new OperandInfo[filters.length];

        for (int i = 0; i < filters.length; i++) {
            try {
                operands[i] = OperandInfo.initOperand(filters[i]);
            } catch (UserDataException e) {
                return null;
            }
        }

        return operands;
    }

    @Override
    public String toString(final OperandInfo[] operandInfos) {
        return null;
    }
}
