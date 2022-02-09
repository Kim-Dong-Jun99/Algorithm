import sys

tc = int(sys.stdin.readline())

for _ in range(tc):
    n = int(sys.stdin.readline())
    count = 1
    people = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    people.sort()
    tempMax = people[0][1]
    for i in range(1, n):
        if tempMax > people[i][1]:
            count += 1
            tempMax = people[i][1]
    print(count)