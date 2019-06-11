package com.ucr;

class Placa {

    private double matriz [][];

    void printer(){
        for (double[] doubles : this.matriz) {
            for (double aDouble : doubles) {
                System.out.print(String.format("%.3f", aDouble) + " ");
                //System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    double getValue(int x, int y){
        return this.matriz[y][x];
    }

    void setValue(int x, int y, double value){
        this.matriz[y][x] = value;
    }

    void placaSegundo0() {
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

    Placa() {
        this.matriz = new double [Constantes.getCANT_NODOS_Y()][Constantes.getCANT_NODOS_X()];
    }

}
