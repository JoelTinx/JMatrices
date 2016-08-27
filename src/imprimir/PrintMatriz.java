/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package imprimir;

/**
 *
 * @author Joel Tinx
 */

import java.awt.print.*;
import java.awt.*;

public class PrintMatriz{
    
    //atributos de la clase que debe hacer la impresion de los documentos _mat
    private Font fuente;
    private String titulo;
    private double alto, ancho;
    private int pagina;


    //Constructor de la clase que debe hacer las impresiones
    public PrintMatriz(Font Fuente, String Titulo, double Alto, double Ancho, int Pagina){
        this.fuente = Fuente;
        this.titulo = Titulo;
        this.ancho = Ancho;
        this.alto = Alto;
        this.pagina = Pagina;
    }




}
