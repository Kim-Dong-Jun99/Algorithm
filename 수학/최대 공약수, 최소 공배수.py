import sys
n,m = map(int,sys.stdin.readline().split())

for i in range(min(n,m),0,-1):
    if n%i == 0 and m%i == 0:
        gcd = i
        print(i)
        break
l = int(n/gcd)
r = int(m/gcd)
if max(l,r) % min(l,r) == 0:
    print(gcd*max(r,l))
else:
    print(gcd*l*r)