n = int(input())


def checkps(i, j, a, b):
    if i == a:
        return False
    elif j == b:
        return False
    elif abs(i - a) == abs(j - b):
        return False
    else:
        return True


class Node():
    def __init__(self, data):
        self.data = data
        self.child = None
        self.visited = [0 for _ in range(n)]


class queens():
    def __init__(self):
        self.head = Node((-1, -1))
        self.head.child = [Node((0, i)) for i in range(n)]
        self.table = {}
        for i in range(n):
            for j in range(n):
                self.table[(i, j)] = Node((i, j))
                if i + 1 < n:
                    self.table[(i, j)].child = [Node((i + 1, k)) for k in range(n)]

    def backtrack(self):
        result = 0
        cur = self.head
        stack = []
        while True:
            print('cur %s' % list(cur.data))
            if cur.child == None:
                print('!!!!!')
                result += 1
                cur = stack.pop()
            else:
                f = False
                for i in cur.child:
                    # print(list(i.data))
                    if cur.visited[i.data[1]] == 0:
                        if cur.data != (-1, -1):
                            if checkps(cur.data[0], cur.data[1], i.data[0], i.data[1]):
                                check = True
                                for j in stack:
                                    if j.data != None:
                                        if checkps(j.data[0], j.data[1], i.data[0], i.data[1]) == False:
                                            check = False
                                            break
                                if check:
                                    stack.append(cur)
                                    cur.visited[i.data[1]] = 1
                                    cur = i
                                    f = True
                                    break
                        else:
                            stack.append(cur)
                            cur.visited[i.data[1]] = 1
                            cur = i
                            f = True
                            break
                if f == False:
                    if stack:
                        cur = stack.pop()
                    else:
                        break

        return result


q = queens()
print(q.backtrack())