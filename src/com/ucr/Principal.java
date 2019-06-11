package com.ucr;

import java.util.ArrayList;

public class Principal {

    private ArrayList <Placa> snapshot; //snapshot por segundo(s)

    public Principal() {
        snapshot = new ArrayList<>();
        snapshot.add(new Placa());
    }

    public void imprimirSnapshot(int segundo){
        this.snapshot.get(segundo).printer();
    }

    public double getTauAcero(){
        return (Constantes.getDIFU_TERM_ACER()*Constantes.getDeltaTAcero())/Math.pow(Constantes.getDIST_NODAL(),2);
    }

    public double getTauCobre(){
        return (Constantes.getDIFU_TERM_COB()*Constantes.getDeltaTCobre())/Math.pow(Constantes.getDIST_NODAL(),2);
    }

    public double getBiot(boolean esCobre){
        if (esCobre)
            return Constantes.getDIST_NODAL()/(Constantes.getRESIS_SOLDA()*Constantes.getCOND_TERM_COBR());
        return Constantes.getDIST_NODAL()/(Constantes.getRESIS_SOLDA()*Constantes.getCOND_TERM_ACER());
    }

    public double getT_ESQ_SUP_IZQ_COB(int segundo){
        double value = (2 * this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(1,0))
                + (snapshot.get(segundo-1).getValue(0,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(0,0) * (1 - 4 * this.getTauCobre()
                -4*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );
        return value;
    }

    public double getT_BORD_IZQ_COB(int segundo, int y){
        double value = (this.getTauCobre()
                * (2*(snapshot.get(segundo-1).getValue(1,y))
                + (snapshot.get(segundo-1).getValue(0,y+1)) + (snapshot.get(segundo-1).getValue(0,y-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(0,y) * (1 - 4 * this.getTauCobre()
                -2*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );

        return value;
    }

    public double getT_ESQ_INF_IZQ_COB(int segundo){
        double value = (2 * this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(1,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(0,Constantes.getCANT_NODOS_Y()-2))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(0,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
                -4*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );

        return value;
    }

    public double getT_BORD_SUP_COB(int segundo, int x){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(x-1,0))
                + (snapshot.get(segundo-1).getValue(x+1,0))
                + 2 * (snapshot.get(segundo-1).getValue(x,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(x,0) * (1 - 4 * this.getTauCobre()
                -2*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );

        return value;
    }

    public double getT_CENTR_COB(int segundo, int x, int y){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(x,y-1))
                + (snapshot.get(segundo-1).getValue(x+1,y))
                + (snapshot.get(segundo-1).getValue(x,y+1))
                + (snapshot.get(segundo-1).getValue(x-1,y)))
                + (snapshot.get(segundo-1).getValue(x,y) * (1 - 4 * this.getTauCobre()))

        );

        return value;
    }

    public double getT_BORD_INF_COB(int segundo, int x){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(x-1,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(x+1,Constantes.getCANT_NODOS_Y()-1))
                + 2 * (snapshot.get(segundo-1).getValue(x,Constantes.getCANT_NODOS_Y()-2))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(x,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
                -2 * this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );

        return value;
    }

    public double getT_ESQ_SUP_DER_COB(int segundo){
        double value = (2 * this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)-1,0))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()/2,1))
                + 2 * (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,0))* this.getBiot(true)
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()/2,0) * (1 - 4 * this.getTauCobre()
                -4 * this.getTauCobre()*( this.getBiot(true))))
        );

        return value;
    }

    public double getT_BORD_DER_COB(int segundo, int y){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),y-1))
                + 2 * (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)-1,y))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),y+2))
                + 2 * this.getBiot(true)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,y)))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()/2,y) * (1 - 4 * this.getTauCobre()
                -2 * this.getTauCobre()*( this.getBiot(true))))
        );

        return value;
    }

    public double getT_ESQ_INF_DER_COB(int segundo){
        double value = (2 * this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)-1,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),Constantes.getCANT_NODOS_Y()-2))
                + 2 *  this.getBiot(true)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-1)))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()/2,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
                -4 * this.getTauCobre()*( this.getBiot(true))))
        );

        return value;
    }

    public double getT_ESQ_SUP_IZQ_ACER(int segundo){
        double value = (2 * this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+2,0))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,1))
                + 2 *  this.getBiot(false)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),0)))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,0) * (1 - 4 * this.getTauAcero()
                -4 * this.getTauAcero()*( this.getBiot(false))))
        );

        return value;
    }

    public double getT_BORD_IZQ_ACER(int segundo, int y){
        double value = (this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,y-1))
                + 2 * (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+2,y))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,y+1))
                + 2 *  this.getBiot(false)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),y)))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,y) * (1 - 4 * this.getTauAcero()
                -2 * this.getTauAcero()*( this.getBiot(false))))
        );

        return value;
    }

    public double getT_ESQ_IZQ_INF_ACER(int segundo){
        double value = (2 * this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+2,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-2))
                + 2 *  this.getBiot(false)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),Constantes.getCANT_NODOS_Y()-1)))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
                -4 * this.getTauAcero()*( this.getBiot(false))))
        );

        return value;
    }

    public double getT_BORD_SUP_ACER(int segundo, int x){
        double value = (this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue(x-1,0))
                + (snapshot.get(segundo-1).getValue(x+1,0))
                + 2 * (snapshot.get(segundo-1).getValue(x,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(x,0) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );

        return value;
    }

    public double getT_CENT_ACER(int segundo, int x, int y){
        double value = (this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue(x,y-1))
                + (snapshot.get(segundo-1).getValue(x+1,y))
                + (snapshot.get(segundo-1).getValue(x,y+1))
                + (snapshot.get(segundo-1).getValue(x-1,y)))
                + (snapshot.get(segundo-1).getValue(x,y)
                * (1 - 4 * this.getTauAcero()))
        );
        return value;
    }

    public double getT_BORD_INF_ACER(int segundo,int x){
        double value = (this.getTauAcero()
                * (2*(snapshot.get(segundo-1).getValue(x,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(segundo-1).getValue(x-1,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(x+1,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL()/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(x,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );

        return value;
    }

    public double getT_ESQ_SUP_DER_ACER(int segundo){
        double value = (2 * this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,0))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,0) * (1 - 4 * this.getTauAcero()
                -4*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );

        return value;
    }

    public double getT_BORD_DER_ACER(int segundo, int y){
        double value = (this.getTauAcero()
                * (2*(snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,y))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,y+1)) +
                (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,y-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,y) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
        return value;
    }

    public double getT_ESQ_INF_DER_ACER(int segundo){
        double value = (2 * this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
                -4*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
        return value;
    }

//    public void aplicaFormulas(int deltaT){
//        for (int i = 0; i < Constantes.getCANT_NODOS_Y(); i++) {
//            for (int j = 0; j < Constantes.getCANT_NODOS_X(); j++) {
//                if (i == 0 && j == 0){
//
//                }
////                if(j < Constantes.getCANT_NODOS_X()/2){ // Es cobre
////                }else { // Es acero
////                }
//            }
//        }
//    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.snapshot.get(0).placaSegundo0();
        // principal.imprimirSnapshot(0);
        // System.out.println("---------------------------------");
        principal.snapshot.add(new Placa());
        // Esto imprime
        principal.imprimirSnapshot(1);
    }

}
