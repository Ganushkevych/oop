public class Matrix implements MatrixInterface{
    private final int rows;
    private final int columns;
    private double[][] numbers;

    public Matrix() {
        this.rows = 0;
        this.columns = 0;
    }

    public Matrix(int rows, int columns) {
        if(rows<=0||columns<=0) throw new WrongParametersException("Numbers of rows and columns should be positive");
        this.rows = rows;
        this.columns = columns;
        this.numbers = new double[rows][columns];
    }

    public Matrix(double[][] numbers) {
        if(numbers==null) throw new WrongParametersException("Array of numbers should not be null");
        this.rows = numbers.length;
        this.columns = numbers[0].length;
        if(columns==0) throw new WrongParametersException("Numbers of rows and columns should be positive");
        this.numbers = numbers;
    }

    public Matrix(Matrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.numbers = matrix.numbers;
    }

    @Override
    public double[][] getNumbers(){
        return numbers;
    }
    @Override
    public MatrixSize getSize(){
        return new MatrixSize(this.rows,this.columns);
    }
}
