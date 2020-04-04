package strategie;

import engine.grabTableFromWeb;
import engine.onlyValueTable;
import engine.sortTable;
import model.model;

public class outsideBar {
/*

    1) świecę odwrócenia poprzedzają przynajmniej 3 świece skierowane w przeciwnym kierunku do świecy odwrócenia.
            2) formacja pojawiła się na wsparciu bądź oporze co dodatkowo wzmocniło jej siłę. Ten warunek wymaga pewnego doświadczenia w wyznaczaniu stref wsparcia i oporu.
*/

    public static String[] outsideFourBars() {
        final Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[3][j][0] > onlyDouble[3][j][3] && onlyDouble[2][j][0] > onlyDouble[2][j][3]
                    && onlyDouble[1][j][0] > onlyDouble[1][j][3] && onlyDouble[0][j][0] < onlyDouble[0][j][3]
                    && onlyDouble[0][j][1] + onlyDouble[0][j][2] > onlyDouble[1][j][1] + onlyDouble[1][j][2]
                    && onlyDouble[0][j][1] > onlyDouble[1][j][1] && onlyDouble[0][j][2] < onlyDouble[1][j][2]) {
                roznica = (onlyDouble[1][j][1] - onlyDouble[1][j][2]) * 100 / (onlyDouble[0][j][1] - onlyDouble[0][j][2]);
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
        System.out.println("Outside bar - dodatkowa siła gdy pojawia się na wsparciu bądź oporze");
        System.out.println("Outside Bar - 4 bars)");
        sortTable.sortedTable(wynik);
        return null;
    }

    public static String[] outsideThreeBars() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[2][j][0] > onlyDouble[2][j][3]
                    && onlyDouble[1][j][0] > onlyDouble[1][j][3] && onlyDouble[0][j][0] < onlyDouble[0][j][3]
                    && onlyDouble[0][j][1] + onlyDouble[0][j][2] > onlyDouble[1][j][1] + onlyDouble[1][j][2]
                    && onlyDouble[0][j][1] > onlyDouble[1][j][1] && onlyDouble[0][j][2] < onlyDouble[1][j][2]) {
                roznica = (onlyDouble[1][j][1] - onlyDouble[1][j][2]) * 100 / (onlyDouble[0][j][1] - onlyDouble[0][j][2]);
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
        System.out.println("Outside Bar - 3 bars)");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

    /*public static String[] outsideTwoBars() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[1][j][0] > onlyDouble[1][j][3] && onlyDouble[0][j][0] < onlyDouble[0][j][3]
                    && onlyDouble[0][j][1] + onlyDouble[0][j][2] > onlyDouble[1][j][1] + onlyDouble[1][j][2]
                    && onlyDouble[0][j][1] > onlyDouble[1][j][1] && onlyDouble[0][j][2] < onlyDouble[1][j][2]) {
                roznica = (onlyDouble[1][j][1] - onlyDouble[1][j][2]) * 100 / (onlyDouble[0][j][1] - onlyDouble[0][j][2]);
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
        System.out.println("Outside Bar - 2 bars)");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }*/


}
