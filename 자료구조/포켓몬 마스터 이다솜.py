import sys
n,m = map(int,sys.stdin.readline().split())
table = {}
dic = [0]
for i in range(1,n+1):
    q = sys.stdin.readline().strip()
    table[q] = i
    dic.append(q)
for _ in range(m):
    q = sys.stdin.readline().strip()
    temp = table.get(q,-1)
    if temp == -1:
        print(dic[int(q)])
    else:
        print(temp)