public final class ImmutableMatrix extends Matrix implements MatrixInterface{
    private final Matrix matrix;
    public ImmutableMatrix(Matrix matrix) {
        this.matrix = new Matrix(matrix.getNumbers());
    }
    public ImmutableMatrix(int rows, int columns){
        this.matrix = new Matrix(rows,columns);
    }
    public ImmutableMatrix(double[][] numbers){
        this.matrix = new Matrix(numbers);
    }
    public ImmutableMatrix() {
        this.matrix = new Matrix();
    }
    @Override
    public double getElement(int row, int column){
        return matrix.getElement(row,column);
    }
    @Override
    public double[] getRow(int row){
        return matrixNumbersCopy(matrix.getRow(row));
    }
    @Override
    public double[] getColumn(int column){
        return matrixNumbersCopy(matrix.getColumn(column));
    }
    @Override
    public double[][] getNumbers(){
        if(matrix.getNumbers()==null) return null;
        return matrixNumbersCopy(matrix.getNumbers());
    }
    @Override
    public void print(){
        matrix.print();
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
    public MatrixSize getSize(){
        return matrix.getSize();
    }
    @Override
    public boolean equals(Matrix someMatrix){
        return matrix.equals(someMatrix);
    }
    @Override
    public int hashCode(){
        return matrix.hashCode();
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