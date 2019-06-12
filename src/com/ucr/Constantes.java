package com.ucr;

public class Constantes {
    private static double DIFU_TERM_COB = 0.000117;
    private static double DIFU_TERM_ACER = 0.0000188;
    private static double LADO_PLACA = 0.05;
    private static double DIST_NODAL = 0.0005;
    private static double TEMP_INF = 300;
    private static double TEMP_COB = 600;
    private static double TEMP_ACER_IZQ = 1100;
    private static double TEMP_ACER_CENT = 950;
    private static double TEMP_ACER_DER = 800;
    private static int COEF_CONV = 13;
    private static double RESIS_SOLDA = 0.00002;
    private static int COND_TERM_COBR = 401;
    private static double COND_TERM_ACER = 63.9;
    private static int TIEMPO = 20;
    private static double DELTA_T_COBRE = 0.000059;
    private static double DELTA_T_ACERO = 0.00039;

    public static double getDeltaTCobre() {
        return DELTA_T_COBRE;
    }

    public static double getDeltaTAcero() {
        return DELTA_T_ACERO;
    }

    static int getCANT_NODOS_X(){
        return (int) ((2* LADO_PLACA)/DIST_NODAL);
    }

    static int getCANT_NODOS_Y(){
        return (int) ((LADO_PLACA)/DIST_NODAL);
    }

    static int getCOND_TERM_COBR() {
        return COND_TERM_COBR;
    }

    static double getCOND_TERM_ACER() {
        return COND_TERM_ACER;
    }

    static double getDIFU_TERM_COB() {
        return DIFU_TERM_COB;
    }

    static double getDIFU_TERM_ACER() {
        return DIFU_TERM_ACER;
    }

    public static double getLADO_PLACA() {
        return LADO_PLACA;
    }

    public static void setLADO_PLACA(double LADO_PLACA) {
        Constantes.LADO_PLACA = LADO_PLACA;
    }

    static double getDIST_NODAL() {
        return DIST_NODAL;
    }

    public static void setDIST_NODAL(double DIST_NODAL) {
        Constantes.DIST_NODAL = DIST_NODAL;
    }

    static double getTEMP_INF() {
        return TEMP_INF;
    }

    public static void setTEMP_INF(double TEMP_INF) {
        Constantes.TEMP_INF = TEMP_INF;
    }

    static double getTEMP_COB() {
        return TEMP_COB;
    }

    public static void setTEMP_COB(double TEMP_COB) {
        Constantes.TEMP_COB = TEMP_COB;
    }

    static double getTEMP_ACER_IZQ() {
        return TEMP_ACER_IZQ;
    }

    public static void setTEMP_ACER_IZQ(double TEMP_ACER_IZQ) {
        Constantes.TEMP_ACER_IZQ = TEMP_ACER_IZQ;
    }

    static double getTEMP_ACER_CENT() {
        return TEMP_ACER_CENT;
    }

    public static void setTEMP_ACER_CENT(double TEMP_ACER_CENT) {
        Constantes.TEMP_ACER_CENT = TEMP_ACER_CENT;
    }

    static double getTEMP_ACER_DER() {
        return TEMP_ACER_DER;
    }

    public static void setTEMP_ACER_DER(double TEMP_ACER_DER) {
        Constantes.TEMP_ACER_DER = TEMP_ACER_DER;
    }

    static int getCOEF_CONV() {
        return COEF_CONV;
    }

    static double getRESIS_SOLDA() {
        return RESIS_SOLDA;
    }

    public static int getTIEMPO() {
        return TIEMPO;
    }

    public static void setTIEMPO(int TIEMPO) {
        Constantes.TIEMPO = TIEMPO;
    }
}
