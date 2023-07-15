def solution(phone_book):
    phone_book.sort(key = lambda x : len(x))
    visited = set()
    for i in phone_book:
        temp = ""
        for j in i:
            temp += j
            if temp in visited:
                return False
        visited.add(i)
    return True