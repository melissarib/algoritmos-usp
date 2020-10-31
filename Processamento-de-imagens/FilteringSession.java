import ij.*;
import ij.plugin.filter.Convolver;

public class FilteringSession {

	/*******************************************************************************
	 *
	 * E D G E   D E T E C T O R   S E C T I O N
	 *
	 ******************************************************************************/

	/**
	 * Detects the vertical edges inside an ImageAccess object.
	 * This is the non-separable version of the edge detector.
	 * The kernel of the filter has the following form:
	 *
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *
	 * Mirror border conditions are applied.
	 */
	static public ImageAccess detectEdgeVertical_NonSeparable(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double arr[][] = new double[3][3]; // matrix of 3x3 for the obtains the neighborhood
		double pixel;
		ImageAccess out = new ImageAccess(nx, ny);
		for (int x = 0; x < nx; x++) {
			for (int y = 0; y < ny; y++) {
				input.getNeighborhood(x, y, arr); // put neighborhood in the arr
				// pixel will be the sum of row = 2 and the subtract the row = 0
				pixel = arr[2][0]+arr[2][1]+arr[2][2]-arr[0][0]-arr[0][1]-arr[0][2];
				pixel = pixel / 6.0; 
				out.putPixel(x, y, pixel);
			}
		}
		return out;
	}

	/**
	 * Detects the vertical edges inside an ImageAccess object.
	 * This is the separable version of the edge detector.
	 * The kernel of the filter applied to the rows has the following form:
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *
	 * The kernel of the filter applied to the columns has the following 
	 * form:
	 *     -------
	 *     |  1  |
	 *     -------
	 *     |  1  |
	 *     -------
	 *     |  1  |
	 *     -------
	 *
	 * Mirror border conditions are applied.
	 */
	static public ImageAccess detectEdgeVertical_Separable(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);

		// loop the rows of image
		double rowin[]  = new double[nx];
		double rowout[] = new double[nx];
		for (int y = 0; y < ny; y++) {
			input.getRow(y, rowin); // the rowin is the row in y
			doDifference3(rowin, rowout); // process the row
			out.putRow(y, rowout); // put on the image the new row
		}
		
		// loop the columns of image
		double colin[]  = new double[ny];
		double colout[] = new double[ny];
		for (int x = 0; x < nx; x++) {
			out.getColumn(x, colin); // the colin is the column in x
			doAverage3(colin, colout); // process the column
			out.putColumn(x, colout); // put on the image the new column
		}
		// to detection vertical edges we applied doDifference3 in the rows and the doAverage3 in the columns
		return out;
	}


	/**
	 * Detects the horizontal edges inside an ImageAccess object.
	 * This is the non-separable version of the edge detector.
	 * The kernel of the filter has the following form:
	 *
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 *
	 * Mirror border conditions are applied.
	 */
	static public ImageAccess detectEdgeHorizontal_NonSeparable(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double arr[][] = new double[3][3]; // matrix of 3x3 for the obtains the neighborhood
		double pixel;
		ImageAccess out = new ImageAccess(nx, ny);
		for (int x = 0; x < nx; x++) {
			for (int y = 0; y < ny; y++) {
				input.getNeighborhood(x, y, arr); // put neighborhood in the arr
				// pixel will be the sum of column = 2 and the subtract the column = 0
				pixel = arr[0][2]+arr[1][2]+arr[2][2]-arr[0][0]-arr[1][0]-arr[2][0];
				pixel = pixel / 6.0; 
				out.putPixel(x, y, pixel);
			}
		}
		return out;
	}

	static public ImageAccess detectEdgeHorizontal_Separable(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);

		// loop the rows of image
		double rowin[]  = new double[nx];
		double rowout[] = new double[nx];
		for (int y = 0; y < ny; y++) {
			input.getRow(y, rowin); // the rowin is the row in y
			doAverage3(rowin, rowout); // process the row
			out.putRow(y, rowout); // put on the image the new row
		}
		
		// loop the columns of image
		double colin[]  = new double[ny];
		double colout[] = new double[ny];
		for (int x = 0; x < nx; x++) {
			out.getColumn(x, colin); // the colin is the column in x
			doDifference3(colin, colout); // process the column
			out.putColumn(x, colout); // put on the image the new column
		}
		// to detection horizontal edges we applied doAverage3 in the rows and the doDifference3 in the columns
		return out;
	}

	/**
	 * Implements an one-dimensional average filter of length 3.
	 * The filtered value of a pixel is the averaged value of
	 * its local neighborhood of length 3.
	 * Mirror border conditions are applied.
	 */
	static private void doAverage3(double vin[], double vout[]) {
		int n = vin.length; // n is the size of vector

		vout[0] = (vin[0] + 2.0 * vin[1]) / 3.0; 

		for (int k = 1; k < n-1; k++) {
			// the pixel vout[k] will be the median of your previous, actual and next pixel
			vout[k] = (vin[k-1] + vin[k] + vin[k+1]) / 3.0;
		}

		vout[n-1] = (vin[n-1] + 2.0 * vin[n-2]) / 3.0;
	}

	/**
	 * Implements an one-dimensional centered difference filter of 
	 * length 3. The filtered value of a pixel is the difference of 
	 * its two neighborhing values.
	 * Mirror border conditions are applied.
	 * 
	 * The function follows this kernel:
	 * The kernel of the filter applied to the rows has the following form:
	 *     -------------------
	 *     | -1  |  0  |  1  |
	 *     -------------------
	 */
	static private void doDifference3(double vin[], double vout[]) {
		int n = vin.length; // n is the size of vector
		vout[0] = 0.0; // the first value is zero

		for (int k = 1; k < n-1; k++) {
			// the pixel vout[k] will be the median of your previous and next pixel
			vout[k] = (vin[k+1] - vin[k-1]) / 2.0;
		}

		vout[n-1] = 0.0; // the last value is zero
	}

	/*******************************************************************************
	 *
	 * M O V I N G   A V E R A G E   5 * 5   S E C T I O N
	 *
	 ******************************************************************************/

	static public ImageAccess doMovingAverage5_NonSeparable(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double arr[][] = new double[5][5]; // matrix of 5x5 for the obtains the neighborhood
		double pixel = 0.0;
		ImageAccess out = new ImageAccess(nx, ny);

		for (int x = 0; x < nx; x++) {
			for (int y = 0; y < ny; y++) {
				input.getNeighborhood(x, y, arr); // put neighborhood in the arr

				// pixel will be the sum of neighborhood
        pixel = arr[0][0] + arr[0][1] + arr[0][2] + arr[0][3] + arr[0][4] + 
                arr[1][0] + arr[1][1] + arr[1][2] + arr[1][3] + arr[1][4] + 
                arr[2][0] + arr[2][1] + arr[2][2] + arr[2][3] + arr[2][4] + 
                arr[3][0] + arr[3][1] + arr[3][2] + arr[3][3] + arr[3][4] + 
                arr[4][0] + arr[4][1] + arr[4][2] + arr[4][3] + arr[4][4];

				pixel = pixel / 25.0; // arr's median of matrix elements 
				out.putPixel(x, y, pixel);
			}
		}
		return out;
	}

	static public ImageAccess doMovingAverage5_Separable(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);

		// loop the rows of image
		double rowin[]  = new double[nx];
		double rowout[] = new double[nx];
		for (int y = 0; y < ny; y++) {
			input.getRow(y, rowin); // the rowin is the row in y
			doAverage5(rowin, rowout); // process the row
			out.putRow(y, rowout); // put on the image the new row
		}
		
		// loop the columns of image
		double colin[]  = new double[ny];
		double colout[] = new double[ny];
		for (int x = 0; x < nx; x++) {
			out.getColumn(x, colin); // the colin is the column in x
			doAverage5(colin, colout); // process the column
			out.putColumn(x, colout); // put on the image the new column
		}
    return out;
	}

  /**
	 * Implements an one-dimensional average filter of length 5.
	 * The filtered value of a pixel is the averaged value of
	 * its local neighborhood of length 5.
	 * Mirror border conditions are applied.
	 */
	static private void doAverage5(double vin[], double vout[]) {
		int n = vin.length; // n is the size of vector

    //  treatment of the frontiers with mirror border conditions.
		vout[0] = (2*vin[0] + 2*vin[1] + vin[2]) / 5.0; 
    vout[1] = (2*vin[0] + vin[1] + vin[2] + vin[3]) / 5.0; 

		for (int k = 2; k < n-2; k++) {
			// the pixel vout[k] will be the median of your previous, actual and next pixel
			vout[k] = (vin[k-2] + vin[k-1] + vin[k] + vin[k+1] + vin[k+2]) / 5.0;
		}

    //  treatment of the frontiers
    //  n-1 is the last pixel
    vout[n-2] = (2*vin[n-1] + vin[n-2] + vin[n-3] + vin[n-4]) / 5.0; 
    vout[n-1] = (2*vin[n-1] + 2*vin[n-2] + vin[n-3]) / 5.0; 
	}

	static public ImageAccess doMovingAverage5_Recursive(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
    
    // loop the rows of image
		double rowin[]  = new double[nx];
		double rowout[] = new double[nx];
		for (int y = 0; y < ny; y++) {
			input.getRow(y, rowin); // the rowin is the row in y
			doAverage5_recursive(rowin, rowout); // process the row
			out.putRow(y, rowout); // put on the image the new row
		}
		
		// loop the columns of image
		double colin[]  = new double[ny];
		double colout[] = new double[ny];
		for (int x = 0; x < nx; x++) {
			out.getColumn(x, colin); // the colin is the column in x
			doAverage5_recursive(colin, colout); // process the column
			out.putColumn(x, colout); // put on the image the new column
		}
    return out;
	}

  /**
	 * Implements an one-dimensional average filter of length 5 WITH RECURSION.
   * Mirror border conditions are applied.
   * vin is the row vector or column vector
   * vout is the vector with the means
	 */
  static private void doAverage5_recursive(double vin[], double vout[]) {
    int n = vin.length; // n is the size of vector

    //  treatment of the frontiers with mirror border conditions.
		vout[0] = (2*vin[0] + 2*vin[1] + vin[2]) / 5.0; 
    vout[1] = (2*vin[0] + vin[1] + vin[2] + vin[3]) / 5.0; 

		for (int k = 2; k < n-2; k++) {
			// the pixel vout[k] will be the median of your two previous, actual and two next pixel
      vout[k] = getMeanRecursive(
        vin, // vector with values for median
        k+2, // is the init of vector with 5 length
        k-2, // is the end of vector with 5 length
        5 // is the max weight to the mean (in this case, is 5 because the vector length is equal to 5)
      );
		}

    //  treatment of the frontiers with mirror border conditions (n-1 is the last pixel)
    vout[n-2] = (2*vin[n-1] + vin[n-2] + vin[n-3] + vin[n-4]) / 5.0; 
    vout[n-1] = (2*vin[n-1] + 2*vin[n-2] + vin[n-3]) / 5.0; 
	}

  /**
	 * Implements the mean with recursion.
   * vet is the vector with all elements of mean (in this case, is a vector of length equal to 5)
   * N indicates the limit to end the mean of column vector or row vector
   * K indicates the limit to start the mean of column vector or row vector
   * weight is used to pondering the mean for the  recursion
	 */
	static private double getMeanRecursive(double vet[], int N, int K, int weight) {
    if (N == K+1) {
      return (vet[N-1]); 
    }
    else{
      // Sum of first N-1 elements = (Mean of N-1 elements)*(weight-1)
      // Mean of N elements = (Sum of first N-1 elements + N-th elements) / (weight)
      return ((double)((getMeanRecursive(vet, N-1, K, weight-1) * (weight-1) + vet[N-1]) / weight)); 
    }
	}

	/*******************************************************************************
	 *
	 * S O B E L
	 *
	 ******************************************************************************/
  /*
   * The mask Gx is:            The separable mode is:
   *  ------------------- =>  -------
	 *  | -1  |  0  |  1  | =>  |  1  |
	 *  ------------------- =>  -------     -------------------
	 *  | -2  |  0  |  2  | =>  |  2  | and | -1  |  0  |  1  |
	 *  ------------------- =>  -------     -------------------
	 *  | -1  |  0  |  1  | =>  |  1  |
	 *  ------------------- =>  -------
   *                          (mask_v)          (maks_h)
   *
   * The mask Gy is:            The separable mode is:
   *  ------------------- =>  -------
	 *  | -1  | -2  | -1  | =>  | -1  |
	 *  ------------------- =>  -------     -------------------
	 *  |  0  |  0  |  0  | =>  |  0  | and |  1  |  2  |  1  |
	 *  ------------------- =>  -------     -------------------
	 *  |  1  |  2  |  1  | =>  |  1  |
	 *  ------------------- =>  -------
   *                          (mask_v)          (maks_h)
   * 
   * The Sobel edge detection is => sqrt( [Gx(input)]^2 + [Gy(input)]^2 )
  */
	static public ImageAccess doSobel(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();

    // Auxliar images 
    ImageAccess auxGx = new ImageAccess(nx, ny);
    ImageAccess auxGy = new ImageAccess(nx, ny);
    
    // Auxiliar vectors
    double[] row_i = new double[nx];
    double[] col_j = new double[nx];
    double[] aux_i = new double[nx];
    double[] aux_j = new double[ny];
    double[] mask = { 1.0, 2.0, 1.0 };

    for (int i = 0; i < ny; i++) { // loop in the height
        input.getRow(i, row_i); // get the row i

        differenceFiltering3(row_i, aux_i);
        auxGx.putRow(i, aux_i);

        sumFiltering3(row_i, mask, aux_i);
        auxGy.putRow(i, aux_i);
    }
    auxGx.show("Gx_Result1");
    auxGy.show("Gy_Result1"); // show the image smooth
    
    for (int j = 0; j < nx; j++) { // loop in the width
        auxGx.getColumn(j, col_j); // get the column j
        sumFiltering3(col_j, mask, aux_j);
        auxGx.putColumn(j, aux_j);

        auxGy.getColumn(j, col_j); // get the column j
        differenceFiltering3(col_j, aux_j);
        auxGy.putColumn(j, aux_j);
    }
    auxGx.show("Gx_Result2");
    auxGy.show("Gy_Result2");

    auxGx.pow(2.0);
    auxGy.pow(2.0);
    auxGx.show("Gx_Result3");
    auxGy.show("Gy_Result3");

    auxGx.add(auxGx, auxGy);
    auxGx.show("Result_add");

    auxGx.sqrt();
    auxGx.show("Resultado Final");

    ImageAccess out = auxGx.duplicate();
    return(out);
	}

  static public void sumFiltering3(double[] vin, double[] mask, double[] vout) {
		int length = vin.length;

    //  treatment of the frontiers with mirror border conditions.
    vout[0] = 2.0 * vin[0] + vin[1];

    for (int i = 1; i < length - 1; ++i) {
        vout[i] = (mask[0]*vin[i - 1]) + (mask[1]*vin[i]) + (mask[2]*vin[i + 1]);
    }

    //  treatment of the frontiers with mirror border conditions.
    vout[length - 1] = 2.0 * vin[length - 2] + vin[length - 1];
	} 

  static public void differenceFiltering3(double[] vin, double[] vout) {
		int length = vin.length;

    //  treatment of the frontiers
    vout[0] = 0.0;

    for (int i = 1; i < length - 1; ++i) {
        vout[i] = vin[i + 1] - vin[i - 1];
    }

    //  treatment of the frontiers
    vout[length - 1] = 0.0;
	}

	/*******************************************************************************
	 *
	 * M O V I N G   A V E R A G E   L * L   S E C T I O N
	 *
	 ******************************************************************************/

	static public ImageAccess doMovingAverageL_Recursive(ImageAccess input, int length) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
    
    // loop the rows of image
		double rowin[]  = new double[nx];
		double rowout[] = new double[nx];
		for (int y = 0; y < ny; y++) {
			input.getRow(y, rowin); // the rowin is the row in y
			doAverageL_recursive(rowin, rowout, length); // process the row
			out.putRow(y, rowout); // put on the image the new row
		}
		
		// loop the columns of image
		double colin[]  = new double[ny];
		double colout[] = new double[ny];
		for (int x = 0; x < nx; x++) {
			out.getColumn(x, colin); // the colin is the column in x
			doAverageL_recursive(colin, colout, length); // process the column
			out.putColumn(x, colout); // put on the image the new column
		}
    return out;
	}

  /**
	 * Implements an one-dimensional average filter of length L WITH RECURSION.
   * Mirror border conditions ARE NOT applied.
   * vin is the row vector or column vector
   * vout is the vector with the means
	 */
  static private void doAverageL_recursive(double vin[], double vout[], int length) {
    int n = vin.length; // n is the size of vector
    int mid = (length-1)/2; // initial middle element of window L 

		for (int k = mid; k < n-mid; k++) {
			// the pixel vout[k] will be the median of your two previous, actual and two next pixel
      vout[k] = getMeanRecursive(
        vin, // vector with values for median
        k+mid, // is the init of vector with 5 length
        k-mid, // is the end of vector with 5 length
        length // is the max weight to the mean (in this case, is 5 because the vector length is equal to 5)
      );
		}

	}

  /*******************************************************************************
	 *
	 * FUNCTIONS THAT NOT USED
	 *
	 ******************************************************************************/

  static private void getFirstsMeansOfVoutWithMirrorBorder(double vin[], double vout[], int length) {
    // int n = vin.length; // n is the size of vector

    // for treatment of the frontiers with mirror border conditions, we have:
    // The vin has length equal to M 
    // the boundaries is represents with K < u < (M-K-1), where u is the middle element of vector that not exceed the border with the window L
		int mid = (length-1)/2; // initial middle element of window L 
    double sum = 0.0, mean;
    
    for (int i = 0; i <= mid; i++) { // find the mean for the firsts mid elements of vector vout
      mean = 0.0; // initially the mean is zero
      sum = 0.0; // initially the sum is zero
      for(int k = mid-1-i; k >= 0; k--){  // sum the mirrored values 
        sum += vin[k]*2;
      }

      for(int l = mid-i; l <= mid; l++){  // sum the non-mirrored values 
        sum += vin[l];
      }
      mean = (double)(sum / length);
      vout[i] = mean;
    }
  }

  /* 
  * Sum the elements of matrix.
  * MAYBE WORKS, HAVE TO TEST
  */ 
  // static public void sumElementsMatrix(double[][] matIn, double sumOfElements) {
	// 	int nrows = matIn.length, ncols = matIn[0].length;
  //   double sum = 0.0;
  //   for (int i = 0; i < nrows; i++) {
	// 		for (int j = 0; j < ncols; j++) {
  //       sum = sum + matIn[i][j];
  //     }
  //   }
  //   sumOfElements = sum;
	// }


}
