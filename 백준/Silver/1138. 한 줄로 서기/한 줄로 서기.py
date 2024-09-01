from sys import stdin,setrecursionlimit
input = stdin.readline

N = int(input()) 
N_list = list(map(int,input().split())) 
cm = list(range(1,N+1)) 
result = []

j = -1
for i in range(N):
    result.insert(N_list[j], cm[j])
    j -=1

for i in result:
    print(i , end = ' ')