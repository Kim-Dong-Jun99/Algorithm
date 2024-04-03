import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int T;
    int n;
    Position s, p;
    Position[] cvs;
    boolean[] visited;
	public static void main(String[] args) throws IOException {
		Main main = new Main();
        main.testCase();
		main.printResult();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }
    
	void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        inputArray = getInputArray();
        s = new Position(inputArray[0], inputArray[1]);
        visited = new boolean[n];
        cvs = new Position[n];
        for (int i = 0; i < n; i++) {
            inputArray = getInputArray();
            cvs[i] = new Position(inputArray[0], inputArray[1]);
        }
        inputArray = getInputArray();
        p = new Position(inputArray[0], inputArray[1]);
	}
	
	void solve() throws IOException {
		if (canGo(s, p)) {
            BW.write("happy\n");
        } else {
            List<Position> currentPs = Collections.singletonList(s);
            while (!currentPs.isEmpty()) {
                List<Position> temp = new ArrayList<>();
                for (Position currentP : currentPs) {
                    if (canGo(currentP, p)) {
                        BW.write("happy\n");
                        return;
                    }
                    for (int i = 0; i < n; i++) {
                        if (!visited[i] && canGo(currentP, cvs[i])) {
                            visited[i] = true;
                            temp.add(cvs[i]);
                        }
                    }
                }
                currentPs = temp;
            }
            BW.write("sad\n");
        }
	}
    
    boolean canGo(Position from, Position to) {
        int distance = Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
        return distance <= 50 * 20;
    }
    
	void printResult() throws IOException {
		
		BW.flush();
		BW.close();
		BR.close();
	}
    
    static class Position {
        int x;
        int y;
        
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
}