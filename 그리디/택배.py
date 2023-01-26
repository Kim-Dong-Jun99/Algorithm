import sys

N, C = map(int, sys.stdin.readline().split())
M = int(sys.stdin.readline())
delivery = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
# 목적지 순으로 정렬
delivery.sort(key = lambda x : x[1]*10+x[0])
cur_weight = [0] * (N+1)
result = 0
# print(delivery)
for depart, dest, weight in delivery:
	cur_max = 0
	for i in range(depart,dest):
		if cur_weight[i] > cur_max:
			cur_max = cur_weight[i]
	if cur_max < C:
		temp_weight = cur_max + weight
		if temp_weight > C:
			weight = C - cur_max
		result += weight
		for i in range(depart, dest):
			cur_weight[i] += weight
print(result)