import java.util.Scanner;

public class Main {
	static long map[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		map = new long[8][8];
		map[0][1] = 1;
		map[0][2] = 1;
		map[1][0] = 1;
		map[1][2] = 1;
		map[1][3] = 1;
		map[2][0] = 1;
		map[2][1] = 1;
		map[2][3] = 1;
		map[2][5] = 1;
		map[3][1] = 1;
		map[3][2] = 1;
		map[3][4] = 1;
		map[3][5] = 1;
		map[4][3] = 1;
		map[4][5] = 1;
		map[4][6] = 1;
		map[5][2] = 1;
		map[5][3] = 1;
		map[5][4] = 1;
		map[5][7] = 1;
		map[6][4] = 1;
		map[6][7] = 1;
		map[7][5] = 1;
		map[7][6] = 1;
		long c[][] = new long[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				c[i][j] = map[i][j];
			}
		}


		c = mypow(c, N);
		System.out.println(c[0][0]);
	}

	static int M = 1000000007;

	private static long[][] mult(long[][] c1, long[][] c2) {
		long res[][] = new long[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++)
					res[i][j] = (res[i][j] + c1[i][k] * c2[k][j]) % M;
			}
		}
		return res;
	}

	private static long[][] mypow(long[][] c, long N) {
		// System.out.println("N= "+ N);
		if (N == 1)
			return c;
		else {
			if (N % 2 == 1) {
				long res[][] = new long[8][8];
				res = mult(c, c);
				return mult(c, mypow(res, N / 2));

			} else {
				long res[][] = new long[8][8];

				res = mult(c, c);
				return mypow(res, N / 2);
			}
		}

	}

	private static void printMat(long[][] res) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}

}