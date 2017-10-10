package ast;

/** A representation of a binary Boolean condition: 'and' or 'or' */
public class BinaryCondition extends AbstractNode implements Condition
{

<<<<<<< HEAD

	/**
	 * Create an AST representation of l op r.
	 * 
	 * @param l 
	 * @param op

=======
>>>>>>> bcbac9a71a41a75cc4a622474fdf2d9fa785a8ad
	/** The left child of this node. */
	private Condition left;
	/** The operation to be performed on the two children. */
	private Operator op;
	/** The right child of this node. */
	private Condition right;
	
	/**
	 * Creates an AST representation of l op r.
	 * @param l
	 * @param o
<<<<<<< HEAD

=======
>>>>>>> bcbac9a71a41a75cc4a622474fdf2d9fa785a8ad
	 * @param r
	 */
	
	public BinaryCondition(Condition l, Operator op, Condition r)
	{
		// TODO
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node nodeAt(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder prettyPrint(StringBuilder sb)
	{
		sb.append(left.toString());
		sb.append(op == Operator.OR ? "or" : "and");
		sb.append(right.toString());
		return sb;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/** An enumeration of all possible binary condition operators. */
	public enum Operator
	{
		OR, AND;
	}
}
