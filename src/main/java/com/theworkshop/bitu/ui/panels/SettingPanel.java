package com.theworkshop.bitu.ui.panels;

import com.theworkshop.bitu.model.SettingProperty;
import com.theworkshop.bitu.ui.components.ApplyButton;
import com.theworkshop.bitu.ui.components.BaseComponent;
import com.theworkshop.bitu.ui.components.CancelButton;
import com.theworkshop.bitu.ui.frames.SettingFrame;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends BaseComponent {

    private JTextField urlField = new JTextField(SettingProperty.getUrl());
    private JTextField usernameField = new JTextField(SettingProperty.getUsername());
    private JPasswordField passwordField = new JPasswordField(SettingProperty.getPassword());

    public SettingPanel() {
        super(new Dimension(540, 200));

        JLabel label = new JLabel("URL");
        label.setForeground(new Color(80, 80, 80));
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setSize(100, 25);
        label.setLocation(20, 20);
        this.add(label);

        label = new JLabel("Username");
        label.setForeground(new Color(80, 80, 80));
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setSize(100, 25);
        label.setLocation(20, 50);
        this.add(label);

        label = new JLabel("Password");
        label.setForeground(new Color(80, 80, 80));
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setSize(100, 25);
        label.setLocation(20, 80);
        this.add(label);

        this.urlField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.urlField.setSize(400, 25);
        this.urlField.setLocation(120, 20);
        this.urlField.setBackground(null);
        this.urlField.setBorder(null);
        this.urlField.setForeground(new Color(80, 80, 80));
        this.urlField.setSelectionColor(Color.ORANGE);
        this.urlField.setSelectedTextColor(Color.WHITE);
        this.add(this.urlField);

        this.usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.usernameField.setSize(400, 25);
        this.usernameField.setLocation(120, 50);
        this.usernameField.setBackground(null);
        this.usernameField.setBorder(null);
        this.usernameField.setForeground(new Color(80, 80, 80));
        this.usernameField.setSelectionColor(Color.ORANGE);
        this.usernameField.setSelectedTextColor(Color.WHITE);
        this.add(this.usernameField);

        this.passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.passwordField.setSize(400, 25);
        this.passwordField.setLocation(120, 80);
        this.passwordField.setBackground(null);
        this.passwordField.setBorder(null);
        this.passwordField.setForeground(new Color(80, 80, 80));
        this.passwordField.setSelectionColor(Color.ORANGE);
        this.passwordField.setSelectedTextColor(Color.WHITE);
        this.add(this.passwordField);

        ApplyButton applyButton = new ApplyButton("UPDATE", Color.ORANGE);
        applyButton.setLocation(0, 150);
        applyButton.setHandler(source -> {
            SettingFrame.close();
            SettingProperty.storeSetting(urlField.getText(), usernameField.getText(), passwordField.getText());
        });
        this.add(applyButton);

        CancelButton cancelButton = new CancelButton("CANCEL", Color.GRAY);
        cancelButton.setLocation(440, 150);
        cancelButton.setHandler(source -> {
            SettingFrame.close();
            urlField.setText(SettingProperty.getUrl());
            usernameField.setText(SettingProperty.getUsername());
            passwordField.setText(SettingProperty.getPassword());
        });
        this.add(cancelButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = this.paintBaseComponent(g);
        g2D.setColor(new Color(220, 220, 220, 220));
        g2D.fillRoundRect(0, 0, this.size.width, this.size.height, 10, 10);
        g2D.setColor(new Color(80, 80, 80));
        g2D.drawLine(120, 45, 520, 45);
        g2D.drawLine(120, 75, 520, 75);
        g2D.drawLine(120, 105, 520, 105);
    }

}