import sys
N = int(sys.stdin.readline())
limits = list(map(int, sys.stdin.readline().split()))
M = int(sys.stdin.readline())
boxes = list(map(int, sys.stdin.readline().split()))

limits.sort()
boxes.sort()
can_remove = [0] * N
result = 0
if limits[-1] < boxes[-1]:
	print(-1)
else:
	limits_index = 0
	for i in range(M):
		if limits[limits_index] >= boxes[i]:
			can_remove[limits_index] += 1
		else:
			while limits[limits_index] < boxes[i]:
				limits_index += 1
			can_remove[limits_index] += 1
	while True:
		stacked = 0
		zero_count = 0
		result += 1
		for i in range(N-1, -1, -1):
			if can_remove[i] > 0:
				stacked += 1
				if can_remove[i] - stacked < 0:
					stacked -= can_remove[i]
					can_remove[i] = 0
				else:
					can_remove[i] -= stacked
					stacked = 0
			else:
				stacked += 1
			if can_remove[i] == 0:
				zero_count += 1
		if zero_count == N:
			break
	print(result)