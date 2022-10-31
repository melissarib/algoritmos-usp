import ij.*;

public class Radon
{

/**
* Apply a Radon transform to an image with a specified number of angles.
*
* @param input		an square input image [size, size]
* @param nbAngles	number of angles
* @return			a sinogram [nbAngles, size]
*/
public static ImageAccess transformRadon(ImageAccess input, int nbAngles)
{
	int size = input.getWidth();
	
	ImageAccess sino = new ImageAccess(nbAngles, size);
	double cos, sin;
	double center = ((double)size-1.0)/2.0;
	double radius2 = center * center;
	double stepAngle = Math.PI/(double)nbAngles;
	double mc, nc, x, y, angle, v;
	double colsino[] = new double[size];
	
	double mean = input.getMean();
	input.subtract(mean);
	
	double[][] array = input.getArrayPixels();
	
	for (int k=0; k<nbAngles; k++) {
		angle = (double)k * stepAngle - Math.PI/2;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		for (int m=0; m<size; m++) {
			colsino[m] = 0.0;
			for (int n=0; n<size; n++) {
				mc = (double)m - center;
				nc = (double)n - center;
				if (mc*mc + nc*nc < radius2) {
					x = center + mc * cos - nc * sin;
					y = center + mc * sin + nc * cos;
					v = getInterpolatedPixel2D(array, x, y);
					colsino[m] = colsino[m] + v;
				}
			}
		}
		sino.putColumn(k, colsino);
	}
	return sino;
}

/**
* Return the bilinear interpolated value in the point (x,y).
*
* @param array	2D pixel array
* @param x		position in x axis
* @param y		position in y axis
* @return		the interpolated value
*/
private static double getInterpolatedPixel2D(double array[][], double x, double y)
{
	int i = floor(x);
	int j = floor(y);
	double dx = x - (double)i;
	double dy = y - (double)j;
	double v00 = array[i][j];
	double v10 = array[i+1][j];
	double v01 = array[i][j+1];
	double v11 = array[i+1][j+1];
	double v = (dx*(v11*dy-v10*(dy-1.0)) - (dx-1.0)*(v01*dy-v00*(dy-1.0)));
	return v;
}

/**
* Apply a Ram-Lak filter to the sinogram.
*
* @param sinogram		an ImageAccess object containing a sinogram
* @return				the filtered version of the sinogram
*/
public static ImageAccess applyRamLakFilter(ImageAccess sinogram)
{
	int nbAngle = sinogram.getWidth();
	int size    = sinogram.getHeight();
	double[] real = new double[size];
	double[] imaginary = new double[size];
	double[] filter = generateRamLak(size);
	ImageAccess output = new ImageAccess(nbAngle, size);
	
	RadonFFT1D fft = new RadonFFT1D(size);
	
	for (int k=0; k<nbAngle; k++) {
		sinogram.getColumn(k, real);
		for(int l=0; l<size; l++) {				
			imaginary[l] = 0.0;
		}
		fft.transform(real, imaginary);
		for(int l=0; l<size; l++) {				
			real[l]      = real[l] * filter[l];
			imaginary[l] = imaginary[l] * filter[l];
		}
		fft.inverse(real, imaginary);
		output.putColumn(k, real);
	}
	return output;
}

/**
* Return the Ram-Lak filter of size [size].
*/
public static double[] generateRamLak(int size)
{
	double[] filter = new double[size];

	// Add your code here
	
	return filter;	
} 


/**
* Apply a Cosine filter to the sinogram.
*
* @param sinogram		an ImageAccess object containing a sinogram
* @return				the filtered version of the sinogram
*/
public static ImageAccess applyCosineFilter(ImageAccess sinogram)
{
	int nbAngle = sinogram.getWidth();
	int size    = sinogram.getHeight();
	ImageAccess output = new ImageAccess(nbAngle, size);
	
	// Add your code here

	return output;
}

/**
* Return the Cosine filter of size [size].
*/
public static double[] generateCosine(int size)
{
	double[] filter= new double[size];
	
	// Add your code here
	
	return filter;	
} 

/**
* Apply a laplacian filter to the sinogram.
*
* @param sinogram		an ImageAccess object containing a sinogram
* @return				the filtered version of the sinogram
*/
public static ImageAccess applyLaplacianFilter(ImageAccess sinogram)
{
	int nbAngle = sinogram.getWidth();
	int size    = sinogram.getHeight();
	ImageAccess output = new ImageAccess(nbAngle, size);
	
	// Add your code here
	
	return output;
}


/**
* Make a BackProjection.
*
* @param sinogram	a sinogram image [nbAngles, size]
* @return			a reconstructed image
*/
public static ImageAccess inverseRadon(ImageAccess sinogram)
{
	int	nbAngles = sinogram.getWidth();
	int	size     = sinogram.getHeight();
	double b[][] = new double[size][size];
	
	double real[] = new double[size];
    double d = (double)(size-1) / 2.0;
    double d1 = d * d;
    double d2 = Math.PI / (double)nbAngles;

	for (int i=0; i<size; i++) {
		for (int j=0; j<size; j++) {
			b[i][j] = 0.0D;
		}
	}
    
	for(int w = 0; w < nbAngles; w++){
        double d6 = (double)w * d2-(Math.PI/2);
        double d7 = Math.cos(d6);
        double d8 = Math.sin(d6);
        sinogram.getColumn(w, real);
        for(int j1 = 0; j1 < size; j1++){
            for(int k1 = 0; k1 < size; k1++){
                double d4 = (double)k1-d;
                double d5 = (double)j1-d;
                if(d4*d4 + d5*d5 < d1){
                    double d3 = d + d4*d7 + d5*d8;
                    double d9 = getInterpolatedPixel1D(real, d3);
                    b[k1][j1] = b[k1][j1] + d9;
                }
            }
        }
    }

    double d10 = Math.PI / (double)size;

    ImageAccess ReconstudedImage = new ImageAccess(b);

    ReconstudedImage.multiply(d10);

    return ReconstudedImage;
	
}


/**
* Return the linear interpolated value in the position (t).
*
* @param vector	1D array
* @param t		position
* @return		the interpolated value
*/
private static double getInterpolatedPixel1D(double vector[], double t)
{	
	double v = 0.0;
	
	// Add you code here
	
	return v;
}

/**
* Returns the largest integer value that is not greater 
* than the argument and is equal to a mathematical integer.
* Faster alternative to the java routine Math.floor(). 
*/
private static int floor(final double d) 
{
	if (d >= 0.0) 
		return (int)d;
	else {
		final int iAdd = (int)d - 1;
		return ((int)(d - (double)iAdd) + iAdd);
	}
}

/**
* Returns the smallest integer value that is not less 
* than the argument and is equal to a mathematical integer .
* Faster alternative to the java routine Math.ceil(). 
*/
private static int ceil(final double d) 
{
	return -floor(-d);
}

/**
* Returns the closest integer to the argument .
* Faster alternative to the java routine Math.round(). 
*/
private static int round(final double d) 
{
	return floor(d + 0.5d);
}

}// end of class
