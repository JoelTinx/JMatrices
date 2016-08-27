
package nucleo;

import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;

/*
 * @author Joel Tinx
 */
public class matriz {

    private int fila;
    private int columna;
    char nombre;
    private float mat[][];

    public matriz(int filas, int columnas){
        this.fila = filas;
        this.columna = columnas;
        this.nombre = '_';
        mat = new float[fila][columna];
    }

    public matriz(char NombreMatriz, int filas, int columnas){
        this.fila = filas;
        this.columna = columnas;
        this.nombre = NombreMatriz;
        mat = new float[fila][columna];
    }

    public matriz(float [][] matrix){
        this.mat = matrix;
        this.fila = mat.length;
        this.nombre = '_';
        this.columna = mat[0].length;
    }

    public matriz(char NombreMatriz, float [][] matrix){
        this.nombre = NombreMatriz;
        this.mat = matrix;
        this.fila = mat.length;
        this.columna = mat[0].length;
    }

    public matriz(char NombreMatriz){
        this.nombre = NombreMatriz;
    }

    public matriz(){

    }

    //Metodos de insercion y devulocion de valores
    public void EstableceValoresArray(float[][] MatArray){
        this.mat = MatArray;

        this.fila = MatArray.length;
        this.columna = MatArray[0].length;
    }

    public void EstableceNombre(char NombreMatriz){
        nombre = NombreMatriz;
    }

    public float[][] DevuelveValoresArray(){
        return this.mat;
    }

    public int NumeroFilas(){
        return this.fila;
    }

    public int NumeroColumnas(){
        return this.columna;
    }

    public char nombrex(){
        return this.nombre;
    }

    //Se desarrollan los metodos que se utilizan
    public String escribir_matriz(){
        String sMatriz = "";

        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                sMatriz += mat[i][j] + "        ";
            }
            sMatriz += "\n";
        }
        return sMatriz;
    }

    public matriz sumar_matriz(matriz uno, matriz dos){
        matriz suma = new matriz(uno.fila, uno.columna);
        for(int i=0;i<suma.fila;i++){
            for(int j=0;j<suma.columna;j++){
                suma.mat[i][j] = uno.mat[i][j] + dos.mat[i][j];
            }
        }
        return suma;
    }

    public matriz multiplica_matriz(matriz uno, matriz dos){
        matriz multiplicacion = new matriz(uno.fila, dos.columna);

        for(int i=0;i<uno.fila;i++)
            for(int j=0;j<dos.columna;j++){
                multiplicacion.mat[i][j] = 0;
                for(int k=0;k<uno.columna;k++)
                    multiplicacion.mat[i][j] = multiplicacion.mat[i][j] + uno.mat[i][k]*dos.mat[k][j];
            }

        return multiplicacion;

    }

    public matriz multiplica_real(matriz uno, float real){
        matriz mult = new matriz();
        for(int i=0;i<mat.length;i++)
            for(int j=0;j<mat[0].length;j++)
                mult.mat[i][j] = uno.mat[i][j]*real;

        return mult;
    }

    public void MultiplicaReal(float real){
        for(int i=0;i<mat.length;i++)
            for(int j=0;j<mat[0].length;j++)
                mat[i][j] = mat[i][j]*real;
    }

    public float traza(){
        float t = 0;
        for(int i=0;i<mat.length;i++)
            t += mat[i][i];

        return t;
    }

    public float determinante(){
            float det=1, temp, tmp;

            for(int k=0;k<mat.length;k++){
                tmp=mat[k][k];
            //Por operaciones elementales.
                if(tmp!=0)
                for(int j=k+1;j<mat.length;j++){
                    temp=mat[j][k];
                    for(int p=0;p<mat.length;p++){
                        mat[j][p]=mat[j][p]-((mat[k][p]*temp)/tmp);
                     }
                }
            }
            for(int i=0;i<mat.length;i++){
                    det*=mat[i][i];
            }

            return det;
        }

    public matriz transpuesta(){
        matriz transp = new matriz(columna,fila);
        for(int i=0;i<mat.length;i++)
            for(int j=0;j<mat[0].length;j++)
                transp.mat[j][i]=mat[i][j];

        return transp;
    }

    public matriz escalonada_matriz(){
        matriz escalonada = new matriz(mat);
        float tmp,temp;
        //Algoritmo Matriz Esaclonada
        for(int k=0;k<fila;k++){
            tmp = escalonada.mat[k][k];
            //Se transforma la fila
            for(int i=0;i<columna;i++){
                escalonada.mat[k][i] = escalonada.mat[k][i] / tmp;
            }
            //Se Restan las demas filas a la k contiene "1"
            for(int j=k+1;j<fila;j++){
                temp=mat[j][k];
                for(int p=0;p<columna;p++){
                    escalonada.mat[j][p] = escalonada.mat[j][p] - (escalonada.mat[k][p]*temp);
                }
            }
        }
        return escalonada;
    }

    public matriz inversa_matriz(){
        matriz t = new matriz(mat);
        matriz Inversa = new matriz(fila,columna);
        float tmp,temp;
       //Darle la forma [A:I]
        for(int i=0;i<fila;i++)
			for(int j=0;j<columna;j++){
				if(i==j)
					Inversa.mat[i][j]=1;
				else
					Inversa.mat[i][j]=0;
			}

        //Algoritmo Matriz Esaclonada
        for(int k=0;k<fila;k++){
            tmp=t.mat[k][k];
            //Se transforma la fila
            for(int i=0;i<columna;i++){
                t.mat[k][i] = t.mat[k][i]/tmp;
                Inversa.mat[k][i] = Inversa.mat[k][i]/tmp;
            }
            for(int j=k+1;j<fila;j++){
                temp = t.mat[j][k];
                for(int p=0;p<columna;p++){
                    t.mat[j][p] = t.mat[j][p]-(t.mat[k][p]*temp);
                    Inversa.mat[j][p] = Inversa.mat[j][p] - (Inversa.mat[k][p]*temp);
                }
            }
        }

	//Ahora se halla la Inversa
        for(int k=(fila-1);k>=0;k--){
			for(int j=(k-1);j>=0;j--){
                temp = t.mat[j][k];
				for(int p=0;p<columna;p++){
					t.mat[j][p] = t.mat[j][p]-(t.mat[k][p]*temp);
                    Inversa.mat[j][p] = Inversa.mat[j][p] - (Inversa.mat[k][p]*temp);
				}
			}
		}

        return Inversa;
    }

    public matriz adjunta(matriz uno){
        matriz adj = new matriz();
        float det = uno.determinante();
        adj = uno.inversa_matriz();
        adj = multiplica_real(adj, det);
        return adj;
    }

    public matriz cofactores(matriz uno){
        return uno.transpuesta();
    }

    public String info_matriz(){
        String inf="";
        inf += ""+nombre+"["+fila+"x"+columna+"] :\n";
        //inf += "El contenido de la matriz es :\n "+this.escribir_matriz()+"\n}";
        inf += ""+this.escribir_matriz()+"\n";
        return inf;
    }

    public String MatrizTexto(){
        String temp = ""+this.nombre+"={";
        for(int i=0;i<this.fila;i++){
            for(int j=0;j<this.columna;j++){
                temp+=this.mat[i][j];
                if(j<(columna - 1))
                    temp+=",";
            }
            if(i < (fila - 1))
                temp+=";";
         }

        temp += "}";

        return temp;
    }

    public String toString(){
        String temp = ""+this.nombre+"={";
        for(int i=0;i<this.fila;i++){
            for(int j=0;j<this.columna;j++){
                temp+=this.mat[i][j];
                if(j<(columna - 1))
                    temp+=",";
            }
            if(i < (fila - 1))
                temp+=";";
         }

        temp += "}";

        return temp;
    }

        public void Leer_matriz(){
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                System.out.print("Mat["+(i+1)+";"+(j+1)+"] : ");
                mat[i][j] = Leer.datoInt();
            }
            System.out.println();
        }
    }



}















