#임계경로
#https://www.acmicpc.net/problem/1948
import sys
from collections import deque
sys.stdin = open("input.txt","r")
n=int(input()) #노드, 도시 개수
m=int(input()) #도로의 개수
graph=[[]*(n+1) for _ in range(n+1)]
back_graph=[[]*(n+1) for _ in range(n+1)]
indegree=[0]*(n+1) #진입차수
result=[0]*(n+1)
check=[0]*(n+1)
q=deque()
for _ in range(m):
    a,b,t=map(int,input().split())
    graph[a].append((b,t))
    back_graph[b].append((a,t))
    indegree[b]+=1
start,end=map(int,input().split())

q.append(start)

def topologysort():
    while q:
        cur=q.popleft()
        for i,t in graph[cur]:
            indegree[i]-=1
            result[i]=max(result[i],result[cur]+t)
            if indegree[i]==0:
                q.append(i)

    #백트래킹
    cnt=0 #임계경로에 속한 모든 정점의 개수
    q.append(end)
    while q: #도착점에서 시작점으로
        cur=q.popleft()
        for i,t in back_graph[cur]:
            #도착점까지의 비용에서 시작점의 비용을 뺐을 때 그 간선비용과 같다면
            if result[cur]-result[i]==t:
                cnt+=1
                if check[i]==0: #큐에 한번씩만 담을 수 있도록,중복방문제거 
                    q.append(i)
                    check[i]=1

    print(result[end])
    print(cnt)

topologysort()