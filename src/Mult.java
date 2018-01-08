import java.util.Map;

/**
 * This class features a Multiply expression, using two expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Mult extends BinaryExpression implements Expression {
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
    private boolean isCommutative = true;

    /**
     * This constructor gets 2 expressions.
     * @param exp1 expression 1.
     * @param exp2 expression 2.
     */
    public Mult(Expression exp1, Expression exp2) {
        this.expression1 = exp1;
        this.expression2 = exp2;
    }

    /**
     * This constructor gets 2 variables.
     * @param var1 first var.
     * @param var2 second var
     */
    public Mult(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * This constructor gets 2 values.
     * @param val1 first value.
     * @param val2 second value.
     */
    public Mult(double val1, double val2) {
        this.expression1 = new Num(val1);
        this.expression2 = new Num(val2);
    }

    /**
     * This constructor gets a string and a number.
     * @param var string.
     * @param val double value.
     */
    public Mult(String var, double val) {
        this.expression1 = new Var(var);
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets a number and a string.
     * @param val a num.
     * @param var a string.
     */
    public Mult(double val, String var) {
        this.expression1 = new Num(val);
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a number and an expression.
     * @param val number.
     * @param exp2 expression.
     */
    public Mult(double val, Expression exp2) {
        this.expression1 = new Num(val);
        this.expression2 = exp2;
    }

    /**
     * This constructor gets an expression and a number.
     * @param exp1 expression.
     * @param val number.
     */
    public Mult(Expression exp1, double val) {
        this.expression1 = exp1;
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets an expression and a string.
     * @param exp1 expression.
     * @param var string.
     */
    public Mult(Expression exp1, String var) {
        this.expression1 = exp1;
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a string and an expression.
     * @param var string.
     * @param exp2 expression.
     */
    public Mult(String var, Expression exp2) {
        this.expression1 = new Var(var);
        this.expression2 = exp2;
    }

    /**
     * This method evaluates the multiply using a dictionary.
     * @param assignment a dictionary.
     * @return a double value of the calculation.
     * @throws Exception if failed- not all of the variables appear in the dictionary.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.expression1.evaluate(assignment) * this.expression2.evaluate(assignment);
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
        return "(" + expression1.toString() + " * " + expression2.toString() + ")";
    }

    /**
     * This method assigns the specific var in this expression with a new expression.
     * @param var the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        return new Mult(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    /**
     * This method differentiate this expression by a specific variable.
     * @param var inputted var we differentiate by.
     * @return the differentiated expression.
     */
    public Expression differentiate(String var) {
        return new Plus(new Mult(this.expression1.differentiate(var), this.expression2),
                new Mult(this.expression1, this.expression2.differentiate(var)));
    }

    /**
     * This method simplifies the expression.
     * @return this expression simplified.
     */
    @Override
    public Expression specificSimplify() {
        Double leftVal = null;
        Double rightVal = null;
        try {
            leftVal = this.expression1.evaluate();
        } catch (Exception ex) {
            // Simple declaring x (so catch block won't be empty).
            int x = 1;
        }
        try {
            rightVal = this.expression2.evaluate();
        } catch (Exception ex) {
            // Simple declaring y (so catch block won't be empty).
            int y = 1;
        }
        // If both evaluated works and return a num with the value.
        if (leftVal != null && rightVal != null) {
            if (leftVal == 0.0 || rightVal == 0.0) {
                return new Num(0);
            } else if (leftVal == 1.0 && rightVal != 1.0) {
                return new Num(rightVal);
            } else if (rightVal == 1.0 && leftVal != 1.0) {
                return new Num(leftVal);
            } else if (rightVal == 1.0 && leftVal == 1.0) {
                return new Num(1);
            } else {
                return new Num(leftVal * rightVal);
            }
        } else if (leftVal != null) {
            if (leftVal == 0) {
                return new Num(0);
            } else if (leftVal == 1) {
                return this.expression2.simplify();
            }
        } else if (rightVal != null) {
            if (rightVal == 0) {
                return new Num(0);
            } else if (rightVal == 1) {
                return this.expression1.simplify();
            }
        }
        //bonus
        // If both expressions are equal- return new power
        if (ifEqual()) {
                return new Pow(this.expression1.simplify(), 2);
        }
        return new Mult(this.expression1.simplify(), this.expression2.simplify());
    }

    /**
     * The plus is a commutative expression so we will return a new expression with swapped inner expressions.
     * @return the swapped expression.
     */
    @Override
    public Expression swapExp() {
        return new Mult(this.expression2, this.expression1);
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
