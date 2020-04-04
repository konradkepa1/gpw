package engine;

import model.model;

public class onlyValueTable {

    public static Double[][][] onlyDoubleTable() {
        String[][][] a = grabTableFromWeb.grabbing4();
        final int rows = grabTableFromWeb.rowCount();
        Double[][][] doubleTableOnly = new Double[model.dayCount][rows][8]; //rows+1 bylo
        for (int d = 0; d < model.dayCount; d++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 7; j++) {
                    double kursOtwarciaVar = Double.parseDouble(a[d][i][3]);
                    double kursMaksymalnyVar = Double.parseDouble(a[d][i][4]);
                    double kursMinimalnyVar = Double.parseDouble(a[d][i][5]);
                    double kursZamknieciaVar = Double.parseDouble(a[d][i][6]);
                    double zmianaKursuVar = Double.parseDouble(a[d][i][7]);
                    double wolumenObrotuVar = Double.parseDouble(a[d][i][8]);
                    double liczbaTransakcjiVar = Double.parseDouble(a[d][i][9]);
                    double wartoscObrotuVar = Double.parseDouble(a[d][i][10]);
                    doubleTableOnly[d][i][0] = kursOtwarciaVar;
                    doubleTableOnly[d][i][1] = kursMaksymalnyVar;
                    doubleTableOnly[d][i][2] = kursMinimalnyVar;
                    doubleTableOnly[d][i][3] = kursZamknieciaVar;
                    doubleTableOnly[d][i][4] = zmianaKursuVar;
                    doubleTableOnly[d][i][5] = wolumenObrotuVar;
                    doubleTableOnly[d][i][6] = liczbaTransakcjiVar;
                    doubleTableOnly[d][i][7] = wartoscObrotuVar;
                }
            }
        }
        //System.out.println(Arrays.deepToString(doubleTableOnly));
        //System.out.println(rows);
        return doubleTableOnly;
    }
}
