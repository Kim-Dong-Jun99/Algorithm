import sys
from collections import defaultdict
N,K=map(int,sys.stdin.readline().split())
if N == K:
    sys.stdout.write("0\n1\n")
    sys.exit()
visited=[0]*100001
visited[N]=1
cur=[N]

def cango(i):
    can_go=[]
    if i - 1 >= 0:
        can_go.append(i-1)
    if i + 1 <= 100000:
        can_go.append(i+1)
    if i * 2 <= 100000:
        can_go.append(i*2)
    return can_go
result = 0
while cur:
    temp = []
    min_time = defaultdict(lambda:0)
    result += 1
    for i in cur:
        for j in cango(i):
            if visited[j] == 0 and min_time[j] == 0:
                temp.append(j)
            if visited[j] == 0:
                min_time[j] += visited[i]
    cur=temp
    for i in min_time.keys():
        visited[i] += min_time[i]
    if visited[K] != 0:
        break
sys.stdout.write("%d\n%d\n"%(result,visited[K]))
                
    