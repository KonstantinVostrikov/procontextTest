import java.math.BigDecimal;

public class MyCurrency implements Comparable{
    private String name;
    private BigDecimal value;
    private String dateOfExchange;

    public MyCurrency(String name, BigDecimal value, String dateOfExchange) {
        this.name = name;
        this.value = value;
        this.dateOfExchange = dateOfExchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDateOfExchange() {
        return dateOfExchange;
    }

    public void setDateOfExchange(String dateOfExchange) {
        this.dateOfExchange = dateOfExchange;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", value=" + value +
                ", dateOfExchange='" + dateOfExchange + '\'';
    }

    @Override
    public int compareTo(Object o) {
        MyCurrency second = (MyCurrency) o;
        return value.compareTo(second.getValue());
    }


}
