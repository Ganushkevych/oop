import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableMatrixTest {
    @Test
    public void emptyMatrixCreation(){
        ImmutableMatrix matrix = new ImmutableMatrix();
        assertAll(() -> assertEquals(matrix.getSize().numOfColumns(),0),
                () -> assertEquals(matrix.getSize().numOfRows(),0),
                () -> assertThrows(WrongParametersException.class, matrix::getNumbers));
    }
    @Test
    public void matrixCreationBySize(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,3);
        assertAll(()-> assertEquals(matrix.getSize().numOfColumns(),3),
                () -> assertEquals(matrix.getSize().numOfRows(),2),
                () -> assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{0,0,0},new double[]{0,0,0}}));
    }
    @Test
    public void matrixCreationByNumbers(){
        ImmutableMatrix matrix = new ImmutableMatrix(new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}});
        assertEquals(matrix.getSize().numOfColumns(),3);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}});
    }
    @Test
    public void matrixCreationByMatrix(){
        ImmutableMatrix matrix = new ImmutableMatrix(new ImmutableMatrix(new Matrix(new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}})));
        assertEquals(matrix.getSize().numOfColumns(),3);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2,3},new double[]{-1,-2,-3}});
    }
    @Test
    public void matrixFillOneElement(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,2);
        try{
            matrix.setElement(0,0,1);
            matrix.setElement(0,1,2);
            assertEquals(matrix.getSize().numOfColumns(),2);
            assertEquals(matrix.getSize().numOfRows(),2);
            assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{0,0}});
            fail();
        }catch(ImmutableMatrixException ignored){}

    }
    @Test
    public void matrixFillRow(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,2);
        try{
            matrix.setRow(0,1);
            matrix.setRow(1,2);
            assertEquals(matrix.getSize().numOfColumns(),2);
            assertEquals(matrix.getSize().numOfRows(),2);
            assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,1},new double[]{2,2}});
            fail();
        }catch(ImmutableMatrixException ignored){}

    }
    @Test
    public void matrixFillColumn(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,2);
        try{
            matrix.setColumn(0,1);
            matrix.setColumn(1,2);
            assertEquals(matrix.getSize().numOfColumns(),2);
            assertEquals(matrix.getSize().numOfRows(),2);
            assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{1,2}});
            fail();
        }catch(ImmutableMatrixException ignored){}

    }
    @Test
    public void matrixFillByArray(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,2);
        try{
            matrix.fillByArray(new double[][]{new double[]{1,2},new double[]{1,2}});
            assertEquals(matrix.getSize().numOfColumns(),2);
            assertEquals(matrix.getSize().numOfRows(),2);
            assertArrayEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{1,2}});
            fail();
        }catch(ImmutableMatrixException ignored){}

    }
    @Test
    public void matrixFillRandom(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,2);
        try{
            matrix.fillRandom();
            fail();
        }catch(ImmutableMatrixException ignored){}
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
    }
    @Test
    public void matrixGetElement(){
        ImmutableMatrix matrix = new ImmutableMatrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertEquals(matrix.getElement(0,1),2);
    }
    @Test
    public void matrixGetRow(){
        ImmutableMatrix matrix = new ImmutableMatrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertEquals(Arrays.toString(matrix.getRow(0)), Arrays.toString((new double[]{1, 2})));
    }
    @Test
    public void matrixGetColumn(){
        ImmutableMatrix matrix = new ImmutableMatrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertEquals(Arrays.toString(matrix.getColumn(1)), Arrays.toString((new double[]{2, 4})));
    }
    @Test
    public void matrixGetNumbers(){
        ImmutableMatrix matrix = new ImmutableMatrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        assertEquals(matrix.getSize().numOfColumns(),2);
        assertEquals(matrix.getSize().numOfRows(),2);
        assertTrue(Arrays.deepEquals(matrix.getNumbers(), new double[][]{new double[]{1,2},new double[]{3,4}}));
    }
    @Test
    public void matrixGetSize(){
        ImmutableMatrix matrix = new ImmutableMatrix(5,6);
        assertEquals(matrix.getSize().numOfColumns(),6);
        assertEquals(matrix.getSize().numOfRows(),5);
    }
    @Test
    public void matrixEquals(){
        ImmutableMatrix matrix = new ImmutableMatrix(new double[][]{new double[]{0,0},new double[]{0,0}});
        ImmutableMatrix otherMatrix = new ImmutableMatrix(2,2);
        assertTrue(matrix.equals(otherMatrix));
    }
    @Test
    public void matrixHashCode(){
        ImmutableMatrix matrix = new ImmutableMatrix(2,2);
        assertEquals(matrix.hashCode(),63488);
    }
    @Test
    public void shouldNotChangeImmutableMatrix(){
        Matrix matrix = new Matrix(new double[][]{new double[]{1,2},new double[]{3,4}});
        ImmutableMatrix iMatrix = new ImmutableMatrix(matrix);
        matrix.setElement(0,0,0);
        double[][] changedNumbers = iMatrix.getNumbers();
        double[] changedRow = iMatrix.getRow(0);
        double[] changedColumn = iMatrix.getColumn(0);
        double changedNumber = iMatrix.getElement(0,0);
        int rows = iMatrix.getSize().numOfRows();
        int columns = iMatrix.getSize().numOfColumns();
        changedNumbers[0][1] = 10000;
        changedRow[0] = 10000;
        changedColumn[1] = 10000;
        changedNumber = 10000;
        rows = 3;
        columns = 3;
        assertArrayEquals(iMatrix.getNumbers(), new double[][]{new double[]{1, 2}, new double[]{3, 4}});
    }
}