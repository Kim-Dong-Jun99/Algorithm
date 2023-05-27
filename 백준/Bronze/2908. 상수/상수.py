import sys

a, b = sys.stdin.readline().split()
n, m = 0, 0
for i in range(2,-1,-1):
    n += int(a[i]) * (10**i)
    m += int(b[i]) * (10**i)
    
sys.stdout.write("%d\n"%(max(n,m)))