package tetris.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tetris.control.KeyHandler;
import tetris.gui.screens.*;

public class GameForm extends JFrame
{
    private Screen screen;
    private ScreenTypes screenType;

    private KeyHandler keyHandler;
    private GameThread gameThread;

    public GameForm()
    {
        // Game Environment
        this.setResizable(false);
        this.setPreferredSize(new Dimension(400, 720));

        keyHandler = new KeyHandler(this);
        switchScreen(ScreenTypes.TITLE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.addKeyListener(keyHandler);

        new GameThread(this).start();
    }

    public void switchScreen(ScreenTypes screenType)
    {
        getContentPane().removeAll();

        switch (screenType) {

            case TITLE: {
                this.screen = new TitleScreen();
                getContentPane().add(this.screen);
            } break;

            case GAME: {
                this.screen = new GameScreen();
                getContentPane().add(this.screen);
            } break;
        }

        this.screenType = screenType;
        revalidate();
        repaint();
    }

    public ScreenTypes getScreenType()
    {
    	return this.screenType;
    }

    public Screen getScreen()
    {
    	return this.screen; 
    }

    public TitleScreen getTitleScreen()
    {
        if (this.screen instanceof TitleScreen) {
            return (TitleScreen) this.screen;
        }
        return null;
    }

    public GameScreen getGameScreen()
    {
        if (this.screen instanceof GameScreen) {
            return (GameScreen) this.screen;
        }
        return null;
    }
}
