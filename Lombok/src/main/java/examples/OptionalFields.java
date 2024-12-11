package examples;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

public class OptionalFields
{
    @Data
    private final static class OrderResponse
    {
        private Optional<Integer> value = Optional.empty();
        private Optional<BigDecimal> decimalValue= Optional.of(BigDecimal.valueOf(100));
    }


    public static void main(String[] args)
    {
        OrderResponse response = new OrderResponse();
        System.out.println(response.value);
        System.out.println(response.getValue().orElse(123));


        System.out.println(response.decimalValue);
        System.out.println(response.getDecimalValue().orElse(BigDecimal.valueOf(123)));
    }
}
