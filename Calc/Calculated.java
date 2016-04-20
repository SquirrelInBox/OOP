import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculated {
    private static Lexem findOperator(Lexem operator, Lexem first_operand, Lexem second_operand) {
        switch (operator.str.charAt(0)) {
            case '+':
                return first_operand.add(second_operand);
            case '*':
                return first_operand.multiply(second_operand);
            case '/':
                return first_operand.divide(second_operand);
            case '^':
                return first_operand.degree(second_operand);
            default:
                return null;
        }
    }

    public static Lexem calculate(List<Lexem> lexems) {
        int i = 0;
        Lexem tempRes;
        while (i < lexems.size()) {
            while ((i < lexems.size()) && (!(lexems.get(i) instanceof Operators))) {
                i++;
            }
            if (i >= lexems.size()) {
                break;
            }
            if ((i < 2) || ((lexems.get(i - 2) instanceof Operators) ||
                    (lexems.get(i - 2) instanceof Brackets))) {
                tempRes = lexems.get(i - 1).multiply(new Numbers(-1, false));
                lexems.set(i - 1, tempRes);
                lexems.remove(i);
            } else {
                if (lexems.get(i).str.equals("-")) {
                    lexems.set(i, new Operators("+"));
                    lexems.set(i - 1, lexems.get(i - 1).multiply(new Numbers(-1, false)));
                }
                tempRes = findOperator(lexems.get(i), lexems.get(i - 2), lexems.get(i - 1));
                if (tempRes != null) {
                    lexems.set(i - 2, tempRes);
                } else {
                    List<Lexem> mixt = new ArrayList<Lexem>(3);
                    mixt.add(lexems.get(i - 2));
                    mixt.add(lexems.get(i));
                    mixt.add(lexems.get(i - 1));
                    lexems.set(i - 2, new MixtLexem(mixt));
                }
                lexems.remove(i);
                lexems.remove(i - 1);
                i--;
            }
        }
        return cancellation(lexems.get(0));
//        return lexems.get(0);
    }

    private static Lexem cancellation(Lexem lexems) {
        if (!(lexems instanceof MixtLexem))
            return lexems;
        List<Lexem> operands = ((MixtLexem) lexems).lexems;
        int size = operands.size();
        Lexem newLex;
        Lexem tempLex;
        List<Lexem> res = new ArrayList<Lexem>();
        boolean[] used = new boolean[((MixtLexem)lexems).countLexem];
        for (int i = 0; i < size - 1; i++) {
            newLex = operands.get(i);
            if (newLex instanceof Operators) {
                res.add(res.size(), newLex);
                used[i] = true;
                continue;
            }
            for (int j = i + 1; j < size; j++) {
                if (operands.get(j) instanceof Operators)
                    continue;
                tempLex = operands.get(j).add(newLex);
                if (tempLex != null)
                {
                    used[j] = true;
//                    used[i] = true;
                    newLex = tempLex;
                }
            }
            res.add(res.size(), newLex);
            used[i] = true;
        }
        for (int i = 0; i < used.length; i ++)
        {
            if (!(used[i]))
                res.add(res.size(), ((MixtLexem) lexems).lexems.get(i));
        }
        changeSign(res);
//        MixtLexem resLex = new MixtLexem(res);
        return new MixtLexem(res);
    }

    private static void changeSign(List<Lexem> lexem) {
        if (lexem.get(lexem.size() - 1) instanceof Operators) {
            lexem.remove(lexem.size() - 1);
        }
        for (int i = 0; i < lexem.size(); i++)
        {
            Pattern p = Pattern.compile("0.0(i)?");
            Matcher m = p.matcher(lexem.get(i).str);
            if (m.find())
            {
                int k;
                if (i > 0){
                    k = i;
                }
                else{
                    k = 1;
                }
                int end;
                String res = m.group();
                if (res.length() + i >= lexem.get(i).str.length())
                {
                    end = lexem.get(i).str.length();
                } else
                {
                    end = i + res.length();
                }
                lexem.get(i).str = lexem.get(i).str.substring(0, k - 1) + lexem.get(i).str.substring(end);
            }
        }
    }
}

//        List<Lexem> tempLex = new ArrayList<Lexem>();
//        for (int i  = 0; i < lexem.countLexem; i++)
//        {
//            if (lexem.lexems.get(i) instanceof MixtLexem)
//            {
//                for (int j = )
//            }
//            if (lexem.lexems.get(i) instanceof Numbers)
//            {
//                if ((((Numbers) lexem.lexems.get(i)).real < 0)&&(i > 0))
//                {
//                    lexem.lexems.set(i - 1, new Operators("-"));
//                    lexem.lexems.set(i, lexem.lexems.get(i).
//                            multiply(new Numbers(-1, ((Numbers) lexem.lexems.get(i)).complex)));
//                } else if (((Numbers) lexem.lexems.get(i)).real == 0){
//                    lexem.lexems.remove(i);
//                    if (i > 0)
//                        lexem.lexems.remove(i - 1);
//                }
////            }
//        }
//    }
//}
