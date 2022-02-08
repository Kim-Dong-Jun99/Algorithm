import sys
sys.setrecursionlimit(10**6)
preorder = []
while True:
    try:
        temp = sys.stdin.readline()
        preorder.append(int(temp))
    except :
        break
# print(preorder)
class Node():
    def __init__(self, data):
        self.data = data
        self.lc = None
        self.rc = None
head = Node(None)
stack = []
cur = None

for i in preorder:
    if head.data == None:
        head.data = i
        cur = head
    else:
        if i < cur.data:
            cur.lc = Node(i)
            stack.append(cur)
            cur = cur.lc
        else:
            while stack:
                if i > stack[-1].data:
                    cur = stack.pop()
                else:
                    break
            cur.rc = Node(i)
            cur = cur.rc

def postorder(node):
    if node.lc != None:
        postorder(node.lc)
    if node.rc != None:
        postorder(node.rc)
    print(node.data)
postorder(head)