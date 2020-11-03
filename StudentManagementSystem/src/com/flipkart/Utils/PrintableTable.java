package com.flipkart.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PrintableTable {

    private boolean leftJustifiedRows = true;

    public String printTable(String[][] table) {

        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            columnLengths.putIfAbsent(i, 0);
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));

        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.forEach((key, value) -> formatString.append("| %").append(flag).append(value).append("s "));
        formatString.append("|\n");

        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        System.out.println(formatString);
        System.out.println(line);

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(line);
        Arrays.stream(table).limit(1).forEach(a -> sb.append(String.format(formatString.toString(), a)));
        sb.append(line);

        Stream.iterate(1, (i -> i < table.length), (i -> ++i))
                .forEach(a -> sb.append(String.format(formatString.toString(), table[a])));
        sb.append(line);
        return sb.toString();
    }

    public void getRightJustifiedRows() {
        this.leftJustifiedRows = false;
    }


    public static void main(String[] args) {
        String[][] table = new String[][] { { "id", "First Name", "Last Name", "Age" },
                { "1", "John", "Johnson", "45" }, { "2", "Tom", "", "35" }, { "3", "Rose", "Johnson", "22" },
                { "4", "Jimmy", "Kimmel", "" } };
        PrintableTable st = new PrintableTable();
        System.out.println(st.printTable(table));
    }
}
