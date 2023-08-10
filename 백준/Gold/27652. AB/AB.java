import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int Q;
    Trie A;
    Trie B;
    String[] query;
    HashMap<String, Trie> trieMap;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch(IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void init() throws IOException {
        Q = Integer.parseInt(BR.readLine());
        A = new Trie();
        B = new Trie();
        trieMap = new HashMap<>();
        trieMap.put("A", A);
        trieMap.put("B", B);
    }
    void solution() throws IOException {
        while (Q-- > 0) {
            query = BR.readLine().split(" ");
            if (query[0].equals("find")) {
                findString(query[1]);
            } else if (query[0].equals("add")) {
                addToTrie(query[1], query[2]);
            } else {
                deleteString(query[1], query[2]);
            }
        }
    }

    void addToTrie(String trie, String givenString) {
        Trie toAdd = trieMap.get(trie);
        if (trie.equals("B")) {
            StringBuilder sb = new StringBuilder(givenString);
            sb.reverse();
            givenString = sb.toString();
        }

        for (int i = 0; i < givenString.length(); i++) {
            if (toAdd.child.containsKey(givenString.charAt(i))) {
                toAdd = toAdd.child.get(givenString.charAt(i));
                toAdd.count += 1;
            } else {
                toAdd.child.put(givenString.charAt(i), new Trie(givenString.charAt(i)));
                toAdd = toAdd.child.get(givenString.charAt(i));
            }
        }
    }

    void findString(String toFind) {
        int toPrint = 0;
        int[] aCounts = new int[toFind.length()];
        int[] bCounts = new int[toFind.length()];
        Trie findA = A;
        Trie findB = B;
        int aIndex = 0;
        int bIndex = toFind.length() - 1;
        while (aIndex < toFind.length()-1) {
            if (findA.child.containsKey(toFind.charAt(aIndex))) {
                findA = findA.child.get(toFind.charAt(aIndex));
                if (findA.count == 0) {
                    break;
                }
            } else {
                break;
            }
            aCounts[aIndex] = findA.count;
//            if (aIndex == i) {
//                aCount = findA.count;
//            }
            aIndex += 1;
        }
        while (bIndex > 0) {
            if (findB.child.containsKey(toFind.charAt(bIndex))) {
                findB = findB.child.get(toFind.charAt(bIndex));
                if (findB.count == 0) {
                    break;
                }
            } else {
                break;
            }
            bCounts[bIndex] = findB.count;
//            if (bIndex == i + 1) {
//                bCount = findB.count;
//            }
            bIndex -= 1;
        }
        for (int i = 0; i < toFind.length() - 1; i++) {
            toPrint += aCounts[i] * bCounts[i + 1];
        }
//        for (int i = 0; i < toFind.length() - 1; i++) {
//            int aCount = 0;
//            int bCount = 0;
//            
//            
//            if (aCount == 0) {
//                continue;
//            }
//           
//            toPrint += aCount * bCount;
//        }
        System.out.println(toPrint);
    }

    void deleteString(String trie, String givenString) {
        Trie toDelete = trieMap.get(trie);
        if (trie.equals("B")) {
            StringBuilder sb = new StringBuilder(givenString);
            sb.reverse();
            givenString = sb.toString();
        }

        for (int i = 0; i < givenString.length(); i++) {
            toDelete = toDelete.child.get(givenString.charAt(i));
            toDelete.count -= 1;
        }
    }

    static class Trie {
        char key;
        HashMap<Character, Trie> child;
        int count;

        public Trie() {
            this.child = new HashMap<>();
        }

        public Trie(char key) {
            this.key = key;
            this.child = new HashMap<>();
            this.count = 1;
        }

    }
}
