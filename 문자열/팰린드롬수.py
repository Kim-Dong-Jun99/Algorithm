import sys

while True:
    n = list(map(int, list(sys.stdin.readline().rstrip())))
    if n == [0]:
        break
    else:
        if n == list(reversed(n)):
            print('yes')
        else:
            print('no')
