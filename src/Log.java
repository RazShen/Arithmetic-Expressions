import java.util.List;
import java.util.Map;

/**
 * This class features a log expression, using two expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Log extends BinaryExpression implements Expression {
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
    public Log(Expression exp1, Expression exp2) {
        this.expression1 = exp1;
        this.expression2 = exp2;
    }

    /**
     * This constructor gets 2 variables.
     * @param var1 first var.
     * @param var2 second var
     */
    public Log(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * This constructor gets 2 values.
     * @param val1 first value.
     * @param val2 second value.
     */
    public Log(double val1, double val2) {
        this.expression1 = new Num(val1);
        this.expression2 = new Num(val2);
    }

    /**
     * This constructor gets a string and a number.
     * @param var string.
     * @param val double value.
     */
    public Log(String var, double val) {
        this.expression1 = new Var(var);
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets a number and a string.
     * @param val a num.
     * @param var a string.
     */
    public Log(double val, String var) {
        this.expression1 = new Num(val);
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a number and an expression.
     * @param val number.
     * @param exp2 expression.
     */
    public Log(double val, Expression exp2) {
        this.expression1 = new Num(val);
        this.expression2 = exp2;
    }

    /**
     * This constructor gets an expression and a number.
     * @param exp1 expression.
     * @param val number.
     */
    public Log(Expression exp1, double val) {
        this.expression1 = exp1;
        this.expression2 = new Num(val);
    }

    /**
     * This constructor gets an expression and a string.
     * @param exp1 expression.
     * @param var string.
     */
    public Log(Expression exp1, String var) {
        this.expression1 = exp1;
        this.expression2 = new Var(var);
    }

    /**
     * This constructor gets a string and an expression.
     * @param var string.
     * @param exp2 expression.
     */
    public Log(String var, Expression exp2) {
        this.expression1 = new Var(var);
        this.expression2 = exp2;
    }

    /**
     * This method evaluates the log using a dictionary.
     * @param assignment a dictionary.
     * @return a double value of the calculation.
     * @throws Exception if failed- not all of the variables appear in the dictionary.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (this.getExpression1().evaluate(assignment) == 1 || this.getExpression1().evaluate(assignment) <= 0
                || this.getExpression2().evaluate(assignment) <= 0) {
            throw new Exception("Wrong values for the log function");
        } else {
            return Math.log(this.expression2.evaluate(assignment)) / Math.log(this.expression1.evaluate(assignment));
        }
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
        return "log" + "(" + expression1.toString() + ", " + expression2.toString() + ")";
    }

    /**
     * This method assigns the specific var in this expression with a new expression.
     * @param var the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        return new Log(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    /**
     * This method differentiate this expression by a specific variable.
     * @param var inputted var we differentiate by.
     * @return the differentiated expression.
     */
    public Expression differentiate(String var) {
        List<String> temp = this.expression1.getVariables();
        // If the log's base contains a variable.
        if (temp.contains(var)) {
            return new Div(new Log(new Var("e"), this.expression2), new Log(new Var("e"),
                    this.expression1)).differentiate(var);
        } else {
            return new Div(expression2.differentiate(var), new Mult(this.expression2, new Log(new Var("e"),
                    this.expression1)));
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
        try {
            // If both evaluated works and return a num with the value.
            if (tempLeft != null && tempRight != null) {
                return new Num(Math.log(tempRight) / Math.log(tempLeft));
            }
        } catch (Exception ex) {
            // Simple declaring a (so catch block won't be empty).
            int a = 1;
        }
            // check if they are equal
        if (ifEqual()) {
            return new Num(1);
        } else {
            return new Log(this.expression1.simplify(), this.expression2.simplify());
        }
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

