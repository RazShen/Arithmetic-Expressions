import java.util.Map;
import java.util.TreeMap;

/**
 * The test for ass4.
 */
public class ExpressionsTest {
    /**
     * A method to run the test.
     * @param args irrelevant here.
     * @throws Exception able to throw an exception.
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        System.out.println(e);
        System.out.println(e.evaluate(assignment));
        System.out.println(e.differentiate("x"));
        System.out.println(e.differentiate("x").evaluate(assignment));
        System.out.println(e.differentiate("x").simplify());
    }
}
