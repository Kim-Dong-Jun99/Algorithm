import sys

N = int(sys.stdin.readline())
limits = list(map(int, sys.stdin.readline().split()))
M = int(sys.stdin.readline())
boxes = list(map(int, sys.stdin.readline().split()))

limits.sort()
boxes.sort()
result = 0

if limits[-1] < boxes[-1]:
	print(-1)
else:
	limits_index = 0
	boxes_index = 0
	while boxes_index < M:
		result += 1
		while limits[limits_index] < boxes[boxes_index]:
			limits_index += 1
		for i in range(limits_index, N):
			if limits[i] >= boxes[boxes_index]:
				boxes_index += 1	
				if boxes_index == M:
					break
		
	print(result)