package ui;
import java.util.Scanner;

public class BisectionMethod{

    private static Scanner reader = new Scanner(System.in);
    /**
     * This is the main method where the other methods are going to be executed.
     * @param args
     */
    public static void main(String[] args){

        int answer = -1;
        System.out.println("¡Welcome to the Bisection Method Calculator!");

        do{
            menu();
            answer = validateInteger(": ");
            executeOption(answer);

        }while(answer != 0 || answer>1 || answer<0);

        reader.close();
    }

    /**
     * this method shows the user the valid options to execute.
     */
    public static void menu(){
        System.out.println("¿What dou you want to do?");
        System.out.println("0. Exit");
        System.out.println("1. Calculate the solution of a function");
    }

    /**
     * This method asks the user to select which function they want to find its root of and shows them the valid options.
     */
    public static void functionMenu(){
        System.out.println("Chose which function you want to find its root:");
        System.out.println("0.  Return");
        System.out.println("1.  2cos(x^2)");
        System.out.println("2.  3x^3 + 7x^2 + 5");
        System.out.println("3.  xcos(x)");
    }

    /**
     * This method executes the option previously selected by the user from the menu.
     * @param answer : int the option selected by the user
     */
    public static void executeOption(int answer){

        int selectedFunction;
        switch(answer){

            case 0:
                System.out.println("¡Thanks for using our app!");
                break;

            case 1:
                //Asks and executes the option selected by the user
                do{
                    functionMenu();
                    selectedFunction = validateInteger(":");

                    //validates if the user chooses any of the valid options
                    if(selectedFunction>3 || selectedFunction<0){
                        System.out.println("Invalid option. Please, try again");

                    }else if(selectedFunction !=0){
                        rootCalculation(selectedFunction);
                    }

                }while(selectedFunction>3 || selectedFunction<0);
                break;

            default:
                System.out.println("Invalid option. Please, try again");
                break;
        }
    }

    /**
     * This method calculates the root of the previously selected function by asking the user for an interval.
     * @param selectedFunction : int the function selected by the user.
     */
    public static void rootCalculation(int selectedFunction){
        //asks the user for the interval.
        double a = validateDouble("Enter the left endpoint of the interval: ");
        double b = validateDouble("Enter the right endpoint of the right interval: ");

        //validates if the user input is valid in order to use the bisection method.
        double fa = functions(selectedFunction, a);
        double fb = functions(selectedFunction, b);
        if(fa*fb<0){
            System.out.println( "\nThe root of selected function is: " + bisectionMethod(a, b, selectedFunction)+"\n");
        }else{
            System.out.println("f(a) and f(b) must have opposite signs");
        }
    }

    /**
     * This method implements the bisection mehtod to calculate the root of any function for any interval.
     * @param a : double the left endopoint of the interval.
     * @param b : double the right endpoint of the interval.
     * @param selectedFunction : int the function selected by the user.
     * @return  double root of the function.
     */
    public static double bisectionMethod(double a, double b, int selectedFunction){
        double epsilon = 0.0000000001; //tolerance value
        double N = absoluteValue(b-a); //the length of the interval
        double c = 0;
        double error;

        //the first convergence criterion is used.
        do{
            c = (a+b)/2;
            double fa = functions(selectedFunction, a);
            double fc = functions(selectedFunction, c);

            if(fa*fc<0){
                b = c;
            }else{
                a = c;
            }
            error = absoluteValue(b-a)/2*N;

        }while(error>epsilon);

        double root = c;

        return root;
    }

    /**
     * this method calculates any value within one of the previously selected functions.
     * @param selectedFunction : int the function selected by the user.
     * @param x : double the value of any number x.
     * @return the result of evaluating x in one of the three functions.
     */
    public static double functions(int selectedFunction, double x){
        double fx = 0;
        int maxIterations = 10;

        switch(selectedFunction){

            //calculates the value of 2cos(x^2)
            case 1:
                fx = 2*cosine(power(x,2), maxIterations);
                break;

            //calculates the value of 3x^3 + 7x^2 + 5
            case 2:
                fx = 3*power(x,3) + 7*power(x,2) +5;
                break;

            //calculates the value of xcox(x)
            case 3:
                fx = x*cosine(x,maxIterations);
                break;
        }

        return fx;
    }

    /**
     * This method calculates the abosulte value for any number. The absolute value of a number is the number without its sign.
     * @param number : double the number to which the absolute value is to be calculated.
     * @return double the absolute value of a number.
     */
    public static double absoluteValue(double number){

        if(number>= 0){
            return number;
        }else{
            return -1*number;
        }
    }

    /**
     * This method calculates the power of any number with any exponent. The power of a number is the result of multiplying itself a set number of times.
     * @param base : double the number being multiplied.
     * @param exponent : int the number of times the base multiplies by itself.
     * @return double the result of the power.
     */
    public static double power(double base, int exponent){

        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        if(exponent<0){
            result = 1/result;
        }

        return result;
    }

    /**
     * This method calculates the factorial of a number. The factorial of a number is the product of an integer and all the integers below it.
     * @param number : int the number to which the factorial is to be calculated.
     * @return double the result of the factorial.
     */
    public static long factorial(int number){
        long result = 1;

        for(int i=1; i<=number; i++){
            result *= i;
        }

        return result;
    }

    /**
     * This method calculates the cosine of a number by using the Taylor's series.
     * @param x : double the value of any number x.
     * @param maxIterations : long the maximun number of times the series is going to be repeated.
     * @return double the cosine value.
     */
    public static double cosine(double x, int maxIterations){
        double result = 0;
        double numerator, denominator;

        for(int i = 0; i<= maxIterations; i++){
            numerator = power(-1, i)*power(x, 2*i);
            denominator = factorial(2*i);

            result += numerator/denominator;
        }

        return result;
    }

    /**
     * This method validates an integer input by the user.
     * @param message : the message that will be displayed to the user to indicate what or where to write their response.
     * @return the input of the user.
     */
    public static int validateInteger(String message){
        int input = -1;
        boolean stopCondition = false; //flag type variable.

        do{
            System.out.print(message);

            if(reader.hasNextInt()){
                stopCondition = true;
                input = reader.nextInt();
                reader.nextLine();


            }
            else{
                reader.nextLine();
                System.out.println("Invalid option. Please, try again");
            }
        }while(stopCondition != true);

        return input;
    }

    /**
     * This method validates a double input by the user.
     * @param message : the message that will be displayed to the user to indicate what or where to write their response.
     * @return the input of the user.
     */
    public static double validateDouble(String message){
        Double input = -1.0;
        boolean stopCondition = false; //flag type variable

        do{
            System.out.print(message);

            if(reader.hasNextDouble()){
                stopCondition = true;
                input = reader.nextDouble();
            }
            else{
                reader.nextLine();
                System.out.println("Invalid option. Please, try again");
            }
        }while(stopCondition != true);

        return input;
    }

}