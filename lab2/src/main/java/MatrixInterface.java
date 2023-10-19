public interface MatrixInterface {
    double[][] getNumbers();
    MatrixSize getSize();

    void setElement(int row, int column, double element);
    void setRow(int row, double element);
    void setColumn(int column, double element);
    void fillByArray(double[][] numbers);
    void fillRandom();
}
