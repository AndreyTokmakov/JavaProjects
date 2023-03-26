package Facade;

class DataSource { }
class ReportHeader { }
class ReportFooter { }
class ReportData { }

enum ReportType {
    PDF, HTML
}

class Report {
    private ReportHeader header;
    private ReportData data;
    private ReportFooter footer;

    public ReportHeader getHeader() {
        return header;
    }
    public void setHeader(ReportHeader header) {
        System.out.println("Setting report header");
        this.header = header;
    }
    public ReportData getData() {
        return data;
    }
    public void setData(ReportData data) {
        System.out.println("Setting report data");
        this.data = data;
    }
    public ReportFooter getFooter() {
        return footer;
    }
    public void setFooter(ReportFooter footer) {
        System.out.println("Setting report footer");
        this.footer = footer;
    }
}

class ReportWriter {
    public void writeHtmlReport(Report report, String location) {
        System.out.println("HTML Report written");
        // implementation
    }

    public void writePdfReport(Report report, String location) {
        System.out.println("Pdf Report written");
        // implementation
    }
}


class ReportGeneratorFacade
{
    public void generateReport(ReportType type,
                               DataSource dataSource,
                               String location)
    {
        Report report = new Report();

        report.setHeader(new ReportHeader());
        report.setFooter(new ReportFooter());

        // Get data from dataSource and set to ReportData object
        report.setData(new ReportData());

        ReportWriter writer = new ReportWriter();
        switch (type) {
            case HTML -> writer.writeHtmlReport(report, location);
            case PDF -> writer.writePdfReport(report, location);
        }
    }
}



public class ReportGenerator
{
    public static void main(String[] args) throws Exception
    {
        ReportGeneratorFacade generator = new ReportGeneratorFacade();
        generator.generateReport(ReportType.HTML, null, null);
        generator.generateReport(ReportType.PDF, null, null);
    }
}