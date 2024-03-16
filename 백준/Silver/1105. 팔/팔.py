L, R = input().split()

if len(R) > len(L):
	print(0)
else:
	result = 0
	for i in range(len(R)):
		if L[i] == R[i]:
			if L[i] == '8':
				result += 1
		else:
			break
	print(result)