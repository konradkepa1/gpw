package strategie;

import engine.grabTableFromWeb;
import engine.onlyValueTable;
import engine.sortTable;

public class formacjaMłota {

    public static String[] hummer() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[1][j][4] < -4 && onlyDouble[1][j][5] < onlyDouble[0][j][5] && onlyDouble[0][j][4] > 0
                    && onlyDouble[0][j][0] < onlyDouble[0][j][3] && onlyDouble[0][j][0] - onlyDouble[0][j][2] > 2 * (onlyDouble[0][j][0] + onlyDouble[0][j][3])
                    && onlyDouble[0][j][1] - onlyDouble[0][j][3] < 0.3 * (onlyDouble[0][j][3] - onlyDouble[0][j][0])) {  //onlyDouble[0][j][1] - onlyDouble[0][j][3] < 0.3 * (onlyDouble[0][j][3] - onlyDouble[0][j][0])
                roznica = onlyDouble[1][j][1] * 100 / onlyDouble[1][j][2];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }

            // gdy minus swiecy dzisiejszej
            if (onlyDouble[1][j][4] < -4 && onlyDouble[1][j][5] < onlyDouble[0][j][5] && onlyDouble[0][j][4] > 0
                    && onlyDouble[0][j][0] > onlyDouble[0][j][3] && onlyDouble[0][j][3] - onlyDouble[0][j][2] > 2 * (onlyDouble[0][j][3] + onlyDouble[0][j][0])
                    && onlyDouble[0][j][1] - onlyDouble[0][j][0] < 0.3 * (onlyDouble[0][j][0] - onlyDouble[0][j][3])) { //&& onlyDouble[0][j][1] - onlyDouble[0][j][0] < 0.3 * (onlyDouble[0][j][0] - onlyDouble[0][j][3])
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
        System.out.println("Formacja młota:");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

}
