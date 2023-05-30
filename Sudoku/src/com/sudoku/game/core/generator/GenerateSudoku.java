package com.sudoku.game.core.generator;

import java.util.LinkedList;
import java.util.Random;

/**
 * Esta clase es la encargada de generar el carton de sudoku.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class GenerateSudoku {
	private int fila = 0, columna = 0;
	private int[][] tabla;
	private int[][] tablaAuxiliar;
	
	private Random random;
	
	private LinkedList<Integer> listaNumeros;
	private LinkedList<Integer> listaRegion;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crea una matriz 9x9 donde se almacenara todo el sudoku.
	 * Se usan numeros aleatorios para llenarlos.
	 */
	public GenerateSudoku() {
		tabla = new int[9][9];
		
		random = new Random();
		
		listaNumeros = new LinkedList<Integer>();
		listaRegion = new LinkedList<Integer>();
	}
	
	/**
	 * Crea un tablero de sudoku.
	 */
	public void iniciar() {
		iniciarTabla();
		listaNumeros = getListaNumeros(listaNumeros);//crea la lista de numeros aleatorios
		listaRegion.clear();
		fila = 0;
		columna = 0;
		//int intentos = 1;
		while(!crearTabla()) {
			iniciarTabla();
			//intentos++;
		}
		
		tablaAuxiliar = cloneTabla(tabla);
		
		//imprimirTabla();
		//System.out.println("INTENTOS: "+intentos);
		
		//imprimirLista(listaNumeros);
	}
	
	/**
	 * Establece la dificultad del sudoku.
	 * 
	 * @param dificulty nivel de dificultad solicitado.
	 */
	public void establecerDificultad(Dificulty dificulty) {
		int index = 0;
		int row, column;
		while(index < dificulty.getTotalHide()) {
			row = dificulty.getHide() - 1;
			column = dificulty.getHide() - 1;
			
			if(tablaAuxiliar[row][column] != 0) {
				tablaAuxiliar[row][column] = 0;
				
				index++;
			}
		}
	}
	
	/**
	 * Devuelve una matriz clonada.
	 * 
	 * @param tabla matriz a clonar.
	 * @return tabla clonada.
	 */
	private int[][] cloneTabla(int[][] tabla) {
		int [][] matrix = new int[9][9];
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				matrix[i][j] = tabla[i][j];
			}
		}
		
		return matrix;
	}
	
	/**
	 * Crea el tablero de sudoku.
	 * 
	 * @return true si tuvo exito al crear el sudoku, false si no.
	 */
	private boolean crearTabla() {
		for(int i=0; i<9; i++) {
			//listaNumeros = getListaNumeros(listaNumeros);
			for(int j=0; j<9; j++) {
				listaRegion = getListaNumeros(listaRegion);
				getRangoRegion(regionTabla(i, j));
				
				listaRegion = getListaNumerosRegion(listaRegion);
				listaRegion = eliminarNumerosFilas(i, listaRegion);
				listaRegion = eliminarNumerosColumnas(j, listaRegion);
				if(listaRegion.size() > 0) {
					tabla[i][j] = getNumeroAleatorioLista(listaRegion);
				}
			}
		}
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(tabla[i][j] == -1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Incializa la matriz tabla con un valor neutral -1.
	 */
	private void iniciarTabla() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				tabla[i][j] = -1;
			}
		}
	}
	
	/**
	 * Llena una lista de nueve numeros aleatorios sin repetir ninguno.
	 * 
	 * @param lista lista donde se almacenaran los numeros.
	 * @return lista modificada.
	 */
	private LinkedList<Integer> getListaNumeros(LinkedList<Integer> lista) {
		int numero = -1;
		
		lista.clear();
		while(lista.size() < 9) {
			numero = getNumeroAleatorio();
			if(!isExisteNumero(numero, lista) ) {
				lista.add(numero);
			}
		}
		return lista;
	}
	
	/**
	 * Devuelve la existencia de un valor dentro de una lista.
	 * 
	 * @param numero valor a verificar.
	 * @param lista lista donde se debe buscar el valor.
	 * @return true si existe el valor, falso si no existe.
	 */
	private boolean isExisteNumero(int numero, LinkedList<Integer> lista) {
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i) == numero) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Devuelve el numero de la region a la que pertenece, dado una fila y columna.
	 * 
	 * @param fila fila donde esta un valor.
	 * @param columna columna donde esta el valor.
	 * @return el numero de la region.
	 */
	private int regionTabla(int fila, int columna) {
		int region = 0, row = 0, column = 0;
		//region1
		if((fila > -1)&&(fila < 3)) {
			row = 1;
		}
		else if((fila > 2)&&(fila < 6)) {
			row = 2;
		}
		else if((fila > 5)&&(fila < 9)) {
			row = 3;
		}
		
		//regionColumna
		if((columna > -1)&&(columna < 3)) {
			column = 1;
		}
		else if((columna > 2)&&(columna < 6)) {
			column = 2;
		}
		else if((columna > 5)&&(columna < 9)) {
			column = 3;
		}
		
		if((row == 1)&&(column == 1)) {
			region = 1;
		}
		else if((row == 1)&&(column == 2)) {
			region = 2;
		}
		else if((row == 1)&&(column == 3)) {
			region = 3;
		}
		else if((row == 2)&&(column == 1)) {
			region = 4;
		}
		else if((row == 2)&&(column == 2)) {
			region = 5;
		}
		else if((row == 2)&&(column == 3)) {
			region = 6;
		}
		else if((row == 3)&&(column == 1)) {
			region = 7;
		}
		else if((row == 3)&&(column == 2)) {
			region = 8;
		}
		else if((row == 3)&&(column == 3)) {
			region = 9;
		}
		
		return region;
	}
	
	/**
	 * Establece los atributos fila y columna con los valores donde inicia una region.
	 * 
	 * @param region region que se desea obtener el rango de inicio.
	 */
	private void getRangoRegion(int region) {
		switch(region) {
		case 1:
			fila = 0;
			columna = 0;
			break;
		case 2:
			fila = 0;
			columna = 3;
			break;
		case 3:
			fila = 0;
			columna = 6;
			break;
		case 4:
			fila = 3;
			columna = 0;
			break;
		case 5:
			fila = 3;
			columna = 3;
			break;
		case 6:
			fila = 3;
			columna = 6;
			break;
		case 7:
			fila = 6;
			columna = 0;
			break;
		case 8:
			fila = 6;
			columna = 3;
			break;
		case 9:
			fila = 6;
			columna = 6;
			break;
		}
	}
	
	/**
	 * Verifica que de una lista de numeros no existan valores que ya estan en una region.
	 * 
	 * @param lista lista de numeros.
	 * @return lista modificada.
	 */
	private LinkedList<Integer> getListaNumerosRegion(LinkedList<Integer>lista) {
		int x = 0;
		for(int i=fila; i<(fila+3); i++) {
			for(int j=columna; j<(columna+3); j++) {
				x = 0;
				while(x < lista.size()) {
					if(tabla[i][j] == lista.get(x)) {
						lista.remove(x);
						x = 0;
						break;
					}
					else {
						x++;
					}
				}
			}
		}
		return lista;
	}
	
	/**
	 * Elimina de una lista de numeros los numeros que esten en la fila de la matriz 9x9.
	 * 
	 * @param row fila a verificar.
	 * @param lista lista de los numeros.
	 * @return la lista modificada.
	 */
	private LinkedList<Integer> eliminarNumerosFilas(int row, LinkedList<Integer> lista) {
		int x = 0;
		for(int i=0; i<9; i++) {
			x = 0;
			while(x < lista.size()) {
				if(tabla[row][i] == lista.get(x)) {
					lista.remove(x);
					x = 0;
					break;
				}
				else {
					x++;
				}
			}
		}
		return lista;
	}
	
	/**
	 * Elimina de una lista de numeros los numeros que esten en la columna de la matriz 9x9.
	 * 
	 * @param col columna a verificar.
	 * @param lista lista de los numeros.
	 * @return la lista modificada.
	 */
	private LinkedList<Integer> eliminarNumerosColumnas(int col, LinkedList<Integer> lista) {
		int x = 0;
		for(int i=0; i<9; i++) {
			x = 0;
			while(x < lista.size()) {
				if(tabla[i][col] == lista.get(x)) {
					lista.remove(x);
					x = 0;
					break;
				}
				else {
					x++;
				}
			}
		}
		return lista;
	}
	
	/**
	 * Devuelve un numero aleatorio.
	 * 
	 * @return numero aleatorio del 1-9.
	 */
	private int getNumeroAleatorio() {
		return random.nextInt(9) + 1;
	}
	
	/**
	 * Devuelve un numero aleatorio que este dentro de una lista.
	 * 
	 * @param lista lista con los numeros.
	 * @return un numero aleatorio.
	 */
	private int getNumeroAleatorioLista(LinkedList<Integer> lista) {
		int indice = 0;
		indice = random.nextInt(lista.size());
		return lista.get(indice);
	}
	
	/**
	 * Imprime los valores de una lista en consola, para hacer pruebas.
	 * 
	 * @param lista lista con los numeros a imprimir.
	 */
	public void imprimirLista(LinkedList<Integer> lista) {
		System.out.println("\nIMPRESION LISTA: ");
		for(int i=0; i<lista.size(); i++) {
			System.out.print(lista.get(i)+" ");
		}
		System.out.println("");
	}
	
	/**
	 * Imprime la tabla en consola, para hacer pruebas.
	 * 
	 * @param table matriz que se desea imprimir.
	 */
	public void imprimirTabla(int[][] table) {
		System.out.println("\nIMPRESION TABLA: ");
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(table[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Verifica si en la celda especifica, el valor es el correcto.
	 * 
	 * @param value valor a verificar.
	 * @param region region a la que pertenece la celda.
	 * @param filaCelda region de fila a la que pertenece la celda.
	 * @param columnaCelda region de columna a la que pertenece la celda.
	 * @return
	 */
	public boolean verificarValorCelda(String value, int region, int filaCelda, int columnaCelda) {
		if(!value.contentEquals("")) {
			getRangoRegion(region);
			int valor = Integer.parseInt(value);
			
			if(valor == tabla[fila + (filaCelda - 1)][columna + (columnaCelda - 1)]) {
				return true;
			}
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * crea la tabla del tutorial y se deja una celda sin llenar.
	 */
	public void getTutorialTable() {
		tabla = cloneTabla(tableTutorial());
		tablaAuxiliar = cloneTabla(tableTutorial());
		
		tablaAuxiliar[2][5] = 0;
	}
	
	/**
	 * Tabla por defecto del tutorial.
	 */
	private int[][] tableTutorial() {
		int[][] table = {
				{8, 1, 2, 9, 4, 3, 6, 7, 5},
				{7, 5, 3, 6, 8, 2, 4, 9, 1},
				{6, 4, 9, 1, 7, 5, 2, 8, 3},
				{1, 5, 4, 3, 6, 9, 2, 8, 7},
				{2, 3, 7, 8, 4, 5, 1, 6, 9},
				{8, 9, 6, 7, 2, 1, 5, 3, 4},
				{5, 2, 1, 4, 3, 8, 7, 9, 6},
				{9, 7, 4, 5, 2, 6, 3, 1, 8},
				{3, 6, 8, 9, 1, 7, 4, 5, 2}
				};
		return table;
	}
	
	/**
	 * Devuelve una matriz 3x3 con los numeros de una region.
	 * 
	 * @param region region a la que se desea obtener sus valores.
	 * @return valores de la region.
	 */
	public int[] getSudokuBoard(int region) {
		getRangoRegion(region);
		int[] numero = new int[9];
		int n = 0;
		for(int i=fila; i<(fila+3); i++) {
			for(int j=columna; j<(columna+3); j++) {
				numero[n] = tablaAuxiliar[i][j];
				n++;
			}
		}
		return numero;
	}
	
	/**
	 * Dado los parametros, esta funcion genera el numero asociado a una celda.
	 * 
	 * @param region region a la que pertenece la celda.
	 * @param rowCell region de fila a la que pertenece la celda.
	 * @param columnCell region de columna a la que pertenece la celda.
	 * @return devuelve el valor que debe ir en la celda.
	 */
	public int getHint(int region, int rowCell, int columnCell) {
		getRangoRegion(region);
		int numero = 0;
		int count = 0;
		for(int i=fila; i<(fila+3); i++) {
			if((rowCell - 1) == count) {
				count = 0;
				for(int j=columna; j<(columna+3); j++) {
					if((columnCell - 1) == count) {
						numero = tabla[i][j];
						return numero;
					}
					count++;
				}
			}
			count++;
		}
		return numero;
	}
}