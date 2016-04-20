import java.util.ArrayList;
import java.util.List;

public class MixtLexem extends Lexem {
    List<Lexem> lexems;
    int countLexem;

    public MixtLexem(List<Lexem> lexems)
    {
        super(toString(lexems));
        this.lexems = lexems;
        this.countLexem = lexems.size();
    }
    public Lexem parser(String str) {return null;}

    private static String toString(List<Lexem> lexems)
    {
        String res = "";
        for (int i = 0; i < lexems.size(); i++)
        {
            res += lexems.get(i).str;
        }
        return res;
    }

    public Lexem add(Lexem other)
    {
        Lexem resAdd;
        List<Lexem> otherLex = new ArrayList<Lexem>();
        List<Lexem> res = new ArrayList<Lexem>();
        if (other instanceof MixtLexem)
        {
            otherLex = ((MixtLexem) other).lexems;
        } else{
            otherLex.add(other);
        }
        boolean[] used = new boolean[otherLex.size()];
        for (int i = 0; i < this.countLexem; i++)
        {
            Lexem nowLex = lexems.get(i);
            if (nowLex instanceof Operators)
            {
                res.add(res.size(), nowLex);
                continue;
            }
            boolean findSum = false;
            for (int j = 0; j < otherLex.size(); j++)
            {
                if (otherLex.get(j) instanceof Operators)
                {
                    used[j] = true;
                    continue;
                }
                resAdd = nowLex.add(otherLex.get(j));
                if (resAdd != null) {
                    res.add(res.size(), resAdd);
                    findSum = true;
                    used[j] = true;
                }
            }
            if (!(findSum))
            {
                res.add(res.size(), nowLex);
            }
        }
        for (int i = 0; i < otherLex.size(); i++)
            if (!(used[i]))
            {
                res.add(res.size(), new Operators("+"));
                res.add(res.size(), otherLex.get(i));
            }
//        this.lexems.add(this.countLexem, new Operators("+"));
//        this.lexems.add(this.countLexem + 1, other);
//        for (int i = 0; i < res.size(); i ++)
//        {
//            System.out.println(res.get(i).str);
//        }
        return new MixtLexem(res);
    }

    public Lexem multiply(Lexem other)
    {
        List<Lexem> res = new ArrayList<Lexem>();
        Lexem temp;
        for (int i = 0; i < this.countLexem; i++)
        {
           temp = lexems.get(i).multiply(other);
            if (temp != null)
            {
                res.add(res.size(), temp);
            } else {
                res.add(res.size(), lexems.get(i));
            }
        }
        return new MixtLexem(res);
    }

    public Lexem divide(Lexem other)
    {
        List<Lexem> res = new ArrayList<Lexem>();
        Lexem temp;
        for (int i = 0; i < this.countLexem; i++)
        {
            temp = lexems.get(i).divide(other);
            if (temp != null)
            {
                res.add(res.size(), temp);
            } else {
                res.add(res.size(), lexems.get(i));
            }
        }
        return new MixtLexem(res);
    }
    public Lexem degree(Lexem other)
    {
        List<Lexem> res = new ArrayList<Lexem>();
        Lexem temp;
        for (int i = 0; i < this.countLexem; i++)
        {
            temp = lexems.get(i).degree(other);
            if (temp != null)
            {
                res.add(res.size(), temp);
            } else {
                res.add(res.size(), lexems.get(i));
            }
        }
        return new MixtLexem(res);
    }
}
