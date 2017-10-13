package com.theworkshop.bitu.ui.panels;

import com.theworkshop.bitu.model.BitcoinRpc;
import com.theworkshop.bitu.ui.components.ApplyButton;
import com.theworkshop.bitu.ui.components.BaseComponent;
import com.theworkshop.bitu.ui.components.Button;
import com.theworkshop.bitu.ui.components.CancelButton;
import com.theworkshop.bitu.ui.frames.SendTxFrame;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;

public class SendTxPanel extends BaseComponent {

    private class TxPanel extends BaseComponent {

        private JTextField addressField = new JTextField();
        private JTextField btcField = new JTextField();

        private TxPanel() {
            super(new Dimension(500, 30));

            JLabel label = new JLabel("Address");
            label.setForeground(new Color(80, 80, 80));
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            label.setSize(100, 25);
            label.setLocation(0, 0);
            this.add(label);

            label = new JLabel("BTC");
            label.setForeground(new Color(80, 80, 80));
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            label.setSize(50, 25);
            label.setLocation(360, 0);
            this.add(label);

            this.addressField.setFont(new Font("Arial", Font.PLAIN, 16));
            this.addressField.setSize(250, 25);
            this.addressField.setLocation(100, 0);
            this.addressField.setBackground(null);
            this.addressField.setBorder(null);
            this.addressField.setForeground(Color.BLACK);
            this.addressField.setSelectionColor(Color.ORANGE);
            this.addressField.setSelectedTextColor(Color.WHITE);
            this.addressField.setFocusable(true);
            this.add(this.addressField);

            this.btcField.setFont(new Font("Arial", Font.PLAIN, 16));
            this.btcField.setSize(100, 25);
            this.btcField.setLocation(400, 0);
            this.btcField.setBackground(null);
            this.btcField.setBorder(null);
            this.btcField.setForeground(Color.BLACK);
            this.btcField.setSelectionColor(Color.ORANGE);
            this.btcField.setSelectedTextColor(Color.WHITE);
            this.btcField.setFocusable(true);
            ((PlainDocument)this.btcField.getDocument()).setDocumentFilter(new DocumentFilter() {

                private boolean test(String text) {
                    try {
                        Double.parseDouble(text);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }

                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                    Document doc = fb.getDocument();
                    StringBuilder sb = new StringBuilder();
                    sb.append(doc.getText(0, doc.getLength()));
                    sb.insert(offset, string);

                    if (test(sb.toString())) {
                        super.insertString(fb, offset, string, attr);
                    } else {
                        // warn the user and don't allow the insert
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    Document doc = fb.getDocument();
                    StringBuilder sb = new StringBuilder();
                    sb.append(doc.getText(0, doc.getLength()));
                    text = text == null?"":text;
                    sb.replace(offset, offset + length, text);

                    if (test(sb.toString())) {
                        super.replace(fb, offset, length, text, attrs);
                    } else {
                        // warn the user and don't allow the insert
                    }
                }

            });
            this.add(this.btcField);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = this.paintBaseComponent(g);
            g2D.setColor(new Color(80, 80, 80));
            g2D.drawLine(100, this.size.height-1, 350, this.size.height-1);
            g2D.drawLine(400, this.size.height-1, 500, this.size.height-1);
        }

    }

    private ArrayList<TxPanel> txPanels = new ArrayList<TxPanel>();
    private Button addButton = new Button("ADD ADDRESS", Color.ORANGE);
    private ApplyButton applyButton = new ApplyButton("SEND", Color.ORANGE);
    private CancelButton cancelButton = new CancelButton("CANCEL", Color.GRAY);
    private JFrame parent = null;

    public SendTxPanel(JFrame parent) {
        super(new Dimension(540, 200));

        this.parent = parent;

        TxPanel txPanel = new TxPanel();
        txPanel.setLocation(20, 20);
        this.add(txPanel);
        this.txPanels.add(txPanel);

        this.addButton.setLocation(180, this.size.height-140);
        this.addButton.setHandler(source -> {
            TxPanel txn = new TxPanel();
            txn.setLocation(20, 20+txPanels.size()*35);
            add(txn);
            txPanels.add(txn);

            size.height += 35;

            addButton.setLocation(180, SendTxPanel.this.size.height-140);

            applyButton.setLocation(0, SendTxPanel.this.size.height-50);
            cancelButton.setLocation(440, SendTxPanel.this.size.height-50);

            SendTxPanel.this.setComponentSize(SendTxPanel.this.size);

            parent.pack();
            parent.setLocationRelativeTo(null);

            SendTxPanel.this.repaint();
        });
        this.add(this.addButton);

        this.applyButton.setLocation(0, this.size.height-50);
        this.applyButton.setHandler(source -> {
            String addresses[] = new String[this.txPanels.size()];
            String btc[] = new String[addresses.length];

            TxPanel firstTxnPanel = this.txPanels.get(0);
            addresses[0] = firstTxnPanel.addressField.getText();
            btc[0] = firstTxnPanel.btcField.getText();
            firstTxnPanel.addressField.setText(null);
            firstTxnPanel.btcField.setText(null);

            for (int i=1; i<this.txPanels.size(); i++) {
                TxPanel txn = this.txPanels.get(i);
                addresses[i] = txn.addressField.getText();
                btc[i] = txn.btcField.getText();
                SendTxPanel.this.remove(txn);
            }

            BitcoinRpc.sendToAddress(addresses, btc);

            this.txPanels.clear();
            this.txPanels.add(firstTxnPanel);

            this.setComponentSize(new Dimension(540, 200));

            addButton.setLocation(180, SendTxPanel.this.size.height-140);

            applyButton.setLocation(0, this.size.height-50);
            cancelButton.setLocation(440, this.size.height-50);

            this.setComponentSize(this.size);

            this.parent.pack();
            this.parent.setLocationRelativeTo(null);

            this.repaint();

            SendTxFrame.close();
        });
        this.add(this.applyButton);

        this.cancelButton.setLocation(440, this.size.height-50);
        this.cancelButton.setHandler(source -> {
            SendTxFrame.close();

            for (int i=1; i<this.txPanels.size(); i++) {
                TxPanel txn = this.txPanels.get(i);
                SendTxPanel.this.remove(txn);
            }
            TxPanel txn = this.txPanels.get(0);
            txn.addressField.setText(null);
            txn.btcField.setText(null);
            this.txPanels.clear();
            this.txPanels.add(txn);

            this.setComponentSize(new Dimension(540, 200));

            addButton.setLocation(180, SendTxPanel.this.size.height-140);

            applyButton.setLocation(0, this.size.height-50);
            cancelButton.setLocation(440, this.size.height-50);

            this.setComponentSize(this.size);

            this.parent.pack();
            this.parent.setLocationRelativeTo(null);

            this.repaint();
        });
        this.add(this.cancelButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = this.paintBaseComponent(g);
        g2D.setColor(new Color(220, 220, 220, 220));
        g2D.fillRoundRect(0, 0, this.size.width, this.size.height, 10, 10);
    }

    public void closePanel() {
        for (int i=1; i<this.txPanels.size(); i++) {
            TxPanel txn = this.txPanels.get(i);
            SendTxPanel.this.remove(txn);
        }
        TxPanel txn = this.txPanels.get(0);
        txn.addressField.setText(null);
        txn.btcField.setText(null);
        this.txPanels.clear();
        this.txPanels.add(txn);

        this.setComponentSize(new Dimension(540, 200));

        addButton.setLocation(180, SendTxPanel.this.size.height-140);

        applyButton.setLocation(0, this.size.height-50);
        cancelButton.setLocation(440, this.size.height-50);

        this.setComponentSize(this.size);

        this.parent.pack();
        this.parent.setLocationRelativeTo(null);

        this.repaint();
    }

}