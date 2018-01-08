import java.util.Map;

/**
 * This class features a Pow expression, using two expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Pow  extends BinaryExpression implements Expression {
    /**
     * The first expression.
     */
    private Expression expression1;
    /**
     * The second expression.
     */
    private Expression expression2;
    /**
     * isCommutative is used for the swap of expressions.
     */
    private boolean isCommutative = false;

    /**
     * This constructor gets 2 expressions.
     * @param exp1 expression 1.
     * @param exp2 expression 2.
     */
    public Pow(Expression exp1, Expression exp2) {
        this.expression1 = exp1;
        this.expression2 = exp2;
    }

    /**
     * This constructor gets 2 variables.
     * @param var1 first var.
     * @param var2 second var
     */
    public Pow(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * This constructor gets 2 values.
     * @param val1 first value.
     * @param val2 second value.
     */
    public Pow(double val1, double val2) {
        this.expression1 = new Num(val1);
        this.expression2 = new Num(val2);
    }

    /**
     * This constructor gets a string and a number.
     * @param var string.
     * @param val double value.
     */
    public Pow(String var, double val) {
        this.expression1 = new Var(var);
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets a number and a string.
     * @param val a num.
     * @param var a string.
     */
    public Pow(double val, String var) {
        this.expression1 = new Num(val);
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a number and an expression.
     * @param val number.
     * @param exp2 expression.
     */
    public Pow(double val, Expression exp2) {
        this.expression1 = new Num(val);
        this.expression2 = exp2;
    }

    /**
     * This constructor gets an expression and a number.
     * @param exp1 expression.
     * @param val number.
     */
    public Pow(Expression exp1, double val) {
        this.expression1 = exp1;
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets an expression and a string.
     * @param exp1 expression.
     * @param var string.
     */
    public Pow(Expression exp1, String var) {
        this.expression1 = exp1;
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a string and an expression.
     * @param var string.
     * @param exp2 expression.
     */
    public Pow(String var, Expression exp2) {
        this.expression1 = new Var(var);
        this.expression2 = exp2;
    }

    /**
     * This method evaluates the power using a dictionary.
     * @param assignment a dictionary.
     * @return a double value of the calculation.
     * @throws Exception if failed- not all of the variables appear in the dictionary.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(this.expression1.evaluate(assignment), this.expression2.evaluate(assignment));
    }

    /**
     * This method returns the first expression.
     * @return first expression.
     */
    @Override
    public Expression getExpression1() {
        return this.expression1;
    }

    /**
     * This method returns the second expression.
     * @return the second expression.
     */
    @Override
    public Expression getExpression2() {
        return this.expression2;
    }

    /**
     * Returns a string of the expression.
     * @return a string of this expression.
     */
    public String toString() {
        return "(" + expression1.toString() + "^" + expression2.toString() + ")";
    }

    /**
     * This method assigns the specific var in this expression with a new expression.
     * @param var the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        return new Pow(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    /**
     * This method differentiate this expression by a specific variable.
     * @param var inputted var we differentiate by.
     * @return the differentiated expression.
     */
    public Expression differentiate(String var) {
        Double tempRight = null;
        try {
            tempRight = this.expression2.evaluate();
        } catch (Exception ex) {
            // Simple declaring z (so catch block won't be empty).
            int z = 1;
        }
        // If the right value is null - there is a variable in the power.
        if (tempRight == null) {
            return new Mult(new Pow(this.expression1, this.expression2), new Plus(new Mult(
                    this.expression1.differentiate(var), new Div(this.expression2, this.expression1)), new Mult(
                    this.expression2.differentiate(var), new Log("e", this.expression1))));
        } else {
            return new Mult(new Mult(tempRight, this.expression1.differentiate(var)),
                    new Pow(this.expression1, new Num(tempRight - 1)));
        }
    }

    /**
     * This method simplifies the expression.
     * @return this expression simplified.
     */
    @Override
    public Expression specificSimplify() {
        Double tempLeft = null;
        Double tempRight = null;
        try {
            tempLeft = this.expression1.evaluate();
        } catch (Exception ex) {
            // Simple declaring x (so catch block won't be empty).
            int x = 1;
        }
        try {
            tempRight = this.expression2.evaluate();
        } catch (Exception ex) {
            // Simple declaring y (so catch block won't be empty).
            int y = 1;
        }
        // If both values aren't null- return new num with the calculation.
        if (tempLeft != null && tempRight != null) {
            return new Num(Math.pow(tempLeft, tempRight));
        }
        // Bonus
        if (this.expression1 instanceof Pow) {
            return new Pow(((Pow) this.expression1).expression1.simplify(),
                    new Mult(((Pow) this.expression1).expression2, this.expression2).simplify());
        }
        return new Pow(this.expression1.simplify(), this.expression2.simplify());
    }

    /**
     * This method returns this expression.
     * @return this expression.
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
