package ast;

import java.util.List;

/** A data structure representing a critter program. */
public class ProgramImpl extends AbstractNode implements Program
{
	/**
	 * rulesList will contain all the rules detailed in the program.
	 */
	private List<Rule> rulesList;

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
	public Program mutate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Program mutate(int index, Mutation m)
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
}