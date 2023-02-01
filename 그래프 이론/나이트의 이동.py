import sys

def can_go(i,j):
	temp = []
	if i - 2 >= 0 and j - 1 >= 0 and visited[i-2][j-1] == 0:
		temp.append([i-2, j-1])
	if i - 2 >= 0 and j + 1 < l and visited[i-2][j+1] == 0:
		temp.append([i-2, j+1])
	if i - 1 >= 0 and j - 2 >= 0 and visited[i-1][j-2] == 0:
		temp.append([i-1, j-2])
	if i - 1 >= 0 and j + 2 < l and visited[i-1][j+2] == 0:
		temp.append([i-1, j+2])
	if i + 1 < l and j - 2 >= 0 and visited[i+1][j-2] == 0:
		temp.append([i+1, j-2])
	if i + 1 < l and j + 2 < l and visited[i+1][j+2] == 0:
		temp.append([i+1, j+2])
	if i + 2 < l and j - 1 >= 0 and visited[i+2][j-1] == 0:
		temp.append([i+2, j-1])
	if i + 2 < l and j + 1 < l and visited[i+2][j+1] == 0:
		temp.append([i+2, j+1])
	return temp
	
def bfs(k_r, k_c):
	result = 0
	visited[k_r][k_c] = 1
	
	go = [[k_r, k_c]]
	while go:
		result += 1
		temp = []
		for i,j in go:
			for k,l in can_go(i,j):
				visited[k][l] = 1
				temp.append([k, l])
		if visited[d_r][d_c] == 1:
			print(result)
			break
		go = temp

TC = int(sys.stdin.readline())

for _ in range(TC):
	l = int(sys.stdin.readline())
	visited = [[0 for _ in range(l)] for _ in range(l)]
	k_r, k_c = map(int, sys.stdin.readline().split())
	d_r, d_c = map(int, sys.stdin.readline().split())
	if k_r == d_r and k_c == d_c:
		print(0)
	else:
		bfs(k_r, k_c)
	
	