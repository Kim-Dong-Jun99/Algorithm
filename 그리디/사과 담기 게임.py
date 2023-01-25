import sys

N, M = map(int, sys.stdin.readline().split())
J = int(sys.stdin.readline())
apples = [int(sys.stdin.readline()) for _ in range(J)]

left_end = 1
right_end = 1 + (M - 1)
result = 0
for i in apples:
	if left_end <= i and i <= right_end:
		continue
	elif i < left_end:
		move = left_end - i
		result += move
		
		left_end -= move
		right_end -= move
	elif i > right_end:
		move = i - right_end
		result += move
		
		left_end += move
		right_end += move

print(result)