package org.example;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int radius = 35;

    private Color normalColor =
            new Color(110,50,20);

    private Color hoverColor =
            new Color(140,70,30);

    public RoundedButton(String text) {

        super(text);

        setContentAreaFilled(false);

        setFocusPainted(false);

        setBorderPainted(false);

        setOpaque(false);

        setForeground(Color.WHITE);

        setFont(new Font("Arial", Font.BOLD, 18));

        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Hover Effect
        if(getModel().isRollover()) {

            g2.setColor(hoverColor);

        } else {

            g2.setColor(normalColor);
        }

        // Rounded Background
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                radius,
                radius);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(normalColor);

        g2.drawRoundRect(
                0,
                0,
                getWidth()-1,
                getHeight()-1,
                radius,
                radius);

        g2.dispose();
    }
}
