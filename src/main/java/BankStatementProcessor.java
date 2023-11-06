import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {

        double total = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total  = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateMinAmount(final LocalDate firstDate, final LocalDate lastDate) {
        double minAmount = Double.MAX_VALUE;

        for (final BankTransaction bankTransaction: bankTransactions) {
            LocalDate transactionDate = bankTransaction.getDate();
            if (transactionDate.isEqual(firstDate) || (transactionDate.isAfter(firstDate) && transactionDate.isBefore(lastDate))){
                if (bankTransaction.getAmount()<minAmount) {
                    minAmount = bankTransaction.getAmount();
                }
            }
        }

        return minAmount;
    }

    public double calculateMaxAmount(final LocalDate firstDate, final LocalDate lastDate) {
        double maxAmount = Double.MIN_VALUE;

        for (final BankTransaction bankTransaction: bankTransactions) {
            LocalDate transactionDate = bankTransaction.getDate();
            if (transactionDate.isEqual(firstDate) || (transactionDate.isAfter(firstDate) && transactionDate.isBefore(lastDate))){
                if (bankTransaction.getAmount() > maxAmount) {
                    maxAmount = bankTransaction.getAmount();
                }
            }
        }

        return maxAmount;
    }
}
