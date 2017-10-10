package ast;

import java.util.LinkedList;

/** A representation of a critter command. */
public class Command extends AbstractNode
{
	/** A LinkedList of all the possible updates stored in this Command node. */
	private LinkedList<Update> UpdateList;
	
	/** Points to the last node stored in this Command node, which can be either
	 *  an update or an action. */
	private CommandComponent last;
	
	public Command(LinkedList<Update> list, CommandComponent cc)
	{
		UpdateList = list;
		last = cc;
	}
	@Override
	public StringBuilder prettyPrint(StringBuilder sb)
	{
		for(Update u : UpdateList)
			sb.append(u.toString() + "\n");
		sb.append(last.toString());
		return sb;
	}
}