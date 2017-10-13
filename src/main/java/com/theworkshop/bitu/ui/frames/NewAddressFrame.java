package com.theworkshop.bitu.ui.frames;

import com.theworkshop.bitu.ui.BitcoinTray;
import com.theworkshop.bitu.ui.panels.GeneratePanel;
import com.theworkshop.bitu.ui.panels.NewAddressPanel;

import javax.swing.*;
import java.awt.*;

public class NewAddressFrame extends JFrame {

    private static NewAddressFrame I = null;
    private NewAddressPanel newAddressPanel = null;

    public NewAddressFrame() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setContentPane(this.newAddressPanel = new NewAddressPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b) {
            this.newAddressPanel.reset();
        }
    }

    public static void load() {
        I.newAddressPanel.load();
    }

    public static NewAddressFrame getInstance() {
        if (null == I) {
            I = new NewAddressFrame();
        }
        return I;
    }

    public static void close() {
        I.setVisible(false);
        BitcoinTray.CURRENT_MODE = BitcoinTray.MODE.DEFAULT;
    }

}
