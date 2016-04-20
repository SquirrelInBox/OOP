import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RateException extends Exception
{
    public RateException(String mess)
    {
        super(mess);
    }

}

public class Numbers extends Lexem
{
    double real;
    boolean complex;

    public Numbers(String str, boolean complex)
    {
        super(str);
        this.complex = complex;
        real = convert(str)[0];
    }

    public Numbers(double numb, boolean complex)
    {
        super(Double.toString(numb));
        this.complex = complex;
        this.real = numb;
        if (this.complex)
            this.str += "i";
    }

    public Numbers()
    {
        super(null);
        real = Double.NaN;
        complex = false;
    }

    public Lexem parser(String str)
    {
        String lexema = searchLexem("\\d+(\\.\\d+)?i?", str);
        if (!(lexema == null))
        {
            Pattern i = Pattern.compile("i");
            Matcher compl = i.matcher(lexema);
            if (compl.find())
            {
                return new Numbers(lexema, true);
            }
            return new Numbers(lexema, false);
        }
        lexema = searchLexem("i", str);
        if (lexema != null)
        {
            Lexem res = new Numbers(1, true);
            res.str = "i";
            return res;
        }
        return null;
    }

    public Lexem add(Lexem other)
    {
        if ((other instanceof Numbers)&&(this.complex == ((Numbers) other).complex))
        {
            return new Numbers(this.real + ((Numbers) other).real, this.complex);
        }
        if (other instanceof MixtLexem)
        {
//            System.out.println(this.str);
            return other.add(this);
        }
        return null;
    }

    public Lexem multiply(Lexem other)
    {
        if (other instanceof Numbers)
        {
            if (this.complex == ((Numbers) other).complex) {
                if (this.complex == true) {
                    return new Numbers(this.real * ((Numbers) other).real * (-1), false);
                }
                return new Numbers(this.real * ((Numbers) other).real, false);
            }
            return new Numbers(this.real * ((Numbers) other).real, true);
        }
        if (other instanceof VectorND)
        {
            return other.multiply(this);
        }
        if (other instanceof MixtLexem)
        {
            return other.multiply(this);
        }
        return null;
    }

    public Lexem divide(Lexem other)
    {
        if (other instanceof Numbers)
        {
            try//сделать без try
            {
                if (((Numbers) other).real == 0)
                {
                    throw new ArithmeticException("Divide on zero");
                }
                if (this.complex == ((Numbers) other).complex)
                {
                    if (!this.complex)
                        return new Numbers(this.real / ((Numbers) other).real, false);
                    return new Numbers(this.real * (-1) / ((Numbers) other).real, false);
                }
                return new Numbers(this.real / ((Numbers) other).real, true);
            }catch (ArithmeticException e){
                System.out.println("Divide on zero");
                System.exit(1);
            }
        }
        if (other instanceof MixtLexem)
        {
            return other.add(this);
        }
//        if (other instanceof MixtLexem)
//        {
//            return other.add(this);
//        }
        return null;
    }

    public Lexem degree(Lexem other)
    {
        if (other instanceof Numbers)
        {
            try {
                long roundNumb = checkIntDegree(((Numbers) other).real);
                if (((Numbers) other).complex)
                    throw new RateException("rate of degree isn't double");
                if (!(this.complex))
                    return new Numbers(Math.pow(this.real, roundNumb), false);
                Numbers res = new Numbers(this.real, true);
                for (int i = 1; i <= roundNumb; i++)
                    res = (Numbers)(Lexem)(res.multiply(res));
            }catch (ArithmeticException e){
                System.out.println("Degree isn't integer");
                System.exit(1);
            }catch (RateException e) {
                System.out.println("Rate of degree isn't double");
                System.exit(1);
            }
        }
        if (other instanceof MixtLexem)
        {
            return other.add(this);
        }
        return null;
    }
}
