package com.crhonvas.data.utils;

import android.annotation.SuppressLint;
import android.os.Build;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ListUtilsTest {
    @Test
    public void map() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.HONEYCOMB);
        List<Integer> inList = Arrays.asList(1, 2, 3, null, 5);
        List<String> mappedList = ListUtils.map(inList, String::valueOf);
        Assert.assertEquals(5, mappedList.size());
        Assert.assertEquals("1", mappedList.get(0));
        Assert.assertEquals("2", mappedList.get(1));
        Assert.assertEquals("3", mappedList.get(2));
        Assert.assertEquals("null", mappedList.get(3));
        Assert.assertEquals("5", mappedList.get(4));
    }

    @Test
    public void mapShouldFilterNulls() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.HONEYCOMB);
        List<Integer> inList = Arrays.asList(1, 2, 3, null, 5);
        List<Integer> mappedList = ListUtils.map(inList, i -> i);
        Assert.assertEquals(4, mappedList.size());
        Assert.assertEquals(1, mappedList.get(0).intValue());
        Assert.assertEquals(2, mappedList.get(1).intValue());
        Assert.assertEquals(3, mappedList.get(2).intValue());
        Assert.assertEquals(5, mappedList.get(3).intValue());
    }

    @Test
    public void map_api24() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.N);
        map();
        ListUtils.setBuildVersionProvider(null);
    }

    @Test
    public void mapFilter() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.HONEYCOMB);
        List<Integer> inList = Arrays.asList(1, 20, 300, 4000, 50000);
        List<String> mappedList = ListUtils.mapFilter(inList, String::valueOf, string -> string.length() > 3);
        Assert.assertEquals(2, mappedList.size());
        Assert.assertEquals("4000", mappedList.get(0));
        Assert.assertEquals("50000", mappedList.get(1));
    }

    @Test
    public void mapFilter_api24() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.N);
        mapFilter();
        ListUtils.setBuildVersionProvider(null);
    }

    @Test
    public void filter() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.HONEYCOMB);
        List<Integer> inList = Arrays.asList(1, 2, 3, null, 5);
        List<Integer> filteredList = ListUtils.filter(inList, value -> value != null);
        Assert.assertEquals(4, filteredList.size());
        Assert.assertEquals(1, (long) filteredList.get(0));
        Assert.assertEquals(2, (long) filteredList.get(1));
        Assert.assertEquals(3, (long) filteredList.get(2));
        Assert.assertEquals(5, (long) filteredList.get(3));
    }

    @Test
    public void filter_api24() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.N);
        filter();
        ListUtils.setBuildVersionProvider(null);
    }

    @Test
    public void foreach() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.HONEYCOMB);
        List<Integer> inList = Arrays.asList(1, 3, 5, 7, 11);
        @SuppressLint("UseSparseArrays") // SparseArray cannot be mocked
        final HashMap<Integer, String> results = new HashMap<>();
        ListUtils.foreach(inList, value -> results.put(value, "inserted"));

        Assert.assertEquals(5, results.size());
        Assert.assertNull(results.get(0));
        Assert.assertEquals("inserted", results.get(1));
        Assert.assertNull(results.get(2));
        Assert.assertEquals("inserted", results.get(3));
        Assert.assertEquals("inserted", results.get(5));
        Assert.assertEquals("inserted", results.get(7));
        Assert.assertEquals("inserted", results.get(11));
    }

    @Test
    public void foreach_api24() throws Exception {
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.N);
        foreach();
        ListUtils.setBuildVersionProvider(null);
    }
}