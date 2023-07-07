def recursion(n,pos):
    if n == 1:
        return "11011"[:pos].count("1")
    a, b = divmod(pos, 5 ** (n-1))
    cnt = 0
    if a <= 1:
        cnt = 4**(n-1) * a + recursion(n-1,b)        
    if a == 2:
        cnt = 2 * 4**(n-1)
    if a > 2:
        cnt = 4**(n-1) * (a-1) + recursion(n-1,b)
    return cnt
    
def solution(n, l, r):
    answer = recursion(n,r) - recursion(n,l-1)
    return answer