import sys
lnum,snum = map(int,sys.stdin.readline().split())
table = {}
result = []
for i in range(lnum):
    query = sys.stdin.readline().rstrip()
    table[query] = 1
for i in range(snum):
    query = sys.stdin.readline().rstrip()
    try:
        temp = table[query]
    except:
        continue
    else:
        result.append(query)
result.sort()
print(len(result))
for i in result:
    print(i)