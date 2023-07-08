def solution(price, money, count):
    sums = sum([i for i in range(1,count+1)])
    sums *= price
    if sums > money:
        return sums - money
    return 0