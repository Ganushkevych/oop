import java.util.Arrays;
import java.util.Objects;
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
    public void print(){
        for(double[] row:numbers) {
            System.out.println(Arrays.toString(row));
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
    @Override
    public boolean equals(Matrix someMatrix) {
        if (this.rows!=someMatrix.rows||this.columns!= someMatrix.columns) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(numbers[i][j]!=someMatrix.numbers[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, Arrays.deepHashCode(numbers));
    }

    public static Matrix plus(Matrix first, Matrix second){
        if(first.getSize().numOfColumns()!=second.getSize().numOfColumns()&&
                first.getSize().numOfRows()!=second.getSize().numOfRows()) throw new WrongParametersException("Matrix must have same size");
        double[][] plusArray = new double[first.getSize().numOfRows()][first.getSize().numOfColumns()];
        for(int i = 0; i < first.getSize().numOfRows(); i++) {
            for (int j = 0; j < first.getSize().numOfColumns(); j++) {
                plusArray[i][j] = first.getElement(i,j)+ second.getElement(i,j);
            }
        }
        return new Matrix(plusArray);
    }
    public static Matrix multiplyScalar(Matrix first,double scalar){
        double[][] multiplyArray = new double[first.getSize().numOfRows()][first.getSize().numOfColumns()];
        for(int i = 0; i < first.getSize().numOfRows(); i++) {
            for (int j = 0; j < first.getSize().numOfColumns(); j++) {
                multiplyArray[i][j] = first.getElement(i,j)*scalar;
            }
        }
        return new Matrix(multiplyArray);
    }
    public static Matrix multiplyMatrix(Matrix first, Matrix second){
        if(first.getSize().numOfColumns()!=second.getSize().numOfRows()) throw new WrongParametersException("Matrix must have special size");
        double[][] multiplyArray = new double[first.getSize().numOfRows()][second.getSize().numOfColumns()];
        System.out.println(first.getSize().numOfRows());
        System.out.println(second.getSize().numOfColumns());
        for(int i = 0; i < first.getSize().numOfRows(); i++) {
            for (int j = 0; j < second.getSize().numOfColumns(); j++) {
                for (int e = 0; e < second.getSize().numOfRows(); e++){
                    multiplyArray[i][j] += first.getElement(i,e)*second.getElement(e,j);
                }
            }
        }
        return new Matrix(multiplyArray);
    }
    public static Matrix transposedMatrix(Matrix matrix){
        double[][] transposeArray = new double[matrix.getSize().numOfColumns()][matrix.getSize().numOfRows()];
        for(int i = 0; i < matrix.getSize().numOfRows(); i++) {
            for (int j = 0; j < matrix.getSize().numOfColumns(); j++) {
                transposeArray[j][i] = matrix.getElement(i,j);
            }
        }
        return new Matrix(transposeArray);
    }
    public static Matrix diagonalMatrix(double[] array){
        double[][] diagonalMatrixArray = new double[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(i==j) diagonalMatrixArray[i][j] = array[i];
                else diagonalMatrixArray[i][j] = 0;
            }
        }
        return new Matrix(diagonalMatrixArray);
    }
}
