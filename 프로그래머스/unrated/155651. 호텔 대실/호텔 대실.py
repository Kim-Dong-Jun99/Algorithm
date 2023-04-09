from heapq import heappush, heappop
def solution(book_time):
    """19:12"""
    answer = 0
    newTime = []
    for start, end in book_time:
        start_ = start.split(":")
        end_ = end.split(":")
        newTime.append([int(start_[0])*60 + int(start_[1]), int(end_[0])*60+int(end_[1])])
    newTime.sort(key=lambda x : x[0] * 1440 + x[1])
    rooms = []
    for start, end in newTime:
        # print(rooms)
        while rooms:
            if rooms[0] <= start:
                heappop(rooms)
            else:
                break
        heappush(rooms, end+10)
        if answer < len(rooms):
            answer = len(rooms)
        
    return answer