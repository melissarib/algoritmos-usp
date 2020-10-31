import ij.*;

public class Morpho8 {

	/**
	* Implements "dilation" method for 8-connected pixels of an ImageAccess object.
	* For each pixel, the maximum value of the gray levels of its 3x3 local neighborhood
	* which is 8-connected is found.
	*
	* @param img       	an ImageAccess object
	*/
	static public ImageAccess doDilation(ImageAccess img) {
		int nx = img.getWidth();
		int ny = img.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
		double arr[] = new double[9];
		double max;
		
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			img.getPattern(x, y, arr, ImageAccess.PATTERN_SQUARE_3x3);
			max = arr[0];
			for (int k=1; k<9; k++) {
				if (arr[k] > max) {
					max = arr[k];
				}
			}
			out.putPixel(x, y, max);
		}
		return out;
	}

	static public ImageAccess doErosion(ImageAccess img) {
		// IJ.showMessage("Question 2.1");

    int nx = img.getWidth();
		int ny = img.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
		double arr[] = new double[9];
		double max;
		
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			img.getPattern(x, y, arr, ImageAccess.PATTERN_SQUARE_3x3);
			max = arr[0];
			for (int k=1; k<9; k++) {
				if (arr[k] < max) {
					max = arr[k];
				}
			}
			out.putPixel(x, y, max);
		}
		return out;
	}

	static public ImageAccess doOpen(ImageAccess img) {
		// IJ.showMessage("Question 2.2 - abertura");
    img = doDilation(doErosion(img)); // Erosão seguida de dilatação
		return img;
	}

	static public ImageAccess doClose(ImageAccess img) {
		// IJ.showMessage("Question 2.2 - fechamento");
    img = doErosion(doDilation(img)); // Dilatação seguida de erosão
		return img;
	}

	static public ImageAccess doGradient(ImageAccess img) {
		IJ.showMessage("Question 2.3");
    ImageAccess out = img.duplicate();
    out.subtract(doErosion(img), doDilation(img));
    out.normalizeContrast();
		return out;
	}

	static public ImageAccess doTopHatBright(ImageAccess img) {
		IJ.showMessage("Question 2.4");
		ImageAccess out = img.duplicate();
    out.subtract(img, doOpen(img));
    out.normalizeContrast();
		return out;
	}

	static public ImageAccess doTopHatDark(ImageAccess img) {
		IJ.showMessage("Question 2.4");
		ImageAccess out = img.duplicate();
    out.subtract(img, doClose(img));
    out.normalizeContrast();
		return out;
	}

	static public ImageAccess doMedian(ImageAccess img) {
		IJ.showMessage("Question 2.5");

		int nx = img.getWidth();
		int ny = img.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
		double arr[] = new double[9];
		double max;
		
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			img.getPattern(x, y, arr, ImageAccess.PATTERN_SQUARE_3x3);
			sortArray(arr);
			out.putPixel(x, y, arr[4]);
		}
		return out;
	}

	/**
	* Implements an algorithm for sorting arrays.
	* Result is returned by the same array used as input.
	*
	* @param array       input and output array of the type double
	*/
	static private void sortArray(double array[]) {
		int len = array.length;
		int l, k, lmin;
		double permute, min;
		
		for (k = 0; k < len - 1; k++) {
			min = array[k];
			lmin = k;
			for (l = k + 1; l < len; l++) {
				if (array[l] < min) { 
					min = array[l];
					lmin = l;
				}
			}
			permute = array[lmin];
			array[lmin] = array[k];
			array[k] = permute;
		}
	}

}