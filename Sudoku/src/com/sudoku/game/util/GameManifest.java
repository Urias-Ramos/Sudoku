package com.sudoku.game.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.sudoku.game.core.gamepad.GameKeyboard;
import com.sudoku.game.core.gamepad.GameMouse;
import com.sudoku.game.core.generator.GenerateSudoku;
import com.sudoku.game.core.graphicsengine.Engine;
import com.sudoku.game.core.graphicsengine.Scenario;

/**
 * Esta clase contiene atributos y metodos estaticos
 * que son usados por todo el juego.
 * 
 * No se requiere una instancia de esta clase para su uso.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class GameManifest {
	public static String VERSION = "1.0";
	
	public static Profile PROFILE = new Profile();
	public static Idiome IDIOME = new Idiome();
	
	public static int ANCHO_PANTALLA_COMPLETA = Toolkit.getDefaultToolkit().getScreenSize().width;;
	public static int ALTO_PANTALLA_COMPLETA = Toolkit.getDefaultToolkit().getScreenSize().height;;
	
	public static int ANCHO_JUEGO = 800;
	public static int ALTO_JUEGO = 600;
	
	public static Point CENTRO_PANTALLA_COMPLETA = new Point();
	
	public static boolean RUNNING_GAME = false;
	public static Thread HILO_JUEGO = null;
	
	public static int APS = 0;
	public static int FPS = 0;
	
	public static int CURSORSIZE = 32;
	public static GameMouse RATON = new GameMouse();
	public static GameKeyboard TECLADO = new GameKeyboard();
	
	public static Random RANDOM = new Random();
	
	public static GenerateSudoku GENERATESUDOKU = new GenerateSudoku();
	
	public static Scenario SCENARIO = new Scenario(800, 600);
	public static Engine ENGINE = new Engine();
	
	public static Tutorial TUTORIAL = new Tutorial();
	
	public static int CELLCORRECT = 0;
	public static String NUMEROSELECT = "1";
	
	public static boolean SHOWNOTES = false;
	public static boolean PAUSED = false;
	
	public static int SCOREACTUAL = 0;
	public static int ERRORCOUNT = 0;
	public static int SCORE = 0;
	
	public static Color COLORCELESTE = Color.decode("#4990e2");
	
	public static Color COLORGAMESCENARIOBACKGROUND = Color.decode("#f5f6fa");
	
	public static Color COLORREGIONBACKGROUND = Color.decode("#d7d7d7");
	
	public static Color COLORCELLBACKGROUND = Color.decode("#ffffff");
	public static Color COLORCELLFOREGROUND = Color.decode("#000000");
	
	public static Color COLORNUMBERSELECTED = Color.decode("#a9caff");
	
	public static Color COLORBACKGROUNDERROR = Color.decode("#6a3643");
	public static Color COLORNUMBERERROR = Color.decode("#d73359");
	
	public static Color COLORLINE = Color.decode("#4f4f4f");
	public static Color COLORREGIONSELECTED = Color.decode("#f2f2f2");
	public static Color COLORVALUE = Color.decode("#0c64cc");
	
	/**
	 * Restaura los valores por defecto del juego.
	 */
	public static void GameEmpty() {
		CELLCORRECT = 0;
		NUMEROSELECT = "1";
		
		SHOWNOTES = false;
		PAUSED = false;
		
		SCOREACTUAL = 0;
		ERRORCOUNT = 0;
		SCORE = 0;
	}
	
	/**
	 * Se encarga de ver si un tiempo dado paso el tiempo limite.
	 * 
	 * @param tiempoInicio valor del tiempo a verificar.
	 * @param tiempoEsperado valor del tiempo que se debe cumpir.
	 * @return un valor booleano indicando si el tiempo inicio paso el tiempo esperado.
	 */
	public static boolean isTiempoCumplidoMilisegundos(long tiempoInicio, int tiempoEsperado) {
		if(TimeUnit.MILLISECONDS.convert((System.nanoTime() - tiempoInicio), TimeUnit.NANOSECONDS) >= tiempoEsperado) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este metodo devuelve un numero de un rango dado.
	 * 
	 * @param min valor minimo del rango.
	 * @param max valor maximo del rango.
	 * @return un numero aleatorio entre min y max.
	 */
	public static int generarNumeroAleatorio(int min, int max) {
		return (min + RANDOM.nextInt((max - min) + 1));
	}
	
	/**
	 * Se encarga de cambiar los atributos de los colores del juego
	 * Aqui se cambia el tema light o dark.
	 */
	public static void changeTheme() {
		if(!GameManifest.PROFILE.isDarkMode()) {
			COLORGAMESCENARIOBACKGROUND = Color.decode("#f5f6fa");
			
			COLORREGIONBACKGROUND = Color.decode("#d7d7d7");
			
			COLORCELLBACKGROUND = Color.decode("#ffffff");
			COLORCELLFOREGROUND = Color.decode("#000000");
			
			COLORNUMBERSELECTED = Color.decode("#a9caff");
			
			COLORLINE = Color.decode("#4f4f4f");
			COLORREGIONSELECTED = Color.decode("#f2f2f2");
			COLORVALUE = Color.decode("#0c64cc");
		}
		else {
			COLORGAMESCENARIOBACKGROUND = Color.decode("#161616");
			
			COLORREGIONBACKGROUND = Color.decode("#313130");
			
			COLORCELLBACKGROUND = Color.decode("#1a1a1a");
			COLORCELLFOREGROUND = Color.decode("#999999");
			
			COLORNUMBERSELECTED = Color.decode("#1f3c5e");
			
			COLORLINE = Color.decode("#4f4f4f");
			COLORREGIONSELECTED = Color.decode("#242424");
			COLORVALUE = Color.decode("#0c64cc");
		}
	}
}