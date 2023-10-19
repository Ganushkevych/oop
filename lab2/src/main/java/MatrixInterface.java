public interface MatrixInterface {
    double getElement(int row, int column);
    double[] getRow(int row);
    double[] getColumn(int column);
    double[][] getNumbers();
    MatrixSize getSize();

    void setElement(int row, int column, double element);
    void setRow(int row, double element);
    void setColumn(int column, double element);
    void fillByArray(double[][] numbers);
    void fillRandom();

    boolean equals(Matrix matrix);
    int hashCode();
}
