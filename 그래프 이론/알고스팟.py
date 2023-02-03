from collections import defaultdict
import sys

def can_go(i):
	temp = []
	if i + U <= F and visited[i+U] == 0:
		temp.append(i+U)
	if i - D > 0 and visited[i-D] == 0:
		temp.append(i-D)
	return temp

F, S, G, U, D = map(int, sys.stdin.readline().split())

visited = defaultdict(int)
visited[S] = 1
go = [S]
result = 0
done = False
while go:
	if visited[G] == 1:
		done = True
		break
	result += 1
	temp = []
	for i in go:
		for j in can_go(i):
			visited[j] = 1
			temp.append(j)
	go = temp
	
if done:
	print(result)
else:
	print("use the stairs")