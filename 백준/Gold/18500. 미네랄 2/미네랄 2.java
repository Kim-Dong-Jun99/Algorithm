import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    static int R, C, N;
    static char[][] map;
    static boolean[][] visit;
    static List<Point> cluster;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        visit = new boolean[R][C];
        cluster = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            map[i] = sc.next().toCharArray();
        }

        N = sc.nextInt();
        for (int n = 0; n < N; n++) {
            int height = sc.nextInt();
            int i = R - height;

            Point target = null;

            if (n % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '.') continue;

                    map[i][j] = '.';
                    target = new Point(i, j);
                    break;
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (map[i][j] == '.') continue;

                    map[i][j] = '.';
                    target = new Point(i, j);
                    break;
                }
            }

            if (target == null) continue;

            findCluster(target);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void findCluster(Point target) {
        for (int d = 0; d < 4; d++) {
            cluster.clear();

            Point next = new Point(target.x + di[d], target.y + dj[d]);

            if (!check(next.x, next.y)) continue;

            if (map[next.x][next.y] == 'x') {
                Queue<Point> queue = new LinkedList<>();
                for (int i = 0; i < R; i++)
                    Arrays.fill(visit[i], false);

                queue.add(next);
                visit[next.x][next.y] = true;
                cluster.add(next);

                while (!queue.isEmpty()) {
                    Point now = queue.poll();

                    if (now.x == R - 1) {
                        cluster.clear();
                        break;
                    }

                    for (int dd = 0; dd < 4; dd++) {
                        int ni = now.x + di[dd];
                        int nj = now.y + dj[dd];

                        if (!check(ni, nj) || visit[ni][nj] || map[ni][nj] == '.') continue;

                        visit[ni][nj] = true;
                        queue.add(new Point(ni, nj));
                        cluster.add(new Point(ni, nj));
                    }
                }

                if (!cluster.isEmpty())
                    down();
            }
        }
    }

    static void down() {
        while (true) {
            boolean bottom = false;

            for (Point p : cluster) {
                if (p.x == R - 1 || (p.x + 1 <= R - 1 && map[p.x + 1][p.y] == 'x' && !cluster.contains(new Point(p.x + 1, p.y)))) {
                    bottom = true;
                }
            }

            if (bottom) break;

            for (Point p : cluster) {
                map[p.x][p.y] = '.';
            }

            for (Point p : cluster) {
                map[p.x + 1][p.y] = 'x';
                p.x++;
            }
        }
    }

    static boolean check(int i, int j) {
        if (i < 0 || i > R - 1 || j < 0 || j > C - 1) return false;
        return true;
    }
}