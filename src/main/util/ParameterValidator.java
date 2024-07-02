package main.util;

public final class ParameterValidator {
    private ParameterValidator() {
    }

    public static String validateNames(String firstPlayer, String secondPlayer) {
        if (firstPlayer == null || firstPlayer.trim().isEmpty() || secondPlayer == null || secondPlayer.trim().isEmpty()) {
            return "Player name cannot be empty or whitespace";
        }
        if (!firstPlayer.matches("[a-zA-Z0-9]+") || !secondPlayer.matches("[a-zA-Z0-9]+")) {
            return "Player name can only contain English letters and numbers";
        }

        if (firstPlayer.equals(secondPlayer)) {
            return "Player names must be different";
        }
        return null;
    }
}
