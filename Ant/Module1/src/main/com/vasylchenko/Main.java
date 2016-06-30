package com.vasylchenko;

import com.vasylchenko.beans.CollectionsCompareWriter;
import com.vasylchenko.beans.SwingTableOut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {

        CollectionsCompareWriter.setTableHead();

        SwingTableOut.start();

        ArrayList<Integer> arrayList = new ArrayList<>();
        CollectionsCompareWriter.getTableData(arrayList, 10 * 1000);
        CollectionsCompareWriter.getTableData(arrayList, 100 * 1000);
        CollectionsCompareWriter.getTableData(arrayList, 1000 * 1000);

        LinkedList<Integer> linkedList = new LinkedList<>();
        CollectionsCompareWriter.getTableData(linkedList, 10 * 1000);
        CollectionsCompareWriter.getTableData(linkedList, 100 * 1000);
        CollectionsCompareWriter.getTableData(linkedList, 1000 * 1000);

        HashSet<Integer> hashSet = new HashSet<>();
        CollectionsCompareWriter.getTableData(hashSet, 10 * 1000);
        CollectionsCompareWriter.getTableData(hashSet, 100 * 1000);
        CollectionsCompareWriter.getTableData(hashSet, 1000 * 1000);

        TreeSet<Integer> treeSet = new TreeSet<>();
        CollectionsCompareWriter.getTableData(treeSet, 10 * 1000);
        CollectionsCompareWriter.getTableData(treeSet, 100 * 1000);
        CollectionsCompareWriter.getTableData(treeSet, 1000 * 1000);
    }
}