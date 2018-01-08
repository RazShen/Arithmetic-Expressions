import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This abstract class includes the common methods for all expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public abstract class BaseExpression {

    /**
     * Evaluate the expression- only works if there isn't any variable in the expression.
     * @return evaluation of the expression.
     * @throws Exception if failed.
     */
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        // Check if there are no variables in the expression.
        if (this.getVariables().isEmpty()) {
            return this.evaluate(assignment);
        } else {
            throw new Exception("Error, must assign values to all Var types");
        }
    }

    /**
     * This method is used for commutative expressions, by default returns null.
     * @return null.
     */
    public Expression swapExp() {
        return null;
    }

    /**
     * This method is simplifying the expression until it can't be further simplified.
     * @return the simplified expression.
     */
    public Expression simplify() {
        Expression start = getExp();
        Expression simplified = getExp().specificSimplify();
        // Until the result of simplification stays the same.
        while (!start.toString().equals(simplified.toString())) {
            start = simplified;
            simplified = simplified.specificSimplify();
        }
        return simplified;
    }

    /**
     * This abstract method used to get the expression.
     * @return this expression.
     */
    public abstract Expression getExp();

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment inputted dictionary.
     * @return evaluation of the expression according to the dictionary.
     * @throws Exception if failed.
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * This method returns the variables in an expression (without duplications).
     * @return the variables in an expression (without duplications).
     */
    public abstract List<String> getVariables();
}
