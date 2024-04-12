package tetris.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tetris.control.KeyHandler;
import tetris.gui.screens.*;

public class GameForm extends JFrame
{
    private GameScreen gameScreen;
    private TitleScreen titleScreen;
    private KeyHandler keyHandler;

    private ScreenTypes currentScreenType;

    public GameForm()
    {
        this.setResizable(false);
        this.setPreferredSize(new Dimension(400, 720));

        titleScreen = new TitleScreen();
        gameScreen = new GameScreen();
        keyHandler = new KeyHandler(this);

        this.add(titleScreen);
        currentScreenType = ScreenTypes.TITLE;

        this.pack();
        this.setLocationRelativeTo(null);
        this.addKeyListener(keyHandler);
    }

    public void switchScreen(ScreenTypes newScreen)
    {
        getContentPane().removeAll();

        switch (newScreen) {
            case TITLE:
                getContentPane().add(titleScreen);
                break;
            case GAME:
                getContentPane().add(gameScreen);
                break;
        }

        currentScreenType = newScreen;
        revalidate();
        repaint();
    }

    public void startGame()
    {
        switchScreen(ScreenTypes.GAME);
        new GameThread(gameScreen, this).start();
    }

    public ScreenTypes getCurrentScreenType()
    {
    	return this.currentScreenType;
    }

    public TitleScreen getTitleScreen()
    {
    	return this.titleScreen; 
    }

    public GameScreen getGameScreen()
    {
    	return this.gameScreen; 
    }
}
