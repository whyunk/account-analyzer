import java.io.IOException;

public class MainApplication {

    public static void main(final String... args) throws IOException {

        final BankTransactionAnalyzer bankTransactionAnalyzer = new BankTransactionAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        final Exporter exporter = new HtmlExporter();

        bankTransactionAnalyzer.analyze(args[0], bankStatementParser, exporter);

    }
}
