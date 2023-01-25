import sys

N, L = map(int, sys.stdin.readline().split())

fruits = list(map(int, sys.stdin.readline().split()))

fruits.sort()

for i in fruits:
	if i <= L:
		L += 1
	else:
		break
		
print(L)