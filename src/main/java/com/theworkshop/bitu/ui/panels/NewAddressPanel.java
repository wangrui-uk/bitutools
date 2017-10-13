package com.theworkshop.bitu.ui.panels;

import com.theworkshop.bitu.model.BitcoinRpc;
import com.theworkshop.bitu.model.generate.GenerateBlock;
import com.theworkshop.bitu.model.newaddress.NewAddress;
import com.theworkshop.bitu.ui.components.ApplyButton;
import com.theworkshop.bitu.ui.components.BaseComponent;
import com.theworkshop.bitu.ui.components.Button;
import com.theworkshop.bitu.ui.frames.GenerateFrame;
import com.theworkshop.bitu.ui.frames.NewAddressFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class NewAddressPanel extends BaseComponent {

    private JTextField message = new JTextField();
    private Button copyButton = null;

    public NewAddressPanel() {
        super(new Dimension(540, 180));

        this.message.setFont(new Font("Arial", Font.PLAIN, 16));
        this.message.setSize(500, 25);
        this.message.setLocation(20, 20);
        this.message.setBackground(null);
        this.message.setBorder(null);
        this.message.setForeground(new Color(80, 80, 80));
        this.message.setSelectionColor(Color.ORANGE);
        this.message.setSelectedTextColor(Color.WHITE);
        this.message.setEditable(false);
        this.add(this.message);

        this.copyButton = new Button("COPY", Color.ORANGE);
        this.copyButton.setLocation(180, this.size.height-100);
        this.copyButton.setHandler(source -> {
            StringSelection stringSelection = new StringSelection(this.message.getText().substring(9));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            copyButton.setText("COPYED");
        });
        this.add(this.copyButton);

        ApplyButton applyButton = new ApplyButton("CLOSE", Color.ORANGE);
        applyButton.setLocation(0, this.size.height-50);
        applyButton.setHandler(source -> {
            NewAddressFrame.close();
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
        NewAddress newAddress = BitcoinRpc.newAddress();
        if (null == newAddress.getError()) {
            this.message.setText("Address: " + newAddress.getResult());
            this.copyButton.setVisible(true);
        }else{
            this.message.setText(newAddress.getError());
            this.copyButton.setVisible(false);
        }
    }

    public void reset() {
        this.copyButton.setText("COPY");
    }

}