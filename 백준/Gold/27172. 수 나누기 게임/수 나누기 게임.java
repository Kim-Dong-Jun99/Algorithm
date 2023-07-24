import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[] cards;
    int[] toPrint;
    int[] scores;
    int maxCard;
    HashSet<Integer> cardHashSet;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (Exception e) {
            System.out.println("exception during I/O");
        }

    }

    /*
    카드의 숫자들을 전체 숫자라고 정의하고, 소수 여부를 판단하면되는 게임이 아닌가?

     */

    void init() throws Exception {
        N = Integer.parseInt(BR.readLine());
        String inputArray = BR.readLine();
        cards = Arrays.stream(inputArray.split(" ")).mapToInt(Integer::parseInt).toArray();
        toPrint = Arrays.stream(inputArray.split(" ")).mapToInt(Integer::parseInt).toArray();
        maxCard = Arrays.stream(cards).max().getAsInt();
        scores = new int[maxCard+1];
        cardHashSet = new HashSet<>();
        Arrays.sort(cards);
        for (int card : cards) {
            cardHashSet.add(card);
        }
    }

    void solution() throws Exception {
        for (int card : cards) {
            for (int i = card * 2; i <= maxCard; i += card) {
                if (cardHashSet.contains(i)) {
                    scores[card] += 1;
                    scores[i] -= 1;
                }
            }
        }


        for (int card : toPrint) {
            BW.write(scores[card]+" ");
        }
        BW.flush();
        BW.close();
    }
}