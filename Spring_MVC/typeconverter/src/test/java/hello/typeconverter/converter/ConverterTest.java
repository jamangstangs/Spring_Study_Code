package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToSpringConverter integerToSpringConverter = new IntegerToSpringConverter();
        String result = integerToSpringConverter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }
}
