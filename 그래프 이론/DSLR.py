import sys
from collections import defaultdict

def match_len(a):
	if len(a) < 4:
		a = '0' * (4 - len(a)) + a
	return a

def match_len_int(a):
	a = str(a)
	return match_len(a)
# a = str, a_ = int
def can_go(a):
	temp = []
	a_ = int(a)
	
	D = (a_ * 2) % 10000
	D = match_len_int(D)
	temp.append([D, 'D'])
	
	L = a_ - 1
	if L == -1:
		L = 9999
	L = match_len_int(L)
	temp.append([L, 'L'])
	
	S = a[1:] + a[0]
	temp.append([S, 'S'])
	
	R = a[3] + a[:3]
	temp.append([R, 'R'])
	return temp


T = int(sys.stdin.readline())

for _ in range(T):
	A, B = sys.stdin.readline().split()
	A = match_len(A)
	B = match_len(B)
	go = [A]
	visited = defaultdict(int)
	visited[A] = []
	while go:
		temp = []
		for i in go:
			for j, l in can_go(i):
				if visited[j] == 0:
					temp.append(j)
					visited[j] = [i, l]
		go = temp
		if visited[B] != 0:
			parent = B
			result = []
			while parent != A:
				result.append(visited[parent][1])
				parent = visited[parent][0]
			for i in range(len(result)-1, -1, -1):
				print(result[i], end='')
			print()
			break
				