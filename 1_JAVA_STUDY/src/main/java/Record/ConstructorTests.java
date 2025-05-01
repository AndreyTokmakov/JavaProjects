package Record;

import java.math.BigDecimal;

public class ConstructorTests
{

    public record AssetBalance(
            String assetSymbol,
            BigDecimal assetQuantity,
            BigDecimal usdAmount,
            BigDecimal usdAmountTotal) {

        AssetBalance( String assetSymbol,
                      BigDecimal assetQuantity,
                      BigDecimal usdAmount) {
            this(assetSymbol, assetQuantity, usdAmount, BigDecimal.ZERO);
        }
    }



    public static void main(String[] args)
    {
        AssetBalance assetOne = new AssetBalance("USDT", BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO);

        AssetBalance assetTwo = new AssetBalance("USDT", BigDecimal.ZERO, BigDecimal.ZERO);



    }
}
