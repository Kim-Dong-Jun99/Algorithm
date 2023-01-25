import sys
import heapq
N = int(sys.stdin.readline())

votes = [int(sys.stdin.readline()) for _ in range(N)]
if N == 1:
	print(0)
	sys.exit()
cur_dasom = votes[0]
result = 0
changed_votes = []
for i in range(1,N):
	heapq.heappush(changed_votes, -votes[i])

while True:
	cur_max = -heapq.heappop(changed_votes)
	if cur_dasom > cur_max:
		break
	cur_dasom += 1
	heapq.heappush(changed_votes, -(cur_max-1))
	result += 1
print(result)