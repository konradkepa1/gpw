package engine;

import model.model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class grabTableFromWeb {

    public static void main(String[] args) {
        grabbing4();
    }

    public static int rowCount() {
        int rowCount = 0;
        int[] rowCountTable = new int[model.dayCount];
        for (int i = 0; i < rowCountTable.length; i++) {
            try {
                final Document document = Jsoup.connect(model.mainDates[i]).get();
                for (Element row : document.select("section.mainContainer.padding-left-20.padding-right-20.padding-bottom-20.quotations-archive")) {
                    if (row.select("tr").text().equals("")) {
                        continue;
                    } else {
                        final String ticker = row.select("tr").text();
                        Pattern p = Pattern.compile(model.waluta);
                        Matcher m = p.matcher(ticker);
                        while (m.find()) {
                            rowCount++; //447
                        }
                        rowCountTable[i] = rowCount;
                        rowCount = 0;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Arrays.sort(rowCountTable);
        int lowestRows = rowCountTable[0];
        //System.out.println(Arrays.toString(rowCountTable));
        return lowestRows;
    }

    public static String[][][] grabbing4() {
        String[][][] ary;
        int rows = grabTableFromWeb.rowCount();
        int cols = 11;
        int firstRow = 1;

        ary = new String[model.dayCount][rows][cols]; // rzędy: 4 kolumny: 447

        for (int i = 0; i < model.dayCount; i++) {
            try {
                final Document document = Jsoup.connect(model.mainDates[i]).get();
                for (Element allTable : document.select(
                        "section.mainContainer.padding-left-20.padding-right-20.padding-bottom-20.quotations-archive")) { //cała tabela
                    for (int j = 0; j < rows; j++) {
                        Node row = allTable.select("tr").get(j);
                        firstRow++;
                        for (int k = 0; k < cols * 2; k++) {
                            Node a = row.childNode(k).clearAttributes();
                            String b = a.toString().replace("<td>", "").replace("</td>", "")
                                    .replace(",", ".").replace(" ", "");
                            if (b.indexOf('/') > 0) {
                                b = "0";
                                ary[i][j][k / 2] = b;
                            } else {
                                ary[i][j][k / 2] = b;
                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println(ary[1][1]);
        //System.out.println(Arrays.deepToString(ary));
        //System.out.println(" " + ary[0][2][4] + " " + ary[1][2][4] + " " + ary[2][2][4] + " " + ary[3][2][4]);

        return ary;
    }

   /* public static String[][] grabbing2() {
        int allItemsFromTable = 0;
        String[][] ary;
        int[] lowestValue = grabTableFromWeb.rowCount();
        Arrays.sort(lowestValue);
        int rows = lowestValue[0];
        allItemsFromTable = rows * 11;

        ary = new String[model.dayCount][allItemsFromTable]; // rzędy: 4 kolumny: 447

        for (int i = 0; i < model.dayCount; i++) {
            try {
                final Document document = Jsoup.connect(model.mainDates[i]).get();
                for (Element allTable : document.select(
                        "section.mainContainer.padding-left-20.padding-right-20.padding-bottom-20.quotations-archive")) { //cała tabela
                    for (int j = 0; j < allItemsFromTable; j++) {
                        String column = allTable.select("td").get(j).text();
                        ary[i][j] = column;
                        if (j > 2) {
                            String y = ary[i][j];
                            String z = y.replaceAll(",", ".").replaceAll(" ", "");
                            ary[i][j] = z;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println(ary[1][1]);
        System.out.println(Arrays.deepToString(ary));
        return ary;
    }*/
}