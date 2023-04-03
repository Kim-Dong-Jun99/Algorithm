import sys

T = int(sys.stdin.readline())
for _ in range(T):
    N, M = map(int, sys.stdin.readline().split())
    if N == M:
        print(1)
        continue
    dp = [[0] * M for _ in range(N)]
    size = M - N + 1
    for i in range(N):
        if i == 0:
            for j in range(size):
                dp[i][j] = 1
        else:
            for j in range(size):
                dp[i][i+j] = sum(dp[i-1][:i+j])

    print(sum(dp[N-1]))