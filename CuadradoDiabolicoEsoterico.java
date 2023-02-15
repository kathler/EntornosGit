import java.util.Scanner;

/**Esta clase sirve para detectar si una matriz es Diabólica y Esotérica en
 * alguna combinación entre ellas o ninguna
 *
 */

public class CuadradoDiabolicoEsoterico {

    private int[][] matriz;

    /**
     * Este constructor inicializa una matriz con las columnas y filas que se le pasan por parámetro
     * y luego la rellena con números que va introduciendo el usuario
     * @param entrada(int)
     * @return
     */
    public int[][] matriz(int entrada){
        Scanner sc=new Scanner(System.in);
        matriz=new int[entrada][entrada];

        for(int f=0; f< matriz.length; f++){
            for(int c=0; c<matriz[0].length; c++){
                matriz[f][c]=sc.nextInt();
            }
        }

        return matriz;
    }


    /**
     * Devuelve la matriz generada
     * @return
     */
    public int[][] getMatriz(){
        return matriz;
    }


    /**Esta función comprueba si la matriz introducida por parámetro suma lo mismo en cada fila, columna y diagonal
     * Sí da lo mismo cada suma la matriz es Diabólica y se devuelve true, en caso contrario se devuelve false
     * @param matriz(int[][])
     * @return
     */
    public boolean magicoDiabolico(int[][] matriz){
        int sumaColumna=0, sumaFila=0, sumaDiagonal=0, sumaDiagonalSecundaria=0, cm=0;
        boolean respuesta=false;

        for(int f=0, c=matriz.length-1; f< matriz.length; f++, c--){
            for(int j=0; j<matriz[0].length; j++){
                if(j==f) {
                    sumaColumna+=matriz[f][f];
                    sumaFila+=matriz[f][f];
                }
                else {
                    sumaColumna+=matriz[j][f];
                    sumaFila+=matriz[f][j];
                }
            }
            sumaDiagonal+=matriz[f][f];
            sumaDiagonalSecundaria+=matriz[f][c];

            if(sumaColumna==sumaFila)
                respuesta=true;
            else
                respuesta=false;
            cm=sumaColumna;
            sumaColumna=0;
            sumaFila=0;
        }
        if(cm==sumaDiagonal && cm==sumaDiagonalSecundaria)
            respuesta=true;
        else
            respuesta=false;
        return respuesta;
    }

    /**Esta función es la primera regla para ver si una matriz es Esotérica o no
     *comprueba cada valor de la matriz para ver si está dentro del rango natural que es la longitud al cuadrado
     *Por ejemplo en una matriz de 2x2 en el interior de la matriz tendría que haber números del 1 al 4
     *
     * @param matriz(int[][])
     * @return
     */
    public boolean reglaEsoterico1(int[][] matriz){
        int numMaximo=matriz.length* matriz.length;
        boolean respuesta=false;

        for(int[]fila:matriz){
            for(int elemento:fila){
                if(numMaximo>=elemento && elemento==matriz[matriz.length-1][matriz.length-1]){
                    respuesta=true;
                }
                else if(numMaximo<elemento || elemento==0) {
                    respuesta = false;
                    break;
                }
            }
        }
        return respuesta;
    }


    /**Es la segunda regla para decir si es Esotérico o no
     *en esta se comprueban si las filas, columnas y diagonales da lo mismo que el centro multiplicado por 4
     *y dividido entre la longitud de la matriz
     *
     * @param matriz(int[][])
     * @return
     */
    public boolean reglaEsoterico2(int[][] matriz){
        int sumaEsquinas=0, cm2=0, cm=0;
        boolean respuesta=false;

        for(int f=0; f< matriz.length; f++)
            cm+=matriz[f][f];
        cm2=(4*cm)/ matriz.length;
        sumaEsquinas=matriz[0][0]+matriz[0][matriz.length-1]+matriz[matriz.length-1][0]+matriz[matriz.length-1][matriz.length-1];
        if(sumaEsquinas==cm2)
            respuesta=true;
        else
            respuesta=false;
        return respuesta;
    }

    /**Es la tercera regla para dicidir si es Esotérico o no
     *en esta primero se mira si es de longitud par o impar y luego se aplican
     *distintos calculos dependiendo de la respuesta anterior
     *
     * @param matriz(int[][])
     * @return
     */
    public boolean reglaEsoterica3(int[][] matriz){
        int sumaLados=0, sumaCentro=0, cm2=0;
        boolean respuesta=false;

        if(matriz.length%2==0){
            cm2=matriz[0][0]
                    +matriz[0][matriz.length-1]
                    +matriz[matriz.length-1][0]
                    +matriz[matriz.length-1][matriz.length-1];

            sumaLados=matriz[(matriz.length)/2][0]
                    +matriz[(matriz.length)/2-1][0]
                    +matriz[0][(matriz.length)/2]
                    +matriz[0][(matriz.length)/2-1]
                    +matriz[matriz.length-1][(matriz.length)/2]
                    +matriz[matriz.length-1][(matriz.length)/2-1]
                    +matriz[(matriz.length)/2][matriz.length-1]
                    +matriz[(matriz.length)/2-1][matriz.length-1];

            sumaCentro=matriz[(matriz.length)/2][(matriz.length)/2]
                    +matriz[(matriz.length)/2][(matriz.length)/2-1]
                    +matriz[(matriz.length)/2-1][(matriz.length)/2]
                    +matriz[(matriz.length)/2-1][(matriz.length)/2-1];

            if(cm2==sumaLados/2 && cm2==sumaCentro)
                respuesta=true;
            else
                respuesta=false;
        }
        else{
            cm2=matriz[0][0]
                    +matriz[0][matriz.length-1]
                    +matriz[matriz.length-1][0]
                    +matriz[matriz.length-1][matriz.length-1];

            sumaLados=matriz[(matriz.length-1)/2][0]
                    +matriz[0][(matriz.length-1)/2]
                    +matriz[matriz.length-1][(matriz.length-1)/2]
                    +matriz[(matriz.length-1)/2][matriz.length-1];

            sumaCentro=matriz[(matriz.length-1)/2][(matriz.length-1)/2]*4;

            if(cm2==sumaLados && cm2==sumaCentro)
                respuesta=true;
            else
                respuesta=false;
        }
        return respuesta;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CuadradoDiabolicoEsoterico cuadrado = new CuadradoDiabolicoEsoterico();

        int entrada;

        do{
            entrada=sc.nextInt();
            if(entrada>=2) {
                cuadrado.matriz(entrada);
                if (cuadrado.magicoDiabolico(cuadrado.getMatriz())) {
                    if (cuadrado.reglaEsoterico1(cuadrado.getMatriz())
                            && cuadrado.reglaEsoterico2(cuadrado.getMatriz())
                            && cuadrado.reglaEsoterica3(cuadrado.getMatriz())) {
                        System.out.println("ESOTERICO\n");

                    } else
                        System.out.println("DIABOLICO\n");
                } else
                    System.out.println("NO\n");
            }
        }while(entrada >= 2 && entrada <= 1024);

    }
}
