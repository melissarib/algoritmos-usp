import java.io.*;
import java.util.Scanner;

public class ShellJava {


  public static void main(String[] args){

    // Declaracao das variaveis
    ProcessBuilder pb;
    Process process1;
    Scanner sc;
    String line;

    // Inicializacao das variavais
    sc = new Scanner(System.in);

    System.out.print("shell$ ");
    line = sc.nextLine();

    while (!line.equals("exit")) {

      if (!line.equals("")) {
        pb = new ProcessBuilder(line.split(" ")).inheritIO();
        try{
            process1 = pb.start();//cria processo filho
            process1.waitFor(); //espero pelo termino de sua execucao
        }
        catch(IOException ioe){//erro no inheritIO
          System.err.println("comando nao encontrado.");

        }
        catch (InterruptedException ie){//erro no waitfor
          System.err.println(ie);
        }
      }

      System.out.print("shell$ ");
      //ler nova linha
			line  = sc.nextLine();
    }

  }
}
