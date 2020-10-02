package wtc.mcarter.swingy.view;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.model.Game;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.util.Algos;

public class PlayGame extends JPanel {
    private WindowManager windowManager;
    private Hero hero;

    public PlayGame(WindowManager windowManager, Hero hero) {
        this.windowManager = windowManager;
        this.hero = hero;
        Main.logger.logMessage("[PlayGame] Setting up components...");
        initComponents();
    }

    private void initComponents() {
        lblNameLbl = new JLabel("Name:");
        lblName = new JLabel();
        lblClassLbl = new JLabel("Class:");
        lblClass = new JLabel();
        lblLevelLbl = new JLabel("Level:");
        lblLevel = new JLabel();
        lblXPLbl = new JLabel("XP:");
        lblXP = new JLabel();
        lblHPLbl = new JLabel("HP:");
        lblHP = new JLabel();
        lblATKLbl = new JLabel("ATK:");
        lblATK = new JLabel();
        lblDEFLbl = new JLabel("DEF:");
        lblDEF = new JLabel();

        lblMove = new JLabel("Move:");
        cbxDirection = new JComboBox<>();
        btnMove = new JButton("Move");
        btnFight = new JButton("Fight");
        btnRun = new JButton("Run");
        btnBack = new JButton("Back");
        btnConsole = new JButton("Console Mode");

        txtGameWindow = new JTextArea("");

        cbxDirection.setModel(
                new DefaultComboBoxModel<>(new String[] { "North/Up", "East/Right", "South/Down", "West/Left" }));
        btnMove.setEnabled(false);
        btnFight.setEnabled(false);
        btnRun.setEnabled(false);
        txtGameWindow.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(lblNameLbl)
                            .addComponent(lblClassLbl)
                            .addComponent(lblLevelLbl)
                            .addComponent(lblXPLbl)
                            .addComponent(lblHPLbl)
                            .addComponent(lblATKLbl)
                            .addComponent(lblDEFLbl))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(lblName)
                            .addComponent(lblClass)
                            .addComponent(lblLevel)
                            .addComponent(lblXP)
                            .addComponent(lblHP)
                            .addComponent(lblATK)
                            .addComponent(lblDEF))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(30, Short.MAX_VALUE)
                            .addComponent(lblMove)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cbxDirection, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(30, Short.MAX_VALUE)
                            .addComponent(btnMove)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnFight)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnRun)
                            .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(30, Short.MAX_VALUE)
                            .addComponent(btnBack)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnConsole)
                            .addContainerGap(30, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup()
                    .addComponent(txtGameWindow, GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblNameLbl)
                            .addComponent(lblClassLbl)
                            .addComponent(lblLevelLbl)
                            .addComponent(lblXPLbl)
                            .addComponent(lblHPLbl)
                            .addComponent(lblATKLbl)
                            .addComponent(lblDEFLbl))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblName)
                            .addComponent(lblClass)
                            .addComponent(lblLevel)
                            .addComponent(lblXP)
                            .addComponent(lblHP)
                            .addComponent(lblATK)
                            .addComponent(lblDEF)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblMove)
                            .addComponent(cbxDirection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(btnMove)
                            .addComponent(btnFight)
                            .addComponent(btnRun))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(btnBack)
                            .addComponent(btnConsole)))
                    .addContainerGap(30, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtGameWindow))
        );
    }

    private JLabel lblNameLbl;
    private JLabel lblName;
    private JLabel lblClassLbl;
    private JLabel lblClass;
    private JLabel lblLevelLbl;
    private JLabel lblLevel;
    private JLabel lblXPLbl;
    private JLabel lblXP;
    private JLabel lblHPLbl;
    private JLabel lblHP;
    private JLabel lblATKLbl;
    private JLabel lblATK;
    private JLabel lblDEFLbl;
    private JLabel lblDEF;

    private JLabel lblMove;
    private JComboBox<String> cbxDirection;
    private JButton btnMove;
    private JButton btnFight;
    private JButton btnRun;
    private JButton btnBack;
    private JButton btnConsole;

    private JTextArea txtGameWindow;
}
