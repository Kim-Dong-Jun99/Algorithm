import sys
n,m=map(int,sys.stdin.readline().split())
ns=list(map(int,sys.stdin.readline().split()))
ns.sort()
s=[]
def backtrack(index):
    global n
    global m
    if len(s) == m:
        to_print=' '.join(map(str, s))
        sys.stdout.write("%s\n"%to_print)
        return
    for i in range(index,n):
        s.append(ns[i])
        backtrack(i)
        s.pop()
    
backtrack(0)