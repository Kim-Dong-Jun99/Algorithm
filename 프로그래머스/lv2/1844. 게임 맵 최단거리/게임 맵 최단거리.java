import java.util.*;
class Solution {
    int[][] visited;
    int n;
    int m;
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new int[n][m];
        List<int[]> cur = List.of(new int[]{0, 0});
        int index = 1;
        while (!cur.isEmpty()) {
            List<int[]> temp = new ArrayList<>();
            for (int[] node : cur) {
                for (int[] cango : nextNode(node)) {
                    if (visited[cango[0]][cango[1]] == 0 && maps[cango[0]][cango[1]] == 1) {
                        temp.add(cango);
                        visited[cango[0]][cango[1]] = 1;
                    }
                }
            }
            cur = temp;
            index += 1;
            System.out.println(visited[n-1][m-1]);
            if (visited[n - 1][m - 1] == 1) {
                return index;
            }
        }
        return -1;
    }

    public List<int[]> nextNode(int[] node) {
        List<int[]> toReturn = new ArrayList<>();
        if (node[0] - 1 >= 0) {
            toReturn.add(new int[]{node[0] - 1, node[1]});
        }
        if (node[0] + 1 < n) {
            toReturn.add(new int[]{node[0] + 1, node[1]});
        }
        if (node[1] - 1 >= 0) {
            toReturn.add(new int[]{node[0], node[1] - 1});
        }
        if (node[1] + 1 < m) {
            toReturn.add(new int[]{node[0], node[1] + 1});
        }

        return toReturn;
    }
}