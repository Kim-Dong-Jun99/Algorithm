import sys

def smaller(i, j):
	if i < j:
		return i
	return j
		

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[0 for _ in range(n)] for _ in range(n)]

for _ in range(m):
	i, j, v = map(int, sys.stdin.readline().split())
	if graph[i-1][j-1] == 0:
		graph[i-1][j-1] = v
	else:
		graph[i-1][j-1] = smaller(graph[i-1][j-1], v)
		
for k in range(n):
	for i in range(n):
		for j in range(n):
			if i == j:
				continue
			if graph[i][k] != 0 and graph[k][j] != 0:
				if graph[i][j] == 0:
					graph[i][j] = graph[i][k] + graph[k][j]
				else:
					graph[i][j] = smaller(graph[i][j], graph[i][k] + graph[k][j])
				
for i in graph:
	for j in i:
		print(j, end = " ")
	print()