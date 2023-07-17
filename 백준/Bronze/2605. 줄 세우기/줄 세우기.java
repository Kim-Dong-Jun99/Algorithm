import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        String[] ns = br.readLine().split(" ");
        
        List<String> students = new ArrayList<>();
        
        for(int i = 0; i < n; i ++){
            students.add(students.size() - Integer.parseInt(ns[i]), Integer.toString(i + 1));
        }
        for (String student : students) {
            bw.write(student+" ");
        }
        bw.newLine();
        bw.flush();
        bw.close();

        
    }
}