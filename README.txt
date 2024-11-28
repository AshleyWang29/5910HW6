Evil Hangman Project
I worked alone.

Description:
An enhanced version of Hangman where the word dynamically changes based on the player's guesses, making the game more challenging.

Cohesion and Coupling:
- High Cohesion:
Class EvilHangman is designed to control the gameplay, such as start & end the game, Inputs & Outputs, record the previous guesses, etc.
Class Solution is designed to do the background algorithm to make game "Evil", such as generate word families, choose the harder family to guess, etc.
- Low Coupling: Utilized data structures such as HashMap and ArrayList for efficient word family management.
Class interact with each other by passing simple data parameters to keep low coupling of the classes.

Instructions to Run:
1. Place the dictionary file "engDictionary.txt" in the project directory.
2. Compile the Java files
3. Run the game through EvilHangmanRunner
4. Follow the prompts to guess letters until the game ends.

Trade-offs:
- Chose HashMap over ArrayList of ArrayLists for word families. Although the memory overhead has increased, the search
efficiency has improved, and by using HashMap, we can obtain partial solutions, which can help us make game as hard as possible,