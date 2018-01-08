import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class includes the common methods for unary expressions.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public abstract class UnaryExpression extends BaseExpression  {

    /**
     * This method returns the variables in an expression (without duplications).
     * @return the variables in an expression (without duplications).
     */
    @Override
    public List<String> getVariables() {
        List<String> var = new ArrayList<String>();
        if (this.getExpression1().getVariables() != null) {
            var.addAll(this.getExpression1().getVariables());
        }
        // Make sure every variable is added only once to the list.
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
     * An abstract method to get the expression.
     * @return the expression the type holds.
     */
    public abstract Expression getExpression1();
}
