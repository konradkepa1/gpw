package strategie;

import engine.grabTableFromWeb;
import engine.onlyValueTable;
import engine.sortTable;

public class fakeyPinBars {

    public static String[] fakeyBars() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        for (int j = 0; j < rows; j++) {
            if ((onlyDouble[2][j][1] + onlyDouble[2][j][2] > onlyDouble[1][j][1] + onlyDouble[1][j][2]) &&
                    (onlyDouble[2][j][2] < onlyDouble[1][j][2]) && (onlyDouble[2][j][1] > onlyDouble[1][j][1])) {
                if (onlyDouble[0][j][2] < onlyDouble[2][j][2]) {  // jezeli swieca dzis jest ponizej tej z przedwczoraj
                    if (onlyDouble[0][j][0] - onlyDouble[0][j][3] < 0) { // kiedy plus swiecy dzis
                        if (onlyDouble[0][j][0] > onlyDouble[2][j][2]) { // kiedy korpus zamyka się powyzej minimalnego cienia z przedwczoraj
                            roznica = onlyDouble[0][j][2] * 100 / onlyDouble[2][j][2];
                            double roundOff = Math.round(roznica) / 10.0;
                            wynik[j][0] = Double.toString(roundOff);
                            wynik[j][1] = tabelaFromWeb[0][j][0];
                        }
                    } else {
                        if (onlyDouble[0][j][3] > onlyDouble[2][j][2]) { // kiedy korpus zamyka się powyzej minimalnego cienia z przedwczoraj
                            roznica = onlyDouble[0][j][2] * 100 / onlyDouble[2][j][2];
                            double roundOff = Math.round(roznica) / 10.0;
                            wynik[j][0] = Double.toString(roundOff);
                            wynik[j][1] = tabelaFromWeb[0][j][0];
                        }

                    }

                }

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
        System.out.println("Fakey Bars (w tredzie wzrostowym wybicie cieniem w dół oznacza fake spadek)");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }

    public static String[] fakeyBars2() {
        Double[][][] onlyDouble = onlyValueTable.onlyDoubleTable();
        String[][][] tabelaFromWeb = grabTableFromWeb.grabbing4();
        double roznica = 0;
        int rows = grabTableFromWeb.rowCount();
        String[][] wynik = new String[rows][2];

        // dwie opcje: z pina barem i te 4 swiece: https://fxlikeapro.weebly.com/articles-strategies--tips/fakey-trading-strategy-inside-bar-false-break-out

        for (int j = 0; j < rows; j++) {
            if (onlyDouble[2][j][0] > onlyDouble[2][j][3] && onlyDouble[1][j][0] < onlyDouble[0][j][3]
                    && onlyDouble[0][j][0] < onlyDouble[0][j][3] && onlyDouble[2][j][1] > onlyDouble[1][j][1]
                    && onlyDouble[2][j][2] < onlyDouble[1][j][2] && onlyDouble[2][j][1] > onlyDouble[0][j][1]
                    && onlyDouble[0][j][1] < onlyDouble[2][j][1] && onlyDouble[0][j][0] - onlyDouble[0][j][2] > onlyDouble[0][j][3] - onlyDouble[0][j][0]
                    && onlyDouble[0][j][1] - onlyDouble[2][j][3] < onlyDouble[0][j][0] - onlyDouble[0][j][2]
                    && onlyDouble[0][j][2] < onlyDouble[2][j][2] && onlyDouble[0][j][0] > onlyDouble[1][j][0]) {
                roznica = onlyDouble[0][j][0] * 100 / onlyDouble[0][j][2];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = tabelaFromWeb[0][j][0];
            }
            if (onlyDouble[3][j][0] > onlyDouble[3][j][3] && onlyDouble[2][j][0] < onlyDouble[2][j][3]
                    && onlyDouble[1][j][0] > onlyDouble[1][j][3] && onlyDouble[0][j][0] < onlyDouble[0][j][3]
                    && onlyDouble[3][j][1] > onlyDouble[2][j][1] && onlyDouble[3][j][2] < onlyDouble[2][j][2]
                    && onlyDouble[1][j][0] < onlyDouble[0][j][3] && onlyDouble[1][j][3] >= onlyDouble[0][j][0]
                    && onlyDouble[0][j][3] > onlyDouble[2][j][1]) {
                roznica = onlyDouble[0][j][0] * 100 / onlyDouble[0][j][2];
                double roundOff = Math.round(roznica) / 10.0;
                wynik[j][0] = Double.toString(roundOff);
                wynik[j][1] = "*" + tabelaFromWeb[0][j][0];
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
        System.out.println("Fakey bars::");
        sortTable.sortedTable(wynik);
        System.out.println();
        return null;
    }


}

// zadanie: stwórz zestawienie 4 świec w którym jest malejący sukcesywnie wolumen  (i teraz pytanie czy potrzeba dynamicznych spadkow oraz czy
// potrzeba by 3 swiece były wewnątrz świecy matki oraz czy musi być rosnący wykres tych trzech wewnątrz inside baru)
