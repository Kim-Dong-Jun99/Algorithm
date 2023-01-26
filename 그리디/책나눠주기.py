import sys
from collections import defaultdict

T = int(sys.stdin.readline())
for _ in range(T):
	N, M = map(int, sys.stdin.readline().split())
	students = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
	students.sort(key = lambda x : x[1]*10 + x[0])
# 	print(students)
	sold = defaultdict(int)
	result = 0
	for i, j in students:
		for k in range(i,j+1):
			if sold[k] == 0:
				sold[k] = 1
				result += 1
				break
	print(result)
	