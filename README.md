# Plague

This is a simple game where the player is given a screen with several bacteria on it and then must eradicate all the bacteria
before the colony overtakes the screen or you run out of money.


## Gameplay

Each turn (which happens automatically after a set amount of time):
1. The bacteria reproduce.
2. One dose of any active(selected) antibiotics are applied.
3. The cost of the antibiotics is removed from the total funds.

If at the end of a turn either
a) the colony has reached a lethal volume,
or
b) the total funds is less than or equal to 0,
the player loses the game. However, if the colony is successfully eradicated then the player wins and the remaining funds is
used as the player's score.

It should be noted that while an antibiotic is selected, a dose of that antibiotic will be applied every turn (and the cost
subtracted) until either the game ends or the antibiotic is unselected. Therefore, if one is no longer of use it is best to 
unselect it as soon as possible.

### Main Objective:
Wipe out all bacteria with as little money as needed.

## Bacteria

The bacteria in this game is a very simplified model of bacteria in the world. Each bacterium has a 
[genotype](https://en.wikipedia.org/wiki/Genotype) that it is given when created. The genotype is a string of characters used
to represent the genetics of the bacterium. Each two letter pair ("AA" or "Cc") is a gene that the bacterium has. Each gene
can either be *homozygous dominant* which is represented as two capital letters ("AA"), *heterozygous* which is represented as 
a capital letter and then a lower case letter ("Bb"), or *homozygous recessive* which is two lowercase letters ("aa"). All the 
bacteria start the game as the "AABBCC" genotype and then as the bacteria reproduce, they have a 1% chance of mutating. This
mutation means that a gene may go from homozygous dominant to homozygous recessive ("AA" to "aa") or any of the three possible
states. This mutation is then responsible for the bacteria being resistant to certain antibiotics and susceptible to others.
Therefore, it is important to watch for the genotypes present to know which antibiotics to use.

## Antibiotics

There are several different antibiotics in the game that target bacteria of a certain genotype. Each turn the active 
antibiotics are applied, killing the bacteria with the targeted genotypes with a certain effectiveness.

### Current Antibiotics in the Game:
- Amoxicilin
  - Cost: $8
  - Effectiveness: 45%
  - Targets: all genotypes ending in "CC"
- Doxycycline
  - Cost: $40
  - Effectiveness: 50%
  - Targets: all genotypes starting with "AA"
- Tetracycline
  - Cost: $60
  - Effectiveness: 55%
  - Targets: all genotypes starting with "Aa"
- Gentamicin
  - Cost: $100
  - Effectiveness: 73%
  - Targets: all genotypes with the middle letters lowercase, i.e. "bb"
