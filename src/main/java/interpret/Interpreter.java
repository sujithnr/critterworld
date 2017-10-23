package interpret;

import ast.*;
/**
 * An example interface for interpreting a critter program. This is just a starting
 * point and may be changed as much as you like.
 */
public interface Interpreter
{
    /**
     * Execute program {@code p} until either the maximum number of rules per turn is reached or some rule
     * whose command contains an action is executed.
     * @param p
     * @return a result containing the action to be performed;
     * the action may be null if the maximum number of rules
     * per turn was exceeded.
     */
    Outcome interpret(Program p);

    /**
     * Evaluate the given binary condition.
     * @param c
     * @return a boolean that results from evaluating c.
     */
    boolean eval(BinaryCondition c);
    
    /**
     * Evaluate the given relation.
     * @param c
     * @return a boolean that results from evaluating c.
     */
    boolean eval(Relation c);

    /**
     * Evaluate the given binary expression.
     * @param e
     * @return an integer that results from evaluating e.
     */
    int eval(BinaryExpr e);
    
    /**
     * Evaluate the given unary expression.
     * @param e
     * @return an integer that results from evaluating e.
     */
    int eval(UnaryExpr e);
    
    /**
     * Evaluate the given sensor.
     * @param e
     * @return an integer that results from evaluating e.
     */
    int eval(Sensor s);
}
