import java.util.*;

/*
지원서 접수, 코딩 테스트 종료

개발 언어는 cpp, java, python,
지원 직군은 backend, frontend,
경력은 junior, senior
소울 푸드는 chicken, pizza


*/

class Solution {
    String[] query;
    Node trie;


    public int[] solution(String[] info, String[] query) {
        init(info, query);
        return solve();
    }
    void init(String[] info, String[] query) {
        this.query = query;
        initTrie(info);
        initSumMap();
    }

    void initTrie(String[] info) {
        trie = new Node();

        List<Node> node = Collections.singletonList(trie);
        for (int depth = 0; depth < 4; depth++) {
            List<Node> temp = new ArrayList<>();
            if (depth == 0) {
                for (Node n : node) {
                    n.nodeMap.put("java", new Node());
                    n.nodeMap.put("cpp", new Node());
                    n.nodeMap.put("python", new Node());
                    n.nodeMap.put("-", new Node());
                    temp.addAll(n.nodeMap.values());
                }
            }
            if (depth == 1) {
                for (Node n : node) {
                    n.nodeMap.put("backend", new Node());
                    n.nodeMap.put("frontend", new Node());
                    n.nodeMap.put("-", new Node());
                    temp.addAll(n.nodeMap.values());
                }
            }
            if (depth == 2) {
                for (Node n : node) {
                    n.nodeMap.put("junior", new Node());
                    n.nodeMap.put("senior", new Node());
                    n.nodeMap.put("-", new Node());
                    temp.addAll(n.nodeMap.values());
                }
            }
            if (depth == 3) {
                for (Node n : node) {
                    n.nodeMap.put("chicken", new Node());
                    n.nodeMap.put("pizza", new Node());
                    n.nodeMap.put("-", new Node());
                    temp.addAll(n.nodeMap.values());
                }
            }


            node = temp;
        }

        for (String infoStr : info) {
            String[] infos = infoStr.split(" ");
            List<Node> currentNodes = Collections.singletonList(trie);
            for (int i = 0; i < 5; i++) {
                List<Node> temp = new ArrayList<>();
                for (Node current : currentNodes) {
                    if (i == 4) {
                        current.scores.put(Integer.parseInt(infos[i]), current.scores.getOrDefault(Integer.parseInt(infos[i]), 0) + 1);
                    } else {
                        temp.add(current.nodeMap.get(infos[i]));
                        temp.add(current.nodeMap.get("-"));
                    }
                }
                
                currentNodes = temp;
            }
        }
    }

    void initSumMap() {
        List<Node> node = Collections.singletonList(trie);
        for (int depth = 0; depth < 4; depth++) {
            List<Node> temp = new ArrayList<>();
            for (Node n : node) {
                temp.addAll(n.nodeMap.values());
            }
            node = temp;
        }
        for (Node n : node) {
            NavigableSet<Integer> descendingKeySet = n.scores.descendingKeySet();
            int sum = 0;
            for (Integer key : descendingKeySet) {
                sum += n.scores.get(key);
                n.sumMap.put(key, sum);
            }
        }
    }




    int[] solve() {
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            String[] qs = q.split(" ");
            Node current = trie;

            for (int d = 0; d < qs.length; d++) {
                if (d == qs.length-1) {
                    Integer ceilingKey = current.scores.ceilingKey(Integer.parseInt(qs[d]));
                    if (ceilingKey != null) {
                        result[i] = current.sumMap.get(ceilingKey);
                    }
                } else {
                    if (!qs[d].equals("and")) {

                        current = current.nodeMap.get(qs[d]);
                    }
                }
            }
        }

        return result;
    }

    static class Node {
        HashMap<String, Node> nodeMap;
        TreeMap<Integer, Integer> scores;
        HashMap<Integer, Integer> sumMap;

        public Node() {
            nodeMap = new HashMap<>();
            scores = new TreeMap<>();
            sumMap = new HashMap<>();
        }
    }

}


