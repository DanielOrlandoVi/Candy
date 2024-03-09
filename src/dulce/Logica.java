
package dulce;

import java.util.Scanner;

public class Logica {    

    
    public int fila; //Filas de la matriz
    public int columna; //Columnas de la matriz
    public int pivote; //Variable auxiliar que me ayuda a llenar el arreglo temporal, que es el arreglo 
    //que almacena en memoria (Programación dinámica)
    
    
    int matriz [][];
    public int[] arregloTemp; //Arreglo que va almacenando en memoria
    
    //Con este método le pedimos al usuario la matriz y la guardamos
    public int[][] crearMatriz() {       
    Scanner teclado = new Scanner(System.in);   
    
        System.out.println("De cuantas filas va a ser tu matriz");
        fila= teclado.nextInt();
        System.out.println("De cuantas columnas va a ser tu matriz");
        columna = teclado.nextInt();
        
        // Inicializa los arreglos que se usan a lo largo de todo el programa
        arregloTemp = new int[columna]; //Arreglo que guarda en memoria ahora tiene el tamaño columna
        arregloMayores[0] = new int[columna]; //Esta es una matriz unidimensional que guardara los mayores de cada fila de la matriz
        //Se hace unidimensional para volver a utilizar recursivamente los metodos y hallar el mayor de ese arreglo de mayores
        //Este último resultado será la mayor cantidad de dulces que puede recolectar el niño loco
        
        matriz = new int [fila][columna];
        for (int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                System.out.println("Ingrese el numero "+(j+1)+" de la columna"+(i+1));
                matriz[i][j]= teclado.nextInt();                
            }
        }
        return matriz;
       
    }
    
    //Metodo para imprimir la matriz ingresada por el usuario
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
        //Primer y segundo valor como los de la matriz ingresada por el usuario
        arregloTemp[0] = matriz[posFila][0];
        arregloTemp[1] = matriz[posFila][1];
        //Tercer termino es el primer valor + segundo valor de la matriz ingresada
        arregloTemp[2] = (matriz[posFila][0] + matriz[posFila][2]);
        
        return arregloTemp;
    }
    
    //llenamos el arreglo temporal que se guardara en memoria con los valores restantes
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
    //Compara las últimas dos posiciones de arregloTemp y saca el mayor, que será el mayor de la fila 
    public int mayorFila(){
        if(arregloTemp[columna - 1] >= arregloTemp[columna - 2]){
            mayorFila = arregloTemp[columna - 1];
        }
        else if(arregloTemp[columna - 2] >= arregloTemp[columna - 1]){
            mayorFila = arregloTemp[columna - 2];
        }
        return mayorFila;
    }
    
    
    
    int indexActual = 0; //Variable auxiliar para llenar el arreglo mayores en orden, brinda el indice actual
    
    //Creo una matriz unidimensional del arreglo de los mayores para poder pasar esta matriz por los metodos 
    //de nuevo, reutilizando codigo
    public int[][] arregloMayores = new int[1][];
    //Este metodo llena el arreglo de mayores con el mayor que vaya saliendo de cada fila de la matriz ingresada
    public void llenarArregloMayores(){
        if (indexActual < columna) {
        arregloMayores[0][indexActual] = mayorFila;
        indexActual++;
    }
        
    }
    
    //Imprime el arreglo que tiene los mayores de cada fila
    public void imprimirArregloMayores() {
    System.out.println("El arreglo de los mayores es: ");
    for(int i = 0; i < arregloMayores[0].length; i++){
        System.out.println(arregloMayores[0][i]);
    }
}

    
    
    //Este metodo es el que ejecuta el programa general
    public void crazyCandy(){
    
    //Primero recibe la matriz del usuario
    int matricita[][] = this.crearMatriz();    
    
    //Con esa matriz va recorriendo cada una de sus filas por medio del bucle
    for (int k = 0; k < fila; k++){
        //En la fila donde este, prepara el arreglo temporal con los valores conocidos de las tres primeras posiciones
        this.prepArregloTemp(k, matricita);
        //Con el arreglo preparado procede a llenarlo con los valores restantes
        this.llenarArregloTemp(k, matricita);
        //Se saca el mayor de la fila, a través del arreglo temporal
        this.mayorFila();
        System.out.println("El mayor numero de dulces de la fila " +(k+1)+ " es: " + mayorFila);
        //Con el mayor de la fila, lo introduce en la matriz unidimensional arreglo mayores
        this.llenarArregloMayores(); 
    }  
    
    //Una vez finalice el ciclo, la matriz arreglo mayores estará llena de valores
    //Se procede a repetir el mismo proceso, ahora pasandole a los metodos la matriz arreglo mayores como parametro
    this.prepArregloTemp(0, arregloMayores);
    this.llenarArregloTemp(0, arregloMayores);
    //Obteniendo el mayor la matriz arregloMayores, obtenemos el mayor número de dulces recolectados
    this.mayorFila();
    System.out.println("La mayor cantidad de dulces recolectados por el nino loco es: " + mayorFila);
}
    
}
