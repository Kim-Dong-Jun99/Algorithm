import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int Q;
    Hashtable<String, Long> A;
    Hashtable<String, Long> B;
    String[] commands;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        Q = Integer.parseInt(BR.readLine());
        A = new Hashtable<>();
        B = new Hashtable<>();
        commands = new String[Q];
        int i = 0;
        while (i < Q) {
            commands[i] = BR.readLine();
            i += 1;
        }
    }

    void find(String toFind) {
        long answer = 0;
        StringBuilder findA = new StringBuilder();
        StringBuilder findB = new StringBuilder(toFind);
        for (int i = 0; i < toFind.length(); i++) {
            findA.append(toFind.charAt(i));
            findB.deleteCharAt(0);
            String strA = findA.toString();
            String strB = findB.toString();
            if (A.containsKey(strA) && B.containsKey(strB)) {
                answer += A.get(strA) * B.get(strB);
            }
        }
        System.out.println(answer);
    }
    void add(String AB, String toAdd) {
        if (AB.equals("A")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < toAdd.length(); i++) {
                sb.append(toAdd.charAt(i));
                String str = sb.toString();
                A.put(str, (A.containsKey(str) ? A.get(str) + 1 : 1));
            }
        } else {
            StringBuilder sb = new StringBuilder(toAdd);
            for (int i = 0; i < toAdd.length(); i++) {
                String str = sb.toString();
                B.put(str, (B.containsKey(str) ? B.get(str) + 1 : 1));
                sb.deleteCharAt(0);
            }
        }
    }

    void delete(String AB, String toDelete) {
        if (AB.equals("A")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < toDelete.length(); i++) {
                sb.append(toDelete.charAt(i));
                String str = sb.toString();
                A.put(str, (A.containsKey(str) ? A.get(str) - 1 : 0));
            }
        } else {
            StringBuilder sb = new StringBuilder(toDelete);
            for (int i = 0; i < toDelete.length(); i++) {
                String str = sb.toString();
                B.put(str, (B.containsKey(str) ? B.get(str) - 1 : 0));
                sb.deleteCharAt(0);
            }
        }
    }
    
    void solution() {
        for (String command : commands) {
            String[] query = command.split(" ");
            if (query[0].equals("find")) {
                find(query[1]);
            } else if (query[0].equals("add")) {
                add(query[1], query[2]);
            } else {
                delete(query[1], query[2]);
            }
        }
        
    }


}