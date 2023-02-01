import sys

def can_go(i, j):
	temp_ = []
	if i - 1 >= 0 and visited[i-1][j] == 0:
		temp_.append([i-1, j])
	if i + 1 < N and visited[i+1][j] == 0:
		temp_.append([i+1, j])
	if j - 1 >= 0 and visited[i][j-1] == 0:
		temp_.append([i, j-1])
	if j + 1 < M and visited[i][j+1] == 0:
		temp_.append([i, j+1])
	return temp_
		

def can_go_wall(i, j):
	temp_ = []
	if i - 1 >= 0 and wall_visited[i-1][j] == 0 and graph[i-1][j] == '0':
		temp_.append([i-1, j])
	if i + 1 < N and wall_visited[i+1][j] == 0 and graph[i+1][j] == '0':
		temp_.append([i+1, j])
	if j - 1 >= 0 and wall_visited[i][j-1] == 0 and graph[i][j-1] == '0':
		temp_.append([i, j-1])
	if j + 1 < M and wall_visited[i][j+1] == 0 and graph[i][j+1] == '0':
		temp_.append([i, j+1])
	return temp_


N, M = map(int, sys.stdin.readline().split())

graph = [sys.stdin.readline().rstrip() for _ in range(N)]

visited = [[0 for _ in range(M)] for _ in range(N)]
wall_visited = [[0 for _ in range(M)] for _ in range(N)]

visited[0][0] = 1

go = [[0,0]]
crashed = []
result = 1
while go or crashed:
# 	print("go : %s"%go)
# 	print("crashed : %s"%crashed)
	result += 1
	temp = []
	temp_ = []
	for i, j in go:
		for k, l in can_go(i, j):
			visited[k][l] = 1
			if graph[k][l] == '1':
				temp_.append([k, l])
			else:
				temp.append([k, l])
	go = temp
	for i, j in crashed:
		for k, l in can_go_wall(i, j):
			wall_visited[k][l] = 1
			temp_.append([k, l])
	crashed = temp_
	if visited[N-1][M-1] == 1 or wall_visited[N-1][M-1] == 1:
		print(result)
		sys.exit()
print(-1)