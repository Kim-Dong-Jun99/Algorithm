import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    int[] inputArray;
    int N, K;
    Country[] countries;
    
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
        N = inputArray[0];
        K = inputArray[1];
        countries = new Country[N];
        for (int i = 0; i < N; i++) {
            inputArray = getInputArray();
            countries[i] = new Country(inputArray[0], inputArray[1], inputArray[2], inputArray[3]);
        }
        Arrays.sort(countries, Country::sort);
	}
	
	void solve() {
		int index = 0;
        while (index < N) {
            int sameIndex = index;
            while (sameIndex < N && countries[index].sameRank(countries[sameIndex])) {
                countries[sameIndex].rank = index + 1;
                sameIndex += 1;
            }
            index = sameIndex;
        }
        for (int i = 0; i < N; i++) {
            if (countries[i].id == K) {
                System.out.println(countries[i].rank);
                return;
            }
        }
    }
    
    static class Country {
        int id;
        int g;
        int s;
        int b;
        int rank;
        
        Country(int id, int g, int s, int b) {
            this.id = id;
            this.g = g;
            this.s = s;
            this.b = b;
            this.rank = 0;
        }
        
        boolean sameRank(Country c) {
            return this.g == c.g && this.s == c.s && this.b == c.b;
        }
        
        int sort(Country compare) {
            if (this.g != compare.g) {
                return Integer.compare(compare.g, this.g);
            }
            if (this.s != compare.s) {
                return Integer.compare(compare.s, this.s);
            }
            return Integer.compare(compare.b, this.b);
        }
    }
}