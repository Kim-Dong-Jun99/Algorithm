import sys
n,m = map(int,sys.stdin.readline().split())
circularq = [i for i in range(1,n+1)]
location = list(map(int,sys.stdin.readline().split()))
result = 0
for i in location:
    index = circularq.index(i)
    result += min(index,len(circularq)-index)
    circularq = circularq[index+1:]+circularq[:index]
print(result)

