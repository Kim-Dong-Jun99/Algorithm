import sys
n = int(sys.stdin.readline())
cost = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

start = 0
cur = 0
visited = 0b1
endcheck = 0b0
for i in range(n):
    endcheck |= (1 << i)
result = sys.maxsize


def bt(cur, visited, v):
    if endcheck & visited == endcheck:
        if cost[cur][start] != 0:
            temp = v + cost[cur][start]
            global result
            if result > temp:
                result = temp
    else:
        for i in range(n):
            if visited & (1 << i) != (1 << i) and cost[cur][i] != 0:
                bt(i, visited | (1 << i), v + cost[cur][i])

bt(cur, visited, 0)

print(result)
