package strategie;

import engine.grabTableFromWeb;
import engine.onlyValueTable;
import engine.sortTable;
import model.model;

import java.util.Arrays;

public class pinBars {

    public static void addedCoefficients() {
        String wartosc, nazwa, wolumen;
        final int rows = grabTableFromWeb.rowCount();
        Double[][][] fromOnlyDT = onlyValueTable.onlyDoubleTable();
        Double[][] onlyCoefficients = new Double[rows][3];
        double cienWDol = 0, tymczasowyCien;
        String[][][] tabelaKoncowa = new String[model.dayCount][rows][3];
        String[][][] tabelaPrzejsciowa = grabTableFromWeb.grabbing4();
        String[][] lastDay = new String[rows][2];
        for (int d = 0; d < model.dayCount; d++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 3; j++) {
                    if (fromOnlyDT[1][i][5] < fromOnlyDT[0][i][5]) { // jezeli wolumen z dnia wczorajszego jest mniejszy od dzisiejszego
                        if (fromOnlyDT[d][i][0] - fromOnlyDT[d][i][3] < 0) { // jeżeli plus
                            if (fromOnlyDT[d][i][1] - fromOnlyDT[d][i][3] < fromOnlyDT[d][i][3] - fromOnlyDT[d][i][0]) { //jezeli cien w gorę jest mniejszy od korpusu
                                double modulo = Math.abs(fromOnlyDT[d][i][0] - fromOnlyDT[d][i][3]); //roznica miedzy otwarciem a zamknieciem
                                tymczasowyCien = fromOnlyDT[d][i][0] - fromOnlyDT[d][i][2];
                                cienWDol = tymczasowyCien * 100 / modulo;
                            }
                        } else {
                            if (fromOnlyDT[d][i][1] - fromOnlyDT[d][i][0] < fromOnlyDT[d][i][0] - fromOnlyDT[d][i][3]) {
                                double modulo = Math.abs(fromOnlyDT[d][i][0] - fromOnlyDT[d][i][3]);
                                tymczasowyCien = fromOnlyDT[d][i][3] - fromOnlyDT[d][i][2];
                                cienWDol = tymczasowyCien * 100 / modulo;
                            }
                        }
                        double roundOff = Math.round(cienWDol) / 10.0;
                        onlyCoefficients[i][0] = roundOff;
                        wartosc = Double.toString(roundOff);
                        nazwa = tabelaPrzejsciowa[d][i][0];
                        wolumen = Double.toString(fromOnlyDT[d][i][5]);
                        tabelaKoncowa[d][i][1] = nazwa;
                        tabelaKoncowa[d][i][2] = wolumen;
                        if (fromOnlyDT[d][i][0] - fromOnlyDT[d][i][3] == 0) {
                            tabelaKoncowa[d][i][0] = "0";
                        } else {
                            tabelaKoncowa[d][i][0] = wartosc;
                        }
                    } else {
                        tabelaKoncowa[d][i][j] = "0";
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                lastDay[i][j] = tabelaKoncowa[0][i][j];
            }
        }
        System.out.println("Największe procentowo pin bary: ");
        sortTable.sortedTable(lastDay);
        //System.out.println(Arrays.deepToString(tabelaKoncowa));
        System.out.println();
    }

    public static String[] teoretycznyPinBar() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[0][j][4] > 0 && onlyDouble[0][j][0] < onlyDouble[0][j][3]
                    && onlyDouble[0][j][0] - onlyDouble[0][j][2] > 2 * (onlyDouble[0][j][0] + onlyDouble[0][j][3])
                    && onlyDouble[0][j][1] - onlyDouble[0][j][3] < 0.3 * (onlyDouble[0][j][3] - onlyDouble[0][j][0])) {
                roznica = onlyDouble[1][j][1] * 100 / onlyDouble[1][j][2];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }

            // gdy minus swiecy dzisiejszej0
            if (onlyDouble[0][j][4] > 0 && onlyDouble[0][j][0] > onlyDouble[0][j][3]
                    && onlyDouble[0][j][3] - onlyDouble[0][j][2] > 2 * (onlyDouble[0][j][3] + onlyDouble[0][j][0])
                    && onlyDouble[0][j][1] - onlyDouble[0][j][0] < 0.3 * (onlyDouble[0][j][0] - onlyDouble[0][j][3])) {
                roznica = onlyDouble[1][j][1] * 100 / onlyDouble[1][j][2];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                if (wynik[i][j] == null) {
                    wynik[i][j] = "0";
                }
            }
        }
        //System.out.println(Arrays.deepToString(wynik));
        System.out.println("Pin bar (cienie mają wybijać kluczowe poziomy wsparcia lub linie trednu, korpusy ponad tymi liniami/poziomami wspracia)");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

    public static String[] spadekIPinBar() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[1][j][0] > onlyDouble[1][j][3] && (onlyDouble[1][j][0] - onlyDouble[1][j][3]) / 2 > onlyDouble[0][j][1]
                    && onlyDouble[0][j][5] > onlyDouble[1][j][5]) {
                roznica = onlyDouble[0][j][5] * 100 / onlyDouble[1][j][5];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }

            if (onlyDouble[1][j][0] > onlyDouble[1][j][3] && onlyDouble[2][j][0] > onlyDouble[2][j][3]
                    && onlyDouble[1][j][4] + onlyDouble[2][j][4] < -6
                    && (onlyDouble[1][j][0] - onlyDouble[1][j][3]) / 2 > onlyDouble[0][j][1]
                    && onlyDouble[0][j][5] > onlyDouble[1][j][5]) {
                roznica = onlyDouble[0][j][5] * 100 / onlyDouble[1][j][5];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }

            if (onlyDouble[1][j][0] > onlyDouble[1][j][3] && onlyDouble[2][j][0] > onlyDouble[2][j][3]
                    && onlyDouble[3][j][0] > onlyDouble[3][j][3]
                    && onlyDouble[1][j][4] + onlyDouble[2][j][4] + onlyDouble[3][j][4] < -8
                    && (onlyDouble[1][j][0] - onlyDouble[1][j][3]) / 2 > onlyDouble[0][j][1]
                    && onlyDouble[0][j][5] > onlyDouble[1][j][5]) {
                roznica = onlyDouble[0][j][5] * 100 / onlyDouble[1][j][5];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }

        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                if (wynik[i][j] == null) {
                    wynik[i][j] = "0";
                }
            }
        }
        //System.out.println(Arrays.deepToString(wynik));
        System.out.println("Spadek/dynamiczny spadek i pin Bar");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

    // zrobic srednia 3 cieni pin bar

}

// zdanie: jeżeli otwarcie niżej niz zamkniecie, to modulo z roznica otwarcia i zamkniecia ma być mniejsza od roznicy otwarcia i minimalnego kursu
// zdanie: jeżeli otwarcie wyzej niz zamkniecie, to modulo z roznica otwarcia i zamkniecia ma być mniejsza od roznicy zamkniecia i minimalnego kursu
// zad: znajdz przy jak najwiekszym procentowym minusie, najwiekszy  przy jak najniższej cenie
