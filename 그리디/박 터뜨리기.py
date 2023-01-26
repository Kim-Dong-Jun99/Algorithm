import sys

N, K = map(int, sys.stdin.readline().split())

if N < int(K*(K+1)/2):
	print(-1)
else:
	diff = N - int(K*(K+1)/2)
	share = diff // K
	remainder = diff % K
	if remainder == 0:
		print(K-1)
	else:
		print(K)