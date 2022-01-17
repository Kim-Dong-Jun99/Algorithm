import sys
n = int(sys.stdin.readline())
coordinate = [list(map(int,sys.stdin.readline().split())) for i in range(n)]
table = {}
for i in coordinate:
    try:
        temp = table[i[1]]
        temp.append(i[0])
        table[i[1]] = temp
    except:
        table[i[1]] = [i[0]]
yco = list(table.keys())
yco.sort()
for i in yco:
    temp = table[i]
    temp.sort()
    for j in temp:
        print('%d %d'%(j,i))