import sys
n = input()
dp = [0 for _ in range(len(n)+1)]
if n[0] == '0' or (n[-1] == '0' and int(n[-2]) > 2):
    print(0)
else:
    dp[0] = 1
    dp[1] = 1
    for i in range(2,len(n)+1):
        if n[i-2:i] == '00':
            print(0)
            sys.exit()
        if 10 <= int(n[i-2:i]) <= 26:
            dp[i] += dp[i-2]
        if n[i-1] != '0':
            dp[i] += dp[i-1]

    print(dp[-1] % 1000000)