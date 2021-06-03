import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class CurrencyEvaluator {
    public static MyCurrency getMaxCurrency(Map<String, List<MyCurrency>> map) {
        MyCurrency max = new MyCurrency("Test", new BigDecimal("-100.00"), "01/01/2000");

        for (Map.Entry<String, List<MyCurrency>> entry : map.entrySet()) {
            List<MyCurrency> list = entry.getValue();

            for (MyCurrency currency : list) {
                if (max.compareTo(currency) < 0) {
                    max = currency;
                }
            }
        }

        return max;
    }

    public static MyCurrency getMinCurrency(Map<String, List<MyCurrency>> map) {
        MyCurrency min = new MyCurrency("Test", new BigDecimal("1000000.00"), "01/01/2000");

        for (Map.Entry<String, List<MyCurrency>> entry : map.entrySet()) {
            List<MyCurrency> list = entry.getValue();

            for (MyCurrency currency : list) {
                if (min.compareTo(currency) > 0) {
                    min = currency;
                }
            }
        }

        return min;
    }

    public static Set<MyCurrency> getAverageCurrency(Map<String, List<MyCurrency>> map) {
        TreeSet<MyCurrency> averageSet = new TreeSet(new Comparator<MyCurrency>() {

            @Override
            public int compare(MyCurrency o1, MyCurrency o2) {
                return o1.getName().compareTo(o2.getName());
            }
        } );

        for (Map.Entry<String, List<MyCurrency>> entry : map.entrySet()) {
            List<MyCurrency> list = entry.getValue();
            String currName = list.get(0).getName();
            BigDecimal avg = new BigDecimal(BigInteger.ZERO);

            for (MyCurrency currency : list) {
                avg = avg.add(currency.getValue());
            }

            avg = avg.divide(new BigDecimal(list.size()), 4, RoundingMode.HALF_UP);

            averageSet.add(new MyCurrency(currName, avg, "01/01/2000"));
        }


        return averageSet;
    }
}
