package edu.wctc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiceGame {
    private final List<Player> players = new ArrayList<>();
    private final List<Die> dice = new ArrayList<>();
    private final int maxRolls;
    private Player currentPlayer;

    public DiceGame(int countPlayers, int countDice, int maxRolls) {

        this.maxRolls = maxRolls;
        if (countPlayers < 2) {
            throw new IllegalArgumentException();
        }else {
            for (int i = 0; i < countPlayers; i++) {
                Player player = new Player();
                players.add(player);
            }
            for (int i = 0; i < countDice; i++) {
                Die die = new Die(6);
                dice.add(die);
            }
        }
    }

    public boolean allDiceHeld() {
        return dice.stream().allMatch(Die ::isBeingHeld);
    }


    public boolean autoHold(int faceValue) {
            boolean hold = false;
            for (Die die:dice) {
                if (die.getFaceValue() == faceValue){
                    die.holdDie();
                    hold = true;
                    break;
                }
            }
            return hold;
    }

    public boolean currentPlayerCanRoll() {
        if (currentPlayer.getRollsUsed() < maxRolls) {
            return true;}
        else {
            return false;}
    }

    public int getCurrentPlayerNumber() {
        return currentPlayer.getPlayerNumber();
    }

    public int getCurrentPlayerScore() {
        return currentPlayer.getScore();
    }

    public String getDiceResults() {
        return "TBD";
    }

    public String getFinalWinner() {
        return "TBD";
    }

    public String getGameResults() {
        return "TBD";
    }

    private boolean isHoldingDie(int faceValue) {
        List<Die> held =
                dice.stream().filter(Die::isBeingHeld).toList();
        for (Die die : held) {
            if (die.getFaceValue() == faceValue) {
                return true;}
            else {
                return false;}
        }
        return false;
    }

    public boolean nextPlayer() {
        if (currentPlayer.getPlayerNumber() < players.size()) {
            currentPlayer = players.get(currentPlayer.getPlayerNumber() + 1);
            return true;}
        else {
            return false;}
    }

    public void playerHold(char dienum) {
        dice.stream().filter(die -> die.getDieNum() == dienum).findFirst().get().holdDie();
    }

    public void resetDice() {
        dice.forEach(Die::resetDie);
    }

    public void resetPlayers() {
        players.forEach(Player::resetPlayer);
    }

    public void rollDice() {
        currentPlayer.roll();
        dice.forEach(Die :: rollDie);
    }

    public void scoreCurrentPlayer() {
        if (allDiceHeld()) {
            for (Die die : dice) {
                if (!die.isBeingHeld()) {
                    currentPlayer.setScore(die.getFaceValue());
                }
            }
        }
        else {
            currentPlayer.setScore(0);
        }
    }

    public void startNewGame() {
        currentPlayer = players.get(0);
        resetPlayers();
    }





}
