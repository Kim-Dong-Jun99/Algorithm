import sys
testcase = int(sys.stdin.readline())
query = [sys.stdin.readline().strip() for i in range(testcase)]
result = 0
for i in query:
    visited = []
    check = True
    for j in range(len(i)):
        if (i[j] not in visited):
            visited.append(i[j])
        else:
            if i[j-1] != i[j]:
                check = False
                break
    if check:
        result += 1
print(result)