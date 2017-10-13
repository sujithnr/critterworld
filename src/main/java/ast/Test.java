package ast;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;

import ast.BinaryExpr;
import ast.BinaryExpr.MathOp;
import ast.Relation.RelOp;
import ast.UnaryExpr.ExprType;
import parse.Parser;
import parse.ParserFactory;
import parse.Tokenizer;
import parsertests.ParserTest;

public class Test
{
	public static void main(String[] args)
	{
		/*UnaryExpr e1 = new UnaryExpr(2);
		UnaryExpr e2 = new UnaryExpr(12);
		BinaryExpr e3 = new BinaryExpr(e1, MathOp.MULTIPLY, e2);
		UnaryExpr e4 = new UnaryExpr(e3, ExprType.NEGATION);
		BinaryExpr e5 = new BinaryExpr(new UnaryExpr(6), MathOp.ADD, new UnaryExpr(67));
		Relation r = new Relation(e4, RelOp.EQUAL, e5);
		
		System.out.println(r.toString());
		System.out.println(r.size());
		
		UnaryExpr e = new UnaryExpr(new UnaryExpr(7), ExprType.MEMORYVAL);
		Relation r1 = new Relation(e, RelOp.NOTEQUAL, new UnaryExpr(17));
		Update u = new Update(new UnaryExpr(7), new UnaryExpr(17));
		LinkedList<Update> ll = new LinkedList<Update>();
		Command c = new Command(ll, u);
		Rule rule = new Rule(r1, c);
		
		System.out.println(rule.toString() + rule.size() + "left: " + r1.size() + "right: " + c.size() + rule.nodeAt(3));
		
		Sensor s = new Sensor();
		System.out.println(s.nodeAt(0).toString());*/
		
		InputStream in = ParserTest.class.getResourceAsStream("example-rules.txt");
        Reader r = new BufferedReader(new InputStreamReader(in));
        Tokenizer t = new Tokenizer(r);
        Parser p = ParserFactory.getParser();
        Program prog = p.parse(r);
        System.out.println(prog.toString());
	}
}