import sys

N = int(sys.stdin.readline())
antena = list(map(int, sys.stdin.readline().split()))
antena.sort()

subsums = [antena[0]]
for i in range(1, N):
	subsums.append(subsums[i-1]+antena[i])

result_val = 0
result_index = 0
for i in range(N):
	if i == 0:
		result_val = subsums[-1] - antena[0]
	else:
		temp_val = (subsums[-1] - subsums[i] - antena[i]*(N-1-i)) + (antena[i]*(i+1) - subsums[i])
		if result_val > temp_val:
			result_val = temp_val
			result_index = i

print(antena[result_index])