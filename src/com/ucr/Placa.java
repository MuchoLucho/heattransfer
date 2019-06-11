package com.ucr;

public class Placa {

    private double matriz [][];

    public void printer(){
        for (double[] doubles : this.matriz) {
            for (int j = 0; j < doubles.length; j++) {
                System.out.print(String.format("%.3f", doubles[j])+ " ");
            }
            System.out.println();
        }
    }

    public double getValue (int x, int y){
        return this.matriz[y][x];
    }

    public double setValue (int x, int y, double value){
        return this.matriz[y][x] = value;
    }

    public boolean esCobre(int pos){
        return pos < this.matriz[0].length/2;
    }

    protected void placaSegundo0() {
        this.matriz = new double [Constantes.getCANT_NODOS_Y()][Constantes.getCANT_NODOS_X()];
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

    protected Placa() {
        this.matriz = new double [Constantes.getCANT_NODOS_Y()][Constantes.getCANT_NODOS_X()];
    }

}
