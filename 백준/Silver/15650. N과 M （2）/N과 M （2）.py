import sys
n,m=map(int, sys.stdin.readline().split())
s=[]
def backtrack(index):
    global m
    global n
    if len(s) == m:
        to_print=' '.join(map(str,s))
        sys.stdout.write("%s\n"%to_print)
        return
    for i in range(index, n+1):
        s.append(i)
        backtrack(i+1)
        s.pop()
    
    
backtrack(1)