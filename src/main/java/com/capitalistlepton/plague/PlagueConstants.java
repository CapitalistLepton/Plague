package com.capitalistlepton.plague;

import java.awt.*;

/**
 * Created by zanelittrell on 3/11/17.
 */
public interface PlagueConstants {
    /** Number of pixels the bacteria can take up horizontally in the bacteria panel. */
    int X_RESOLUTION = 640;
    /** Number of pixels the bacteria can take up vertically in the bacteria panel. */
    int Y_RESOLUTION = 480;
    /** Whether the game should be rendered in fullscreen or not. */
    boolean FULL_SCREEN = false;
    /** Font used whenever text is displayed. */
    Font GAME_FONT = new Font("Menlo", Font.PLAIN, 12);
    /** Bigger version of <pre>GAME_FONT</pre>. */
    Font BIG_GAME_FONT = new Font("Menlo", Font.PLAIN, 32);
    /** Biggest font used for the logo. */
    Font LOGO_FONT = new Font("Menlo", Font.PLAIN, 72);
    /** The background color of the MainWindow. */
    Color BACKGROUND = new Color(0x404446);
    /** A slightly darker color than the background. */
    Color DARK = new Color(0x2A2E30);
    /** A slightly lighter color than the background. */
    Color LIGHT = new Color(0x515F61);

    /** Number of bacteria that are generated at the beginning of the game. */
    int STARTING_BACTERIA = 50;
    /** The genotype of all the bacteria at the beginning of the game. */
    String STARTING_GENOTYPE = "AABBCC";

    /** The amount of money the player starts the game with. */
    int STARTING_FUNDS = 500;
    /** If the amount of bacteria goes above this number the player loses. */
    int MAX_BACTERIA = 1300;
    /** The time in milliseconds between each turn in the game (must be > 100). */
    int WAIT_BETWEEN_TURNS = 1000;
    /** The number of turns to wait before antibiotics are able to be applied. */
    int WAIT_BEFORE_ANTIBIOTICS = 12;
}
