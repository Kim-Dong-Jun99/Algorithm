import sys

def bigger(i, j):
	if i > j:
		return i
	return j

N = int(sys.stdin.readline())

graph = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]


for k in range(N):
	for i in range(N):
		for j in range(N):
			graph[i][j] = bigger(graph[i][j], graph[i][k] * graph[k][j])
			
for i in graph:
	for j in i:
		print("%d"%j, end = " ")
	print()