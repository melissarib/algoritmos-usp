import java.util.concurrent.Semaphore;
import java.io.IOException;


public class Exe5 {

  public static void main(String[] args) {

    //organizar os arquivos
    Semaphore mutex = new Semaphore(1);                   //semaforo binario
    PrintSpooler printer = new PrintSpooler("papel.txt"); //objeto de spool
    PrintJob[] arquivos = new PrintJob[args.length];      //threads de impressao
    boolean printing;

    try {
      printing = printer.openPrintSpooler();    //abre para impressao

      if(printing){
        for (int i = 0; i < args.length ; i++) {

          arquivos[i] = new PrintJob(printer, mutex, args[i]);  //parametros de impressao
          arquivos[i].start();  //comeca a imprimir

          synchronized(arquivos[i]){
            try{
              arquivos[i].wait();   //imprime um de cada vez
            }
            catch(InterruptedException ie){
            }
          }
        }

        printer.closePrintSpooler();  //fecha spool apos acabar impressao
      }

    } catch (IOException ioe) {

    }
  }
}
