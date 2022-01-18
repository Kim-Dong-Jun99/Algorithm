import sys

query = sys.stdin.readline().rstrip()
while ('+' in query):
    index = query.index('+')
    leftend = index - 1
    rightend = index + 1
    while 0 <= leftend:
        if query[leftend].isdigit():
            leftend -= 1
        else:
            break
    while rightend < len(query):
        if query[rightend].isdigit():
            rightend += 1
        else:
            break
    leftend += 1
    rightend -= 1
    newV = str(int(query[leftend:index]) + int(query[index + 1:rightend + 1]))
    query = query[:leftend] + newV + query[rightend + 1:]
me = query.split('-')
result = int(me[0])
for i in range(1, len(me)):
    result -= int(me[i])
print(result)
