package ast;

public class MutationRemove implements Mutation
{
	public boolean equals(Mutation m)
	{
		// TODO Auto-generated method stub
		return false;
	}	
	
	public boolean mutate(ProgramImpl p)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mutate(Rule r)
	{
		return false;
	}
	
	public boolean mutate(BinaryCondition c)
	{
		Node parent = c.getParent();
		
	}
	
	public boolean mutate(Command comm)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean mutate(Update u)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mutate(Action a)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean mutate(Relation r)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mutate(BinaryExpr be)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean mutate(UnaryExpr ue)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean mutate(Sensor s)
	{
		// TODO Auto-generated method stub
		return false;
	}
}