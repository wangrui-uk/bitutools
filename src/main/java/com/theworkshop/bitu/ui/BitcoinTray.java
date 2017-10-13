package com.theworkshop.bitu.ui;

import com.theworkshop.bitu.model.BitcoinRpc;
import com.theworkshop.bitu.model.generate.GenerateBlock;
import com.theworkshop.bitu.ui.frames.*;

import javax.swing.*;
import java.awt.*;

public class BitcoinTray {

    public enum MODE {

        SEND_TRANSACTION(SendTxFrame.getInstance()),
        GENERATE_ADDRESS(NewAddressFrame.getInstance()),
        CONFIRM_BLOCK(GenerateFrame.getInstance()),
        WALLET_STATUS(WalletFrame.getInstance()),
        SETTING_STATUS(SettingFrame.getInstance()),
        DEFAULT(null),
        ;

        private JFrame frame = null;

        private MODE(JFrame frame) {
            this.frame = frame;
        }

        private JFrame getFrame() {
            return this.frame;
        }

    }

    public static MODE CURRENT_MODE = MODE.DEFAULT;

    public BitcoinTray() {
        Image image = Toolkit.getDefaultToolkit().getImage(
                Thread.currentThread().getContextClassLoader().getResource("Bitcoin-icon.png")
        );
        if (SystemTray.isSupported()) {
            PopupMenu popup = new PopupMenu();

            MenuItem item = new MenuItem("Send transaction");
            item.addActionListener(e -> {
                if (null != CURRENT_MODE.getFrame() && !MODE.DEFAULT.equals(CURRENT_MODE)) {
                    CURRENT_MODE.getFrame().setVisible(false);
                }
                CURRENT_MODE = MODE.SEND_TRANSACTION;
                CURRENT_MODE.getFrame().setVisible(true);
            });
            popup.add(item);

            item = new MenuItem("Generate new address");
            item.addActionListener(e -> {
                if (null != CURRENT_MODE.getFrame() && !MODE.DEFAULT.equals(CURRENT_MODE)) {
                    CURRENT_MODE.getFrame().setVisible(false);
                }
                CURRENT_MODE = MODE.GENERATE_ADDRESS;
                ((NewAddressFrame)CURRENT_MODE.getFrame()).load();
                CURRENT_MODE.getFrame().setVisible(true);
            });
            popup.add(item);

            item = new MenuItem("Confirm transaction (blocks+10)");
            item.addActionListener(e -> {
                if (null != CURRENT_MODE.getFrame() && !MODE.DEFAULT.equals(CURRENT_MODE)) {
                    CURRENT_MODE.getFrame().setVisible(false);
                }
                CURRENT_MODE = MODE.CONFIRM_BLOCK;
                ((GenerateFrame)CURRENT_MODE.getFrame()).load();
                CURRENT_MODE.getFrame().setVisible(true);
            });
            popup.add(item);

            item = new MenuItem("Wallet status");
            item.addActionListener(e -> {
                if (null != CURRENT_MODE.getFrame() && !MODE.DEFAULT.equals(CURRENT_MODE)) {
                    CURRENT_MODE.getFrame().setVisible(false);
                }
                CURRENT_MODE = MODE.WALLET_STATUS;
                ((WalletFrame)CURRENT_MODE.getFrame()).load();
                CURRENT_MODE.getFrame().setVisible(true);
            });
            popup.add(item);

            popup.addSeparator();

            item = new MenuItem("Setting");
            item.addActionListener(e -> {
                if (null != CURRENT_MODE.getFrame() && !MODE.DEFAULT.equals(CURRENT_MODE)) {
                    CURRENT_MODE.getFrame().setVisible(false);
                }
                CURRENT_MODE = MODE.SETTING_STATUS;
                CURRENT_MODE.getFrame().setVisible(true);
            });
            popup.add(item);

            popup.addSeparator();

            item = new MenuItem("Exit");
            item.addActionListener(e -> {
                System.exit(0);
            });
            popup.add(item);


            SystemTray tray = SystemTray.getSystemTray();

            TrayIcon trayIcon = new TrayIcon(image, "Bitu RPC UI", popup);
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String argv[]) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            new BitcoinTray();
        });
    }

}