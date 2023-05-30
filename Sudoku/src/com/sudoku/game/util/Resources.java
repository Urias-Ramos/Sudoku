package com.sudoku.game.util;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

/**
 * Esta contiene metodos estaticos que se utilizan para cargar archivos de recurso del juego, 
 * como imagenes o archivos.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Resources {
	
	/**
	 * Se encarga de obtener un BufferedImage de una direccion dentro de la aplicacion.
	 * 
	 * @param rutaImage ruta de la imagen a cargar.
	 * @return un BufferedImage dada la ruta.
	 */
	public static BufferedImage getImagen(String rutaImage) {
		Image imagen = null;
		try {
			imagen = ImageIO.read(Resources.class.getClassLoader().getResource(rutaImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenCargada = null;
		
		imagenCargada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.TRANSLUCENT);
		
		Graphics g = imagenCargada.getGraphics();
		g.drawImage(imagen, 0, 0, null);
		g.dispose();
		
		return imagenCargada;
	}
	
	/**
	 * Carga el archivo profile si existe, de lo contrario crea y guarda los valores por defecto.
	 */
	public static void loadProfile() {
		if(existeData()) {
			GameManifest.PROFILE = (Profile) CargarProfile();
		}
		else {
			guardarProfile();
		}
	}
	
	/**
	 * Gudarda el archivo profile.
	 */
	public static void guardarProfile() {
		existeData();
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		
		try {
			fos = new FileOutputStream(getRutaFichero());
			salida = new ObjectOutputStream(fos);
			
			salida.writeObject(GameManifest.PROFILE);
			
			fos.close();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga la clase profile guardado en un archivo.
	 * 
	 * @return devuelve la clase Profile serializado.
	 */
	public static Object CargarProfile() {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		
		try {
			fis = new FileInputStream(getRutaFichero());
			entrada = new ObjectInputStream(fis);
			
			return entrada.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Si la ruta del archivo existe devuelve true, de lo contrario 
	 * crea la ruta completa del archivo y devuelve false.
	 * 
	 * @return la existencia del archivo.
	 */
	private static boolean existeData() {
		File fichero = new File(getRutaFichero());
		if(fichero.exists()) {
			return true;
		}
		else {
			crearDirectorio();
			return false;
		}
	}
	
	/**
	 * Devuelve la ruta donde deria estar guardado los datos del juego..
	 * 
	 * @return ruta de los datos del juego.
	 */
	private static String getRutaFichero() {
		return new File("").getAbsolutePath()+"\\data\\profile.dat"; 
	}
	
	/**
	 * Crea la ruta completa estabecida.
	 */
	private static void crearDirectorio() {
		File fichero = new File(getRutaFichero()).getParentFile();
		fichero.mkdirs();
	}
}