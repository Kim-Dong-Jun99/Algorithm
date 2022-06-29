from sys import stdin
import heapq


n, m = map(int, stdin.readline().split())


cards = []
card_list = [int(x) for x in stdin.readline().split()]

for card in card_list:
    heapq.heappush(cards, card)

for _ in range(m):
    card1 = heapq.heappop(cards)
    card2 = heapq.heappop(cards)

    heapq.heappush(cards, card1 + card2)
    heapq.heappush(cards, card1 + card2)

print(sum(cards))