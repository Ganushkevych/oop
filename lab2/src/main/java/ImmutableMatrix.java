import java.util.Arrays;
import java.util.Objects;

public final class ImmutableMatrix implements MatrixInterface{
    private final int rows;
    private final int columns;
    private final double[][] numbers;
    public ImmutableMatrix() {
        this.rows = 0;
        this.columns = 0;
        this.numbers = null;
    }

    public ImmutableMatrix(int rows, int columns) {
        if(rows<=0||columns<=0) {
            throw new WrongParametersException("Numbers of rows and columns should be positive");
        }
        this.rows = rows;
        this.columns = columns;
        this.numbers = new double[rows][columns];
    }

    public ImmutableMatrix(double[][] numbers) {
        if(numbers==null) {
            throw new WrongParametersException("Array of numbers should not be null");
        }
        this.rows = numbers.length;
        this.columns = numbers[0].length;
        if(columns==0) {
            throw new WrongParametersException("Numbers of rows and columns should be positive");
        }
        this.numbers = numbers;
    }

    public ImmutableMatrix(ImmutableMatrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.numbers = matrix.numbers;
    }
    @Override
    public double getElement(int row, int column){
        if(numbers==null) {
            throw new WrongParametersException("Matrix is empty");
        }
        if(row<0||column<0||row>rows||column>columns) {
            throw new WrongParametersException("Numbers of rows and columns should be positive");
        }
        return numbers[row][column];
    }
    @Override
    public double[] getRow(int row){
        if(numbers==null) {
            throw new WrongParametersException("Matrix is empty");
        }
        if(row<0||row>rows) {
            throw new WrongParametersException("Numbers of rows and columns should be positive");
        }
        return matrixNumbersCopy(numbers[row]);
    }
    @Override
    public double[] getColumn(int column){
        if(numbers==null) {
            throw new WrongParametersException("Matrix is empty");
        }
        if(column<0||column>columns) {
            throw new WrongParametersException("Numbers of rows and columns should be positive");
        }
        double[] columnArray = new double[rows];
        for (int i = 0; i < rows; i++) {
            columnArray[i] = numbers[i][column];
        }
        return columnArray;
    }
    @Override
    public double[][] getNumbers(){
        if(numbers==null) {
            throw new WrongParametersException("Matrix is empty");
        }
        return matrixNumbersCopy(numbers);
    }
    @Override
    public void print(){
        if(numbers==null) {
            throw new WrongParametersException("Matrix is empty");
        }
        for(double[] row:numbers) {
            System.out.println(Arrays.toString(row));
        }
    }
    @Override
    public void setElement(int row, int column, double element){
        throw new ImmutableMatrixException("It's Immutable Matrix");
    }
    @Override
    public void setRow(int row, double element){
        throw new ImmutableMatrixException("It's Immutable Matrix");
    }
    @Override
    public void setColumn(int column, double element){
        throw new ImmutableMatrixException("It's Immutable Matrix");
    }
    @Override
    public void fillByArray(double[][] numbers){
        throw new ImmutableMatrixException("It's Immutable Matrix");
    }
    @Override
    public void fillRandom(){
        throw new ImmutableMatrixException("It's Immutable Matrix");
    }

    @Override
    public boolean equals(Matrix matrix) {
        return false;
    }

    @Override
    public MatrixSize getSize(){
        return new MatrixSize(this.rows,this.columns);
    }
    @Override
    public boolean equals(ImmutableMatrix someMatrix){
        if(numbers==null||someMatrix.numbers==null) {
            throw new WrongParametersException("Matrix is empty");
        }
        if (this.rows!=someMatrix.rows||this.columns!= someMatrix.columns) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(numbers[i][j]!=someMatrix.numbers[i][j]) return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode(){
        return Objects.hash(rows, columns, Arrays.deepHashCode(numbers));
    }
    private double[][] matrixNumbersCopy(double[][] numbers){
        double[][] copiedNumbers = new double[numbers.length][numbers[0].length];
        for (int i = 0; i < numbers.length; i++) {
            System.arraycopy(numbers[i], 0, copiedNumbers[i], 0, numbers[i].length);
        }
        return copiedNumbers;
    }
    private double[] matrixNumbersCopy(double[] numbers){
        double[] copiedNumbers = new double[numbers.length];
        System.arraycopy(numbers, 0, copiedNumbers, 0, numbers.length);
        return copiedNumbers;
    }
}