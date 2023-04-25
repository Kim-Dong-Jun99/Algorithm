import sys
N, K = map(int, input().split())
aristo = [True] * (N+1)

m = int(N ** 0.5)
index = 0
for i in range(2, N+1):
    if aristo[i] == True:
        # print(i)
        index += 1
        if index == K:
            print(i)
            sys.exit()
        for j in range(i+i, N+1, i):
            # print(j)
            if aristo[j] == True:
                aristo[j] = False
                index += 1
                if index == K:
                    print(j)
                    sys.exit()

