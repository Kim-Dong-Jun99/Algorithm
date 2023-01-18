import sys

N = int(sys.stdin.readline())

distance = list(map(int, sys.stdin.readline().split()))
gas = list(map(int, sys.stdin.readline().split()))

result = 0
index = 0
while True:
    done = True
    for i in range(index + 1, N):
        result += gas[index] * distance[i-1]
        if gas[i] < gas[index]:
            done = False
            index = i
            break
    # print(result)
    if index == N-1 or done:
        break

print(result)