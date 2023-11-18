import sys

N, K = map(int, sys.stdin.readline().split())
things = [[0, 0]]
bag = [[0 for _ in range(K + 1)] for _ in range(N + 1)]

for _ in range(N):
    things.append(list(map(int, sys.stdin.readline().split())))

# 냅색 문제 풀이
for i in range(1, N + 1):
    for j in range(1, K + 1):
        weight = things[i][0]
        value = things[i][1]

        if j < weight:
            bag[i][j] = bag[i - 1][j]  # weight보다 작으면 위의 값을 그대로 가져온다
        else:
            bag[i][j] = max(value + bag[i - 1][j - weight], bag[i - 1][j])

print(bag[N][K])
