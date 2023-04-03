import sys
n = int(sys.stdin.readline())
parents = list(map(int,sys.stdin.readline().split()))
erase = int(sys.stdin.readline())
tree = [[] for i in range(n)]
visited = [0 for i in range(n)]
for i in range(n):
    if parents[i] != -1:
        if i!= erase and parents[i] != erase:
            tree[parents[i]].append(i)
    else:
        root = i
cur = root
stack = []
leaves = 0
visited[cur] = 1
while True:
    f = False
    for i in tree[cur]:
        if visited[i] == 0:
            stack.append(cur)
            f = True
            cur = i
            visited[i] = 1
            break
    if f == False:
        if tree[cur] == []:
            leaves += 1
        if stack == []:
            break
        
        
        cur = stack.pop()
if root == erase:
    print(0)
else:
    print(leaves)
