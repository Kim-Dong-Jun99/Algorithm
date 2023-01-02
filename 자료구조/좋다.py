import sys
from collections import defaultdict

N = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
int_dict = defaultdict(int)
for i in ns:
    int_dict[i] += 1
good = 0
for i in range(N):
    check = False
    for j in range(N):
        if ((ns[i] - ns[j]) in int_dict) and i != j:
            if (ns[i] - ns[j]) == ns[j]:
                if int_dict[ns[i] - ns[j]] > 1:
                    if ns[i] == 0:
                        if int_dict[0] > 2:
                            check = True
                            break
                    else:
                        check = True
                        break
            else:
                if ns[j] == 0:
                    if int_dict[ns[i]] > 1:
                        check = True
                        break
                else:
                    check = True
                    break
    if check:
        good += 1
print(good)
