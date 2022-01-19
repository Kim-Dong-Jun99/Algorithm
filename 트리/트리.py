import sys

n = int(sys.stdin.readline())
query = list(map(int, sys.stdin.readline().split()))
erase = int(sys.stdin.readline())


class node():
    def __init__(self, data):
        self.data = data
        self.childs = {}
        self.leaf = 0


class Tree():
    def __init__(self):
        self.head = node(0)
        self.table = {0: self.head}

    def insert(self, parent, data):
        parentnode = self.table[parent]
        childnode = node(data)
        parentnode.childs[data] = childnode
        self.table[data] = childnode

    def cut(self,data):
        leaves = 0
        cur = self.head
        visited = {cur.data: 1}
        stack = []
        while True:
            f = False
            for i in cur.childs:
                try:
                    check = visited[i]
                except:
                    stack.append(cur)
                    visited[i] = 1
                    cur = cur.childs[i]
                    f = True
                    break
            if f == False:

                if cur.childs == {}:
                    cur.leaf += 1
                    leaves += 1
                else:
                    for i in cur.childs:
                        cur.leaf += cur.childs[i].leaf
                if stack == []:
                    break
                cur = stack.pop()
        return leaves - self.table[data].leaf


tree = Tree()
for i in range(len(query)):
    if query[i] != -1:
        tree.insert(query[i], i)
print(tree.cut(erase))




input = sys.stdin.readline


def dfs(num, arr):
    arr[num] = -2
    for i in range(len(arr)):
        if num == arr[i]:
            dfs(i, arr)


n = int(input())
arr = list(map(int, input().split()))
k = int(input())
count = 0

dfs(k, arr)

for i in range(len(arr)):
    if arr[i] != -2 and i not in arr:
        count += 1
print(count)