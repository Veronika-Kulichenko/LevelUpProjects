package com.levelup.services.demo;

import com.levelup.services.demo.dao.core.model.Language;
import com.levelup.services.demo.dao.core.model.Roles;
import com.levelup.services.demo.dao.core.model.User;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashMap<Long, User> userMap = new HashMap<>();

        User u = null;
        for (int i = 1; i <= 12; i++){
            u = new User(i, "user" + i + "@yopmail.com", Roles.USER, "P@ssw0rd"+i, Language.RU, i);
            userMap.put(u.getId(), u);
        }

//        userMap.values().forEach(System.out::println);

        System.out.print("size() : " + userMap.size() + ", ");
        System.out.print("isEmpty() : " + userMap.isEmpty() + ", ");

        System.out.print("containsKey(1) : " + userMap.containsKey(1L) + ", ");
        System.out.println("containsValue(lastUser) : " + userMap.containsValue(u));

        System.out.print("KeySet: ");
        userMap.keySet().forEach((obj) -> System.out.print(obj + ", "));

        System.out.println("\r\nEntrySet:");
        userMap.entrySet().forEach((Map.Entry<Long, User> x) -> System.out.println(x.getKey() + "\t:\t" + x.getValue()));

        System.out.println("Values: ");
        userMap.values().forEach((x) -> System.out.println('\t' + x.getEmail() + ", "));

        System.out.print("\r\nremove() element with key '5' , ");
        userMap.remove(5L);

        System.out.print("size() : " + userMap.size());

        //non-modification
//        map.size();
//        map.isEmpty();
//
//        map.get();
//        map.getOrDefault();
//
//        map.containsKey();
//        map.containsValue();
//
//        map.keySet();
//        map.entrySet();
//        map.values();
//
//        //modification
//        map.put();
//        map.putAll();
//        map.replace();
//        map.clear();
//        map.remove();
    }
}
