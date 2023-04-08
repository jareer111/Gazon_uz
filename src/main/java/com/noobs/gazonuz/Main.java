package com.noobs.gazonuz;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Main {
    public static void main(String[] args) {


        Gson gson = new Gson();

        final myclass src = new myclass(10 , "jack" , "myjack");

        final String s = gson.toJson(src);


        System.out.println(s);
    }

    static class myclass {

        int age;
        String name;
        String username;

        public myclass(int age , String name , String username) {
            this.age = age;
            this.name = name;
            this.username = username;
        }
    }

}
