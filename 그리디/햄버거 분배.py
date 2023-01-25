import sys
from collections import defaultdict
N, K = map(int, sys.stdin.readline().split())

table = sys.stdin.readline().rstrip()

index = table.find('P')
eaten = defaultdict(int)
result = 0
if index == -1:
	print(0)
else:
	for i in range(index, N):
		if table[i] == 'P':
			for j in range(i-K,i+K+1):
				if 0 <= j and j < N:
					if table[j] == 'H' and eaten[j] == 0:
# 						print(i)
						eaten[j] = 1
						result += 1
						break
						
	print(result)