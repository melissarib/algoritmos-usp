/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

public class Matrix {

    private int[][] matrix;
    private int rowsSize, columnsSize;

    /**
     * Construtor que gera uma matriz vazia
     * @param rowsSize número de linhas
     * @param columnsSize número de colunas
     */
    public Matrix(int rowsSize, int columnsSize){
        this.rowsSize = rowsSize;
        this.columnsSize = columnsSize;
        matrix = new int[rowsSize][columnsSize];
    }

    /**
     * Construtor que gera uma matriz quadrada vazia
     * @param rowsSize número de linhas/colunas
    */
    public Matrix(int rowsSize){
        this.rowsSize = rowsSize;
        this.columnsSize = rowsSize;
        matrix = new int[rowsSize][rowsSize];
    }

    /**
     * Preenche a matriz a partir dos valores inseridos pelo usuário
     */
    public void readMatrix() {
        Scanner sc;
        String line;

        sc = new Scanner(System.in);

        try{
            for(int i = 0; i < rowsSize; i++) {
                for(int j = 0; j < columnsSize; j++) {
                    System.out.print("Element m[" + i + "]["
                        + j + "]: ");
                    line = sc.nextLine();
                    matrix[i][j] = Integer.parseInt(line);
                }
            }
        } catch(NumberFormatException nfe) {
            System.err.println(nfe);
			System.exit(0);
        }
    }

    /**
     * Imprime os valores da matriz
     */
    public void printMatrix(){
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    /**
     * Retorna o valor de um elemento da matriz
     * @param i linha
     * @param j coluna
     * @return valor do elemento[i][j]
     */
    public int getElement(int i, int j) {
        return matrix[i][j];
    }

    /**
     * Modifica o valor do elemento[i][j] da matriz
     * @param i índice da linha (a partir do zero)
     * @param j índice da coluna (a partir do zero)
     * @param value
     */
    public void setElement(int i, int j, int value) {
        matrix[i][j] = value;
    }

    /**
     * Gera uma submatriz retirando a linha 0 e coluna j1
     * @param j1 coluna que será removida para gerar a nova matriz
     * @return
     */
    public Matrix getSubmatrix(int j1){
        Matrix submatrix;
		int i, j, j2;

		submatrix = new Matrix(getRowsSize() - 1);

        for (i = 1; i < getRowsSize(); i++){
             j2 = 0;
            for (j = 0; j < getRowsSize(); j++){
                if(j != j1) {
                    submatrix.setElement(i-1, j2, getElement(i,j));
                    j2++;
                }
            }
        }
        return submatrix;
    }

    /**
     * Retorna o número de linhas da matriz
     * @return
     */
    public int getRowsSize(){
        return rowsSize;
    }

    /**
     * Retorna o número de colunas da matriz
     * @return
     */
    public int getColumnsSize(){
        return columnsSize;
    }
}
