package com.crhonvas.data.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * COPY OF SIMULATION ListUtils CLASS
 * Helper utilities for performing functional operations on lists.
 * List manipulations all return new lists and can be chained together for complex operations,
 * eg combinations of mapping and filtering.
 * This class makes use of streams on Android api 24. Tests need to be kept up to date to ensure that
 * there aren't behavioural differences.
 */

public class ListUtils {

    private static BuildVersionProvider buildVersionProvider = () -> Build.VERSION.SDK_INT;

    public static void setBuildVersionProvider(@Nullable BuildVersionProvider provider) {
        if (provider == null) {
            buildVersionProvider = () -> Build.VERSION.SDK_INT;
        } else {
            buildVersionProvider = provider;
        }
    }

    /**
     * Please note! this returns a List in the same order that the collection was passed in
     */
    @SuppressLint("NewApi")
    public static <T, S> List<S> map(Collection<T> mapList, Mapper<T, S> mapper) {
        return mapFilter(mapList, mapper, s -> s != null);
    }

    @SuppressLint("NewApi")
    public static <T, S> List<S> mapFilter(Collection<T> mapList, Mapper<T, S> mapper, Condition<S> condition) {
        if (buildVersionProvider.getBuildVersion() >= Build.VERSION_CODES.N) {
            return mapList.stream().map(mapper::map).filter(condition::isTrue).collect(Collectors.toList());
        } else {
            List<S> returnList = new ArrayList<>();
            //noinspection Convert2streamapi
            for (T t : mapList) {
                S mappedVal = mapper.map(t);
                if (condition.isTrue(mappedVal)) {
                    returnList.add(mappedVal);
                }
            }
            return returnList;
        }
    }

    @SuppressLint("NewApi")
    public static <T, S> List<S> convert(Collection<T> mapList, Mapper<T, S> mapper) {
        if (buildVersionProvider.getBuildVersion() >= Build.VERSION_CODES.N) {
            return mapList.stream().map(mapper::map).collect(Collectors.toList());
        } else {
            List<S> returnList = new ArrayList<>();
            //noinspection Convert2streamapi
            for (T t : mapList) {
                S mappedVal = mapper.map(t);
                returnList.add(mappedVal);
            }
            return returnList;
        }
    }

    @SuppressLint("NewApi")
    public static <T> List<T> filter(List<T> mapList, Condition<T> condition) {
        if (buildVersionProvider.getBuildVersion() >= Build.VERSION_CODES.N) {
            return mapList.stream().filter(condition::isTrue).collect(Collectors.toList());
        } else {
            List<T> returnList = new ArrayList<>();
            //noinspection Convert2streamapi
            for (T t : mapList) {
                if (condition.isTrue(t)) {
                    returnList.add(t);
                }
            }
            return returnList;
        }
    }

    @SuppressLint("NewApi")
    public static <T> void foreach(Collection<T> collection, Job<T> job) {
        if (buildVersionProvider.getBuildVersion() >= Build.VERSION_CODES.N) {
            collection.forEach(job::perform);
        } else {
            //noinspection Convert2streamapi
            for (T t : collection) {
                job.perform(t);
            }
        }
    }

    /**
     * Find the first object in the list matching the condition.
     *
     * @return -1 if no match found, otherwise index within list length
     */
    public static <T> int indexOfObjectMatchingCondition(List<T> list, Condition<T> condition) {
        for (int i = 0; i < list.size(); i++) {
            if (condition.isTrue(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public interface BuildVersionProvider {
        int getBuildVersion();
    }
}