package com.pubmatic.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExceptionDemo {

    private static void scenarioCME1() {

        List<String> names = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            names.add(i + "");
        }

        names.remove(0);

        System.out.println(names);
    }

    private static void scenarioCME2() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {

        List<String> names = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            names.add(i + "");
        }

        Iterator<String> iterator = names.iterator();

        System.out.println(" Expected Mod Count : " + getExpectedModCount(iterator, "expectedModCount")
                + " Mod Count : " + getModCount(names, "modCount"));
        names.remove(0);

        System.out.println(" Expected Mod Count : " + getExpectedModCount(iterator, "expectedModCount")
                + " Mod Count : " + getModCount(names, "modCount"));

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(names);
    }

    private static void scenarioCME3() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        System.out.println("========================");
        List<String> names = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 5; i++) {
            names.add(i + "");
        }

        Iterator<String> iterator = names.iterator();

        // System.out.println(" Expected Mod Count : " +
        // getExpectedModCount(iterator, "expectedModCount")
        // + " Mod Count : " + getModCount(names, "modCount"));
        names.remove(0);

        // System.out.println(" Expected Mod Count : " +
        // getExpectedModCount(iterator, "expectedModCount")
        // + " Mod Count : " + getModCount(names, "modCount"));

        while (iterator.hasNext()) {
            iterator.remove();
        }
        System.out.println(names);
    }

    public static Object getModCount(Object obj, String fieldName) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);

        field.setAccessible(true);

        Object value = field.get(obj);
        return value;
    }

    public static Object getExpectedModCount(Object obj, String fieldName) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);

        field.setAccessible(true);

        Object value = field.get(obj);
        return value;
    }

    public static void main(String[] args) {
        scenarioCME1();
        try {
            scenarioCME2();
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
                | ConcurrentModificationException e) {
            System.out.println("Exception Occured : " + e.getClass().getName());
        }

        try {
            scenarioCME3();
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | UnsupportedOperationException e) {
            System.out.println("Exception Occured : " + e.getClass().getName());
        }
    }

}


//       [1, 2, 3, 4]
//       Expected Mod Count : 5 Mod Count : 5
//       Expected Mod Count : 5 Mod Count : 6
//       Exception Occured : java.util.ConcurrentModificationException
//       ========================
//       Exception Occured : java.lang.UnsupportedOperationException
