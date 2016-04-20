//import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class PostfixNotationException extends Exception
{
    public PostfixNotationException(String msg)
    {
        super(msg);
    }
}
public class PostfixNotation {
    private static void findException(Stack<Lexem> lexemStack)
    {
        try {
            if (lexemStack.isEmpty())
            {
                throw new PostfixNotationException("Incorrect brackets");
            }
        }catch (PostfixNotationException e)
        {
            System.out.println("Incorrect brackets");
            System.exit(1);
        }
    }
    private static void operationsWithBrackets(Lexem contLexema, List<Lexem> lexems, Stack<Lexem> lexemStack) {
        if (((Brackets) contLexema).priority == 0) {
            lexemStack.add(contLexema);
        } else {
            while (!(lexemStack.isEmpty())) {
                if (!(lexemStack.peek() instanceof Brackets)){
                    lexems.add(lexemStack.pop());
                } else {
                    break;
                }
            }
            findException(lexemStack);
            lexemStack.pop();//удаляет открывающуюся скобку из стека
        }
    }

    private static void operationsWithOperators(Lexem contLexema, List<Lexem> lexems, Stack<Lexem> lexemStack)
    {
        while(!(lexemStack.isEmpty()) && !(lexemStack.peek() instanceof Brackets))
        {
            if (((Operators)contLexema).priority <= ((Operators)lexemStack.peek()).priority)
            {
                lexems.add(lexemStack.pop());
            } else {
                break;
            }
        }
        lexemStack.add(contLexema);
    }

    private static void addInStack(Lexem contLexema, List<Lexem> lexems, Stack<Lexem> lexemStack) {
        if (contLexema instanceof Brackets) {
            operationsWithBrackets(contLexema, lexems, lexemStack);
        } else {
            operationsWithOperators(contLexema, lexems, lexemStack);
        }
    }

    private static void clearStack(List<Lexem> lexems, Stack<Lexem>lexemStack)
    {
        while (!(lexemStack.isEmpty()))
        {
            lexems.add(lexemStack.pop());
        }
    }

    public static List<Lexem> convertToPostfixNotation(List<Lexem> lexems) {
        Lexem tempLexem;
//        System.out.println(lexems);
        List<Lexem> result = new ArrayList<Lexem>();
        Stack<Lexem> lexemStack = new Stack<Lexem>();
        for (int i = 0; i < lexems.size(); i++) {
            tempLexem = lexems.get(i);
            if (!(tempLexem instanceof Brackets) && !(tempLexem instanceof Operators)) {
                result.add(tempLexem);
            } else {
                addInStack(tempLexem, result, lexemStack);
            }
        }
        clearStack(result, lexemStack);
        return result;
    }
}
