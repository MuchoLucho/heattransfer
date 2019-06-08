package com.ucr;

public class Constantes {
    private  static double DIFU_TERM_COB = 0.000117;
    private  static double DIFU_TERM_ACER = 0.0000188;
    private  static double LARGO_COBRE = 0.05;
    private  static double LARGO_ACERO = 0.05;
    private  static double ANCHO_COB = 0.05;
    private  static double ANCHO_ACE = 0.05;
    private  static double DIST_NODAL = 0.0005;
    private  static int TEMP_INF = 300;
    private  static int TEMP_COB = 600;
    private  static int TEMP_ACER_IZQ = 1100;
    private  static int TEMP_ACER_CENT = 950;
    private  static int TEMP_ACER_DER = 800;
    private  static int COEF_CONV = 13;
    private  static int RESIS_SOLDA = 200000;
    private  static int COND_TERM_COBR = 401;
    private  static double COND_TERM_ACER = 60.5;
    private  static int TIEMPO = 20;


    public static int getCANT_NODOS_X(){
        return (int) ((int)(LARGO_ACERO+LARGO_COBRE)/DIST_NODAL);
    }

    public static int getCANT_NODOS_Y(){
        return (int) ((int)(ANCHO_ACE)/DIST_NODAL);
    }

    public static int getCOND_TERM_COBR() {
        return COND_TERM_COBR;
    }

    public static double getCOND_TERM_ACER() {
        return COND_TERM_ACER;
    }


    public static double getDIFU_TERM_COB() {
        return DIFU_TERM_COB;
    }

    public static void setDIFU_TERM_COB(double DIFU_TERM_COB) {
        Constantes.DIFU_TERM_COB = DIFU_TERM_COB;
    }

    public static double getDIFU_TERM_ACER() {
        return DIFU_TERM_ACER;
    }

    public static void setDIFU_TERM_ACER(double DIFU_TERM_ACER) {
        Constantes.DIFU_TERM_ACER = DIFU_TERM_ACER;
    }

    public static double getLARGO_COBRE() {
        return LARGO_COBRE;
    }

    public static void setLARGO_COBRE(double LARGO_COBRE) {
        Constantes.LARGO_COBRE = LARGO_COBRE;
    }

    public static double getLARGO_ACERO() {
        return LARGO_ACERO;
    }

    public static void setLARGO_ACERO(double LARGO_ACERO) {
        Constantes.LARGO_ACERO = LARGO_ACERO;
    }

    public static double getANCHO_COB() {
        return ANCHO_COB;
    }

    public static void setANCHO_COB(double ANCHO_COB) {
        Constantes.ANCHO_COB = ANCHO_COB;
    }

    public static double getANCHO_ACE() {
        return ANCHO_ACE;
    }

    public static void setANCHO_ACE(double ANCHO_ACE) {
        Constantes.ANCHO_ACE = ANCHO_ACE;
    }

    public static double getDIST_NODAL() {
        return DIST_NODAL;
    }

    public static void setDIST_NODAL(double DIST_NODAL) {
        Constantes.DIST_NODAL = DIST_NODAL;
    }

    public static int getTEMP_INF() {
        return TEMP_INF;
    }

    public static void setTEMP_INF(int TEMP_INF) {
        Constantes.TEMP_INF = TEMP_INF;
    }

    public static int getTEMP_COB() {
        return TEMP_COB;
    }

    public static void setTEMP_COB(int TEMP_COB) {
        Constantes.TEMP_COB = TEMP_COB;
    }

    public static int getTEMP_ACER_IZQ() {
        return TEMP_ACER_IZQ;
    }

    public static void setTEMP_ACER_IZQ(int TEMP_ACER_IZQ) {
        Constantes.TEMP_ACER_IZQ = TEMP_ACER_IZQ;
    }

    public static int getTEMP_ACER_CENT() {
        return TEMP_ACER_CENT;
    }

    public static void setTEMP_ACER_CENT(int TEMP_ACER_CENT) {
        Constantes.TEMP_ACER_CENT = TEMP_ACER_CENT;
    }

    public static int getTEMP_ACER_DER() {
        return TEMP_ACER_DER;
    }

    public static void setTEMP_ACER_DER(int TEMP_ACER_DER) {
        Constantes.TEMP_ACER_DER = TEMP_ACER_DER;
    }

    public static int getCOEF_CONV() {
        return COEF_CONV;
    }

    public static void setCOEF_CONV(int COEF_CONV) {
        Constantes.COEF_CONV = COEF_CONV;
    }

    public static int getRESIS_SOLDA() {
        return RESIS_SOLDA;
    }

    public static void setRESIS_SOLDA(int RESIS_SOLDA) {
        Constantes.RESIS_SOLDA = RESIS_SOLDA;
    }

    public static int getTIEMPO() {
        return TIEMPO;
    }

    public static void setTIEMPO(int TIEMPO) {
        Constantes.TIEMPO = TIEMPO;
    }
}
