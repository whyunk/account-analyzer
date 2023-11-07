import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCES = "src/main/resources/";
    public void analyze(final String fileName, final BankStatementParser bankStatementParser,final Exporter exporter) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        System.out.println(exporter.export(summaryStatistics));
        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {

        System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total for transactions in January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for transactions in February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("salary"));
        System.out.println("From 2023.01.01 to 2023.02.28, the minimum amount is"
                + bankStatementProcessor.calculateMinAmount(LocalDate.of(2023,01,01),
                LocalDate.of(2023,02,28)));
        System.out.println("From 2023.01.01 to 2023.02.28, the maximum amount is"
                + bankStatementProcessor.calculateMaxAmount(LocalDate.of(2023,01,01),
                LocalDate.of(2023,02,28)));
    }
}
