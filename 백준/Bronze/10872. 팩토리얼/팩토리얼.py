n = int(input())
def recur(n):
    if n == 0 :
        return 1
    else:
        return n * recur(n-1)
print(recur(n))