import sys

N = int(sys.stdin.readline())

tips = [int(sys.stdin.readline()) for _ in range(N)]
tips.sort(reverse = True)
rank = 1
result = 0
for i in tips:
	temp = i - (rank - 1)
	if temp > 0:
		result += temp
	rank += 1
	
print(result)