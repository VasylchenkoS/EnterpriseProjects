package com.vasylchenko.beans;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.LinkedList;

public class CollectionsCompareBeansTest {

    Collection<Integer> testList;
    @BeforeMethod
    public void setUp(){
        testList = new LinkedList<>();
    }

    @Test
    public void testCompareCollectionAdd() throws Exception {
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionAdd(testList, 1), 1);
    }

    @Test
    public void testCompareCollectionRemove() throws Exception {
        testList.add(1);
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionRemove(testList), 1);
    }

    @Test
    public void testCompareCollectionContains() throws Exception {
        testList.add(1);
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionContains(testList, 1), 1);
    }

    @Test
    public void testCompareCollectionPopulate() throws Exception {
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionContains(testList, 1), 1);
    }

    @Test
    public void testCompareListGet() throws Exception {
        testList.add(1);
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionContains(testList, 1), 1);
    }

    @Test
    public void testCompareListIteratorAdd() throws Exception {
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionContains(testList, 1), 1);
    }

    @Test
    public void testCompareListIteratorRemove() throws Exception {
        testList.add(1);
        Assert.assertEquals(0 , new CollectionsCompareBeans().compareCollectionContains(testList, 1), 1);
    }
}