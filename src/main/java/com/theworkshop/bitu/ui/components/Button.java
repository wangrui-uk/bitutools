package com.theworkshop.bitu.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends BaseComponent {

    public interface Handler {

        void onClick(Button source);

    }

    private JLabel label = new JLabel();
    private Color color = Color.GRAY;
    private Handler handler = null;

    public Button(String text, Color color) {
        super(new Dimension(200, 30));

        this.color = color;

        this.label.setText(text);
        this.label.setSize(this.size);
        this.label.setFont(new Font("Arial", Font.BOLD, 16));
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setVerticalAlignment(JLabel.CENTER);

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (null != handler) {
                    handler.onClick(Button.this);
                }
            }

        });
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = this.paintBaseComponent(g);
        g2D.setColor(this.color);
        g2D.fillRoundRect(0, 0, this.size.width, this.size.height, 10, 10);
        this.label.paint(g2D);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setText(String name) {
        this.label.setText(name);
        this.repaint();
    }

}