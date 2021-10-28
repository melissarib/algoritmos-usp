import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.DateFormat;


public class PrintSpooler {
  //
  String spoolFile;
  BufferedReader job;
  BufferedWriter spool;

  // construtor classe - recebe como parâmetro o nome do arquivo de spool
  public PrintSpooler(String spoolFile) {
      this.spoolFile = spoolFile;
      this.spool = null;
      this.job = null;
  }


  // método utilizado para abrir o arquivo de spool
  public boolean openPrintSpooler() throws IOException {
    try {
      spool = new BufferedWriter(new FileWriter(spoolFile));
    }
    catch (IOException ioe) {
      System.err.println("Erro ao abrir spool");
      return false;
    }
    return true;
  }


  // método utilizado para imprimir um job
  public void printJob(String jobName) throws IOException, FileNotFoundException {
    DateFormat df = DateFormat.getDateTimeInstance();
    try {
      job = new BufferedReader(new FileReader(jobName));

      String linha = new String("\n\n" + df.format(new Date()) + " - Imprimindo arquivo: " + jobName);

      while (linha != null) {

        try {
          spool.append(linha + "\n");
        }
        catch (IOException ioe) {
          System.err.println("Erro durante a impressao");
        }

        linha = job.readLine();
      }
    }
    catch (FileNotFoundException fnfe) {
      System.err.println("Erro no job");
    }
    finally{
      job.close();
    }
  }


  // método utilizado para fechar o arquivo de spool
  public void closePrintSpooler() throws IOException {
    try {
      spool.close();
    }
    catch (IOException ioe) {
      System.err.println("Erro ao fechar spool");
    }
  }

}
