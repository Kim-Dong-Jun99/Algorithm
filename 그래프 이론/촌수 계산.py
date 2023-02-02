import sys
from collections import defaultdict

n = int(sys.stdin.readline())

graph = [[0 for _ in range(n)] for _ in range(n)]
visited = defaultdict(int)

x, y = map(int, sys.stdin.readline().split())

m = int(sys.stdin.readline())

for _ in range(m):
	parent, child = map(int, sys.stdin.readline().split())
	graph[parent-1][child-1] = 1
	graph[child-1][parent-1] = 1
	
result = 0
go = [x-1]
while go:
	result += 1
	temp = []
	for i in go:
		for j in range(n):
			if graph[i][j] == 1 and visited[j] == 0:
				visited[j] = 1
				temp.append(j)
	go = temp
	if visited[y-1] == 1:
		print(result)
		sys.exit()

print(-1)
	