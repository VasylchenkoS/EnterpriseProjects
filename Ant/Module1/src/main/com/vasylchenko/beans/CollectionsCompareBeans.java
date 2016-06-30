package com.vasylchenko.beans;

import java.util.*;

class CollectionsCompareBeans {

    public long compareCollectionAdd(Collection<Integer> inputCollection, int dataCount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < dataCount; i++)
            inputCollection.add(i);
        return System.currentTimeMillis() - startTime;
    }

    public long compareCollectionRemove(Collection<Integer> inputCollection) {
        long startTime = System.currentTimeMillis();
        //collectionRemoveDP(inputCollection);
        inputCollection.remove(0);
        return System.currentTimeMillis() - startTime;
    }

    /*private void collectionRemoveDP(Collection<Integer> inputCollection) {
        int y = inputCollection.size();
        for (int i = inputCollection.size()/2; i < y; i++)
            inputCollection.remove(i);
        if (!inputCollection.isEmpty())
            compareCollectionRemove(inputCollection);
    }*/

    public long compareCollectionContains(Collection<Integer> inputCollection, int dataCount) {
        long startTime = System.currentTimeMillis();
        inputCollection.contains(dataCount - 1);
        return System.currentTimeMillis() - startTime;
    }

    public long compareCollectionPopulate(Collection<Integer> inputCollection, int dataCount) {
        inputCollection.clear();
        Integer y[] = new Integer[dataCount];
        for (int i = 0; i < dataCount; i++)
            y[i] = i;
        long startTime = System.currentTimeMillis();
        inputCollection.addAll(Arrays.asList(y));
        return System.currentTimeMillis() - startTime;
    }

    public long compareListGet(List<Integer> inputList, int dataCount) {
        long startTime = System.currentTimeMillis();
        inputList.get(dataCount - 1);
        return System.currentTimeMillis() - startTime;
    }

    public long compareListIteratorAdd(List<Integer> inputList, int dataCount) {
        inputList.clear();
        ListIterator<Integer> iterator = inputList.listIterator();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < dataCount; i++)
            iterator.add(i);
        return System.currentTimeMillis() - startTime;
    }

    public long compareListIteratorRemove(List<Integer> inputList) {
        Iterator iterator = inputList.iterator();
        long startTime = System.currentTimeMillis();
//        while (iterator.hasNext()) {
//            String s = String.valueOf(iterator.next());
//            iterator.remove();
//        }
        String s = String.valueOf(iterator.next());
        iterator.remove();
        return System.currentTimeMillis() - startTime;
    }
}