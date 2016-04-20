import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Lexem {
    public String str;
    protected Lexem(String str)
    {
        this.str = str;
    }

    public abstract Lexem add(Lexem other);
//    public abstract Lexem sub(Lexem other);
    public abstract Lexem multiply(Lexem other);
    public abstract Lexem divide(Lexem other);
    public  abstract Lexem degree(Lexem other);
    public abstract Lexem parser(String str);

    protected double[] convert(String str)
    {
        double[] res = new double[str.length() + 1];
        int count = 0;
        String temp_numb = "";
        String numbers = "0123456789.";
        for (int i = 0; i < str.length(); i++)
        {
            if (numbers.indexOf(str.charAt(i)) >= 0)
            {
                temp_numb += str.charAt(i);
            } else {
                if (!(temp_numb.equals("")))
                {
                    double new_el = Double.parseDouble(temp_numb);
                    res[count] = new_el;
                    count++;
                    temp_numb = "";
                }
            }
        }
        if (!(temp_numb.equals(""))) {
            res[count] = Double.parseDouble(temp_numb);
            count++;
        }
        double[] newRes = new double[count];//убрать это, некрасиво((
        for (int i = 0; i < count; i++)
            newRes[i] = res[i];
        return newRes;
    }

    protected long checkIntDegree(double number) throws ArithmeticException
    {
        long roundNumb = Math.round(number);
        if (Math.abs(roundNumb - number) < 0.00001)//косая проверка на целый показатель степени
        {
            return roundNumb;
        } else {
            throw new ArithmeticException("degree isn't integer");
        }
    }

    protected String searchLexem(String regexpr, String str)
    {
        Pattern p = Pattern.compile("^" + regexpr);
        Matcher m = p.matcher(str);
        if (m.find()){return m.group();}
        return null;
    }
//    protected void addLexems(List<Lexem> res, Lexem checkLexem, Lexem firstOperand, Lexem secondOperand, Lexem operator)
//    {
//        if(checkLexem != null)
//        {
//            res.add(checkLexem);
//        }else {
//            List<Lexem> arrLexem = new ArrayList<Lexem>(3);
//            arrLexem.add(firstOperand);
//            arrLexem.add(operator);
//            arrLexem.add(secondOperand);
//            res.add(new MixtLexem(arrLexem));
//        }
//    }
}
