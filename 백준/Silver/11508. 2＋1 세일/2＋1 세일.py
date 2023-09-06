import sys

N = int(sys.stdin.readline())

milks = [int(sys.stdin.readline()) for _ in range(N)]

milks.sort(reverse = True)

groups = N // 3
remainder = N % 3

result = 0

for i in range(groups):
	result += milks[i*3] + milks[i*3+1]
	
if remainder == 2:
	result += milks[-2] + milks[-1]
elif remainder == 1:
	result += milks[-1]
print(result)	