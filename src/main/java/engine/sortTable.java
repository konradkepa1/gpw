package engine;

import java.util.Arrays;
import java.util.Comparator;

public class sortTable {

    public static void sortedTable(String[][] tabelaKoncowa) {

        Arrays.sort(tabelaKoncowa, new Comparator<String[]>() {
            @Override
            public int compare(String[] first, String[] second) {
                return Double.valueOf(second[0]).compareTo(
                        Double.valueOf(first[0]));
            }
        });

        System.out.print(Arrays.deepToString(tabelaKoncowa));
        System.out.println();
    }
}