import domain.BankTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.BankStatementCSVParser;
import parser.BankStatementParser;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class BankStatementCSVParserTest {

    private final BankStatementParser bankStatementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {

        final String line = "2023-05-01,-500,gum";
        final BankTransaction result = bankStatementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2023, Month.MAY,1), -500, "gum");
        final double tolerance = 0;

        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());

    }

    @Test
    public void shouldParseMultipleTransaction() throws Exception {

        final List<String> lines = List.of(
                "2023-05-01,-500,gum",
                "2023-06-01,5000,salary"
        );
        final List<BankTransaction> result = bankStatementParser.parseLinesFrom(lines);

        final BankTransaction expected1 =
                new BankTransaction(LocalDate.of(2023, Month.MAY,1), -500, "gum");
        final BankTransaction expected2 =
                new BankTransaction(LocalDate.of(2023, Month.JUNE,1), 5000, "salary");
        final double tolerance = 0;

        //index 0  0f List<domain.BankTransaction> that after parseLinesFrom()
        Assertions.assertEquals(expected1.getDate(), result.get(0).getDate());
        Assertions.assertEquals(expected1.getAmount(), result.get(0).getAmount(), tolerance);
        Assertions.assertEquals(expected1.getDescription(), result.get(0).getDescription());

        //index 1  0f List<domain.BankTransaction> that after parseLinesFrom()
        Assertions.assertEquals(expected2.getDate(), result.get(1).getDate());
        Assertions.assertEquals(expected2.getAmount(), result.get(1).getAmount(), tolerance);
        Assertions.assertEquals(expected2.getDescription(), result.get(1).getDescription());


    }

}