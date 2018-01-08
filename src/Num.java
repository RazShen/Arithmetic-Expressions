import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * This class features a number, using type Double.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Num  extends BaseExpression implements Expression {
    /**
    *A number holds a Double value.
     */
    private Double value;
    /**
     * isCommutative is used for the swap of expressions, num only holds 1 expression so it's false.
     */
    private boolean isCommutative = false;

    /**
     * The constructor of Num gets a value of type double.
     * @param inputValue user inputted value.
     */
    public Num(double inputValue) {
        this.value = inputValue;
    }

    /**
     * This method evaluates the num.
     * @param assignment a dictionary.
     * @return the num value.
     * @throws Exception if failed (irrelevant here).
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.value;
    }

    /**
     * This method evaluates the num.
     * @return a double value.
     * @throws Exception if failed (irrelevant here).
     */
    public double evaluate() throws Exception {
        return this.value;
    }

    /**
     * This method retuns the variables the num hold (an empty list).
     * @return new empty list.
     */
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    /**
     * This method converts the num value to a string.
     * @return string which holds the num value.
     */
    public String toString() {
        return String.valueOf(this.value);
    }

    /**
     * This method assign an expression to a var in the expression.
     * @param var the var in this expression.
     * @param expression user inputted expression.
     * @return new expression which replace the var with the new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Num(this.value);
    }

    /**
     * This method differentiate a num.
     * @param var the variable which we differentiate by.
     * @return 0 (differencing a number).
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Simplify of a number is the number itself.
     * @return this number.
     */
    @Override
    public Expression specificSimplify() {
            return this;
    }

    /**
     * Getter of the number.
     * @return this number.
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
