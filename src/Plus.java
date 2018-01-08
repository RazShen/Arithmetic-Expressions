import java.util.Map;

/**
 * This class features a Plus expression, using two expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Plus extends BinaryExpression implements Expression {
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
    public Plus(Expression exp1, Expression exp2) {
        this.expression1 = exp1;
        this.expression2 = exp2;
    }

    /**
     * This constructor gets 2 variables.
     * @param var1 first var.
     * @param var2 second var
     */
    public Plus(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * This constructor gets 2 values.
     * @param val1 first value.
     * @param val2 second value.
     */
    public Plus(double val1, double val2) {
        this.expression1 = new Num(val1);
        this.expression2 = new Num(val2);
    }

    /**
     * This constructor gets a string and a number.
     * @param var string.
     * @param val double value.
     */
    public Plus(String var, double val) {
        this.expression1 = new Var(var);
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets a number and a string.
     * @param val a num.
     * @param var a string.
     */
    public Plus(double val, String var) {
        this.expression1 = new Num(val);
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a number and an expression.
     * @param val number.
     * @param exp2 expression.
     */
    public Plus(double val, Expression exp2) {
        this.expression1 = new Num(val);
        this.expression2 = exp2;
    }

    /**
     * This constructor gets an expression and a number.
     * @param exp1 expression.
     * @param val number.
     */
    public Plus(Expression exp1, double val) {
        this.expression1 = exp1;
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets an expression and a string.
     * @param exp1 expression.
     * @param var string.
     */
    public Plus(Expression exp1, String var) {
        this.expression1 = exp1;
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a string and an expression.
     * @param var string.
     * @param exp2 expression.
     */
    public Plus(String var, Expression exp2) {
        this.expression1 = new Var(var);
        this.expression2 = exp2;
    }

    /**
     * This method evaluates the plus using a dictionary.
     * @param assignment a dictionary.
     * @return a double value of the calculation.
     * @throws Exception if failed- not all of the variables appear in the dictionary.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.expression1.evaluate(assignment) + this.expression2.evaluate(assignment);
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
        return "(" + expression1.toString() + " + " + expression2.toString() + ")";
    }

    /**
     * This method assigns the specific var in this expression with a new expression.
     * @param var the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        return new Plus(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    /**
     * This method differentiate this expression by a specific variable.
     * @param var inputted var we differentiate by.
     * @return the differentiated expression.
     */
    public Expression differentiate(String var) {
        return new Plus(this.expression1.differentiate(var), this.expression2.differentiate(var));
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
            // Simple declaring x (so catch block won't be empty).
            int y = 1;
        }
        // If both evaluated works and returned a number.
        if (leftVal != null && rightVal != null) {
            if (leftVal == 0.0 && rightVal == 0.0) {
                return new Num(0);
            } else if (leftVal == 0 && rightVal != 0) {
                return new Num(rightVal);
            } else if (leftVal != 0 && rightVal == 0) {
                return new Num(leftVal);
            } else {
                return new Num(leftVal + rightVal);
            }
        } else if (leftVal != null) {
            if (leftVal == 0) {
                return this.expression2.simplify();
            }
        } else if (rightVal != null) {
            if (rightVal == 0) {
                return this.expression1.simplify();
            }
        }
        // Bonus
        if (ifEqual()) {
            return new Mult(2, this.expression1.simplify());
            // Find if the plus included two multiplies and return their sum if their variables are equal/
        } else if (this.expression1 instanceof Mult && this.expression2 instanceof Mult) {
            if (((Mult) this.expression1).getExpression1() instanceof Num
                    && ((Mult) this.expression1).getExpression2() instanceof Var) {
                if (((Mult) this.expression2).getExpression1() instanceof Num
                        && ((Mult) this.expression2).getExpression2() instanceof Var) {
                    if (((Mult) this.expression1).getExpression2().toString()
                            .equals(((Mult) this.expression2).getExpression2().toString())) {
                        return new Mult(new Plus(((Mult) this.expression1).getExpression1(),
                                ((Mult) this.expression2).getExpression1()),
                                ((Mult) this.expression1).getExpression2().simplify());
                    }
                } else if (((Mult) this.expression2).getExpression1() instanceof Var
                        && ((Mult) this.expression2).getExpression2() instanceof Num) {
                    if (((Mult) this.expression1).getExpression2().toString()
                            .equals(((Mult) this.expression2).getExpression1().toString())) {
                        return new Mult(new Plus(((Mult) this.expression1).getExpression1(),
                                ((Mult) this.expression2).getExpression2()),
                                ((Mult) this.expression1).getExpression2().simplify());
                    }
                }
            } else if (((Mult) this.expression1).getExpression1() instanceof Var
                    && ((Mult) this.expression1).getExpression2() instanceof Num) {
                if (((Mult) this.expression2).getExpression1() instanceof Num
                        && ((Mult) this.expression2).getExpression2() instanceof Var) {
                    if (((Mult) this.expression1).getExpression1().toString()
                            .equals(((Mult) this.expression2).getExpression2().toString())) {
                        return new Mult(new Plus(((Mult) this.expression1).getExpression2(),
                                ((Mult) this.expression2).getExpression1()),
                                ((Mult) this.expression1).getExpression1().simplify());
                    }
                } else if (((Mult) this.expression2).getExpression1() instanceof Var
                        && ((Mult) this.expression2).getExpression2() instanceof Num) {
                    if (((Mult) this.expression1).getExpression1().toString()
                            .equals(((Mult) this.expression2).getExpression1().toString())) {
                        return new Mult(new Plus(((Mult) this.expression1).getExpression2(),
                                ((Mult) this.expression2).getExpression2()),
                                ((Mult) this.expression1).getExpression1().simplify());
                    }
                }
            }
        }
        return new Plus(this.expression1.simplify(), this.expression2.simplify());
    }

    /**
     * The plus is a commutative expression so we will return a new expression with swapped inner expressions.
     * @return a swapped expression.
     */
    @Override
    public Expression swapExp() {
        return new Plus(this.expression2, this.expression1);
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
