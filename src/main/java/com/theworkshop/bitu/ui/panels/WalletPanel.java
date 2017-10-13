package com.theworkshop.bitu.ui.panels;

import com.theworkshop.bitu.model.BitcoinRpc;
import com.theworkshop.bitu.model.wallet.Wallet;
import com.theworkshop.bitu.ui.components.ApplyButton;
import com.theworkshop.bitu.ui.components.BaseComponent;
import com.theworkshop.bitu.ui.frames.WalletFrame;

import javax.swing.*;
import java.awt.*;

public class WalletPanel extends BaseComponent {

    private JLabel walletNameLabel = new JLabel();
    private JLabel walletversionLabel = new JLabel();
    private JLabel balanceLabel = new JLabel();
    private JLabel unconfirmed_balanceLabel = new JLabel();
    private JLabel immature_balanceLabel = new JLabel();
    private JLabel txcountLabel = new JLabel();
    private JLabel keypoololdestLabel = new JLabel();
    private JLabel keypoolsizeLabel = new JLabel();
    private JLabel keypoolsize_hd_internalLabel = new JLabel();
    private JLabel paytxfeeLabel = new JLabel();
    private JLabel hdmasterkeyidLabel = new JLabel();

    public WalletPanel() {
        super(new Dimension(540, 420));

        this.walletNameLabel = new JLabel("");
        this.walletNameLabel.setForeground(new Color(80, 80, 80));
        this.walletNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.walletNameLabel.setSize(500, 25);
        this.walletNameLabel.setLocation(20, 20);
        this.add(this.walletNameLabel);

        this.walletversionLabel = new JLabel("");
        this.walletversionLabel.setForeground(new Color(80, 80, 80));
        this.walletversionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.walletversionLabel.setSize(500, 25);
        this.walletversionLabel.setLocation(20, 50);
        this.add(this.walletversionLabel);

        this.balanceLabel = new JLabel("");
        this.balanceLabel.setForeground(new Color(80, 80, 80));
        this.balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.balanceLabel.setSize(500, 25);
        this.balanceLabel.setLocation(20, 80);
        this.add(this.balanceLabel);

        this.unconfirmed_balanceLabel = new JLabel("");
        this.unconfirmed_balanceLabel.setForeground(new Color(80, 80, 80));
        this.unconfirmed_balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.unconfirmed_balanceLabel.setSize(500, 25);
        this.unconfirmed_balanceLabel.setLocation(20, 110);
        this.add(this.unconfirmed_balanceLabel);

        this.immature_balanceLabel = new JLabel("");
        this.immature_balanceLabel.setForeground(new Color(80, 80, 80));
        this.immature_balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.immature_balanceLabel.setSize(500, 25);
        this.immature_balanceLabel.setLocation(20, 140);
        this.add(this.immature_balanceLabel);

        this.txcountLabel = new JLabel("");
        this.txcountLabel.setForeground(new Color(80, 80, 80));
        this.txcountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.txcountLabel.setSize(500, 25);
        this.txcountLabel.setLocation(20, 170);
        this.add(this.txcountLabel);

        this.keypoololdestLabel = new JLabel("");
        this.keypoololdestLabel.setForeground(new Color(80, 80, 80));
        this.keypoololdestLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.keypoololdestLabel.setSize(500, 25);
        this.keypoololdestLabel.setLocation(20, 200);
        this.add(this.keypoololdestLabel);

        this.keypoolsizeLabel = new JLabel("");
        this.keypoolsizeLabel.setForeground(new Color(80, 80, 80));
        this.keypoolsizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.keypoolsizeLabel.setSize(500, 25);
        this.keypoolsizeLabel.setLocation(20, 230);
        this.add(this.keypoolsizeLabel);

        this.keypoolsize_hd_internalLabel = new JLabel("");
        this.keypoolsize_hd_internalLabel.setForeground(new Color(80, 80, 80));
        this.keypoolsize_hd_internalLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.keypoolsize_hd_internalLabel.setSize(500, 25);
        this.keypoolsize_hd_internalLabel.setLocation(20, 260);
        this.add(this.keypoolsize_hd_internalLabel);

        this.paytxfeeLabel = new JLabel("");
        this.paytxfeeLabel.setForeground(new Color(80, 80, 80));
        this.paytxfeeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.paytxfeeLabel.setSize(500, 25);
        this.paytxfeeLabel.setLocation(20, 290);
        this.add(this.paytxfeeLabel);

        this.hdmasterkeyidLabel = new JLabel("");
        this.hdmasterkeyidLabel.setForeground(new Color(80, 80, 80));
        this.hdmasterkeyidLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.hdmasterkeyidLabel.setSize(500, 25);
        this.hdmasterkeyidLabel.setLocation(20, 320);
        this.add(this.hdmasterkeyidLabel);

        ApplyButton applyButton = new ApplyButton("CLOSE", Color.ORANGE);
        applyButton.setLocation(0, this.size.height-50);
        applyButton.setHandler(source -> {
            WalletFrame.close();
        });
        this.add(applyButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = this.paintBaseComponent(g);
        g2D.setColor(new Color(220, 220, 220, 220));
        g2D.fillRoundRect(0, 0, this.size.width, this.size.height, 10, 10);
    }

    public void load() {
        Wallet wallet = BitcoinRpc.getWalletInfo();
        if (null == wallet.getError()) {
            this.walletNameLabel.setText("Wallet name: " + wallet.getResult().getWalletname());
            this.walletversionLabel.setText("Wallet version: " + wallet.getResult().getWalletversion());
            this.balanceLabel.setText("Balance: " + wallet.getResult().getBalance());
            this.unconfirmed_balanceLabel.setText("Unconfirmed balance: " + wallet.getResult().getUnconfirmed_balance());
            this.immature_balanceLabel.setText("Immature balance: " + wallet.getResult().getImmature_balance());
            this.txcountLabel.setText("Transaction count: " + wallet.getResult().getTxcount());
            this.keypoololdestLabel.setText("Keypool oldest: " + wallet.getResult().getKeypoololdest());
            this.keypoolsizeLabel.setText("Keypool size: " + wallet.getResult().getKeypoolsize());
            this.keypoolsize_hd_internalLabel.setText("Keypool size HD internal: " + wallet.getResult().getKeypoolsize_hd_internal());
            this.paytxfeeLabel.setText("Pay transaction fee: " + wallet.getResult().getPaytxfee());
            this.hdmasterkeyidLabel.setText("HD master key id: " + wallet.getResult().getHdmasterkeyid());
        }else{
            this.walletNameLabel.setText(wallet.getError());
            this.walletversionLabel.setText("");
            this.balanceLabel.setText("");
            this.unconfirmed_balanceLabel.setText("");
            this.immature_balanceLabel.setText("");
            this.txcountLabel.setText("");
            this.keypoololdestLabel.setText("");
            this.keypoolsizeLabel.setText("");
            this.keypoolsize_hd_internalLabel.setText("");
            this.paytxfeeLabel.setText("");
            this.hdmasterkeyidLabel.setText("");
        }

    }

}