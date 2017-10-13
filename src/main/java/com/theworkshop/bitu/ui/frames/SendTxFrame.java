package com.theworkshop.bitu.ui.frames;

import com.theworkshop.bitu.ui.BitcoinTray;
import com.theworkshop.bitu.ui.panels.SendTxPanel;

import javax.swing.*;
import java.awt.*;

public class SendTxFrame extends JFrame {

    private static SendTxFrame I = null;
    private SendTxPanel sendTxPanel = null;

    public SendTxFrame() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setContentPane(this.sendTxPanel = new SendTxPanel(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b) {
            this.sendTxPanel.closePanel();
        }
    }

    public static SendTxFrame getInstance() {
        if (null == I) {
            I = new SendTxFrame();
        }
        return I;
    }

    public static void close() {
        I.setVisible(false);
        BitcoinTray.CURRENT_MODE = BitcoinTray.MODE.DEFAULT;
    }

}
