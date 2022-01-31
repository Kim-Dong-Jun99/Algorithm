import sys
from collections import Counter

n = int(sys.stdin.readline())
ns = [int(sys.stdin.readline()) for _ in range(n)]

ns.sort()
cm = Counter(ns).most_common()
print(round(sum(ns) / n))
print(ns[n // 2])
if len(cm) > 1:
    if cm[0][1] == cm[1][1]:
        print(cm[1][0])
    else:
        print(cm[0][0])
else:
    print(cm[0][0])
print(ns[-1] - ns[0])
