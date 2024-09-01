import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(str, sys.stdin.readline().rstrip()))
    red = arr.count('R')
    blue = N - red

    ans = min(red, blue)
    cnt = 0
    for i in range(N):
        if arr[i] != arr[0]: break
        cnt += 1
    if arr[0] == 'R': ans = min(ans, red - cnt)
    else: ans = min(ans, blue - cnt)

    cnt = 0
    for i in range(N - 1, -1, -1):
        if arr[i] != arr[N - 1]: break
        cnt += 1
    if arr[N - 1] == 'R': ans = min(ans, red - cnt)
    else: ans = min(ans, blue - cnt)
    print(ans)