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

    public double getT_BORD_IZQ_COB(int segundo){
        double value = (this.getTauCobre()
                * (2*(snapshot.get(segundo-1).getValue(1,1))
                + (snapshot.get(segundo-1).getValue(0,2)) + (snapshot.get(segundo-1).getValue(0,0))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(0,1) * (1 - 4 * this.getTauCobre()
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

    public double getT_BORD_SUP_COB(int segundo){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(2,0))
                + (snapshot.get(segundo-1).getValue(4,0))
                + 2 * (snapshot.get(segundo-1).getValue(3,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(3,0) * (1 - 4 * this.getTauCobre()
                -2*this.getTauCobre()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR()))
        );

        return value;
    }

    public double getT_CENTR_COB(int segundo){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(3,Constantes.getCANT_NODOS_Y()-3))
                + (snapshot.get(segundo-1).getValue(4,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(segundo-1).getValue(3,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(1,Constantes.getCANT_NODOS_Y()-2)))
                + (snapshot.get(segundo-1).getValue(3,Constantes.getCANT_NODOS_Y()-2) * (1 - 4 * this.getTauCobre()))

        );

        return value;
    }

    public double getT_BORD_INF_COB(int segundo){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue(2,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(4,Constantes.getCANT_NODOS_Y()-1))
                + 2 * (snapshot.get(segundo-1).getValue(3,Constantes.getCANT_NODOS_Y()-2))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_COBR())
                + (snapshot.get(segundo-1).getValue(3,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauCobre()
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

    public double getT_BORD_DER_COB(int segundo){
        double value = (this.getTauCobre()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),0))
                + 2 * (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)-1,1))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),2))
                + 2 * this.getBiot(true)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,1)))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()/2,1) * (1 - 4 * this.getTauCobre()
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

    public double getT_BORD_IZQ_ACER(int segundo){
        double value = (this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,0))
                + 2 * (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+2,1))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,2))
                + 2 *  this.getBiot(false)* (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2),1)))
                + (snapshot.get(segundo-1).getValue((Constantes.getCANT_NODOS_X()/2)+1,1) * (1 - 4 * this.getTauAcero()
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

    public double getT_BORD_SUP_ACER(int segundo){
        double value = (this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-4,0))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,0))
                + 2 * (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,1))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,0) * (1 - 4 * this.getTauAcero()
                -2*this.getTauAcero()*(Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER()))
        );

        return value;
    }

    public double getT_CENT_ACER(int segundo){
        double value = (this.getTauAcero()
                * ((snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-3))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-4,Constantes.getCANT_NODOS_Y()-2)))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-2)
                * (1 - 4 * this.getTauAcero()))

        );

        return value;
    }

    public double getT_BORD_INF_ACER(int segundo){
        double value = (this.getTauAcero()
                * (2*(snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-2))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-4,Constantes.getCANT_NODOS_Y()-1))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,Constantes.getCANT_NODOS_Y()-1))
                + 2 * Constantes.getTEMP_INF() * Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL()/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-1) * (1 - 4 * this.getTauAcero()
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

    public double getT_BORD_DER_ACER(int segundo){
        double value = (this.getTauAcero()
                * (2*(snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-2,1))
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,2)) + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,0))
                + 2 * Constantes.getTEMP_INF() * (Constantes.getCOEF_CONV()*Constantes.getDIST_NODAL())/Constantes.getCOND_TERM_ACER())
                + (snapshot.get(segundo-1).getValue(Constantes.getCANT_NODOS_X()-1,1) * (1 - 4 * this.getTauAcero()
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

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.snapshot.get(0).placaSegundo0();
        principal.imprimirSnapshot(0);
        System.out.println("---------------------------------");
        principal.snapshot.add(new Placa());
        // get(1) significa segundo 1
        // setvalue es el nodo que vamos a afectar
        // el tercer parametro es la funcion
        // Función 1
        principal.snapshot.get(1).setValue(0,0,principal.getT_ESQ_SUP_IZQ_COB(1));
        // Función 2
        principal.snapshot.get(1).setValue(0,1,principal.getT_BORD_IZQ_COB(1));
        // Función 3
        principal.snapshot.get(1).setValue(0,Constantes.getCANT_NODOS_Y()-1,principal.getT_ESQ_INF_IZQ_COB(1));
        // Función 4
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()-1,0,principal.getT_ESQ_SUP_DER_ACER(1));
        // Función 5
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()-1,Constantes.getCANT_NODOS_Y()-1,principal.getT_ESQ_INF_DER_ACER(1));
        // Función 6
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()-1,1,principal.getT_BORD_DER_ACER(1));
        // Función 7
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-1,principal.getT_BORD_INF_ACER(1));
        // Función 8
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()-3,Constantes.getCANT_NODOS_Y()-2,principal.getT_CENT_ACER(1));
        // Función 9
        principal.snapshot.get(1).setValue(3,Constantes.getCANT_NODOS_Y()-2,principal.getT_CENTR_COB(1));
        // Función 10
        principal.snapshot.get(1).setValue(3,0,principal.getT_BORD_SUP_COB(1));
        // Función 11
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()-3,0,principal.getT_BORD_SUP_ACER(1));
        // Función 12
        principal.snapshot.get(1).setValue(3,Constantes.getCANT_NODOS_Y()-1,principal.getT_BORD_INF_COB(1));
        // Función 13
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()/2,0,principal.getT_ESQ_SUP_DER_COB(1));
        // Función 14
        principal.snapshot.get(1).setValue(Constantes.getCANT_NODOS_X()/2,1,principal.getT_BORD_DER_COB(1));
        // Función 15
        principal.snapshot.get(1).setValue((Constantes.getCANT_NODOS_X()/2),Constantes.getCANT_NODOS_Y()-1,principal.getT_ESQ_INF_DER_COB(1));
        // Función 16
        principal.snapshot.get(1).setValue((Constantes.getCANT_NODOS_X()/2)+1,0,principal.getT_ESQ_SUP_IZQ_ACER(1));
        // Función 17
        principal.snapshot.get(1).setValue((Constantes.getCANT_NODOS_X()/2)+1,1,principal.getT_BORD_IZQ_ACER(1));
        // Función 18
        principal.snapshot.get(1).setValue((Constantes.getCANT_NODOS_X()/2)+1,Constantes.getCANT_NODOS_Y()-1,principal.getT_ESQ_IZQ_INF_ACER(1));

        // Esto imprime
        principal.imprimirSnapshot(1);

//        System.out.println("---------------------------------");
//        principal.snapshot.add(new Placa());
//        principal.imprimirSnapshot(2);
    }

}
