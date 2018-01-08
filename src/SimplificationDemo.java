/**
 * This class shows the pre simplified expressions and the expressions after simplifying.
 */
public class SimplificationDemo {
    /**
     * A method to run the bonus part.
     * @param args irrelevant here.
     * @throws Exception able to throw an exception.
     */
    public static void main(String[] args) throws Exception {
        Expression neg;
        Expression min;
        Expression mult;
        Expression plus1;
        Expression plus2;
        Expression pow;
        neg = new Neg(new Neg(new Plus("x", "y")));
        System.out.println(neg);
        System.out.println(neg.simplify());
        System.out.println();
        min = new Neg(new Neg(new Minus(new Plus("x", "y"), new Plus("y", "x"))));
        System.out.println(min);
        System.out.println(min.simplify());
        System.out.println();
        mult = new Mult(new Plus(new Plus("x", "y"), "z"), new Plus("z", new Plus("x", "y")));
        System.out.println(mult);
        System.out.println(mult.simplify());
        System.out.println();
        plus1 = new Plus(new Mult(2, "x"), new Mult("x", 5));
        System.out.println(plus1);
        System.out.println(plus1.simplify());
        System.out.println();
        plus2 = new Plus(new Mult("x", "y"), new Mult("y", "x"));
        System.out.println(plus2);
        System.out.println(plus2.simplify());
        System.out.println();
        pow = new Pow(new Pow("x", "y"), "z");
        System.out.println(pow);
        System.out.println(pow.simplify());
    }
}
