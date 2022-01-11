import sys
n = int(sys.stdin.readline())
coordinate = [list(map(int,sys.stdin.readline().split())) for i in range(n)]
dic = {}
for i in coordinate:
    if (i[0] in dic):
        temp = dic[i[0]]
        temp.append(i[1])
        dic[i[0]] = temp
    else:
        dic[i[0]] = [i[1]]
key = list(dic.keys())
key.sort()
for i in key:
    temp = dic[i]
    temp.sort()
    for j in temp:
        print('%d %d'%(i,j))