/**
 * This interface declare the needed method to simplify.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public interface Simplifying {

    /**
     * Every method holds a specificSimplify to simplify it according to it's type.
     * @return new simplified expression.
     */
    Expression specificSimplify();

    /**
     * Every commutative can swap it's expression.
     * @return new expression with swapped expressions in it (only for commutativity).
     */
    Expression swapExp();

    /**
     * A getter for the commutativity of the expression.
     * @return if this expression is commutative.
     */
    boolean getIsCommutative();
}
