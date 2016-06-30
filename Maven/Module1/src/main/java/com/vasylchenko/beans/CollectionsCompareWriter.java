package com.vasylchenko.beans;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CollectionsCompareWriter {

    public static void getTableData(Collection<Integer> currentCollection, int dataCount) {
        CollectionsCompareBeans compareBeans = new CollectionsCompareBeans();
        long timeToAdd = 0;
        long timeToGet = 0;
        long timeToContains = 0;
        long timeToPopulate = 0;
        long timeToRemove = 0;
        long timeToIterationAdd = 0;
        long timeToIterationRemove = 0;
        for (int i = 0; i < 100; i++) {
            currentCollection.clear();
            timeToAdd += compareBeans.compareCollectionAdd(currentCollection, dataCount);
            timeToContains += compareBeans.compareCollectionContains(currentCollection, dataCount);
            timeToRemove += compareBeans.compareCollectionRemove(currentCollection);
            if (currentCollection.getClass().toString().contains("List")) {
                timeToIterationAdd += compareBeans.compareListIteratorAdd((List<Integer>) currentCollection, dataCount);
                timeToGet += compareBeans.compareListGet((List<Integer>) currentCollection, dataCount);
                timeToIterationRemove += compareBeans.compareListIteratorRemove((List<Integer>) currentCollection);
            }
            timeToPopulate += compareBeans.compareCollectionPopulate(currentCollection, dataCount);
        }
        saveLogs(String.format("%15s%3s%15d%3s%10f%3s%10f%3s%10f%3s%10f%3s%10f%3s%15f%3s%18f%n",
                currentCollection.getClass().getName().substring(
                        currentCollection.getClass().getName().lastIndexOf(".") + 1, currentCollection.getClass().getName().length()),
                "||", dataCount, "||", timeToAdd / 100.0, "||", timeToGet / 100.0, "||", timeToRemove / 100.0, "||", timeToContains / 100.0, "||", timeToPopulate / 100.0,
                "||", timeToIterationAdd / 100.0, "||", timeToIterationRemove / 100.0), true);
        SwingTableOut.createTable(currentCollection.getClass().getName().substring(
                currentCollection.getClass().getName().lastIndexOf(".") + 1, currentCollection.getClass().getName().length()), dataCount, new double[]{timeToAdd / 100.0,
                timeToGet / 100.0, timeToRemove / 100.0, timeToContains / 100.0, timeToPopulate / 100.0,
                timeToIterationAdd / 100.0, timeToIterationRemove / 100.0});
    }

    private static void saveLogs(String inputString, boolean writeType) {
        try (FileWriter fileWriter = new FileWriter("./src/main/java/com/vasylchenko/resources/CollectionsCompare.txt", writeType)) {
            fileWriter.write(inputString);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setTableHead() {
        saveLogs(String.format("%15s%3s%15s%3s%10s%3s%10s%3s%10s%3s%10s%3s%10s%3s%15s%3s%18s%n",
                "CollectionName", "||", "DataCount", "||", "add", "||", "get", "||", "remove", "||",
                "contains", "||", "populate", "||", "iterator.add", "||", "iterator.remove"), false);
    }
}