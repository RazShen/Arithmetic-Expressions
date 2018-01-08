import java.util.Map;

/**
 * This class features a cosinus, using type expression.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     *A cosinus holds an expression.
     */
    private Expression expression1;
    /**
     * isCommutative is used for the swap of expressions, cos only holds 1 expression so it's false.
     */
    private boolean isCommutative = false;

    /**
     * This constructor of cos gets an expression.
     * @param exp an expression.
     */
    public Cos(Expression exp) {
        this.expression1 = exp;
    }

    /**
     * This constructor of cos gets an variable.
     * @param var an inputted string.
     */
    public Cos(String var) {
        this.expression1 = new Var(var);
    }

    /**
     * This constructor of cos gets an string.
     * @param val an inputted number.
     */
    public Cos(double val) {
        this.expression1 = new Num(val);
    }

    /**
     * This method evaluates the cos using a dictionary.
     * @param assignment a dictionary.
     * @return a double value of the calculation.
     * @throws Exception if failed- not all of the variables appear in the dictionary.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(this.expression1.evaluate(assignment)));
    }

    /**
     * Getter of the expression.
     * @return the expression cosinus holding.
     */
    @Override
    public Expression getExpression1() {
        return this.expression1;
    }

    /**
     * Returns a string of the expression.
     * @return a string of this expression.
     */
    public String toString() {
        return "cos" + "(" + expression1.toString() + ")";
    }

    /**
     * This method assigns the specific var in this expression with a new expression.
     * @param var the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        return new Cos(this.expression1.assign(var, expression));
    }

    /**
     * This method differentiate this expression by a specific variable.
     * @param var inputted var we differentiate by.
     * @return the differentiated expression.
     */
    public Expression differentiate(String var) {
        return new Neg(new Mult(this.expression1.differentiate(var), new Sin((this.expression1))));
    }

    /**
     * This method simplifies the expression.
     * @return this expression simplified.
     */
    @Override
    public Expression specificSimplify() {
        Double temp = null;
        try {
            temp = this.expression1.evaluate();
        } catch (Exception ex) {
            // Simple declaring x (so catch block won't be empty).
            int x = 1;
        }
        // If temp has a value of number- return new Num with the calculation value.
        if (temp != null) {
            return new Num(Math.cos(Math.toRadians(temp)));
        }
        return new Cos(this.expression1.simplify());
    }

     /**
     * Getter of isCommutative.
     * @return true/false if isCommutative.
     */
    public boolean getIsCommutative() {
        return this.isCommutative;
    }

    /**
     * This method returns this expression.
     * @return this expression.
     */
    @Override
    public Expression getExp() {
        return this;
    }
}
