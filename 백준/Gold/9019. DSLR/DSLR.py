import sys
from collections import defaultdict, deque

def can_go(a):
	temp = [[(a*2)%10000,'D']]
	S = a - 1
	if S == -1:
		S = 9999
	temp.append([S, 'S'])
	L = (a % 1000) * 10 + (a // 1000)
	temp.append([L, 'L'])
	R = (a % 10)*1000 + (a // 10)
	temp.append([R, 'R'])
	return temp
	
	
T = int(sys.stdin.readline())
for _ in range(T):
	A, B = map(int, sys.stdin.readline().split())
	go = deque([A])
	visited = defaultdict(list)
	visited[A] = [A, '']
	while go:
		X = go.popleft()
		for j, k in can_go(X):
			if len(visited[j]) == 0:
				visited[j] = [X, k]
				go.append(j)
		if len(visited[B]) != 0:
			parent = B
			result = []
			while parent != A:
				result.append(visited[parent][1])
				parent = visited[parent][0]
			for i in range(len(result)-1, -1, -1):
				sys.stdout.write(result[i])
			if _ != T-1:
				sys.stdout.write('\n')
			break