import java.util.*;

class Solution {
    static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
    
    static final char EMPTY = 'O';
    static final char PARTITION = 'X';
    static final char PERSON = 'P';
    
    int[] answer;
    int n, m;
    char[][] places;
    
    public int[] solution(String[][] places) {
        List<Integer> answerList = new ArrayList<>();
        for (String[] place : places) {
            init(place);
            answerList.add(solve());
            solve();
        } 
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    void init(String[] places) {
        n = 5;
        m = 5;
        this.places = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.places[i][j] = places[i].charAt(j);
            }
        }
    }
    
    int solve() {
        int answer = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (places[i][j] == PERSON) {
                    for (int d = 0; d < 8; d++) {
                        if (d % 2 == 0) {
                            int oneX = i + DX[d];
                            int oneY = j + DY[d];
                            if (isInner(oneX, oneY) && places[oneX][oneY] == PERSON) {
                                return 0;
                            }
                            int twoX = i + DX[d]*2;
                            int twoY = j + DY[d]*2;
                            if (isInner(twoX, twoY) && places[twoX][twoY] == PERSON && places[oneX][oneY] == EMPTY) {
                                return 0;
                            }
                            
                        } else {
                            int dx = i + DX[d];
                            int dy = j + DY[d];
                            if (isInner(dx, dy)) {
                                int left = getLeft(d);
                                int right = getRight(d);
                                if (places[dx][dy] == PERSON) {
                                    int leftX = i + DX[left];
                                    int leftY = j + DY[left];
                                    if (places[leftX][leftY] == EMPTY) {
                                        return 0;
                                    }
                                    int rightX = i + DX[right];
                                    int rightY = j + DY[right];
                                    if (places[rightX][rightY] == EMPTY) {
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    
    int getLeft(int d) {
        return (d + 7) % 8;
    }
    
    int getRight(int d) {
        return (d + 1) % 8;
    }
}