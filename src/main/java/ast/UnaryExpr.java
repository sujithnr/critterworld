package ast;

public class UnaryExpr extends AbstractNode implements Expr
{
	
	private Expr exp;
	private UnaryExprType type;
	
	public UnaryExpr(Expr e, UnaryExprType t)
	{
		this.exp = e;
		this.type = t;
	}
	
	@Override
	public int evaluateNode()
	{
		/*
		int val = exp.evaluateNode();
		switch(type)
		{
			case CONSTANT:
				return val;
			case MEMORYVAL:
				return mem[val];
			case EXPRESSION:
				return val;
			case NEGATION:
				return -1 * val;
			case SENSORVAL:
				
			default:
				return 0;
		}
		*/
		throw new UnsupportedOperationException();
	}
	
	public enum UnaryExprType
	{
		CONSTANT, MEMORYVAL, EXPRESSION, NEGATION, SENSORVAL
	}
}
