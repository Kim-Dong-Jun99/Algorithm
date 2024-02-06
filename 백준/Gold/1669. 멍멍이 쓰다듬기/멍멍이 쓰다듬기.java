import java.util.*;
import java.io.*;

/*
첫째날과 마지막날은 무조건 1 cm
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int X, Y;
    int age;
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    void init() throws IOException {
        inputArray = getInputArray();
        X = inputArray[0];
        Y = inputArray[1];
        age = 1;
    }
    
    void solve() throws IOException {
        int answer = 0;

        if (X == Y) {
            BW.write(Integer.toString(answer));
            BW.flush();
            BW.close();
            BR.close();
            return;
        }
        answer = 1;
        X += 1;
        while (X < Y) {
            
            int diff = Y - X;
            int increase = getPrefixSum(age + 1);
            int same = getPrefixSum(age);
            if (diff >= increase) {
                age += 1;
                
            } else if (diff < same) {
                age -= 1;
            }
            
            X += age;
            
            answer += 1;
            
        }
        BW.write(Integer.toString(answer));
        BW.flush();
        BW.close();
        BR.close();
        
    }
    
    int getPrefixSum(int i) {
        if (i % 2 == 0) {
            return (i + 1) * (i / 2);
        } else {
            return (i + 1) * (i / 2) + i / 2 + 1;
        }
    }
    
}