import sys

N, K = map(int, sys.stdin.readline().split())

kids = list(map(int, sys.stdin.readline().split()))

diffs = []
for i in range(N-1):
	diffs.append([i,i+1,kids[i+1] - kids[i]])

diffs.sort(key = lambda x:x[2], reverse = True)

print(diffs)

result = kids[-1] - kids[0]

for i in range(K-1):
	result -= diffs[i][2]
print(result)
	


