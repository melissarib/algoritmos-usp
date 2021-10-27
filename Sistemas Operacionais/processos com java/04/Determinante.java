public class Determinante extends Thread {

  //Campos
  private Matrix m;
  private double det;

  //Construtor
  public Determinante(Matrix matrix){
    m = matrix;
    det = 0.0;
  }

  //THREAD
  @Override
  public void run() {
    synchronized(this){

      // Trivial
      if (m.getRowsSize() == 2) {
        setDeterminante( m.getElement(0, 0) * m.getElement(1, 1) - m.getElement(0, 1) * m.getElement(1, 0) );
        notify();
      }
      else{

        // Laplace na primeira linha
        double detParcial = 0.0;
        for (int j = 0; j < m.getRowsSize(); j++) {

          // Thread filho
          Determinante subDet = new Determinante( m.getSubmatrix(j) );
          // subDet.mToString(); //Visualizacao das matrizes

          subDet.start();

          synchronized(subDet){
            try{
              subDet.wait();
            }
            catch(InterruptedException e){
              e.printStackTrace();
            }
            detParcial += Math.pow(-1, j+2) * m.getElement(0, j) * subDet.getDeterminante();

            setDeterminante(detParcial);
          }
        }
        notify();
      }
    }
  }


  public double getDeterminante(){
    return det;
  }

  public void setDeterminante(double determinante){
    det = determinante;
  }

  // Exibe matriz armazenada na classe
  private void mToString(){
    System.out.println();
    m.printMatrix();
  }

}
