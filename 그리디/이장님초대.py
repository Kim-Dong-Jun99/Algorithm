import sys

N = int(sys.stdin.readline())
trees = list(map(int, sys.stdin.readline().split()))

trees.sort(reverse=True)
result = 0
for i in range(N):
	if result < i+1 + trees[i]:
		result = i+1+trees[i]
print(result+1)