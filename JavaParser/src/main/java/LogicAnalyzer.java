import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Log;
import com.github.javaparser.utils.SourceRoot;

final class LogicAnalyzerTests
{
    private static final String RESOURCES_FOLDER = "src/main/resources";

    private final CompilationUnit readClass(String filename) {
        return readClass(filename, "");
    }

    private final CompilationUnit readClass(String filename, String startPackage)
    {
        // JavaParser has a minimal logging class that normally logs nothing.
        // Let's ask it to write to standard out:
        Log.setAdapter(new Log.StandardOutStandardErrorAdapter());

        // SourceRoot is a tool that read and writes Java files from packages on a certain root directory.
        // In this case the root directory is found by taking the root from the current Maven module,
        // with src/main/resources appended.
        SourceRoot sourceRoot = new SourceRoot(
                CodeGenerationUtils.mavenModuleRoot(LogicAnalyzer.class).resolve(RESOURCES_FOLDER));

        // Our sample is in the root of this directory, so no package name.
        return sourceRoot.parse(startPackage, filename);
    }

    public void Analyze_IF_ELSE() {
        final CompilationUnit cu = readClass("IF_Else_TestClass.java");
        Log.info("Ok!");

        cu.accept(new ModifierVisitor<Void>() {
            /**
             * For every if-statement, see if it has a comparison using "!=".
             * Change it to "==" and switch the "then" and "else" statements around.
             */
            @Override
            public Visitable visit(IfStmt n, Void arg) {

                System.out.println("If-else statement");
                return super.visit(n, arg);
            }
        }, null);
    }


}

public final class LogicAnalyzer
{
    public static void main(String[] args)
    {
        LogicAnalyzerTests tests = new LogicAnalyzerTests();
        tests.Analyze_IF_ELSE();
    }
}