import java.util.*;
import java.io.*;

public class Main {
    
	static int A, B, C;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	A = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	// true: 1 출력, false: 0 출력
    	System.out.println(bfs(A, B, C) ? 1 : 0);
    }
    
    static boolean bfs(int a, int b, int c) {
    	
    	// 세 그룹의 합이 3으로 안 나눠지면 false
    	if((a + b + c) % 3 != 0) return false;
    	
    	Queue<Stone> que = new LinkedList<>();
    	boolean[][] isChecked = new boolean[1501][1501];
    	
    	que.add(new Stone(a, b, c));
    	isChecked[a][b] = true;
    	
    	while(!que.isEmpty()) {
    		Stone s = que.poll();
    		
    		a = s.a;
    		b = s.b;
    		c = s.c;
    		
    		// 세 개가 모두 같아지면 true 반환
    		if(a == b && b == c) {
    			return true;
    		}
    		
    		// 갯수가 다른 두 개 골라서 연산
    		if(a != b) {
    			int na = a > b ? a-b : a+a;
    			int nb = a > b ? b+b : b-a;
    			
    			if(!isChecked[na][nb]) {
    				que.add(new Stone(na, nb, c));
    				isChecked[na][nb] = true;
    			}
    		}
    		
    		if(b != c) {
    			int nb = b > c ? b-c : b+b;
    			int nc = b > c ? c+c : c-b;
    			
    			if(!isChecked[nb][nc]) {
    				que.add(new Stone(a, nb, nc));
    				isChecked[nb][nc] = true;
    			}
    		}
    		
    		if(a != c) {
    			int na = a > c ? a-c : a+a;
    			int nc = a > c ? c+c : c-a;
    			
    			if(!isChecked[na][nc]) {
    				que.add(new Stone(na, b, nc));
    				isChecked[na][nc] = true;
    			}
    		}
    		
    	}
    	
    	// Queue가 끝나도 3개가 모두 같아지지 않으면 false 반환
    	return false;
    }
    
    static class Stone{
    	int a, b, c;
    	
    	Stone(int a, int b, int c){
    		this.a = a;
    		this.b = b;
    		this.c = c;
    	}
    }
  
}