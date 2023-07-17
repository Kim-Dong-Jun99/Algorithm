import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[][] size = new int[N][2];
        
        for (int i = 0; i < N; i++){
            String[] info = br.readLine().split(" ");
            size[i][0] = Integer.parseInt(info[0]);
            size[i][1] = Integer.parseInt(info[1]);
        }
        
        for (int i = 0; i < N; i ++){
            int count = 1;
            for (int j = 0; j < N; j++) {
                if (size[i][0] < size[j][0] && size[i][1] < size[j][1]) {
                    count += 1;
                }
            }
            bw.write(Integer.toString(count)+ " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        
        
    }
    
}