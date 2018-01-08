import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class features a variable, using type String.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Var extends BaseExpression implements Expression {
    /**
     *A variables holds a String.
     */
    private String string;
    /**
     * isCommutative is used for the swap of expressions, var only holds 1 expression so it's false.
     */
    private boolean isCommutative = false;

    /**
     * The constructor of Var gets a value of type String.
     * @param inputString user inputted value.
     */
    public Var(String inputString) {
        this.string = inputString;
    }

    /**
     * This method evaluates a var using a dictionary, if the dictionary doesn't contain the var - throw exception.
     * @param assignment a dictionary.
     * @return the var value in the dictionary.
     * @throws Exception if failed.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.toString())) {
            return (assignment.get(this.toString()));
        } else {
            throw new Exception("Must assign a value to Val type");
        }
    }

    /**
     * When trying to evaluate a var without a dictionary- throw exception.
     * @return a double (irrelevant here).
     * @throws Exception trying to evaluate a variable.
     */
    public double evaluate() throws Exception {
        throw new Exception("Must assign a value to Val type");
    }

    /**
     * Get the variable's string (putted in an ArrayList).
     * @return a list that holds the string of the var.
     */
    public List<String> getVariables() {
        List<String> var = new ArrayList<String>();
        var.add(this.string);
        return var;
    }

    /**
     * Returns a new string which hold the variable's string.
     * @return a new string which hold the variable's string.
     */
    public String toString() {
        return this.string;
    }

    /**
     * This method assigns the var with a new expression.
     * @param var the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        if (this.string.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * This method differentiate a var. if it's according this var strings return 1, else 0;
     * @param var the variable which we differentiate by.
     * @return 0 (differencing a number) or 1 if it's according to this var's string.
     */
    public Expression differentiate(String var) {
        if (this.string.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * This method simplifies a variable.
     * @return this variable.
     */
    @Override
    public Expression specificSimplify() {
        return this;
    }

    /**
     * Getter of the var.
     * @return this var.
     */
    @Override
    public Expression getExp() {
        return this;
    }

    /**
     * Getter of isCommutative.
     * @return true/false if isCommutative.
     */
    public boolean getIsCommutative() {
        return this.isCommutative;
    }
}
