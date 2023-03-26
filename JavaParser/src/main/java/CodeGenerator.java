import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;

public class CodeGenerator
{
    public void CreateTestClass()
    {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackageDeclaration("ru.habrahabr.hub.java.examples.javaparser");

        ClassOrInterfaceDeclaration habraPost = cu.addClass("HabraPost");
        habraPost.addField("String", "title");
        habraPost.addField("String", "text");

        habraPost.addConstructor()
                .addParameter("String", "title")
                .addParameter("String", "text")
                .setBody(new BlockStmt()
                        .addStatement(new ExpressionStmt(new AssignExpr(
                                new FieldAccessExpr(new ThisExpr(), "title"),
                                new NameExpr("title"),
                                AssignExpr.Operator.ASSIGN)))
                        .addStatement(new ExpressionStmt(new AssignExpr(
                                new FieldAccessExpr(new ThisExpr(), "text"),
                                new NameExpr("text"),
                                AssignExpr.Operator.ASSIGN))));

        habraPost.addMethod("getTitle").setBody(new BlockStmt().
                addStatement(new ReturnStmt(new NameExpr("title"))));

        habraPost.addMethod("getText").setBody(new BlockStmt().addStatement(
                new ReturnStmt(new NameExpr("text"))));

        System.out.println(cu.toString());
    }
}
