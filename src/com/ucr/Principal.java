package com.ucr;

public class Principal {
    private double matriz [][];

    public Principal() {
        this.matriz = new double [Constantes.getCANT_NODOS_X()][Constantes.getCANT_NODOS_Y()];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j < matriz[i].length/2){ // Es cobre
                    matriz[i][j] =  Constantes.getTEMP_COB();
                }else { // Es acero
                    if (j == (matriz[i].length/2)) // Es primera linea de acero?
                        matriz[i][j] =  Constantes.getTEMP_ACER_IZQ();
                    else if (j == matriz[i].length-1) // Es última linea de acero?
                        matriz[i][j] =  Constantes.getTEMP_ACER_DER();
                    else // acero del centro
                        matriz[i][j] =  Constantes.getTEMP_ACER_CENT();
                }
            }
        }
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.printer();

    }

    public void printer(){
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getT_ESQ_SUP_IZQ_COB(){
        return -1;
    }

    public static int getT_BORD_IZQ_COB(){
        return -1;
    }

    public static int getT_ESQ_INF_IZQ_COB(){
        return -1;
    }

    public static int getT_BORD_SUP_COB(){
        return -1;
    }

    public static int getT_CENTR_COB(){
        return -1;
    }

    public static int getT_BORD_INF_COB(){
        return -1;
    }

    public static int getT_ESQ_SUP_DER_COB(){
        return -1;
    }

    public static int getT_BORD_DER_COB(){
        return -1;
    }

    public static int getT_ESQ_INF_DER_COB(){
        return -1;
    }

    public static int getT_ESQ_SUP_IZQ_ACER(){
        return -1;
    }

    public static int getT_BORD_IZQ_ACER(){
        return -1;
    }

    public static int getT_ESQ_INF_ACER(){
        return -1;
    }

    public static int getT_BORD_SUP_ACER(){
        return -1;
    }

    public static int getT_CENT_ACER(){
        return -1;
    }

    public static int getT_BORD_INF_ACER(){
        return -1;
    }

    public static int getT_ESQ_SUP_DER_ACER(){
        return -1;
    }

    public static int getT_BORD_DER_ACER(){
        return -1;
    }

    public static int getT_ESQ_DER_ACER(){
        return -1;
    }

}
