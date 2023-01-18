import sys

ns = sys.stdin.readline()
count = [0,0]
for i in range(len(ns) - 1):
    if ns[i] != ns[i+1]:
        count[int(ns[i])] += 1

print(min(count))