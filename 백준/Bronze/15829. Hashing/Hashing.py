import sys
L = int(sys.stdin.readline())
given = sys.stdin.readline().rstrip()
hash = 0
mod = 1234567891
for i in range(len(given)):
    hash += 31**i * (ord(given[i]) - 96)
    hash %= mod
sys.stdout.write("%d\n"%hash)