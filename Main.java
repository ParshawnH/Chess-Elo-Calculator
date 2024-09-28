//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Import statements here

// Your JavaDoc here
import java.util.Scanner;
import java.lang.Math;
/**
 *  ChessElo
 *
 * This program takes in input from System.in
 * Calculates elo change with equations, sends new elo back
 *
 * @author Parshawn Haynes,
 *
 * @version Sep 26, 2024
 *
 */

public class Main{

    public static final String WELCOME_MESSAGE = "Welcome to the Chess Elo Calculator!";
    public static final String MAIN_MENU = "Please Select an Operation\n" + "1 - Single Match\n" +
            "2 - Tournament Results\n" + "3 - Exit";

    public static final String SINGLE_MATCH = "Please Enter Player 1's Elo followed by a hyphen '-' " +
            "then the Outcome followed by Player 2's Elo.";
    public static final String TOURNAMENT_RESULTS = "Please Enter the Tournament String to be Calculated.";
    public static final String MATCH_OUTCOME = "The Result of the Single Match is ";
    public static final String TOURNAMENT_OUTCOME = "The Final Tournament Results are ";
    public static final String CONFIRMATION = "Are You Sure You Want to Exit?";
    public static final String INVALID_INPUT = "Invalid Input! Try again";
    public static final String THANK_YOU = "Thank You For Using the Chess Elo Calculator!";


    public static String newElo(String match) {
        int playerElo1 = Integer.parseInt(match.substring(0, match.indexOf("-")));
        int playerElo2 = Integer.parseInt(match.substring(match.indexOf("-") + 2, match.length()));

        Double rPlayer1 = Math.pow(10f, playerElo1 / 400f);
        Double rPlayer2 = Math.pow(10f, playerElo2 / 400f);

        Double ePlayer1 = (Double) rPlayer1 / (rPlayer1 + rPlayer2);
        Double ePlayer2 = (Double) rPlayer2 / (rPlayer1 + rPlayer2);

        if (match.contains("W")) {

            playerElo1 = (int) ((float) playerElo1 + 25 * (1 - ePlayer1));
            playerElo2 = (int) ((float) playerElo2 + 25 * (0 - ePlayer2));

        } else {

            playerElo2 = (int) ((float) playerElo2 + 25 * (1 - ePlayer2));
            playerElo1 = (int) ((float) playerElo1 + 25 * (0 - ePlayer1));


        }
        String matchResult = playerElo1 + "-" + playerElo2;
        return matchResult;
    }
    public static void main(String[] args) { //Start Main
        Scanner input = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);
        boolean confirm = false;

        do { //Start While

            System.out.println(MAIN_MENU);
            String menuChoice = input.nextLine();
            switch(menuChoice) {

                case "1": //Single Match
                    System.out.println(SINGLE_MATCH);
                    String singleMatch = input.nextLine();

                    //Output
                    System.out.println(MATCH_OUTCOME + newElo(singleMatch));
                    break;

                case "2": //Tournament Results
                    System.out.println(TOURNAMENT_RESULTS);
                    String tournamentResults = input.nextLine();
                    int iter = 0;
                    String dashLocat = ""; //Making a fake list
                    for (int i = 0; i < tournamentResults.length(); i++) {
                        if (tournamentResults.charAt(i) == '-') {
                            iter += 1;
                            dashLocat += Integer.toString(i + 1) + "-"; //Grabs dash locations
                        }
                    }
                    dashLocat += Integer.toString(tournamentResults.length()) + "-";

                    String[] contenders = new String[iter + 1];
                    int beginner = 0;
                    for (int i = 0; i <= iter; i++) {
                        if (i < iter) {
                            contenders[i] = tournamentResults.substring(beginner,
                                    Integer.parseInt(dashLocat.substring(0, dashLocat.indexOf("-"))) - 1);
                        } else {
                            contenders[i] = tournamentResults.substring(beginner,
                                    Integer.parseInt(dashLocat.substring(0, dashLocat.indexOf("-"))));
                        }
                        beginner = Integer.parseInt(dashLocat.substring(0, dashLocat.indexOf("-")));
                        dashLocat = dashLocat.substring(dashLocat.indexOf("-") + 1, dashLocat.length());
                    }

                    for (int i = 1; i <= iter; i++) {
                        if (i >= 1) {
                            String afterMatch = newElo(contenders[0] + "-" + contenders[i]);
                            contenders[0] = afterMatch.substring(0, afterMatch.indexOf("-"));
                            contenders[i] = afterMatch.substring(afterMatch.indexOf("-") + 1, afterMatch.length());
                        }
                    }
                    String multiMatch = contenders[0];

                    for (int i = 1; i <= iter; i++) {

                        multiMatch += "-";
                        multiMatch += contenders[i];
                    }
                    System.out.println(TOURNAMENT_OUTCOME + multiMatch);
                    break;

                case "3": //Ender
                    String confirmation;
                    boolean valid = false;
                    do {
                        System.out.println(CONFIRMATION);
                        confirmation = input.nextLine();
                        if (confirmation.equalsIgnoreCase("y") ||
                                confirmation.equalsIgnoreCase("yes")) {
                            confirm = true;
                            valid = true;
                            break;
                        } else if (confirmation.equalsIgnoreCase("no") ||
                                confirmation.equalsIgnoreCase("n")) {
                            valid = true;
                            continue;
                        } else {
                            System.out.println(INVALID_INPUT);

                        }
                    } while (valid == false);
                    break;

                default: //Wrong Statement
                    System.out.println(INVALID_INPUT);
            }


        } while (confirm == false); //End while

        //Ending Statement
        System.out.println(THANK_YOU);

    } // End main
} // End class}
