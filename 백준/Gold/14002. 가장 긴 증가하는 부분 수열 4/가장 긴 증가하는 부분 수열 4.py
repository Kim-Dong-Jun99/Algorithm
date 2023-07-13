import sys

N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().split()))

dp = [[-1,0] for _ in range(N)]
max_len = [0,0]
for i in range(N):
    dp[i][1] += 1
    if dp[i][1] > max_len[1]:
        max_len[1] = dp[i][1]
        max_len[0] = i
    for j in range(i + 1, N):
        if A[j] > A[i] and dp[i][1] > dp[j][1]:
            dp[j][1] = dp[i][1]
            dp[j][0] = i

sys.stdout.write("%d\n"%max_len[1])
i = max_len[0]
answer = []
while i != -1:
    answer.append(A[i])

    i = dp[i][0]
for i in range(len(answer)-1,-1,-1):
    sys.stdout.write("%d "%answer[i])
