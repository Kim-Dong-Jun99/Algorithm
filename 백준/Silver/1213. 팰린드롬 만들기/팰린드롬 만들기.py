from collections import defaultdict

n = input()
n_dic = defaultdict(int)
n_set = set()
# print(n)
for i in n:
    # print(i)
    n_dic[i] += 1
    n_set.add(i)
n_list = list(n_set)
n_list.sort()
is_odd = 0
for i in n_dic.values():
    if i % 2 == 1:
        is_odd += 1

if is_odd > 1:
    print("I'm Sorry Hansoo")
elif is_odd == 1 and len(n) % 2 == 0:
    print("I'm Sorry Hansoo")
else:
    left_result = ""
    right_result = ""
    if is_odd == 1:
        center = ""
        for i in n_list:
            if n_dic[i] % 2 == 1:
                center += i
            left_result += i * (n_dic[i] // 2)
            right_result = i * (n_dic[i] // 2) + right_result
        print(left_result+center+right_result)
    else:
        for i in n_list:
            left_result += i * (n_dic[i]//2)
            right_result = i * (n_dic[i]//2) + right_result
        print(left_result+right_result)
