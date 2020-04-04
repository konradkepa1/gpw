package strategie;

import engine.grabTableFromWeb;
import engine.onlyValueTable;
import engine.sortTable;

public class marubozu {

    public static String[] marubozu() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[2][j][3] < onlyDouble[2][j][0] && onlyDouble[1][j][3] > onlyDouble[1][j][0] && onlyDouble[0][j][3] > onlyDouble[0][j][0]
                    && (onlyDouble[2][j][1] - onlyDouble[2][j][2]) / 2 + onlyDouble[2][j][2] > onlyDouble[0][j][3]) {
                roznica = onlyDouble[2][j][1] * 100 / onlyDouble[2][j][2];
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
        System.out.println("Dluga swieca + 2 wzrosty");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

    public static String[] czysteMarubozu() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[0][j][3] > onlyDouble[0][j][0] && onlyDouble[0][j][0].equals(onlyDouble[0][j][2])
                    && onlyDouble[0][j][3].equals(onlyDouble[0][j][1]) && !onlyDouble[1][j][0].equals(onlyDouble[1][j][3])
                    && Math.abs(onlyDouble[1][j][3] - onlyDouble[1][j][0]) != Math.abs(onlyDouble[1][j][1] - onlyDouble[1][j][2])
                    && Math.abs(onlyDouble[2][j][3] - onlyDouble[2][j][0]) != Math.abs(onlyDouble[2][j][1] - onlyDouble[2][j][2])
                    && Math.abs(onlyDouble[3][j][3] - onlyDouble[3][j][0]) != Math.abs(onlyDouble[3][j][1] - onlyDouble[3][j][2])) {
                roznica = onlyDouble[0][j][3] * 100 / onlyDouble[0][j][0];
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
        System.out.println("Czyste marubozu (nie stosowac w okresie konsolidacji) ");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

}
