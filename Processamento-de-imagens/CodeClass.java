/*
 * Morphology
 *
 */
import ij.*;
import jdk.nashorn.internal.runtime.arrays.IntElements;

import java.lang.Math;

public class CodeClass {
	
	private static final int SQUARE 	= 0;
	private static final int CROSS 	= 1;
	private static final int DISC	 	= 2;
	
	/**
	*/
	static public ImageAccess dilate3(ImageAccess input) {
    IJ.showMessage("Dilate3 implementado");
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double block[][] = new double[3][3];
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			input.getNeighborhood(x, y, block);
			double max = -Double.MAX_VALUE;

      // Percorre a matriz de vizinhança do elemento estruturante em x,y
			for (int k=0; k<3; k++)
			for (int l=0; l<3; l++) {
        // 0: preto e 255: branco
        // Obtem o maior valor de pixel da vizinhanca em x,y
				if (block[k][l] > max) {
					max = block[k][l];
				}
			}
			output.putPixel(x, y, max);
		}
		return output;
	}

	/**
	*/
	static public ImageAccess erode3(ImageAccess input) {
    IJ.showMessage("Erode3 implementado");
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double block[][] = new double[3][3];

    // percorrendo os pixels da imagem
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			input.getNeighborhood(x, y, block);
			double max = Double.MAX_VALUE;

      // Percorre a matriz de vizinhança do elemento estruturante em x,y
			for (int k=0; k<3; k++)
			for (int l=0; l<3; l++) {
        // 0: preto e 255: branco
        // Obtem o menor valor de pixel da vizinhanca em x,y
				if (block[k][l] < max) {
					max = block[k][l];
				}
			}
			output.putPixel(x, y, max);
		}
		return output;
	}

	/**
	*/
	static private boolean[][] square(int n) {
		boolean element[][] = new boolean[n][n];
    for (int k=0; k<n; k++)
		for (int l=0; l<n; l++){
      element[k][l] = true;
    }
		return element;
	}

	/**
	*/
	static private boolean[][] disc(int n) {
		boolean element[][] = new boolean[n][n];
		int radius = (int)(n/2.0 -0.5) + 1;
		int xc = n/2+1;
		int yc = n/2+1;
    int a, b; // diferencas do eixo y e x, respectivamente
    double dist;

    for (int k=1; k<n+1; k++){
      b = k-xc;
      b = b*b; // ao quadrado
      for (int l=1; l<n+1; l++){
        a = l-yc;
        a = a*a; // ao quadrado
        // Calculando a distância
        dist = Math.sqrt(a + b);
        if((int)dist < radius){
          // -1 pq o vetor vai de 0 a n-1
          // e o for comecou em 1
          element[k-1][l-1] = true;
        }
        else{ // Se tiver fora do raio
          element[k-1][l-1] = false;
        }
      }
    }
		return element;
	}

	/**
	*/
	static private boolean[][] cross(int n) {
		boolean element[][] = new boolean[n][n];
		for (int k=0; k<n; k++)
		for (int l=0; l<n; l++)
			element[k][l] = false;
		for (int k=0; k<n; k++)
			element[k][n/2] = true;
		for (int l=0; l<n; l++)
			element[n/2][l] = true;
		return element;
	}
	
	/**
	*/
	static public ImageAccess erode(ImageAccess input, int type, int size) {
    IJ.showMessage("Erode implementado");
		int nx = input.getWidth();
		int ny = input.getHeight();
    int elementInt;
		
		boolean element[][] = null;
		if (type == SQUARE)
			element = square(size);
		else if (type == CROSS)
			element = cross(size);
		else if (type == DISC)
			element = disc(size);
		
		ImageAccess output = new ImageAccess(nx, ny);
		double block[][] = new double[size][size];

    // percorrendo os pixels da imagem
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			input.getNeighborhood(x, y, block);
			double min = Double.MAX_VALUE;

      // Percorre a matriz de vizinhança do elemento estruturante em x,y
			for (int k=0; k<size; k++)
		  for (int l=0; l<size; l++) {
        elementInt = element[k][l] ? 1 : -1; // transforma o boolean em inteiro
        block[k][l] = block[k][l] * elementInt;

        // 0: preto e 255: branco
        // Obtem o menor valor de pixel da vizinhanca em x,y
				if (block[k][l] < min && block[k][l] > -1) {
					min = block[k][l];
				}
			}
			output.putPixel(x, y, min);
		}
		return output;
	}

	/**
	*/
	static public ImageAccess dilate(ImageAccess input, int type, int size) {
		IJ.showMessage("Dilate implementado");
		int nx = input.getWidth();
		int ny = input.getHeight();
    int elementInt;
		
		boolean element[][] = null;
		if (type == SQUARE)
			element = square(size);
		else if (type == CROSS)
			element = cross(size);
		else if (type == DISC)
			element = disc(size);
		
		ImageAccess output = new ImageAccess(nx, ny);
		double block[][] = new double[size][size];

    // percorrendo os pixels da imagem
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			input.getNeighborhood(x, y, block);
			double max = -Double.MAX_VALUE;

      // Percorre a matriz de vizinhança do elemento estruturante em x,y
			for (int k=0; k<size; k++)
		  for (int l=0; l<size; l++){
        elementInt = element[k][l] ? 1 : 0; // transforma o boolean em inteiro
        block[k][l] = block[k][l] * elementInt;
        // 0: preto e 255: branco
        // Obtem o maior valor de pixel da vizinhanca em x,y
				if (block[k][l] > max) {
					max = block[k][l];
				}
			}
			output.putPixel(x, y, max);
		}
		return output;
	}

	/**
	*/
	static public ImageAccess open(ImageAccess input, int type, int size) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
    output = erode(input, type, size);
    output = dilate(output, type, size);
		return output;	
	}

	/**
	*/
	static public ImageAccess close(ImageAccess input, int type, int size) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		
    output = dilate(input, type, size);
    output = erode(output, type, size);
		return output;	
	}

	/**
	*/
	static public ImageAccess gradient(ImageAccess input, int type, int size) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		output.subtract(erode(input, type, size), dilate(input, type, size));
    output.normalizeContrast();
		return output;	
	}
  

	/**
	*/
  //static public ImageAccess gradient(ImageAccess input, int type, int size) {
	static public ImageAccess topHatBright(ImageAccess input, int type, int size) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		output.subtract(input, open(input, type, size));
    output.normalizeContrast();
		return output;	
	}

	/**
	*/
  //static public ImageAccess gradient(ImageAccess input, int type, int size) {
	static public ImageAccess topHatDark(ImageAccess input, int type, int size) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		output.subtract(input, close(input, type, size));
    output.normalizeContrast();
		return output;	
	}

	/**
	*/
	static public ImageAccess median(ImageAccess input, int type, int size) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		// Add your code here
		return output;	
	}

	/**
	* Implements an algorithm for sorting arrays.
	* Result is returned by the same array used as input.
	*
	* @param array	input and output array of the type double
	*/
	static private void sortArray(double array[]) {
		int len = array.length;
		int l, k, lmin;
		double permute, min;

		for (k = 0; k < len - 1; k++) {
			min = array[k];
			lmin = k;
			for (l = k + 1; l < len; l++) {
				if (array[l] < min){ 
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
