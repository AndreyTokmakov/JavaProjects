import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.Optional;

public class JCommanderParser
{

    @Parameter(names = {"--web-trainer-alias"}, required = true,
               description = "Name of the directory, that corresponds to particular test")
    private String webTrainerAlias;

    @Parameter(names = {"--path-to-student-code"}, required = true)
    private String pathToStudentCode;

    @Parameter(names = {"--password"},
               description = "Connection password", // required = true,
               password = true, echoInput = true)
    private String password;

    @Parameter(names = {"--run-compilation-only"})
    private boolean runCompilationOnly = false;

    @Parameter(names = {"--enable-error-stacktrace"})
    private boolean enableErrorStacktrace = false;

    @Parameter(names = {"--path-to-chrome-driver"})
    private String pathToChromeDriver;

    @Parameter(names = {"--selenoid-url"})
    private String selenoidUrl;



    protected void printfParams() {
        System.out.println("webTrainerAlias      : " + webTrainerAlias);
        System.out.println("pathToStudentCode    : " + pathToStudentCode);
        System.out.println("password             : " + password);
        System.out.println("runCompilationOnly   : " + runCompilationOnly);
        System.out.println("enableErrorStacktrace: " + enableErrorStacktrace);
        System.out.println("selenoidUrl          : " + selenoidUrl);
    }

    public static void main(String[] args)
    {
        JCommanderParser runner = new JCommanderParser();
        JCommander.newBuilder()
                .acceptUnknownOptions(true) // not fail in case of unknown parameters
                .addObject(runner)
                .build()
                .parse(args);


        runner.printfParams();
    }
}
