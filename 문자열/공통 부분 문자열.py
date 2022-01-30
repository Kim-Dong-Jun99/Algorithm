import sys

target = sys.stdin.readline().strip()
pattern = sys.stdin.readline().strip()

dp = [[0] * (len(target) + 1) for _ in range(len(pattern) + 1)]
maxL = 0

for i in range(len(pattern)):
    for j in range(len(target)):
        if pattern[i] == target[j]:
            dp[i + 1][j + 1] = dp[i][j] + 1
            maxL = max(maxL, dp[i + 1][j + 1])

print(maxL)