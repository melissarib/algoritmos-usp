
/**
	* Universidade de São Paulo
	* Faculdade de Filosofia, Ciências e Letras de Ribeirão Preto
	* Departamento de Computação e Matemática 
	* Disciplina de Processamento de Imagens Médicas
	* Discente: Melissa Augusto Ribeiro 

**/

public class PointwiseTransform extends Object {

	// Questao 2.1 Inversão de Contraste

	static public ImageAccess inverse(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double value = 0.0;
		for (int x=0; x<nx; x++)
		for (int y=0; y<ny; y++) {
			value = input.getPixel(x, y);
			value = 255 - value;
			output.putPixel(x, y, value);
		}
		return output;	
	}

	
	// Questão 2.2 Esticando e normalizando contraste
	
	static public ImageAccess rescale(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double max = input.getMaximum();
		double min = input.getMinimum();
		ImageAccess output = new ImageAccess(nx, ny);
		double pixel = 0.0;
        double alpha = 255/(max-min);
        double beta = min;
        
        for(int i=0; i<nx; i++){
            for(int j=0; j<nx; j++){
                pixel = input.getPixel(i,j);
                pixel = alpha*(pixel-beta);
                output.putPixel(i, j, pixel);
            }
        }
		return output;	
	}

	
	// Questao 2.3 Saturação de uma imagem médica

	static public ImageAccess saturate(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double pixel = 0.0;
        
        for(int i=0; i<nx; i++){
            for(int j=0; j<ny; j++){
                pixel = input.getPixel(i,j);
                if(pixel > 10000)
                    pixel = 10000;
                
                output.putPixel(i, j, pixel);
            }
        }
        rescale(output);
		return output;
	}
	
	
	// Questao 4.1 Projeção de máxima intensidade (z-stack maximum)

	static public ImageAccess zprojectMaximum(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
		
        double pixel = 0.0;
        double auxiliar = 0.0;
        
        for(int i=0; i < nx; i++){
            for(int j=0; j < ny; j++){
                pixel = 0.0;
                for(int k=0; k < nz; k++){
                    auxiliar = zstack[k].getPixel(i,j);
                    if(auxiliar >= pixel)
                        pixel = auxiliar;
                }
                output.putPixel (i, j, pixel);
            }
        }
		return output;	
	}

	
	// Questao 4.2 Projeção de intensidade média (Z-stack mean)

	static public ImageAccess zprojectMean(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
		
        double pixel = 0.0;
        
        for(int i=0; i < nx; i++){
            for(int j=0; j < ny; j++){
                for(int k=0; k < nz; k++){
                    pixel = pixel + zstack[k].getPixel(i,j);
                }
                pixel = pixel/nz;
                output.putPixel(i, j, pixel);
            }
        }
		return output;	
	}

}
