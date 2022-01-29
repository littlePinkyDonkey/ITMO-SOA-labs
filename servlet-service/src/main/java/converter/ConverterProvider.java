package converter;

import converter.converters.FiltersConverter;
import converter.converters.SortingConverter;
import util.OperandInfo;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if (aClass.equals(OperandInfo[].class)) {
            return (ParamConverter<T>) new FiltersConverter();
        }
        if (aClass.equals(OperandInfo.class)) {
            return (ParamConverter<T>) new SortingConverter();
        }
        return null;
    }
}
