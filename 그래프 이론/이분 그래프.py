import sys
from collections import defaultdict

K = int(sys.stdin.readline())

for _ in range(K):
	V, E = map(int, sys.stdin.readline().split())
	graph = [[] for _ in range(V)]
	visited = defaultdict(int)
	# 0 = 미방문, 1 = 1 번 그룹, -1 = 2번 그룹
	for __ in range(E):
		i, j = map(int, sys.stdin.readline().split())
		graph[i-1].append(j-1)
		graph[j-1].append(i-1)
	for i in range(V):
		done = False
		if visited[i] == 0:
			go = [i]
			visited[i] = 1
			while go:
				temp = []
				for i in go:
					for j in graph[i]:
						if visited[j] == 0:
							visited[j] = visited[i] * -1
							temp.append(j)
						else:
							if visited[i] * visited[j] == 1:
								done = True
								break
					if done:
						break
				if done:
					break
				go = temp
		if done:
			break
	if done:
		print("NO")
	else:
		print("YES")
		
	