
package dulce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Logica {    

    
    public int fila;
    public int columna;
    public int pivote ;
    
    
    int matriz [][];
    public int[] arregloTemp;
    
    public int[][] crearMatriz() {       
    Scanner teclado = new Scanner(System.in);   
    
        System.out.println("De cuantas filas va a ser tu matriz");
        fila= teclado.nextInt();
        System.out.println("De cuantas columnas va a ser tu matriz");
        columna = teclado.nextInt();
        
        // Inicializa los arreglos que se usan a lo largo de todo el programa
        arregloTemp = new int[columna]; //Arreglo que guarda en memoria
        arregloMayores[0] = new int[columna];

        
        matriz = new int [fila][columna];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                System.out.println("Ingrese el numero "+(j+1)+" de la columna"+(i+1));
                matriz[i][j]= teclado.nextInt();                
            }
        }
        return matriz;
       
    }
    
    public void imprimir() {
         for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                System.out.print(matriz [i][j]+" "); 
                System.out.println(); 
            }
        }    
    }

    
    //Rellenamos el arreglo temporal con los tres datos que conocemos
    public int[] prepArregloTemp(int posFila, int[][] matriz){
        arregloTemp[0] = matriz[posFila][0];
        arregloTemp[1] = matriz[posFila][1];
        arregloTemp[2] = (matriz[posFila][0] + matriz[posFila][2]);
        
        return arregloTemp;
    }
    
    //Organizamos el arreglo temporal que guardara en memoria
    public void llenarArregloTemp(int filaActual, int[][] matriz){
    // Recorremos las columnas para la fila actual
    for(int j = 3; j < columna; j++){
        if((matriz[filaActual][j-2] >= matriz[filaActual][j-3])){
            pivote =  arregloTemp[j-2] + matriz[filaActual][j];
            arregloTemp[j] = pivote;
        }
        else if((matriz[filaActual][j-3] >= matriz[filaActual][j-2])){
            pivote =  arregloTemp[j-3] + matriz[filaActual][j];
            arregloTemp[j] = pivote;
        }
    }            
}
    
    private int mayorFila;
    //Compara las últimas dos posiciones de arregloTemp y saca el mayor que será el mayor de la fila 
    public int mayorFila(){
        if(arregloTemp[columna - 1] >= arregloTemp[columna - 2]){
            mayorFila = arregloTemp[columna - 1];
        }
        else if(arregloTemp[columna - 2] >= arregloTemp[columna - 1]){
            mayorFila = arregloTemp[columna - 2];
        }
        return mayorFila;
    }
    
    
    
    int indexActual = 0; //Variable auxiliar para llenar el arreglo mayores en orden
    
    //Creo una matriz unidimensional del arreglo de los mayores para poder pasar esta matriz por los metodos 
    //de nuevo, reutilizando codigo
    public int[][] arregloMayores = new int[1][];
    public void llenarArregloMayores(){
        if (indexActual < columna) {
        arregloMayores[0][indexActual] = mayorFila;
        indexActual++;
    }
        
    }
    
    public void imprimirArregloMayores() {
    System.out.println("El arreglo de los mayores es: ");
    for(int i = 0; i < arregloMayores[0].length; i++){
        System.out.println(arregloMayores[0][i]);
    }
}


    public void crazyCandy(){
        
    int matricita[][] = this.crearMatriz();        
    for (int k = 0; k < fila; k++){  
        this.prepArregloTemp(k, matricita);
        this.llenarArregloTemp(k, matricita);
        this.mayorFila();
        System.out.println("El mayor de la fila " +(k+1)+ " es: " + mayorFila);
        this.llenarArregloMayores(); 
    }              
    this.imprimirArregloMayores();
    this.prepArregloTemp(0, arregloMayores);
    this.llenarArregloTemp(0, arregloMayores);
    this.mayorFila();
    System.out.println("La mayor cantidad de dulces recolectados por el nino loco es: " + mayorFila);
}
    
}
