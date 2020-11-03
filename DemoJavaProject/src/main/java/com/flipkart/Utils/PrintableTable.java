package com.flipkart.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Printable table - Utility class to pretty print tables.
 */
public class PrintableTable {

    private boolean leftJustifiedRows = true;

    /**
     * Print table string.
     *
     * @param table the table containing data in 2D string matrix
     * @return single string output
     */
    public String printTable(String[][] table) {

        Map<Integer, Integer> columnLengths = new HashMap<>();
        for (int i = 0; i < table[0].length; i++) {
            for (String[] strings : table) {
                if (strings[i].length() > columnLengths.getOrDefault(i, Integer.MIN_VALUE))
                    columnLengths.put(i, strings[i].length());
            }
        }

        final StringBuilder formatString = new StringBuilder();
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.forEach((key, value) -> formatString.append("| %").append(flag).append(value).append("s "));
        formatString.append("|\n");

        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            StringBuilder templn = new StringBuilder();
            templn.append("+-");
            for (int j = 0; j < columnLengths.get(b.getKey()); j++)
                templn.append("-");
            templn.append("-");
            return ln + templn.toString();
        }, (a, b) -> a + b);
        line = line + "+\n";

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(line);
        Arrays.stream(table).limit(1).forEach(a -> sb.append(String.format(formatString.toString(), (Object[]) a)));
        sb.append(line);

        for (int i = 1; i < table.length; i++) {
            sb.append(String.format(formatString.toString(), (Object[]) table[i]));
        }
        sb.append(line);
        return sb.toString();
    }

    /**
     * sets right justified rows.
     */
    public void getRightJustifiedRows() {
        this.leftJustifiedRows = false;
    }

    /**
     * Test main.
     *
     * @param args kept empty.
     */
    public static void main(String[] args) {
        String[][] table = new String[][] { { "id", "First Name", "Last Name", "Age" },
                { "1", "John", "Johnson", "45" }, { "2", "Tom", "", "35" }, { "3", "Rose", "Johnson", "22" },
                { "4", "Jimmy", "Kimmel", "" } };
        PrintableTable st = new PrintableTable();
        System.out.println(st.printTable(table));
    }
}
