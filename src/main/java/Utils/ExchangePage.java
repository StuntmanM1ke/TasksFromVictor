package Utils;

import data.Currency;
import data.OperationType;

public interface ExchangePage extends Page {
     double getCourseDouble(OperationType operationType, Currency currency);
}
