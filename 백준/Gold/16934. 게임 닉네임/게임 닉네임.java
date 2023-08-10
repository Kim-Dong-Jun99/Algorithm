import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    String nickname;
    Trie root;
    StringBuilder sb;
    boolean nicknameFound;
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
        N = Integer.parseInt(BR.readLine());
        root = new Trie();
    }
    void solution() throws IOException {

        while (N-- > 0) {
            nickname = BR.readLine();
            sb = new StringBuilder();
            nicknameFound = false;
            Trie toFound = root;
            for (int i = 0; i < nickname.length(); i++) {
                if (!nicknameFound) {
                    sb.append(nickname.charAt(i));
                }
                if (!toFound.child.containsKey(nickname.charAt(i))) {
                    nicknameFound = true;
                    toFound.child.put(nickname.charAt(i), new Trie(nickname.charAt(i)));
                }
                toFound = toFound.child.get(nickname.charAt(i));
            }
            toFound.isData = true;
            toFound.count += 1;
            if (!nicknameFound) {
                BW.write(nickname);
                if (toFound.count > 1) {
                    BW.write(Integer.toString(toFound.count));
                }
                BW.newLine();
            } else {
                BW.write(sb.toString()+"\n");
            }
        }
        BW.flush();
        BW.close();
    }

    static class Trie {
        char value;
        int count;
        boolean isData;
        HashMap<Character, Trie> child;

        public Trie() {
            this.child = new HashMap<>();
        }

        public Trie(char value) {
            this.value = value;
            this.count = 0;
            this.child = new HashMap<>();
            this.isData = false;
        }
    }
}
