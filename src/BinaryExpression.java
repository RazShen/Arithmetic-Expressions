import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class includes the common methods for binary expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public abstract class BinaryExpression extends BaseExpression {
    /**
     * This method returns the variables in an expression (both of the expressions ((without duplications)).
     * @return the variables in an expression (without duplications).
     */
    @Override
    public List<String> getVariables() {
        List<String> var = new ArrayList<String>();
        if (this.getExpression1().getVariables() != null) {
            var.addAll(this.getExpression1().getVariables());
        }
        if (this.getExpression2().getVariables() != null) {
            var.addAll(this.getExpression2().getVariables());
        }
        // Make sure every variable is added only one time to the list.
        for (int i = 0; i < var.size(); i++) {
            for (int j = (i + 1); j < var.size(); j++) {
                if (var.get(i).equals(var.get(j))) {
                    var.remove(j);
                }
            }
        }
        return var;
    }

    /**
     * This method checks if two expressions are equal by switching their inner expressions (only works if they are
     * commutatives).
     * @return true/false if they are equal.
     */
    public boolean ifEqual() {
        if (getExpression1().toString().equals(getExpression2().toString())) {
            return true;
        } else if (getExpression1().getIsCommutative()) {
            if (getExpression1().swapExp().toString().equals(getExpression2().toString())) {
                return true;
            }
        } else if (getExpression2().getIsCommutative()) {
            if (getExpression2().toString().equals(getExpression1().toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method gets the first expression.
     * @return the first expression.
     */
    public abstract Expression getExpression1();

    /**
     * This method gets the second expression.
     * @return the second expression.
     */
    public abstract Expression getExpression2();
}
