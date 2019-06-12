package com.ucr;

import javax.swing.*;
import java.util.ArrayList;

public class Principal {

    private ArrayList <Placa> snapshot; //snapshot por segundo(s)

    public Principal() {
        snapshot = new ArrayList<>();
        snapshot.add(new Placa());
        snapshot.get(0).placaSegundo0();
    }

    public void imprimirSnapshot(){
        this.snapshot.get(0).printer();
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

    public double getT_ESQ_SUP_IZQ_COB(){
        return (2 * this.getTauCobre()
                * ((snapshot.get(0).getValue(1,0))
                + (snapshot.get(0).getValue(0,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(0).getValue(0,0) * (1 - 4 * this.getTauCobre()
                -4*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );
    }

    public double getT_BORD_IZQ_COB(int y){
        return (this.getTauCobre()
                * (2*(snapshot.get(0).getValue(1,y))
                + (snapshot.get(0).getValue(0,y+1)) + (snapshot.get(0).getValue(0,y-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(0).getValue(0,y) * (1 - 4 * this.getTauCobre()
                -2*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );
    }

    public double getT_ESQ_INF_IZQ_COB(){
        return (2 * this.getTauCobre()
                * ((snapshot.get(0).getValue(1,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(0).getValue(0,Constantes.getCANT_NODOS_Y()-2))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(0).getValue(0,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
                -4*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );
    }

    public double getT_BORD_SUP_COB(int x){
        return (this.getTauCobre()
                * ((snapshot.get(0).getValue(x-1,0))
                + (snapshot.get(0).getValue(x+1,0))
                + 2 * (snapshot.get(0).getValue(x,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(0).getValue(x,0) * (1 - 4 * this.getTauCobre()
                -2*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );
    }

    public double getT_CENTR_COB(int x, int y){
        return (this.getTauCobre()
                * ((snapshot.get(0).getValue(x,y-1))
                + (snapshot.get(0).getValue(x+1,y))
                + (snapshot.get(0).getValue(x,y+1))
                + (snapshot.get(0).getValue(x-1,y)))
                + (snapshot.get(0).getValue(x,y) * (1 - 4 * this.getTauCobre()))

        );
    }

    public double getT_BORD_INF_COB(int x){
        return (this.getTauCobre()
                * ((snapshot.get(0).getValue(x-1,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(0).getValue(x+1,Constantes.getCANT_NODOS_Y()-1))
                + 2 * (snapshot.get(0).getValue(x,Constantes.getCANT_NODOS_Y()-2))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(0).getValue(x,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
                -2 * this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );
    }

    public double getT_ESQ_SUP_DER_COB(){
        return (2 * this.getTauCobre()
                * ((snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)-1,0))
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()/2,1))
                + 2 * (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,0))* this.getBiot(true)
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()/2,0) * (1 - 4 * this.getTauCobre()
                -4 * this.getTauCobre()*( this.getBiot(true))))
        );
    }

    public double getT_BORD_DER_COB(int y){
        return (this.getTauCobre()
                * ((snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2),y-1))
                + 2 * (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)-1,y))
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2),y+1))
                + 2 * this.getBiot(true)* (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,y)))
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()/2,y) * (1 - 4 * this.getTauCobre()
                -2 * this.getTauCobre()*( this.getBiot(true))))
        );
    }

    public double getT_ESQ_INF_DER_COB(){
        return (2 * this.getTauCobre()
                * ((snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)-1,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2),Constantes.getCANT_NODOS_Y()-2))
                + 2 *  this.getBiot(true)* (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-1)))
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()/2,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
                -4 * this.getTauCobre()*( this.getBiot(true))))
        );
    }

    public double getT_ESQ_SUP_IZQ_ACER(){
        return (2 * this.getTauAcero()
                * ((snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+2,0))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,1))
                + 2 *  this.getBiot(false)* (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2),0)))
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,0) * (1 - 4 * this.getTauAcero()
                -4 * this.getTauAcero()*( this.getBiot(false))))
        );
    }

    public double getT_BORD_IZQ_ACER(int y){
        return (this.getTauAcero()
                * ((snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,y-1))
                + 2 * (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+2,y))
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,y+1))
                + 2 *  this.getBiot(false)* (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2),y)))
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,y) * (1 - 4 * this.getTauAcero()
                -2 * this.getTauAcero()*( this.getBiot(false))))
        );
    }

    public double getT_ESQ_IZQ_INF_ACER(){
        return (2 * this.getTauAcero()
                * ((snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+2,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-2))
                + 2 *  this.getBiot(false)* (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2),Constantes.getCANT_NODOS_Y()-1)))
                + (snapshot.get(0).getValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
                -4 * this.getTauAcero()*( this.getBiot(false))))
        );
    }

    public double getT_BORD_SUP_ACER(int x){
        return (this.getTauAcero()
                * ((snapshot.get(0).getValue(x-1,0))
                + (snapshot.get(0).getValue(x+1,0))
                + 2 * (snapshot.get(0).getValue(x,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(0).getValue(x,0) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
    }

    public double getT_CENT_ACER(int x, int y){
        return (this.getTauAcero()
                * ((snapshot.get(0).getValue(x,y-1))
                + (snapshot.get(0).getValue(x+1,y))
                + (snapshot.get(0).getValue(x,y+1))
                + (snapshot.get(0).getValue(x-1,y)))
                + (snapshot.get(0).getValue(x,y)
                * (1 - 4 * this.getTauAcero()))
        );
    }

    public double getT_BORD_INF_ACER(int x){
        return (this.getTauAcero()
                * (2*(snapshot.get(0).getValue(x,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(0).getValue(x-1,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(0).getValue(x+1,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL()/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(0).getValue(x,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
    }

    public double getT_ESQ_SUP_DER_ACER(){
        return (2 * this.getTauAcero()
                * ((snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-2,0))
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,0) * (1 - 4 * this.getTauAcero()
                -4*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
    }

    public double getT_BORD_DER_ACER(int y){
        return (this.getTauAcero()
                * (2*(snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-2,y))
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,y+1)) +
                (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,y-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,y) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
    }

    public double getT_ESQ_INF_DER_ACER(){
        return (2 * this.getTauAcero()
                * ((snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-2,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(0).getValue(Constantes.getCANT_NODOS_X()-1,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
                -4*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );
    }

    public void aplicaFormulas(){

        for (int y = 0; y < Constantes.getCANT_NODOS_Y(); y++) {
            for (int x = 0; x < Constantes.getCANT_NODOS_X(); x++) {
                if (y == 0 && x == 0){ // Esquina superior izquierda de cobre
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_SUP_IZQ_COB());
                }else if(x == 0 && y == Constantes.getCANT_NODOS_Y()-1){ // Esquina inferior izquierda de cobre
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_INF_IZQ_COB());
                }else if(x == (Constantes.getCANT_NODOS_X())/2 && y == 0){ // Esquina superior derecha de cobre
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_SUP_DER_COB());
                }else if(x == (Constantes.getCANT_NODOS_X())/2 && y == Constantes.getCANT_NODOS_Y()-1){//Esquina inferior derecha de cobre
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_INF_DER_COB());
                }else if(x == (Constantes.getCANT_NODOS_X()/2)+1 && y == 0){//Esquina superior izquierda de acero
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_SUP_IZQ_ACER());
                }else if(x == (Constantes.getCANT_NODOS_X()/2)+1 && y == Constantes.getCANT_NODOS_Y()-1){//Esquina inferior izquierda de acero
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_IZQ_INF_ACER());
                }else if(x == Constantes.getCANT_NODOS_X()-1 && y == 0){//Esquina superior derecha de acero
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_SUP_DER_ACER());
                }else if(x == Constantes.getCANT_NODOS_X()-1 && y == Constantes.getCANT_NODOS_Y()-1){//Esquina inferior derecha de acero
                    snapshot.get(1).setValue(x,y,this.getT_ESQ_INF_DER_ACER());
                }else if(x < Constantes.getCANT_NODOS_X()/2 && y == 0){//Borde superior de cobre
                    snapshot.get(1).setValue(x,y,this.getT_BORD_SUP_COB(x));
                }else if(x == 0 && y < Constantes.getCANT_NODOS_Y()-1){//Borde Izquierdo de cobre
                    snapshot.get(1).setValue(x,y,this.getT_BORD_IZQ_COB(y));
                }else if(x < Constantes.getCANT_NODOS_X()/2 && y == Constantes.getCANT_NODOS_Y()-1){//Borde inferior de cobre
                    snapshot.get(1).setValue(x,y,this.getT_BORD_INF_COB(x));
                }else if(x == (Constantes.getCANT_NODOS_X()/2)){//Borde derecho de cobre
                    snapshot.get(1).setValue(x,y,this.getT_BORD_DER_COB(y));
                }else if(x == (Constantes.getCANT_NODOS_X()/2)+1){//Borde izquierdo de acero
                    snapshot.get(1).setValue(x,y,this.getT_BORD_IZQ_ACER(y));
                }else if(y == 0){//Borde superior de acero
                    snapshot.get(1).setValue(x,y,this.getT_BORD_SUP_ACER(x));
                }else if(y == Constantes.getCANT_NODOS_Y()-1){//Borde inferior de acero
                    snapshot.get(1).setValue(x,y,this.getT_BORD_INF_ACER(x));
                }else if(x == Constantes.getCANT_NODOS_X()-1){//Borde derecho acero
                    snapshot.get(1).setValue(x,y,this.getT_BORD_DER_ACER(y));
                }else if(x < Constantes.getCANT_NODOS_X()/2){//Centro de cobre
                    snapshot.get(1).setValue(x,y,this.getT_CENTR_COB(x,y));
                }else {//Centro de acero
                    snapshot.get(1).setValue(x,y,this.getT_CENT_ACER(x,y));
                }
            }
        }
    }

    public void iterar(int veces){
        for (int i = 1; i <= veces; i++) {
            snapshot.add(new Placa());
            aplicaFormulas();
            //snapshot.get(1).printer();
            //System.out.println(snapshot.size());
            snapshot.remove(0);
        }
    }

    public double getNodoTiempo(int x, int y, int iteracion){
        iterar(iteracion);
        return snapshot.get(0).getValue(x,y);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        int x = 150;
        int y = 43;
        int iteracion = 200000;
        JOptionPane.showMessageDialog(null,"Temperatura del nodo x:"+x+", y:"+y+" tiempo: "+iteracion+" es "+principal.getNodoTiempo(x,y,iteracion)+" K");
        principal.imprimirSnapshot();
    }
}
