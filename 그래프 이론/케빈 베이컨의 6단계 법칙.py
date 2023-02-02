import sys

def smaller(i, j):
	if i < j:
		return i
	return j

N, M = map(int, sys.stdin.readline().split())

graph = [[0 for _ in range(N)] for _ in range(N)]

for _ in range(M):
	a, b = map(int, sys.stdin.readline().split())
	graph[a-1][b-1] = 1
	graph[b-1][a-1] = 1
	
for k in range(N):
	for i in range(N):
		for j in range(N):
			if graph[i][k] != 0 and graph[k][j] != 0:
				if graph[i][j] == 0:
					graph[i][j] = graph[i][k] + graph[k][j]
				else:
					graph[i][j] = smaller(graph[i][j], graph[i][k] + graph[k][j])
result = 1000000
result_index = 0
for i in range(N):
	temp = sum(graph[i])
	if result > temp:
		result = temp
		result_index = i
		
print(result_index+1)
	
