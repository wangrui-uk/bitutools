package com.theworkshop.bitu.ui.panels;

import com.theworkshop.bitu.model.BitcoinRpc;
import com.theworkshop.bitu.model.generate.GenerateBlock;
import com.theworkshop.bitu.model.wallet.Wallet;
import com.theworkshop.bitu.ui.components.ApplyButton;
import com.theworkshop.bitu.ui.components.BaseComponent;
import com.theworkshop.bitu.ui.frames.GenerateFrame;
import com.theworkshop.bitu.ui.frames.WalletFrame;

import javax.swing.*;
import java.awt.*;

public class GeneratePanel extends BaseComponent {

    private JLabel message = new JLabel();

    public GeneratePanel() {
        super(new Dimension(540, 150));

        this.message = new JLabel("");
        this.message.setForeground(new Color(80, 80, 80));
        this.message.setFont(new Font("Arial", Font.PLAIN, 16));
        this.message.setSize(500, 25);
        this.message.setLocation(20, 20);
        this.add(this.message);

        ApplyButton applyButton = new ApplyButton("CLOSE", Color.ORANGE);
        applyButton.setLocation(0, this.size.height-50);
        applyButton.setHandler(source -> {
            GenerateFrame.close();
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
        GenerateBlock generateBlock = BitcoinRpc.generateBlock(10);
        if (null == generateBlock.getError()) {
            this.message.setText("10 blocks generated");
        }else{
            this.message.setText(generateBlock.getError());
        }
    }

}