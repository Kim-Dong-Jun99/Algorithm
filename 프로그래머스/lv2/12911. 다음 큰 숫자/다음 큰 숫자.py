def solution(n):
    answer = 0
    binary = bin(n)
    bin_count = binary.count("1")
    print(binary, bin_count)
    index = n + 1
    while True:
        temp_binary = bin(index)
        temp_count = temp_binary.count("1")
        if temp_count == bin_count:
            return index
        index += 1
    # return answer