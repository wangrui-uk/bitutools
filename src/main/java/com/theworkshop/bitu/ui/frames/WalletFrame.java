package com.theworkshop.bitu.ui.frames;

import com.theworkshop.bitu.ui.BitcoinTray;
import com.theworkshop.bitu.ui.panels.WalletPanel;

import javax.swing.*;
import java.awt.*;

public class WalletFrame extends JFrame {

    private static WalletFrame I = null;
    private WalletPanel walletPanel = null;

    public WalletFrame() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setContentPane(this.walletPanel = new WalletPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public static void load() {
        I.walletPanel.load();
    }

    public static WalletFrame getInstance() {
        if (null == I) {
            I = new WalletFrame();
        }
        return I;
    }

    public static void close() {
        I.setVisible(false);
        BitcoinTray.CURRENT_MODE = BitcoinTray.MODE.DEFAULT;
    }

}
