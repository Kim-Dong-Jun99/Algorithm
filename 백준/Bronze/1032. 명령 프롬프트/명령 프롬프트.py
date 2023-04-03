import sys
n = int(sys.stdin.readline())
strs = [sys.stdin.readline().strip() for i in range(n)]
compare = strs[0]
for i in range(1,n):
    for j in range(len(compare)):
        if compare[j] != strs[i][j]:
            compare = compare[:j]+'?'+compare[j+1:]
print(compare)