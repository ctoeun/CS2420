package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Erin Parker & Cobi Toeun
 * @version January 20, 2021
 */
class MathVectorTester {
	
	private MathVector rowVec, largerRowVec, rowVecTranspose, unitVec, largerUnitRowVec, unitColVec, sumVec, colVec, smallerColVec, largerColVec, colVecTranspose, sumColVec, negCol, negRow;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a larger row vector with ten elements: 4.0, 15.0, 6.0, 24.0, 16.0, 3.0, 54.0, 25.0, 67.0, 8.0
		largerRowVec = new MathVector(new double[][]{{4, 15, 6, 24, 16, 3, 54, 25, 67, 8}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a larger row vector with ten elements: 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0
		largerUnitRowVec = new MathVector(new double[][] {{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}});
		
		// Creates a larger column vector with five elements: 10.0, 10.0, 10.0, 10.0, 10.0
		unitColVec = new MathVector(new double[][] {{10}, {10}, {10}, {10}, {10}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});
		
		// Creates a column vector with five elements: -1.0, 2.5, 6.0, -3.5, 8.0
		smallerColVec = new MathVector(new double[][]{{-1}, {2.5}, {6}, {-3.5}, {8}});
		
		// Creates a larger column vector with ten elements: 100.0, 25.5, -32.0, 3.14, 4.1, -10.0, 55.0, 1.0, -100.0, 60.5
		largerColVec = new MathVector(new double[][]{{100}, {25.5}, {-32}, {3.14}, {4.1}, {-10}, {55}, {1.0}, {-100}, {60.5}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVecTranspose = new MathVector(new double[][]{{-11, 2.5, 36, -3.14, 7.1}});
		
		// Creates a column vector with five elements: 9.0, 12.5, 16.0, 6.5, 18.0
		sumColVec = new MathVector(new double[][]{{9}, {12.5}, {16}, {6.5}, {18}});
		
		// Creates a column vector with three negative elements: -9.0, -1.5, -20.0, -2, -50.4
		negCol = new MathVector(new double[][]{{-9}, {-1.5}, {-20}, {-2}, {-50.4}});
				
		// Creates a row vector with three elements: -10.0, -7.0, -34.0
		negRow = new MathVector(new double[][]{{-10, -7, -34}});
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}

	@Test
	void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);		
	}
	
	@Test
	void smallRowVectorLength() {
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	@Test
	void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));		
	}
	
	@Test
	void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());		
	}

	// 'Many more' tests
	
	@Test
	void smallColVectorEquality() {
		assertTrue(colVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}})));
	}
	
	@Test
	void largerRowVectorEquality() {
		assertTrue(largerRowVec.equals(new MathVector(new double[][]{{4, 15, 6, 24, 16, 3, 54, 25, 67, 8}})));
	}
	
	@Test
	void largerColVectorEquality() {
		assertTrue(largerColVec.equals(new MathVector(new double[][]{{100}, {25.5}, {-32}, {3.14}, {4.1}, {-10}, {55}, {1.0}, {-100}, {60.5}})));
	}
	
	@Test
	void largerRowVectorInequality() {
		assertFalse(largerRowVec.equals(unitVec));
	}
	
	@Test
	void largerRowVectorInequality2() {
		assertFalse(largerRowVec.equals(largerUnitRowVec));
	}
	
	@Test
	void largerColVectorInequality() {
		assertFalse(largerColVec.equals(unitVec));
	}
	
	@Test
	void largerColVectorInequality2() {
		assertFalse(largerColVec.equals(largerUnitRowVec));
	}
	
	@Test
	public void createVectorFromBadArray2() {
	  double arr[][] = {{-1, 3} , {5, -15, -32} , {-4, 16, 3, 10}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}
	
	@Test
	public void createVectorFromBadArray3() {
	  double arr[][] = {{43, -23}, {5, -15, 41, 65, -100} , {10, -2} , {4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}
	
	@Test
	void transposeColVector() {
		MathVector transposeResult = colVec.transpose();
		assertTrue(transposeResult.equals(colVecTranspose));
	}
	
	@Test
	public void addLargerRowToSmallerColVector() {
	  assertThrows(IllegalArgumentException.class, () -> { largerRowVec.add(colVec); });
	}
	
	@Test
	public void addLargerColToSmallerRowVector() {
	  assertThrows(IllegalArgumentException.class, () -> { largerColVec.add(rowVec); });
	}
	
	@Test
	public void addLargerColToSmallerColVector() {
	  assertThrows(IllegalArgumentException.class, () -> { largerColVec.add(colVec); });
	}
	
	@Test
	public void addLargerRowToSmallerRowVector() {
	  assertThrows(IllegalArgumentException.class, () -> { largerRowVec.add(rowVec); });
	}
	
	@Test
	void addSmallColVectors() {
		MathVector addResult = smallerColVec.add(unitColVec);
		assertTrue(addResult.equals(sumColVec));		
	}
	
	@Test
	void dotProductSmallColVectors() {
		double dotProdResult = colVec.dotProduct(unitColVec);
		assertEquals(dotProdResult, -11.0 * 10.0 + 2.5 * 10.0 + 36.0 * 10.0 + -3.14 * 10.0 + 7.1 * 10.0);		
	}
	
	@Test
	public void throwsDotProductDifferentVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.dotProduct(colVec); });
	}
	
	@Test
	public void throwsDotProductDifferentVectorSize() {
	  assertThrows(IllegalArgumentException.class, () -> { colVec.dotProduct(largerColVec); });
	}
	
	@Test
	void dotProductNegRowVectors() {
		double dotProdResult = negRow.dotProduct(rowVec);
		assertEquals(dotProdResult, -10.0 * 3.0 + -7.0 * 1.0 + -34.0 * 2.0);		
	}
	
	@Test
	void dotProductNegColVectors() {
		double dotProdResult = negCol.dotProduct(negCol);
		assertEquals(dotProdResult, -9.0 * -9.0 + -1.5 * -1.5 + -20.0 * -20.0 + -2.0 * -2.0 + -50.4 * -50.4);		
	}
	
	@Test
	void colVectorLength() {
		double vecLength = colVec.magnitude();
		assertEquals(vecLength, Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + -3.14 * -3.14 + 7.1 * 7.1));		
	}
	
	@Test
	void colVectorNormalize() {
		MathVector normalVec = colVec.normalize();
		double length = Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + -3.14 * -3.14 + 7.1 * 7.1);
		assertFalse(normalVec.equals(new MathVector(new double[][]{{-11.0 / length, 2.5 / length, 36.0 / length, -3.14 / length, 7.1 / length}})));		
	}
	
	@Test
	void smallColVectorLength() {
		double vecLength = smallerColVec.magnitude();
		assertEquals(vecLength, Math.sqrt(-1.0 * -1.0 + 2.5 * 2.5 + 6.0 * 6.0 + -3.5 * -3.5 + 8.0 * 8.0));		
	}
	
	@Test
	void smallColVectorNormalize() {
		MathVector normalVec = smallerColVec.normalize();
		double length = Math.sqrt(-1.0 * -1.0 + 2.5 * 2.5 + 6.0 * 6.0 + -3.5 * -3.5 + 8.0 * 8.0);
		assertFalse(normalVec.equals(new MathVector(new double[][]{{-1.0 / length, 2.5 / length, 6.0 / length, -3.5 / length, 8.0 / length}})));		
	}
	
	@Test
	void largerRowVectorLength() {
		double vecLength = largerRowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(4.0 * 4.0 + 15.0 * 15.0 + 6.0 * 6.0 + 24.0 * 24.0 + 16.0 * 16.0 + 3.0 * 3.0 + 54.0 * 54.0 + 25.0 * 25.0 + 67.0 * 67.0 + 8.0 * 8.0));		
	}
	
	@Test
	void largerRowVectorNormalize() {
		MathVector normalVec = largerRowVec.normalize();
		double length = Math.sqrt(4.0 * 4.0 + 15.0 * 15.0 + 6.0 * 6.0 + 24.0 * 24.0 + 16.0 * 16.0 + 3.0 * 3.0 + 54.0 * 54.0 + 25.0 * 25.0 + 67.0 * 67.0 + 8.0 * 8.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{4.0 / length, 15.0 / length, 6.0 / length, 24.0 / length, 16.0 / length, 3.0 / length, 54.0 / length, 25.0 / length, 67.0 / length, 8.0 / length}})));
	}
	
	@Test
	void smallRowVectorToString() {
		String resultStr = "3.0 1.0 2.0";
		assertEquals(resultStr, rowVec.toString());		
	}
	
	@Test
	void largerRowVectorToString() {
		String resultStr = "4.0 15.0 6.0 24.0 16.0 3.0 54.0 25.0 67.0 8.0";
		assertEquals(resultStr, largerRowVec.toString());		
	}
	
	@Test
	void largerColVectorToString() {
		String resultStr = "100.0\n25.5\n-32.0\n3.14\n4.1\n-10.0\n55.0\n1.0\n-100.0\n60.5";
		assertEquals(resultStr, largerColVec.toString());
	}
}
