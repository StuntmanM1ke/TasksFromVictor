package data;

public class ExchangeRate implements Comparable<ExchangeRate>{
    String bank;
    Double rate;

    public String getBank() {
        return bank;
    }

    public Double getRate() {
        return rate;
    }

    public ExchangeRate(String bank, Double rate) {
        this.bank = bank;
        this.rate = rate;
    }


    @Override
    public int compareTo(ExchangeRate exchangeRate) {
        return Double.compare(this.rate, exchangeRate.rate);
    }
}
