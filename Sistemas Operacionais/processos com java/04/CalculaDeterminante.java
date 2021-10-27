import java.util.Scanner;

public class CalculaDeterminante {

  public static void main(String[] args) {


    // decladacao das variaveis
    int dimensao;
    Matrix m;
    Scanner sc;

    // inicializacao
    sc = new Scanner(System.in);
    System.out.println("Informe a dimensao da matriz");
    dimensao = sc.nextInt();
    m = new Matrix(dimensao);
    m.readMatrix();


    // // PREENCHIMENTO AUTOMATIZADO PARA TESTES
    // m = new Matrix(4);
    // for (int i = 0; i < 4; i++)
    // for (int j = 0; j < 4; j++)
    // m.setElement(i, j, (int) (10 * Math.random()) );
    // m.printMatrix();


    //calculo
    Determinante d = new Determinante(m);

    d.start();

    synchronized(d){
      try{
        d.wait();
      }
      catch(InterruptedException e){
      }

      System.out.println("\nDeterminante: " + d.getDeterminante() );
    }
  }
}

