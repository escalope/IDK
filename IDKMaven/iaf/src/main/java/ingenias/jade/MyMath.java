package ingenias.jade;
/*************************************************************************
 *  Compilation:  javac MyMath.java
 *  Execution:    java MyMath z
 *
 *  Implements a number of mathematical functions: absolute value,
 *  square root, exponential, error function, and the cumulative
 *  Gaussian distribution. Also produces random Gaussians.
 *  The first three are for illustration only, since they are
 *  already in the standard Math library.
 *  
 *  The test client reads in a command line inputs z and prints out
 *  erf(z) and Phi(z) to 7 significant digits, where
 *
 *              erf(z) = 2 / sqrt(pi) * integral(exp(-t*t), t = 0..z) 
 *              Phi(z) = normal cdf
 *
 *
 *  % java MyMath 1.0
 *  abs(1.0) = 1.0
 *  exp(1.0) = 2.7182818284590455
 *  cosh(1.0) = 1.543080634815244
 *  sqrt(1.0) = 1.0
 *  erf(1.0) = 0.8427007877600067         // actual = 0.84270079294971486934
 *  Phi(1.0) = 0.8413447386043253         // actual = 0.8413447460

 *
 *  % java MyMath -1.0
 *  abs(-1.0) = 1.0
 *  exp(-1.0) = 0.36787944117144245
 *  cosh(-1.0) = 1.543080634815244
 *  sqrt(-1.0) = NaN
 *  erf(-1.0) = -0.8427007877600068
 *  Phi(-1.0) = 0.15865526139567465
 *
 *  % java MyMath 3.0
 *  abs(3.0) = 3.0
 *  exp(3.0) = 20.08553692318766
 *  cosh(3.0) = 10.067661995777765
 *  sqrt(3.0) = 1.7320508075688772
 *  erf(3.0) = 0.9999779095015785         // actual = 0.99997790950300141456
 *  Phi(3.0) = 0.9986501019267444
 * 
 *  % java MyMath 30
 *  abs(30.0) = 30.0
 *  exp(30.0) = 1.0686474581524467E13
 *  cosh(30.0) = 5.343237290762231E12
 *  sqrt(30.0) = 5.477225575051661
 *  erf(30.0) = 1.0
 *  Phi(30.0) = 1.0
 *
 *  % java MyMath -30
 *  abs(-30.0) = 30.0
 *  exp(-30.0) = 9.357622968840171E-14
 *  cosh(-30.0) = 5.343237290762231E12
 *  sqrt(-30.0) = NaN
 *  erf(-30.0) = -1.0
 *  Phi(-30.0) = 0.0
 *
 *  % java MyMath 1E-20
 *  abs(1.0E-20)  = 1.0E-20
 *  exp(1.0E-20)  = 1.0
 *  cosh(1.0E-20) = 1.0
 *  sqrt(1.0E-20) = 9.999999999999999E-11
 *  erf(1.0E-20)  = -3.0000000483809686E-8     // true anser 1.13E-20
 *  Phi(1.0E-20)  = 0.49999998499999976


 *
 *  Reference: Chebyshev fitting formula for erf(z) from
 *  Numerical Recipes, 6.2
 *  @author Robert Sedgewick and Kevin Wayne. 
 *************************************************************************/

public class MyMath {

    // absolute value
    public static double abs(double x) {
        if      (x == 0.0) return  x;    // for -0 and +0
        else if (x >  0.0) return  x;
        else               return -x;
    }

    // exponentiation - special case for negative input improves accuracy
    public static double exp(double x) {
        double term = 1.0;
        double sum  = 1.0;
        for (int N = 1; sum != sum + term; N++) {
            term = term * Math.abs(x) / N;
            sum  = sum + term;
        }
        if (x >= 0) return sum;
        else        return 1.0 / sum;
    }

    // calculate square root using Newton's method
    public static double sqrt(double c) {
        if (c == 0) return c;
        if (c <  0) return Double.NaN;
        double EPSILON = 1E-15;
        double t = c;
        while (Math.abs(t - c/t) > EPSILON * t) {
            t = (c/t + t) / 2.0;
        }
        return t;
    }

    // fractional error in math formula less than 1.2 * 10 ^ -7.
    // although subject to catastrophic cancellation when z in very close to 0
    public static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        // use Horner's method
        double ans = 1 - t * Math.exp( -z*z   -   1.26551223 +
                                            t * ( 1.00002368 +
                                            t * ( 0.37409196 + 
                                            t * ( 0.09678418 + 
                                            t * (-0.18628806 + 
                                            t * ( 0.27886807 + 
                                            t * (-1.13520398 + 
                                            t * ( 1.48851587 + 
                                            t * (-0.82215223 + 
                                            t * ( 0.17087277))))))))));
        if (z >= 0) return  ans;
        else        return -ans;
    }

    // fractional error less than x.xx * 10 ^ -4.
    public static double erf2(double z) {
        double t = 1.0 / (1.0 + 0.47047 * Math.abs(z));
        double poly = t * (0.3480242 + t * (-0.0958798 + t * (0.7478556)));
        double ans = 1.0 - poly * Math.exp(-z*z);
        if (z >= 0) return  ans;
        else        return -ans;
    }

    public static double phi(double x) {
        return Math.exp(-0.5 * x * x) / Math.sqrt(2 * Math.PI);
    }

    public static double phi(double x, double mu, double sigma) {
        return phi((x - mu) / sigma) / sigma;
    }

    // accurate with absolute error less than 8 * 10^-16
    // Reference: http://www.jstatsoft.org/v11/i04/v11i04.pdf
    public static double Phi2(double z) {
        if (z >  8.0) return 1.0;    // needed for large values of z
        if (z < -8.0) return 0.0;    // probably not needed
        double sum = 0.0, term = z;
        for (int i = 3; sum + term != sum; i += 2) {
            sum  = sum + term;
            term = term * z * z / i;
        }
        return 0.5 + sum * phi(z);
    }

    // cumulative normal distribution
    public static double Phi(double z) {
        return 0.5 * (1.0 + erf(z / (Math.sqrt(2.0))));
    }

    // cumulative normal distribution with mean mu and std deviation sigma
    public static double Phi(double z, double mu, double sigma) {
        return Phi((z - mu) / sigma);
    }

    // random integer between 0 and N-1
    public static int random(int N) {
        return (int) (Math.random() * N);
    }

    // random number with standard Gaussian distribution
    public static double gaussian() {
        double U = Math.random();
        double V = Math.random();
        return Math.sin(2 * Math.PI * V) * Math.sqrt((-2 * Math.log(1 - U)));
    }

    // random number with Gaussian distribution of mean mu and stddev sigma
    public static double gaussian(double mu, double sigma) {
        return mu + sigma * gaussian();
    }


   /*************************************************************************
    *  Hyperbolic trig functions
    *************************************************************************/
    public static double cosh(double x) {
        return (Math.exp(x) + Math.exp(-x)) / 2.0;
    }

    public static double sinh(double x) {
        return (Math.exp(x) - Math.exp(-x)) / 2.0;
    }

    public static double tanh(double x) {
        return sinh(x) / cosh(x);
    } 


   /*************************************************************************
    *  Test client
    *************************************************************************/
    public static void main(String[] args) { 
        double x = Double.parseDouble(args[0]);

        System.out.println("MyMath library");
        System.out.println("abs(" + x + ")  = " + MyMath.abs(x));
        System.out.println("exp(" + x + ")  = " + MyMath.exp(x));
        System.out.println("cosh(" + x + ") = " + MyMath.cosh(x));
        System.out.println("sqrt(" + x + ") = " + MyMath.sqrt(x));
        System.out.println("erf(" + x + ")  = " + MyMath.erf(x));
        System.out.println("erf2(" + x + ") = " + MyMath.erf2(x));
        System.out.println("Phi(" + x + ")  = " + MyMath.Phi(x));
        System.out.println("Phi2(" + x + ")  = " + MyMath.Phi2(x));
        System.out.println();

        System.out.println("Math library");
        System.out.println("abs(" + x + ")  = " + Math.abs(x));
        System.out.println("exp(" + x + ")  = " + Math.exp(x));
        System.out.println("sqrt(" + x + ") = " + Math.sqrt(x));

    }

}