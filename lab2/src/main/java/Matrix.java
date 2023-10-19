import java.util.Random;

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
    public double getElement(int row, int column){
        if(row<0||column<0||row>rows||column>columns) throw new WrongParametersException("Numbers of rows and columns should be positive");
        return numbers[row][column];
    }
    @Override
    public double[] getRow(int row){
        if(row<0||row>rows) throw new WrongParametersException("Numbers of rows and columns should be positive");
        return numbers[row];
    }
    @Override
    public double[] getColumn(int column){
        if(column<0||column>columns) throw new WrongParametersException("Numbers of rows and columns should be positive");
        double[] columnArray = new double[rows];
        for (int i = 0; i < rows; i++) {
            columnArray[i] = numbers[i][column];
        }
        return columnArray;
    }
    @Override
    public double[][] getNumbers(){
        return numbers;
    }
    @Override
    public MatrixSize getSize(){
        return new MatrixSize(this.rows,this.columns);
    }
    @Override
    public void setElement(int row, int column, double element) {
        if(row<0||column<0||row>rows||column>columns) throw new WrongParametersException("Numbers of rows and columns should be positive");
        this.numbers[row][column] = element;
    }

    @Override
    public void setRow(int row, double element) {
        if(row<0||row>rows) throw new WrongParametersException("Numbers of rows and columns should be positive");
        for(int i=0;i<columns;i++) {
            this.setElement(row, i, element);
        }
    }

    @Override
    public void setColumn(int column, double element) {
        if(column<0||column>columns) throw new WrongParametersException("Numbers of rows and columns should be positive");
        for(int i=0;i<rows;i++) {
            this.setElement(i, column, element);
        }
    }
    @Override
    public void fillByArray(double[][] numbers){
        if(numbers==null) throw new WrongParametersException("Array of numbers should not be null");
        if(!(this.numbers.length == numbers.length && this.numbers[0].length == numbers[0].length)) throw new WrongParametersException("Array of numbers should be same size as matrix");
        this.numbers = numbers;
    }

    @Override
    public void fillRandom(){
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                numbers[i][j] = random.nextDouble();
            }
        }
    }
}
