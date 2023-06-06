import sys
n,m=map(int,sys.stdin.readline().split())
campus=[]
doyeon=[]
visited=[]
for i in range(n):
    temp = sys.stdin.readline().rstrip()
    campus.append(temp)
    visited.append([0]*m)
    for j in range(m):
        if temp[j]=='I':
            doyeon.append([i,j])
            visited[i][j] = 1
result = 0

def can_go(i,j):
    cango=[]
    global n
    global m
    if i - 1 >= 0:
        cango.append([i-1,j])
    if i + 1 < n:
        cango.append([i+1,j])
    if j - 1 >= 0:
        cango.append([i,j-1])
    if j + 1 < m:
        cango.append([i,j+1])
    return cango

while doyeon:
    temp = []
    for i,j in doyeon:
        for k,l in can_go(i,j):
            if visited[k][l] == 0:
                visited[k][l] = 1
                if campus[k][l] != 'X':
                    temp.append([k,l])
                    if campus[k][l] == 'P':
                        result += 1
    doyeon=temp

if result == 0:
    print("TT")
else:
    print(result)
            