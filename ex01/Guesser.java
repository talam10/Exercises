import java.math.BigInteger;
//@SuppressWarnings("UnusedReturnValue")
public class Guesser {
    /**
     * This method must return the number Chooser c has chosen. c can guess() any
     * number and tell you whether the number is "correct", "higher", or "lower".
     *
     * @param c The "chooser" that has chosen a number you must guess.
     * @return The number that the "chooser" has chosen
     */
    public static BigInteger findNumber(Chooser c) {
        // Tip: If you're not sure how to work with BigInteger numbers, we encourage
        // you to look up its Javadoc online.


        //THIS IS A BINARY SEARCH TREE IMPLEMENTATION WITH TWO METHODS
        //we will start with the lower bound that has to be greater than 0

        BigInteger lower_bound = new BigInteger("1");

        String guess = "higher"; //To determine what our guess should be

        //when guess is higher than what expected
        while (guess.equals("higher")) {

            guess = c.guess(lower_bound); //our search goes to the lower value node

            lower_bound = lower_bound.multiply(BigInteger.valueOf(2)); //and we keep increasing the range by multiplying

        }

        lower_bound = lower_bound.divide(BigInteger.valueOf(2));
        //We maintain a balance between the range by increasing and decreasing at the same time.

        if (guess.equals("correct")) {

            return lower_bound; //once it gives correct value, we return it

        }
        BigInteger low_guess = lower_bound.divide(BigInteger.valueOf(2));
        //Since I was having trouble to add a return statement, I decided to create a separate BigInt method

        //this is returning the only case left now that is returning the range between higher and lower bound
        return determination((low_guess).subtract(BigInteger.valueOf(1)), lower_bound, c); //calling the method

    }
    //creating a static method to align with our initial BigInt findNumber method. This method would help us to get a range between upper and lower bound
    private static BigInteger determination(BigInteger guess_l, BigInteger guess_h, Chooser c) {

        //First we would compare our guesses
        if (guess_h.compareTo(guess_l) < 0) {

            return BigInteger.valueOf(-1);

        }

        BigInteger total = guess_l.add(guess_h);

        //This is the key point where we decide the guess number would be in between upper and lower bound

        BigInteger midpoint = (total).divide(BigInteger.valueOf(2)); //first we calculate an average of the upper and lower bounds

        String result = c.guess(midpoint); //then we guess our number in that midpoint/average range

        //The cases: if the guess is lower than expected
        if (result.equals("lower")) {

            //we do a recursion of the method so that it keeps doing it until it gets to the value
            return determination(guess_l, midpoint.subtract(BigInteger.valueOf(1)), c);


            //The only other case left is guess being higher than expected
        } else if (result.equals("higher")) {

            return determination(midpoint.add(BigInteger.valueOf(1)), guess_h, c); //we do the recursion again

        }

        return midpoint; //returning the average

    }

}

