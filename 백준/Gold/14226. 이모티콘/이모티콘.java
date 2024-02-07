import java.io.*;
import java.util.*;
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int S, answer;
    boolean[][] visited;
    List<Status> currentStatus;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        S = Integer.parseInt(BR.readLine());
        visited = new boolean[S*2+1][S*2+1];
        currentStatus = new ArrayList<>();
        answer = 0;
        
	}
	void solve() throws IOException {
        currentStatus.add(new Status(1, 0));
        visited[1][0] = true;
        boolean done = false;
        while (!currentStatus.isEmpty() && !done) {
            List<Status> temp = new ArrayList<>();
            for (Status status : currentStatus) {
                int written = status.written;
                int copied = status.copied;
                if (copied != 0) {
                    int newWritten = written + copied;
                    if (isInner(newWritten) && !visited[newWritten][copied]) {
                        visited[newWritten][copied] = true;
                        temp.add(new Status(newWritten, copied));
                        if (newWritten == S) {
                            done = true;
                        } 
                    }
                } 
                if (!visited[written][written]) {
                    visited[written][written] = true;
                    temp.add(new Status(written, written));
                    if (written == S) {
                        done = true;
                    }
                }
                if (isInner(written - 1) && !visited[written-1][copied]) {
                    visited[written-1][copied] = true;
                    temp.add(new Status(written-1, copied));
                    if (written - 1 == S) {
                        done = true;
                    }
                }
            }
            answer += 1;
            currentStatus = temp;
        }
        System.out.println(answer);
        
	}
    
    boolean isInner(int i) {
        return 0 <= i && i <= S*2;
    }
    
    public static class Status {
        int written;
        int copied;
        
        public Status(int written, int copied) {
            this.written = written;
            this.copied = copied;
        } 
    }
    
    
}