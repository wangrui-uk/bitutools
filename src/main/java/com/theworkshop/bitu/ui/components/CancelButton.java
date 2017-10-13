package com.theworkshop.bitu.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CancelButton extends BaseComponent {

    public interface Handler {

        void onClick(CancelButton source);

    }

    private JLabel label = new JLabel();
    private Color color = Color.GRAY;
    private Handler handler = null;

    public CancelButton(String text, Color color) {
        super(new Dimension(100, 30));

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
                    handler.onClick(CancelButton.this);
                }
            }

        });
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = this.paintBaseComponent(g);
        g2D.setColor(this.color);
        g2D.fillRoundRect(0, 0, this.size.width, this.size.height, 10, 10);
        g2D.fillRect(this.size.width-20, 0, 20, this.size.height);
        this.label.paint(g2D);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}