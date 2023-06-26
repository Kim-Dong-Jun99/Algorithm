import sys

target = list(sys.stdin.readline().strip())
bomb = sys.stdin.readline().strip()


left = []
for i in target:
    left.append(i)
    if left[-1] == bomb[-1]:
        if left[-len(bomb):] == list(bomb):
            for _ in range(len(bomb)):
                left.pop()


if left == []:
    print('FRULA')
else:
    for _ in left:
        print(_,end = '')
