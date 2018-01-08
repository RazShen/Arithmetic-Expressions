import java.util.Map;
import java.util.List;

/**
 * This interface declares the needed method for an expression.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public interface  Expression extends Simplifying {

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment inputted dictionary.
     * @return evaluation of the expression according to the dictionary.
     * @throws Exception if failed.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,but uses an empty assignment.
     * @return evaluation of the expression.
     * @throws Exception if failed (there is a variable).
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a string representation of the expression.
     * @return a string representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with the provided expression
     * (Does not modify the current expression).
     * @param var inputted var.
     * @param expression the expression which will replace the var.
     * @return the new expression after assigning the value with the inputted expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     * @param var inputted var we differentiate by.
     * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}
