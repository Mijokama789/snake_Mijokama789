/*
 * This file is part of a Körber Pharma Software GmbH project.
 *
 * Copyright (c)
 *    Körber Pharma Software GmbH
 *    All rights reserved.
 *
 * This source file may be managed in different Java package structures,
 * depending on actual usage of the source file by the Copyright holders:
 *
 * for Werum:  com.werum.* or any other Körber Pharma Software owned Internet domain
 *
 * Any use of this file as part of a software system by none Copyright holders
 * is subject to license terms.
 *
 */
package com.koerber.training.windowframework;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The main GUI Window and all things to interact with the GUI.
 *
 * @author simon_oelerich
 * @company Körber Pharma Software GmbH
 * @created 28.07.2021
 * @since windowframework 1.0
 */
public class GameWindow extends AbstractWindow implements MouseListenerDefault, KeyListenerDefault, Runnable {

    private static final int BORDER_SIZE = 10;

    private final GridPanel gridPanel;
    private final GameActions gameActions;
    private Thread thread = null;
    private int tickTimeout = 1000;
    private volatile boolean isThreadRunning = true;
    private final JLabel label;
    private boolean paused = false;

    private final int fieldSize;
    private JPanel buttonContainer = null;

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                 | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public GameWindow(String title, int sizeX, int sizeY, int fieldSize, GameActions gameActions) {
        this(title, sizeX, sizeY, fieldSize, null, gameActions);
    }

    public GameWindow(String title, int sizeX, int sizeY, int fieldSize, Color gridColor, GameActions gameActions) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.gridPanel = new GridPanel(sizeX, sizeY, fieldSize, gridColor);
        this.gridPanel.addMouseListener(this);
        this.addKeyListener(this);
        this.gameActions = gameActions;
        this.fieldSize = fieldSize;

        buttonContainer = new JPanel(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        this.label = new JLabel("");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(this.label);
        contentPanel.add(labelPanel);

        Component spacer = Box.createVerticalStrut(BORDER_SIZE);
        spacer.setVisible(false);
        contentPanel.add(spacer);

        gridPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, 0, 0, 0));
        contentPanel.add(this.gridPanel);

        contentPanel.add(buttonContainer);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        this.pack();
        moveWindowOnSameScreenAsMouse();
    }

    // https://stackoverflow.com/questions/4627553/show-jframe-in-a-specific-screen-in-dual-monitor-configuration
    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
        } else if (gd.length > 0) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    public JPanel getButtonContainer() {
        return buttonContainer;
    }

    public GridPanel getGridPanel() {
        return gridPanel;
    }

    public void setText(String text) {
        this.label.setText(text);
        if (text == null || text.trim().isEmpty()) {
            this.getContentPane().getComponent(0).setVisible(false);
            this.getContentPane().getComponent(1).setVisible(false);
        } else {
            this.getContentPane().getComponent(0).setVisible(true);
            this.getContentPane().getComponent(1).setVisible(true);
        }
        this.pack();
    }

    public void setAllColor(Color color) {
        gridPanel.streamAllFields().forEach(field -> field.setColor(color));
        repaint();
    }

    public void setColorAt(Color color, int x, int y) {
        gridPanel.getFieldAt(x, y).setColor(color);
        repaint();
    }

    public Field getFieldAt(int x, int y) {
        return gridPanel.getFieldAt(x, y);
    }

    public List<Field> getAllFields() {
        return gridPanel.getAllFields();
    }

    public void setTextAt(String text, int x, int y) {
        this.gridPanel.setTextAt(text, x, y);
    }

    public void setTextAt(String text, int x, int y, int fontSize) {
        this.gridPanel.setTextAt(text, x, y);
        this.gridPanel.getFieldAt(x, y).setFontSize(fontSize);
    }

    public Stream<Field> streamAllFields() {
        return gridPanel.streamAllFields();
    }

    public void setGameLoopTimeout(int timeoutMilis) {
        this.tickTimeout = timeoutMilis;
    }

    public void startGameLoop(int timeoutMilis) {
        this.tickTimeout = timeoutMilis;
        startGameLoop();
    }

    public void startGameLoop() {
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.isThreadRunning = true;
            this.thread.start();
        }
    }

    public void stopGameLoop() {
        if (this.thread != null) {
            this.isThreadRunning = false;
            thread.interrupt();
            thread = null;
        }
    }

    @Override
    public void run() {
        try {
            while (this.isThreadRunning) {
                Thread.sleep(this.tickTimeout);
                if (!paused) {
                    this.gameActions.onGameLoopTick();
                    this.gridPanel.repaint();
                }
            }
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickPoint = pixelLocationToSquare(e.getX(), e.getY());
        if (clickPoint.x < 0 || clickPoint.y < 0 || clickPoint.x >= this.gridPanel.getSizeX()
                || clickPoint.y >= this.gridPanel.getSizeY()) {
            return;
        }
        MouseButton MouseClick = null;
        if (e.getButton() == MouseEvent.BUTTON1) {
            MouseClick = MouseButton.LEFTCLICK;
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            MouseClick = MouseButton.MIDDLECLICK;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            MouseClick = MouseButton.RIGHTCLICK;
        }
        if (MouseClick != null) {
            this.gameActions.onMousePressed(MouseClick);
        }
        this.gameActions.onGridClicked(clickPoint.x, clickPoint.y, MouseClick);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction dir = null;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dir = Direction.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dir = Direction.RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            dir = Direction.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            dir = Direction.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            paused = !paused;
        }
        if (dir != null) {
            this.gameActions.onArrowPressed(dir);
        }
    }

    private Point pixelLocationToSquare(int x, int y) {
        return new Point(x / this.fieldSize, y / this.fieldSize);
    }

    public void updateFixedSize() {
        Dimension size = calculateSize();
        setMinimumSize(size);
        setSize(size);
        repaint();
    }

    public Dimension calculateSize() {
        Dimension gridPanelSize = gridPanel.getMinimumSize();
        Insets gridInsets = new Insets(10, 10, 10, 10);

        Dimension btnPanelSize = buttonContainer.getMinimumSize();
        Insets border = getInsets();

        int width = Math.max(gridPanelSize.width, btnPanelSize.width) + border.left + border.right + gridInsets.left
                + gridInsets.right;
        int height = gridPanelSize.height + btnPanelSize.height + border.top + border.bottom + gridInsets.top
                + gridInsets.bottom + BORDER_SIZE;

        return new Dimension(width, height);
    }
}
