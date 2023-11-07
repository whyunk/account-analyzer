public class JsonExporter implements Exporter {
    @Override
    public String export(SummaryStatistics summaryStatistics) {

        String json = "{";
        json += "\"sum\":" + summaryStatistics.getSum() + ",";
        json += "\"average\":" + summaryStatistics.getAverage() + ",";
        json += "\"max\":" + summaryStatistics.getMax() + ",";
        json += "\"min\":" + summaryStatistics.getMin() + ",";
        json += "}";

        return json;
    }
}
