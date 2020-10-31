
public class PointwiseTransform extends Object {

	/**
	* Question 2.1 Contrast reversal
	*/
	static public ImageAccess inverse(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double value = 0.0;
		for (int x=0; x<nx; x++)
		for (int y=0; y<ny; y++) {
			value = input.getPixel(x, y);
			value = 255 - value; // onde 0 representa preto e 255 o branco
			output.putPixel(x, y, value);
		}
		return output;	
	}

	/**
	* Question 2.2 Stretch normalized constrast
	*/
	static public ImageAccess rescale(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double max = input.getMaximum(); // Retorna o maximo de uma imagem
		double min = input.getMinimum(); // Retorna o minimo de uma imagem
                
		ImageAccess output = new ImageAccess(nx, ny);
		
                // Declarando o que precisa para obter a funcao g(x,y)
                double function_g = 0.0; 
                double alpha = 0.0;
                alpha = 255 / (max - min);
                
                // Percorrendo a imagem para aplicar a funcao
                for(int x = 0; x < nx; x++){
                    for(int y = 0; y < ny; y++){
                        // Calculando a funcao para o pixel corrente
                        function_g = alpha * ( input.getPixel(x,y) - min );
                        
                        // Alterando o valor do pixel para ser o valor da funcao
                        output.putPixel(x, y, function_g); 
                    }
                }
		return output; // Retorna a imagem
	}

	/**
	* Question 2.3 Saturate an image
	*/
	static public ImageAccess saturate(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
                
                // Percorrendo a imagem 
                for(int x = 0; x < nx; x++){
                    for(int y = 0; y < ny; y++){
                        
                        if( input.getPixel(x,y) > 10000){
                            output.putPixel(x, y, 10000);
                        }
                    }
                }
                
		return rescale(output); // Aplica rescale e retorna a imagem
	}
	
	/**
	* Question 4.1 Maximum Intensity Projection
	*/
	static public ImageAccess zprojectMaximum(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);

    double maximum_value = 0.0, temp = 0.0; 

    // Percorrendo os pixels da imagem 
    for(int x = 0; x < nx; x++){
        for(int y = 0; y < ny; y++){
          
          // percorre todas as imagens da pilha para encontrar o maior valor de pixel em x,y
          for(int l = 0; l < nz; l++){
            if(l == 0){
              temp = zstack[l].getPixel(x,y);
              maximum_value = temp;
            }
            else{
              temp = zstack[l].getPixel(x,y);

              if (temp > maximum_value){
                maximum_value = temp;
              }
            }
          }

          // Agora coloca o maior valor na imagem de saida
          output.putPixel(x, y, maximum_value);
        }
    }

		return output;	
	}

	/**
	* Question 4.2 Z-stack mean
	*/
	static public ImageAccess zprojectMean(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
	    
    double medium_value = 0.0, sum = 0.0;

    // Percorrendo os pixels da imagem 
    for(int x = 0; x < nx; x++){
        for(int y = 0; y < ny; y++){
          
          sum = 0.0; // Zera a soma para auxiliar na media
          // percorre todas as imagens da pilha para encontrar o maior valor de pixel em x,y
          for(int l = 0; l < nz; l++){
            sum = sum + zstack[l].getPixel(x,y);
          }

          // Calcula a media
          medium_value = sum/nz;

          // Agora coloca o valor media na imagem de saida
          output.putPixel(x, y, medium_value);
        }
    }
		return output;	
	}

}
