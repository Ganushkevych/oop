import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    @Test
    public void emptyMatrixCreation(){
        Matrix matrix = new Matrix();
        assertEquals(matrix.getSize().numOfColumns(),0);
        assertEquals(matrix.getSize().numOfRows(),0);
        assertNull(matrix.getNumbers());
    }
    @Test
    public void matrixCreationBySize(){
        Matrix matrix = new Matrix(2,3);
        assertEquals(matrix.getSize().numOfColumns(),3);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{0,0,0},new double[]{0,0,0}});
    }
    @Test
    public void matrixCreationByNumbers(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}});
        assertEquals(matrix.getSize().numOfColumns(),3);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}});
    }
    @Test
    public void matrixCreationByMatrix(){
        Matrix matrix = new Matrix(new Matrix(new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}}));
        assertEquals(matrix.getSize().numOfColumns(),3);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}});
    }
    @Test
    public void matrixFillOneElement(){
        Matrix matrix = new Matrix(2,2);
        matrix.setElement(0,0,1);
        matrix.setElement(0,1,2);
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{0,0}});
    }
    @Test
    public void matrixFillRow(){
        Matrix matrix = new Matrix(2,2);
        matrix.setRow(0,1);
        matrix.setRow(1,2);
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,1},new double[]{2,2}});
    }
    @Test
    public void matrixFillColumn(){
        Matrix matrix = new Matrix(2,2);
        matrix.setColumn(0,1);
        matrix.setColumn(1,2);
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{1,2}});
    }
    @Test
    public void matrixFillByArray(){
        Matrix matrix = new Matrix(2,2);
        matrix.fillByArray(new double[][]{new double[]{1,2},new double[]{1,2}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{1,2}});
    }
    @Test
    public void matrixFillRandom(){
        Matrix matrix = new Matrix(2,2);
        matrix.fillRandom();
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
    }
    @Test
    public void matrixGetElement(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertEquals(matrix.getElement(0,1),2);
    }
    @Test
    public void matrixGetRow(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertEquals(Arrays.toString(matrix.getRow(0)), Arrays.toString((new double[]{1, 2})));
    }
    @Test
    public void matrixGetColumn(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertEquals(Arrays.toString(matrix.getColumn(1)), Arrays.toString((new double[]{2, 4})));
    }
    @Test
    public void matrixGetNumbers(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertTrue(Arrays.deepEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{3,4}}));
    }
    @Test
    public void matrixGetSize(){
        Matrix matrix = new Matrix(5,6);
        assertEquals(matrix.getSize().numOfColumns(),6);
        assertEquals(matrix.getSize().numOfRows(),5);
    }
    @Test
    public void matrixEquals(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        Matrix otherMatrix = new Matrix(2,2);
        otherMatrix.setRow(0, 1);
        otherMatrix.setColumn(1,2);
        otherMatrix.setElement(1,0,3);
        otherMatrix.setElement(1,1,4);
        assertTrue(matrix.equals(otherMatrix));
    }
    @Test
    public void matrixHashCode(){
        Matrix matrix = new Matrix(2,2);
        assertEquals(matrix.hashCode(),63488);
    }
    @Test
    public void shouldChangeMutableMatrix(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        double[][] changedNumbers = matrix.getNumbers();
        double[] changedRow = matrix.getRow(0);
        double[] changedColumn = matrix.getColumn(0);
        double changedNumber = matrix.getElement(0,0);
        int rows = matrix.getSize().numOfRows();
        int columns = matrix.getSize().numOfColumns();
        assert changedNumbers != null;
        changedNumbers[0][1] = 10000;
        changedRow[0] = 10000;
        changedColumn[1] = 10000;
        changedNumber = 10000;
        rows = 3;
        columns = 3;
        assertFalse(Arrays.deepEquals(matrix.getNumbers(),new double[][]{new double[]{1,2},new double[]{3,4}}));
    }
    @Test
    public void matrixAddition(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2,3},new double[]{3,4,5}});
        Matrix otherMatrix = new Matrix(new double[][]{new double[]{1,2,3},new double[]{3,4,5}});
        assertArrayEquals(Matrix.plus(matrix, otherMatrix).getNumbers(),
                new double[][]{new double[]{2, 4,6}, new double[]{6, 8,10}});
    }
    @Test
    public void matrixMultiplyOnScalar(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2,3},new double[]{3,4,5}});
        assertArrayEquals(Matrix.multiplyScalar(matrix, 3).getNumbers(),
                new double[][]{new double[]{3, 6,9}, new double[]{9, 12,15}});
    }
    @Test
    public void matrixMultiply(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4},new double[]{5,6},new double[]{7,8}});
        Matrix otherMatrix = new Matrix(new double[][]{new double[]{1,2,3},new double[]{4,5,6}});
        assertArrayEquals(Matrix.multiplyMatrix(matrix, otherMatrix).getNumbers(),
                new double[][]{new double[]{9, 12,15}, new double[]{19,26,33},new double[]{29,40,51},
                        new double[]{39,54,69}});
    }
    @Test
    public void matrixTranspose(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2,3},new double[]{3,4,5}});
        assertArrayEquals(Matrix.transposedMatrix(matrix).getNumbers(),
                new double[][]{new double[]{1,3}, new double[]{2,4},new double[]{3,5}});
    }
    @Test
    public void diagonalMatrix(){
        double[] diagonalMatrixArray = {1,2,3,4};
        assertArrayEquals(Matrix.diagonalMatrix(diagonalMatrixArray).getNumbers(),
                new double[][]{new double[]{1,0,0,0}, new double[]{0,2,0,0},new double[]{0,0,3,0},
                new double[]{0,0,0,4}});
    }
    @Test
    public void matrixI(){
        int size = 4;
        assertArrayEquals(Matrix.matrixI(size).getNumbers(),
                new double[][]{new double[]{1,0,0,0}, new double[]{0,1,0,0},new double[]{0,0,1,0},
                        new double[]{0,0,0,1}});
    }
}
