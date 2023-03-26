/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Interpreter design pattern demo
*
* @name      : InterpreterDemo.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 17, 2020
* 
****************************************************************************/ 

package Interpreter;

// Expression interface used to check the interpreter. 
interface Expression  {
	boolean interpret(String con);
} 


// TerminalExpression class implementing the above interface. 
// This interpreter just check if the data is same as the interpreter data. 
class TerminalExpression implements Expression  { 
	private final String data;

	public TerminalExpression(String data) { 
		this.data = data; 
	} 

	public boolean interpret(String con)  { 
		return con.contains(data);
	} 
} 


// OrExpression class implementing the above interface. This interpreter just
// returns the or condition of the data is same as the interpreter data. 
class OrExpression implements Expression { 
	Expression expr1; 
	Expression expr2; 

	public OrExpression(Expression expr1, Expression expr2) { 
		this.expr1 = expr1; 
		this.expr2 = expr2; 
	} 
	
	public boolean interpret(String con) {		 
		return expr1.interpret(con) || expr2.interpret(con); 
	} 
} 


// AndExpression class implementing the above interface. This interpreter just
// returns the And condition of the data is same as the interpreter data. 
class AndExpression implements Expression { 
	Expression expr1; 
	Expression expr2; 

	public AndExpression(Expression expr1, Expression expr2) { 
		this.expr1 = expr1; 
		this.expr2 = expr2; 
	} 
	
	public boolean interpret(String con) {		 
		return expr1.interpret(con) && expr2.interpret(con); 
	} 
} 

public class InterpreterDemo {
	public static void main(String[] args) {
	
		Expression person1 = new TerminalExpression("Kushagra"); 
        Expression person2 = new TerminalExpression("Lokesh"); 
        Expression vikram = new TerminalExpression("Vikram"); 
        Expression committed = new TerminalExpression("Committed"); 
        
        Expression isSingle = new OrExpression(person1, person2); 
        Expression isCommitted = new AndExpression(vikram, committed);  
        
        
  
        System.out.println(isSingle.interpret("Kushagra"));  // true
        System.out.println(isSingle.interpret("Lokesh"));    // true
        System.out.println(isSingle.interpret("Achint"));    // false
          
        System.out.println(isCommitted.interpret("Committed, Vikram")); // true
        System.out.println(isCommitted.interpret("Single, Vikram"));    // false
	}
}




