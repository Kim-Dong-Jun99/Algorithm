import sys

"""
12:45
12:55
주어지는 좌표 값은 10,10 정사각형 왼쪽 아래 좌표값
정사각형 하나의 사이즈 = 100,
"""
n = int(sys.stdin.readline())
graph = [[0 for _ in range(101)] for _ in range(101)]

squares = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

for l, d in squares:
    r = l + 10
    u = d + 10
    for i in range(d, u):
        for j in range(l, r):
            graph[i][j] = 1
result = 0
for i in range(101):
    for j in range(101):
        if graph[i][j] == 1:
            result += 1
print(result)
