import sys
from collections import defaultdict
abc = [int(sys.stdin.readline()) for _ in range(3)]
result = 1
num_dict = defaultdict(lambda : 0)

for i in abc:
    result *= i
    
result_str = str(result)

for i in result_str:
    num_dict[i] += 1

for i in range(10):
    print(num_dict[str(i)])