package com.wy.wallpaper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yang.wang on 11/23/16.
 */
public class LinkedListTest {
    public static void main(String[] args){
        List<String> nodeContent = new LinkedList<String>();
        nodeContent.add("1");
        nodeContent.add("2");
        nodeContent.add("3");
        nodeContent.remove(1);
        for (String s : nodeContent){
            System.out.println(s);
        }
    }
}
