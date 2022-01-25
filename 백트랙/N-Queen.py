import sys
n = int(sys.stdin.readline())
result = 0
cb = [0 for _ in range(n)]

def check(m):
    for i in range(m):
        if cb[m] == cb[i] or abs(cb[i]-cb[m]) == m-i:
            return False
    return True

def backtrack(v):
    global result
    if v == n:
        result += 1
        return
    for i in range(n):
        cb[v] = i
        if check(v):
            backtrack(v+1)

backtrack(0)
print(result)