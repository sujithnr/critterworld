package ast;

public class MutationInsert implements Mutation
{
	public boolean equals(Mutation m)
	{
		// TODO Auto-generated method stub
		return false;
	}	
	
	
	public boolean mutate(ProgramImpl p)
	{
		System.out.println(parentNode(p));
		return false;
	}

	public boolean mutate(Rule r)
	{
		return false;
	}
	
	public boolean mutate(BinaryCondition c)
	{
		// TODO Auto-generated method stub
		return false;
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
		System.out.println(parentNode(ue));
		return false;
	}
	
	public boolean mutate(Sensor s)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public ProgramImpl parentNode(Node child) {
		Node parent = child.getParent();
		if (parent != null) {
		while (parent != null) {
			child = parent;
			parent = parent.getParent();
		}
		}
		return (ProgramImpl) child;
	}
}