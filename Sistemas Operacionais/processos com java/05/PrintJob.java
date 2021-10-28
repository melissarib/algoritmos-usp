import java.util.concurrent.Semaphore;
import java.io.IOException;

public class PrintJob extends Thread {

  private PrintSpooler printer;
  private Semaphore mutex;
  private String doc;

  public PrintJob(PrintSpooler printer, Semaphore mutex, String doc) {
    this.printer = printer;
    this.mutex = mutex;
    this.doc = doc;
  }

  @Override
  public void run(){
    try {
      mutex.acquire();
      printer.printJob(doc);  //imprimindo
    }
    catch (InterruptedException ie) {
      ie.printStackTrace();
    }
    catch(IOException ioe){
      System.err.println("Erro durante a impressao");
    }

    finally {
      mutex.release();
    }
  }
}

