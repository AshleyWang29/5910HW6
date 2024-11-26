Evil Hangman Project
I worked alone.

Description:
An enhanced version of Hangman where the word dynamically changes based on the player's guesses, making the game more challenging.

Cohesion and Coupling:
- High Cohesion: Each class is designed with a single, well-defined responsibility (gameplay, word families, I/O).
- Low Coupling: Utilized data structures such as HashMap and ArrayList for efficient word family management.

Instructions to Run:
1. Place the engDictionary.txt file in the project directory.
2. Compile the Java files
3. Run the game through HangmanRunner
4. Follow the prompts to guess letters until the game ends.

Trade-offs:
- Chose HashMap over ArrayList of ArrayLists for word families to improve lookup efficiency.
