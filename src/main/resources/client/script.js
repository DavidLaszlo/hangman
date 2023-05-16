String.prototype.replaceAt = function(index, replacement) {
    return this.substring(0, index) + replacement + this.substring(index + replacement.length);
};

document.addEventListener('DOMContentLoaded', function() {
    // Initialize game state
    let word = '';
    let attemptsRemaining = 6;

    // DOM elements
    const wordDisplay = document.getElementById('word-display');
    const guessInput = document.getElementById('guess-input');
    const guessButton = document.getElementById('guess-button');
    const attemptsRemainingDisplay = document.getElementById('attempts-remaining');
    const host = 'http://localhost:8080';

    // Function to update the game display
    function updateDisplay() {
        wordDisplay.textContent = word;
        attemptsRemainingDisplay.textContent = attemptsRemaining;
    }

    // Function to handle guess submission
    function handleGuess() {
        const letter = guessInput.value.toLowerCase();
        guessInput.value = '';

        // Send guess to the server
        const url = host + '/guess?guessedLetter=' + letter;

        fetch(url, {
            method: 'GET',
            })
            .then(response => response.json())
            .then(response => {
                if (!word && response.wordLength) {
                    word = '_'.repeat(response.wordLength);
                }

                if (response.indices && response.indices.length > 0) {
                    response.indices.forEach(index => {word = word.replaceAt(index, letter)});
                } else {
                    attemptsRemaining--;
                }

                // Update display
                updateDisplay();

                // Check game over conditions
                if (attemptsRemaining === 0) {
                    alert('Game over! You lost.');
                } else if (!word.includes('_')) {
                    alert('Congratulations! You won!');
                }
            })
            .catch(error => console.error('Error:', error));
    }

    // Attach event listener to guess button
    guessButton.addEventListener('click', handleGuess);
    // Attach event listener to guess input field for Enter key press
    guessInput.addEventListener('keyup', function(event) {
        if (event.key === 'Enter') {
            handleGuess();
        }
    });

    // Function to initialize the game state and display
    function initializeGame() {
        fetch( host + '/guess?guessedLetter= ')
            .then(response => response.json())
            .then(response => {
                if (response.wordLength) {
                    word = '_'.repeat(response.wordLength);
                }
                updateDisplay();
            })
            .catch(error => console.error('Error:', error));
    }

    // Invoke the function to initialize the game state and display
    initializeGame();
});
