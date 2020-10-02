package wtc.mcarter.swingy.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.controller.CharacterController;

public class NewHero extends JPanel {
    private CharacterController characterController = new CharacterController();
    private WindowManager windowManager;

    public NewHero(WindowManager windowManager) {
        this.windowManager = windowManager;
        Main.logger.logMessage("[NewHero] Setting up components...");
        initComponents();
    }

    private void initComponents() {
        txtName = new JTextField();
        lblName = new JLabel();
        lblType = new JLabel();
        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        cbxType = new JComboBox<>();
        btnCreate = new JButton();
        btnCancel = new JButton();

        lblName.setText("Name");
        lblType.setText("Type");
        cbxType.setModel(new DefaultComboBoxModel<>(new String[] { "Knight", "Elf" }));
        btnCreate.setText("Create");
        btnCreate.addActionListener((evt) -> {
            btnCreate_Click(evt);
        });
        btnCancel.setText("Cancel");
        btnCancel.addActionListener((evt) -> {
            btnCancel_Click(evt);
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblType)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxType, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblName)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    )))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(errorMessage))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnCreate)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnCancel)))
                        .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblType)
                        .addComponent(cbxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(errorMessage)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnCreate)
                        .addComponent(btnCancel))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void btnCreate_Click(ActionEvent evt) {
        Main.logger.logMessage("[NewHero] Create button clicked");
        try {
            characterController.newHero((String) cbxType.getSelectedItem(), txtName.getText());
            windowManager.showSelectHero();
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    private void btnCancel_Click(ActionEvent evt) {
        Main.logger.logMessage("[NewHero] Cancel button clicked");
        windowManager.showSelectHero();
    }

    private JButton btnCreate;
    private JButton btnCancel;
    private JComboBox<String> cbxType;
    private JLabel lblName;
    private JLabel lblType;
    private JLabel errorMessage;
    private JTextField txtName;
}
