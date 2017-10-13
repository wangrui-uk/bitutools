package com.theworkshop.bitu.ui.components;

import javax.swing.*;
import java.awt.*;

public abstract class BaseComponent extends JComponent {

    protected Dimension size = null;

    public BaseComponent(Dimension size) {
        this.setComponentSize(size);
        this.setLayout(null);
    }

    public void setComponentSize(Dimension size) {
        this.size = size;
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
    }

    protected Graphics2D paintBaseComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2D;
    }
}
