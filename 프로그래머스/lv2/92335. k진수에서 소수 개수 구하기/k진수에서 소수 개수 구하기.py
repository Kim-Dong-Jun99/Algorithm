def solution(n, k):
    answer = 0
    def convert(n, q):
        rev_base = ''

        while n > 0:
            n, mod = divmod(n, q)
            rev_base += str(mod)

        return rev_base[::-1] 
    # print(convert(n,k).split('0'))
    for i in convert(n, k).split('0'):
        if len(i) >= 1 and int(i) != 1:
            num = int(i)
            is_prime = True
            for j in range(2, int(num**0.5)+1):
                if num % j == 0:
                    is_prime = False
                    break
            if is_prime:
                answer += 1
            
    return answer