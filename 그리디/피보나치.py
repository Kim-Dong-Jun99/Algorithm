p = [1,2]
for i in range(2, 46):
    p.append(p[i-2]+p[i-1]) 
T = int(input())

for j in range(T):
    n = int(input())
    result=[]
    while(n):
        for k in range(46):
            if(p[k]<=n):
                t = p[k]
        n -= t
        result.append(t)
        result.sort()
    for b in range(len(result)):
        print(result[b], end=' ')