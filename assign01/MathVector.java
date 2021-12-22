package assign01;

/**
 * This class represents a simple row or column vector of numbers. In a row
 * vector, the numbers are written horizontally (i.e., along the columns). In a
 * column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Cobi Toeun
 * @version January 20, 2021
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data, transpose, sum, normalized;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	private int length;

	/**
	 * Creates a new row or column vector. For a row vector, the input array is
	 * expected to have 1 row and a positive number of columns, and this number of
	 * columns represents the vector's length. For a column vector, the input array
	 * is expected to have 1 column and a positive number of rows, and this number
	 * of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the
	 *                                  input 2D array is not compatible with a row
	 *                                  or column vector
	 */
	public MathVector(double[][] data) {
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if (data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if (data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		} else if (data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		} else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if (this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality
	 * is defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		if (!(other instanceof MathVector))
			return false;

		// Create new MathVector of other vector
		MathVector otherVec = (MathVector) other;

		// Check if both this data and other data have same numbers in same positions
		if (this.isRowVector == otherVec.isRowVector && this.vectorSize == otherVec.vectorSize) {
			int rowLength = data.length;
			int colLength = data[0].length;
			
			// Check if vector does not have equal columns and rows
			for (int row = 0; row < rowLength; row++) {
				for (int col = 0; col < colLength; col++) {
					if (this.data[row][col] != otherVec.data[row][col])
						// Return false if this data vector and other vector don't contain same numbers in same position
						return false;
				}
			}
			// Return true if this data vector and other vector contain same numbers in same positions
			return true;
		}
		
		// Return false if boolean argument is false for either
		return false;
		
		
	}

	/**
	 * Generates a returns a new vector that is the transposed version of this
	 * vector.
	 */
	public MathVector transpose() {
		// Initialize and set transpose vector and row and col lengths
		transpose = new double[this.data.length][data[0].length];
		double rowLength = data.length;
		double colLength = data[0].length;

		if (this.isRowVector) {
			transpose = new double[this.vectorSize][1];
		} 
		else {
			transpose = new double[1][this.vectorSize];
		}

		// Loop over vector and swap positions/transpose vector
		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				transpose[col][row] = this.data[row][col];
			}
		}
		
		// Create new transpose vector
		MathVector newTransposeVector = new MathVector(transpose);
		
		return newTransposeVector;
	}

	/**
	 * Generates and returns a new vector representing the sum of this vector and
	 * another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		// Throw argument if vectors are not equal in length, size, and type
		if (this.isRowVector != other.isRowVector || this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Vectors aren't same type, length, or size!");
		}
		
		// Initialize and set sum vector
		sum = new double[this.data.length][data[0].length];
		double rowLength = data.length;
		double colLength = data[0].length;

		for (int col = 0; col < rowLength; col++) {
			for (int row = 0; row < colLength; row++) {
				sum[col][row] = (this.data[col][row] + other.data[col][row]);
			}
		}
		
		// Create new add vector
		MathVector newSumVector = new MathVector(sum);
		
		return newSumVector;
	}

	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the
	 *              dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public double dotProduct(MathVector other) {
		// Throw argument if vectors are not equal in length, size, and type
		if (this.isRowVector != other.isRowVector || this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Vectors don't equal in length or size");
		} 
		
		// Initialize product variable and get row and col length
		double product = 0.0;
		double rowLength = data.length;
		double colLength = data[0].length;

		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				product += (this.data[row][col] * other.data[row][col]);
			}
		}

		return product;
	}

	/**
	 * Computes and returns this vector's magnitude (also known as a vector's
	 * length) .
	 */
	public double magnitude() {
		// Initialize magnitude
		double magnitude = 0.0;
		double rowLength = data.length;
		double colLength = data[0].length;

		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				// Multiply vector index by itself
				magnitude += Math.pow(data[row][col], 2);
			}
		}
		
		magnitude = Math.sqrt(magnitude);
		
		return magnitude;
	}

	/**
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		// Call magnitude method
		double magnitude = magnitude();
		
		// Initialize normalized vector and get row and col length
		normalized = new double[this.data.length][this.data[0].length];
		double rowLength = data.length;
		double colLength = data[0].length;

		// Calculate the normalized values of the data
		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				// Get data and divide by magnitude to get normalized vector
				normalized[row][col] = (this.data[row][col] / magnitude);
			}
		}

		// Create new normalized vector
		MathVector newNormalizeVector = new MathVector(normalized);

		return newNormalizeVector;
	}

	/**
	 * Generates and returns a textual representation of this vector. For example,
	 * "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and "1.0 2.0 3.0
	 * 4.0 5.0" for a sample column vector of length 5. In both cases, notice the
	 * lack of a newline or space after the last number.
	 */
	public String toString() {
		// Initialize and set empty string
		String vectorToString = "";
		String space = " ";
		String newLine = "\n";

		// Check if data is column vector
		if (!isRowVector) {
			for (int col = 0; col < vectorSize - 1; col++) {
				vectorToString += this.data[col][0] + newLine;
			}
			vectorToString += this.data[this.vectorSize - 1][0];
		}
		else {
			for (int row = 0; row < vectorSize - 1; row++) {
				vectorToString += this.data[0][row] + space;
			}
			vectorToString += this.data[0][this.vectorSize - 1];
		}
		
		return vectorToString;
	}
}