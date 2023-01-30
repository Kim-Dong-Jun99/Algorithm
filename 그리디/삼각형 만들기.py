import sys

N = int(sys.stdin.readline())

straws = [int(sys.stdin.readline()) for _ in range(N)]

straws.sort()
result = -1
for i in range(N-1, 1, -1):
	if straws[i] < straws[i-1] + straws[i-2]:
		result = straws[i] + straws[i-1] + straws[i-2]
		break
		
print(result)