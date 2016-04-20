public class VectorND extends Lexem
{
    int dim;
    double[] coord;

    VectorND(double[] coord)
    {
        super(VectorND.toString(coord));
        this.coord = coord;
        this.dim = coord.length;
    }
    VectorND(String coord)
    {
        super(coord);
        this.coord = convert(coord);
        this.dim = this.coord.length;
    }

    public VectorND()
    {
        super(null);
        this.coord = new double[0];
        this.dim = 0;
    }

    public static String toString(double[] arr)
    {
        String res = "(";
        for (int i = 0; i < arr.length; i++)
            res += arr[i] + ", ";
        res = res.substring(0, (res.length() - 2)) + ")";
        return res;
    }

    public Lexem parser(String str)
    {
        String lexema = searchLexem("(\\(|\\[|<)\\d+(\\.\\d+)?i?(,( )?\\d+(\\.\\d+)?i?).*?(\\)|\\]|>)", str);
        if (lexema != null){ return new VectorND(lexema);}
        return null;
    }

    public Lexem add(Lexem other)
    {
        if ((other instanceof VectorND)&&(this.dim == ((VectorND) other).dim)){
            double[] resCoord = new double[this.dim];

            for (int i = 0; i < ((VectorND)other).coord.length; i++) {

                resCoord[i] = this.coord[i] + ((VectorND)other).coord[i];
            }
            return new VectorND(resCoord);
        }
        if (other instanceof MixtLexem)
        {
            return other.add(this);
        }
        return null;
    }

    public Lexem multiply(Lexem other)
    {
        if (other instanceof VectorND)
        {
            return new Numbers(this.sc(other), false);
        }
        if (other instanceof Numbers)
        {
            return increaseVector(((Numbers) other).real);
        }
        if (other instanceof MixtLexem)
        {
            return other.multiply(this);
        }
        return null;
    }

    private Lexem increaseVector(double coef)
    {
        double[] res = this.coord;
        for (int i = 0; i < this.dim; i ++)
        {
            res[i] *= coef;
        }
        return new VectorND(res);
    }

    private double sc(Lexem other)
    {
        if (this.dim == ((VectorND)other).dim)
        {
            double res = 0;
            for (int i = 0; i < this.dim; i++)
            {
                res += this.coord[i] * ((VectorND)other).coord[i];
            }
            return res;
        }
        else
        {
            return Double.NaN;
        }
    }

    public Lexem divide(Lexem other)
    {
        if (other instanceof Numbers)
        {
            try
            {
                if (((Numbers) other).real == 0) {
                    throw new ArithmeticException("divide on zero");
                }
                return divideOnNumber(((Numbers) other).real);
            }
            catch (ArithmeticException e)
            {
                System.out.println("Divide vector on zero");
                System.exit(1);
            }
        }
        if (other instanceof MixtLexem)
        {
            return other.divide(this);
        }
        return null;
    }

    private VectorND divideOnNumber(double coef)
    {
        double[] res = this.coord;
        for (int i = 0; i < this.dim; i++)
        {
            res[i] /= coef;
        }
        return new VectorND(res);
    }

    public Lexem degree(Lexem other)
    {
        return null;
    }

    public boolean equals(VectorND other)
    {
        if (other == null)
        {
            return false;
        }
        if (this.dim != other.dim)
        {
            return false;
        }
        for (int i = 0; i < this.dim; i++)
        {
            if (Math.abs(this.coord[i] - other.coord[i]) > 0.0001)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int hash = this.dim;
        for (int i = 0; i < this.dim; i++)
        {
            hash += Math.round(this.coord[i]) + i;
        }
        return hash;
    }
}
