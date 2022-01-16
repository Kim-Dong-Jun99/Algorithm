import sys

n = int(sys.stdin.readline())
table = {}
query = [list(sys.stdin.readline().split()) for i in range(n)]
for i in query:
    try:
        temp = table[int(i[0])]
        temp.append(i[1])
        table[int(i[0])] = temp
    except:
        table[int(i[0])] = [i[1]]

age = list(table.keys())
age.sort()
for i in age:
    for j in table[i]:
        print('%d %s' % (i, j))