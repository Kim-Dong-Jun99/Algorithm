import sys
from collections import defaultdict
T = int(sys.stdin.readline())
for _ in range(T):
	N, M = map(int, sys.stdin.readline().split())
	students = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
	left_limit = students.copy()
	right_limit = students.copy()
	left_limit.sort()
	right_limit.sort(key = lambda x : x[1])
	print(left_limit)
	print(right_limit)
	sold = defaultdict(int)
	result = 0
	print(result)
	