package ast;

/** A representation of a binary Boolean condition: 'and' or 'or' */
public class BinaryCondition extends AbstractNode implements Condition
{


	/**
	 * Create an AST representation of l op r.
	 * 
	 * @param l 
	 * @param op

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
		// TODO Auto-generated method stub
		return null;
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
