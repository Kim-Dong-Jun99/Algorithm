import sys

def can_go(i, j, stan):
	temp_ = []
	if i - 1 >= 0 and city[i-1][j] > stan and visited[i-1][j] == 0:
		temp_.append([i-1, j])
	if i + 1 < N and city[i+1][j] > stan and visited[i+1][j] == 0:
		temp_.append([i+1, j])
	if j - 1 >= 0 and city[i][j-1] > stan and visited[i][j-1] == 0:
		temp_.append([i, j-1])
	if j + 1 < N and city[i][j+1] > stan and visited[i][j+1] == 0:
		temp_.append([i, j+1])
	return temp_

def bfs(go, stan):
	while go:
		temp = []
		for i, j in go:
			for k,l in can_go(i, j, stan):
				visited[k][l] = 1
				temp.append([k, l])
		go = temp	
	

N = int(sys.stdin.readline())

city = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
result = 1
min_ = 101
max_ = 0
for i in range(N):
	for j in range(N):
		if city[i][j] > max_:
			max_ = city[i][j]
		if city[i][j] < min_:
			min_ = city[i][j]
			
for stan in range(min_, max_):
	visited = [[0 for _ in range(N)] for _ in range(N)]
	temp_result = 0
	for i in range(N):
		for j in range(N):
			if city[i][j] > stan and visited[i][j] == 0:
				visited[i][j] = 1
				temp_result += 1
				bfs([[i,j]], stan)
	if temp_result > result:
		result = temp_result
print(result)
				
			
		