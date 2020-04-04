package strategie;

import engine.grabTableFromWeb;
import engine.onlyValueTable;
import engine.sortTable;

public class insideBars {

    public static String[] insideBar() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double highOfMotherBar = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int i = 0; i < rows; i++) {
            if ((onlyDouble[1][i][1] + onlyDouble[1][i][2] > onlyDouble[0][i][1] + onlyDouble[0][i][2]) &&
                    (onlyDouble[1][i][2] < onlyDouble[0][i][2]) && (onlyDouble[1][i][1] > onlyDouble[0][i][1])) {
                highOfMotherBar = ((onlyDouble[0][i][1] + onlyDouble[0][i][2]) * 100) / (onlyDouble[1][i][1] + onlyDouble[1][i][2]);
                double roundOff = Math.round(highOfMotherBar) / 10.0;
                wynik[i][0] = Double.toString(roundOff);
                wynik[i][1] = tabelaFromWeb[0][i][0];
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
        System.out.println("Inside bars (kontynuacja trendu w trendzie wzrostowym - Często można ją znaleźć bezpośrednio w następstwie dużych ruchów w górę lub w dół i reprezentuje ona okres konsolidacji)");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

}
