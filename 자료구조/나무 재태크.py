import sys

N, M, K = map(int, sys.stdin.readline().split())

nutrition = [[5 for _ in range(N)] for _ in range(N)]
tree = {}
annual_add = []


def add_to_tree(i, j, age):
    if i in tree.keys():
        if j in tree[i].keys():
            tree[i][j].insert(0, age)
        else:
            tree[i][j] = [age]
    else:
        tree[i] = {}
        tree[i][j] = [age]


def breed(i, j):
    if j + 1 < N:
        add_to_tree(i, j + 1, 1)
    if j - 1 >= 0:
        add_to_tree(i, j - 1, 1)
    if i - 1 >= 0:
        add_to_tree(i - 1, j, 1)
        if j - 1 >= 0:
            add_to_tree(i - 1, j - 1, 1)
        if j + 1 < N:
            add_to_tree(i - 1, j + 1, 1)
    if i + 1 < N:
        add_to_tree(i + 1, j, 1)
        if j - 1 >= 0:
            add_to_tree(i + 1, j - 1, 1)
        if j + 1 < N:
            add_to_tree(i + 1, j + 1, 1)


for _ in range(N):
    annual_add.append(list(map(int, sys.stdin.readline().split())))

for _ in range(M):
    i, j, age = map(int, sys.stdin.readline().split())
    add_to_tree(i - 1, j - 1, age)

for _ in range(K):
    breed_q = []
    for i in range(N):
        for j in range(N):
            if i in tree and j in tree[i]:
                temp = []
                temp_nutrition = 0
                for k in tree[i][j]:
                    if nutrition[i][j] - k >= 0:
                        nutrition[i][j] -= k
                        temp.append(k + 1)
                        if (k + 1) % 5 == 0:
                            breed_q.append([i, j])
                    else:
                        temp_nutrition += int(k / 2)
                nutrition[i][j] += temp_nutrition
                tree[i][j] = temp
            nutrition[i][j] += annual_add[i][j]

    for i, j in breed_q:
        breed(i, j)


result = 0
for i in tree:
    for j in tree[i]:
        result += len(tree[i][j])

print(result)
