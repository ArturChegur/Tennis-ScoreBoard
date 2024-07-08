Tennis Match Scoreboard Web Application Usage Guide

Functionality

Home Page

	•	Links:
	•	New Match: Start a new tennis match.
	•	Completed Matches: View the list of finished matches.

New Match Page

	•	URL: /new-match
	•	How to Use:
	•	Enter the names of Player 1 and Player 2 in the form fields.
	•	Click the “Start” button to begin the match.

Match Score Page

	•	URL: /match-score?uuid=$match_id
	•	How to Use:
	•	View the current score in the table displaying player names.
	•	Use the buttons “Player 1 won the current point” and “Player 2 won the current point” to update the score.
	•	The score will update in real-time based on who wins each point.

Completed Matches Page

	•	URL: /matches?page=$page_number&filter_by_player_name=$player_name
	•	How to Use:
	•	Use the input field to enter a player’s name and click “Search” to filter matches by player name.
	•	Browse through the list of completed matches.
	•	Use the page switcher to navigate through multiple pages of match results if available.

Project`s technical specifications - https://zhukovsd.github.io/java-backend-learning-course/Projects/TennisScoreboard/

Author - [ArturChegur]