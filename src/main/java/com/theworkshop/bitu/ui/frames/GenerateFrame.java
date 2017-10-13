package com.theworkshop.bitu.ui.frames;

import com.theworkshop.bitu.ui.BitcoinTray;
import com.theworkshop.bitu.ui.panels.GeneratePanel;
import com.theworkshop.bitu.ui.panels.WalletPanel;

import javax.swing.*;
import java.awt.*;

public class GenerateFrame extends JFrame {

    private static GenerateFrame I = null;
    private GeneratePanel generatePanel = null;

    public GenerateFrame() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setContentPane(this.generatePanel = new GeneratePanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public static void load() {
        I.generatePanel.load();
    }

    public static GenerateFrame getInstance() {
        if (null == I) {
            I = new GenerateFrame();
        }
        return I;
    }

    public static void close() {
        I.setVisible(false);
        BitcoinTray.CURRENT_MODE = BitcoinTray.MODE.DEFAULT;
    }

}
