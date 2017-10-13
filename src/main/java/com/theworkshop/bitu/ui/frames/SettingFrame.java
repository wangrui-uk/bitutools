package com.theworkshop.bitu.ui.frames;

import com.theworkshop.bitu.ui.BitcoinTray;
import com.theworkshop.bitu.ui.panels.SettingPanel;

import javax.swing.*;
import java.awt.*;

public class SettingFrame extends JFrame {

    private static SettingFrame I = null;

    public SettingFrame() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setContentPane(new SettingPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public static SettingFrame getInstance() {
        if (null == I) {
            I = new SettingFrame();
        }
        return I;
    }

    public static void close() {
        I.setVisible(false);
        BitcoinTray.CURRENT_MODE = BitcoinTray.MODE.DEFAULT;
    }

}
