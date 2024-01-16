# GoFish Game

## Overview

- This app is designed to offer a GoFish-like gaming experience where users face off against a computer opponent.
- The computer in this game is equipped with memory capabilities, recalling the cards the player requests that it doesn't have and keeping track of cards it has already asked for but the player doesn't possess (to its knowledge).
- The computer strategically asks for cards in a specific order, enhancing the gameplay with a touch of intelligence.
- The project was initiated in December 2023 and is considered a basic AI implementation due to the computer player's ability to make decisions beyond random chance.

## How It Works

### Player Setup
- Each player is assigned a hand and a point total.
- The point total is updated whenever a pair is made during the game.

### Card Handling
- The deck/pond and player hands utilize ArrayLists to store Card objects.
- A method is implemented to check a player's hand for pairs.

### Computer Logic
The computer follows a specific order when asking for cards:
  1. Inquires about cards it knows the player has and also possesses.
  2. Asks for cards it has but hasn't previously requested from the player.
  3. Seeks cards it has asked for before and knows the player doesn't have.

## Gameplay Features

- Standard rules for GoFish are implemented, providing an authentic gaming experience.
- Flexibility to implement individual rules, such as adjusting the number of cards drawn when a hand is empty or using 4-of-a-kinds for points instead of pairs.

Feel free to explore the code and consider contributing to enhance the game or customize the rules according to your preferences. Have fun playing GoFish!
