import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

dp = [[-1 for _ in range(n)] for _ in range(n)]
for i in range(n):
    dp[i][i] = 1

m = int(sys.stdin.readline())
for _ in range(m):
    l, r = map(int, sys.stdin.readline().split())
    l -= 1
    r -= 1
    if dp[l][r] == -1:
        ltr = l
        rtr = r
        while ltr < rtr and ns[ltr] == ns[rtr] and dp[ltr][rtr] == -1:
            ltr += 1
            rtr -= 1

        if ltr > rtr:
            print(1)
            while l < r:
                dp[l][r] = 1
                l += 1
                r -= 1
        else:
            if dp[ltr][rtr] == 0:
                while l <= ltr and rtr <= r:
                    dp[l][r] = 0
                    l += 1
                    r -= 1
                print(0)
            elif dp[ltr][rtr] == 1:
                while l <= ltr and rtr <= r:
                    dp[l][r] = 1
                    l += 1
                    r -= 1
                print(1)
            else:
                dp[l][r] = 0
                print(0)
    elif dp[l][r] == 1:
        print(1)
    else:
        print(0)