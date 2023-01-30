import sys

N, K = map(int, sys.stdin.readline().split())

kids = list(map(int, sys.stdin.readline().split()))

# (m-1)*x = N-K, x = 조원이 한명이 아닌 조의 개수, m = 한명이 아닌 조의 조원 수

left = 0
right = N-1
result = 0
X = N - K
ones = N - 2*X
if ones >= 0:
	for i in range(ones):
		# 왼쪽 합이 더 작음
		if kids[right-1] - kids[left] < kids[right] -  kids[left+1]:
			right -= 1
		else:
			left += 1
	for i in range(left,right,2):
		result += kids[i+1] - kids[i]
else:

	
	
print(result)



