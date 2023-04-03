import sys

def cal_top3():
	minimum = 1000
	for i in range(6):
		for j in range(6):
			if i + j != 5 and i != j:
				for k in range(6):
					if i + k != 5 and i != k and j != k and j + k != 5:
						temp = dices[i] + dices[j] + dices[k]
						if temp < minimum:
							minimum = temp
	return minimum
	
	
def cal_top2():
	minimum = 1000
	for i in range(6):
		for j in range(6):
			if i + j != 5 and i != j:
				temp = dices[i] + dices[j]
				if temp < minimum:
					minimum = temp
	return minimum
				

N = int(sys.stdin.readline())
# a,b,c,d,e,f 
dices = list(map(int, sys.stdin.readline().split()))

top3 = cal_top3()
top2 = cal_top2()
top1 = min(dices)

if N == 1:
	result = sum(dices) - max(dices)
	print(result)
else:
	result = 2*(top3*2 + top2*2 + top2*3*(N-2) + top1*((N-2)**2) + top1*(N-2))
	result += (N-2) * (top2*2 + top1*(N-2)*3 + top1*2)
	print(result)
	