import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer st = new StringBuffer();
        int[] d = new int[9];
        int sum = 0;
        boolean end = false;
        for (int i = 0; i < 9; i++) {
            d[i] = Integer.parseInt(br.readLine());
            sum += d[i];
        }
        Arrays.sort(d);
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                if (d[i] + d[j] == sum - 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k != i && k != j) {
                            st.append(d[k]).append("\n");
                            
                        }
                    }
                    end = true;
                    break;
                }
            }
            if (end ){
                break;
            }
        }
        
        System.out.println(st);

    }
}
