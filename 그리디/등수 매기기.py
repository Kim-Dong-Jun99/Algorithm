import sys

N = int(sys.stdin.readline())

ranks = [int(sys.stdin.readline()) for _ in range(N)]

ranks.sort()

diff = 0
for i in range(N):
	diff += abs(ranks[i] - (i+1))
print(diff)