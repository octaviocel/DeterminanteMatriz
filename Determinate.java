package DeterminanteDeMatriz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Determinate {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			
			System.out.println("DETERMINANTE");
			System.out.println("CALCULAMOS LA DETERMINA TE DE TU MATRIZ DESDE TU ARCHIVO");
			System.out.println("TU ARCHIVO DEBE CONTNER EN LA PRIMER LINEA LA DIMENSION DE TU MATRIZ SEPARADA POR UN ESPACIO (N M) Y DESPUES TU MATRIZ");
			
			
			double [][] matriz= obtenermatriz();
			imprimir(matriz);
			System.out.println("El determinante de tu matriz es: "+Determinante(matriz));
			
	}
	public static double[][] SubMatriz(double[][]matriz, int indicador){
		/* Recorrera toda la matriz pero con el indicador quitara los elementos
		 * de la amtriz en fila y columna para regresar una submatriz
		 */
		int nuevacolumna=0;
		double [][] matriznew= new double[matriz.length-1][matriz[0].length-1];
		for (int i = 1; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(j!=indicador) {
					matriznew[i-1][nuevacolumna]=matriz[i][j];
					nuevacolumna++;
				}
			}
		}
		return matriznew;
	}
	public static double Determinante(double[][] matriz) {
		int fila= matriz.length;// contamos el largo de las filas
		int columna= matriz[0].length;// contamos el largo de las columnas
		if((fila==1)&&(columna==1)){// si la matriz que nos pasan contiene un solo digito regresamos el mismo dijito
			return matriz[0][0];
		}else {
			double determinanteaco=0;
			for (int i = 0; i < fila; i++) {// como el determinnte de la matriz es la multiplicacion de la posicion de arriba por una sibmatrix entramos a un f
				if(i%2==0) {//si la fila es par sera positivo y se suma
					determinanteaco= determinanteaco+(matriz[0][i]* Determinante(SubMatriz(matriz,i)));
				}else {//si la fila no es par se resta
					determinanteaco= determinanteaco-(matriz[0][i]* Determinante(SubMatriz(matriz,i)));
				}
			}
			return determinanteaco;
		}
	}
	public static void imprimir(double [][] matriz) {  //imprime la matriz del archivo
		for (int j = 0; j < matriz.length; j++) {
			for (int j2 = 0; j2 < matriz.length; j2++) {
				System.out.print(matriz[j][j2]+"\t");
			}
			System.out.println();
		}
	}
	public static double[][] obtenermatriz() throws IOException{// funcion para obtener la matriz desde el archivo
		try (Scanner pen = new Scanner(System.in)) {
			System.out.println("Ingresa la ruta del archivo ejemplo(C:\\Users\\DELL\\Desktop\\)");
			String leer = pen.next();
			System.out.println("Ingresa el nombre del archivo, ejemplo(input.txt)");
			String nombre = pen.next();
			File file =new File(leer+nombre);
			FileReader reader = new FileReader (file);  
			BufferedReader li = new BufferedReader(reader);
			String fre= li.readLine();			//Lee la primer linea que continen las dimenciones de la matriz
			StringTokenizer tokens = new StringTokenizer(fre);
			int []filacolu= new int[2];
			int i=0;
			while(tokens.hasMoreTokens()){
				filacolu[i]=Integer.parseInt(tokens.nextToken());  //Inserta las dimenciones en una matriz para separarlas por columna y fila
				i++;
			}
			int fila=filacolu[0];
			int columna=filacolu[1];
			double [][]matriz= new double[fila][columna];  //declara la matriz con las dimensiones dadas
			String linea= li.readLine();
			int k=0;
			while(linea!=null) {							//ciclo para leer toda la matriz 
				for (int j = 0; j < fila; j++) {
					matriz[k][j]= linea.charAt(j)-'0';		//agrega los valores a la matriz 
				}
				k++;
				linea=li.readLine();
			}
			return matriz;
		}
		
	}
	
	
	
}
