package wtc.mcarter.swingy.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.model.Game;
import wtc.mcarter.swingy.model.artifacts.Artifact;
import wtc.mcarter.swingy.model.characters.BlackMage;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Orc;
import wtc.mcarter.swingy.model.characters.Villain;
import wtc.mcarter.swingy.storage.HeroStorage;
import wtc.mcarter.swingy.util.Algos;
import wtc.mcarter.swingy.util.Misc;

public class PlayGame extends JPanel {
    private WindowManager windowManager;
    private Hero hero;

    private Game game;
    private Villain villain;

    public PlayGame(WindowManager windowManager, Hero hero) {
        this.windowManager = windowManager;
        this.hero = hero;
        Main.logger.logMessage("[PlayGame] Setting up components...");
        initComponents();
        setHeroValues();

        game = new Game();
        game.posX = 0;
        game.posY = 0;
        renderMap();
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
        btnMove.addActionListener((evt) -> {
            btnMove_Click(evt);
        });
        btnFight.setEnabled(false);
        btnFight.addActionListener((evt) -> {
            btnFight_Click(evt);
        });
        btnRun.setEnabled(false);
        btnRun.addActionListener((evt) -> {
            btnRun_Click(evt);
        });
        btnBack.addActionListener((evt) -> {
            btnBack_Click(evt);
        });
        btnConsole.addActionListener((evt) -> {
            btnConsole_Click(evt);
        });
        txtGameWindow.setEditable(false);
        // txtGameWindow.setFont(new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 12));

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

    private void setHeroValues() {
        if (hero != null) {
            lblName.setText(hero.getName());
            lblClass.setText(hero.getClass().getSimpleName());
            lblLevel.setText(Integer.toString(hero.getLevel()));
            lblXP.setText(Integer.toString(hero.getExperience()));
            lblHP.setText(Integer.toString(Math.max(hero.getHp(), 0)));
            lblATK.setText(String.format("%s+%s", hero.getBaseAttack(), hero.getWeapon().getDamage()));
            lblDEF.setText(String.format("%s+%s", hero.getBaseDefense(), hero.getArmor().getDefense()));
            if (hero.getHp() < 10) {
                lblHP.setForeground(Color.RED);
            } else {
                lblHP.setForeground(new Color(51, 51, 51)); // default color
            }
        }
    }

    private void renderMap() {
        String newText = "";

        game.gameSize = Algos.getGameSize(hero.getLevel());
        for (int y = -(game.gameSize / 2); y <= game.gameSize / 2; y++) {
            for (int x = -(game.gameSize / 2); x <= game.gameSize / 2; x++) {
                if (x == game.posX && y == game.posY)
                    newText += " X  ";
                else
                    newText += "▒ ";
            }
            newText += System.lineSeparator();
        }

        txtGameWindow.setText(newText);
        btnMove.setEnabled(true);
    }

    private void setMessage(String message, Object... args) {
        message = String.format(message, args);
        txtGameWindow.append(message);
    }

    private void btnMove_Click(ActionEvent evt) {
        Main.logger.logMessage("[PlayGame] Move button clicked");

        switch (cbxDirection.getSelectedIndex()) {
            case 0:
                Main.logger.logMessage("Moving Up...");
                tryMove(game.posX, game.posY - 1);
                break;
            case 1:
                Main.logger.logMessage("Moving Right...");
                tryMove(game.posX + 1, game.posY);
                break;
            case 2:
                Main.logger.logMessage("Moving Down...");
                tryMove(game.posX, game.posY + 1);
                break;
            case 3:
                Main.logger.logMessage("Moving Left...");
                tryMove(game.posX - 1, game.posY);
                break;
        }
    }

    private void tryMove(int x, int y) {
        btnMove.setEnabled(false);
        if (x < -(game.gameSize / 2) || y < -(game.gameSize / 2) || x > game.gameSize / 2 || y > game.gameSize / 2) {
            Main.logger.logMessage("Invalid location to move to. Aborted.");
            setMessage("%nInvalid Location!");
            btnMove.setEnabled(true);
        } else if (Algos.getEncounteredEnemy()) {
            Main.logger.logMessage("Found enemy!");
            if (Algos.getRandom(0, 1) == 1)
                villain = new BlackMage();
            else
                villain = new Orc();
            Main.logger.logMessage("Enemy type: " + villain.getClass().getSimpleName());

            setMessage("%nEncountered enemy %s!", villain.getClass().getSimpleName());
            btnFight.setEnabled(true);
            btnRun.setEnabled(true);
            newX = x;
            newY = y;
        } else {
            Main.logger.logMessage("Nothing found at target. Moved to X:%s Y:%s", x, y);
            doMove(x, y);
        }
    }

    private int newX;
    private int newY;

    private void doMove(int x, int y) {
        game.posX = x;
        game.posY = y;
        btnMove.setEnabled(true);
        btnFight.setEnabled(false);
        btnRun.setEnabled(false);
        setHeroValues();
        renderMap();

        if (Algos.isOnEdge(game.posX, game.posY, game.gameSize)) {
            if (JOptionPane.showConfirmDialog(this, "Mission Won! Continue?", "Mission Won", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                windowManager.showSelectHero();
                // currently all calls to this end here, so we should be safe just changing windows
            }
        }
    }

    private void btnFight_Click(ActionEvent evt) {
        Main.logger.logMessage("[PlayGame] Fight button clicked");
        if (villain != null)
            fight();
    }

    private void btnRun_Click(ActionEvent evt) {
        Main.logger.logMessage("[PlayGame] Run button clicked");
        if (villain == null)
            return;

        if (Algos.getMustFight()) {
            Main.logger.logMessage("Run failed. Continuing with fight...");
            setMessage("%nYou were caught!");
            fight();
        } else {
            Main.logger.logMessage("Run back successful.");
            setMessage("%nRan back successfully.");
            btnMove.setEnabled(true);
            btnFight.setEnabled(false);
            btnRun.setEnabled(false);
        }
    }

    private void fight() {
        btnFight.setEnabled(false);
        btnRun.setEnabled(false);
        Main.logger.logMessage("Fighting enemy %s...", villain.getClass().getSimpleName());
        Artifact droppedArtifact = windowManager.SimulateFight(hero, villain);
        setHeroValues();

        if (droppedArtifact != null) {
            Main.logger.logMessage("Enemy defeated, artifact dropped: %s Effect: %s", droppedArtifact.getClass().getSimpleName(), droppedArtifact.getEffect());

            setMessage("%nDefeated enemy dropped an artifact. Type: %s Effect: %s", droppedArtifact.getClass().getSimpleName(), droppedArtifact.getEffect());
            if (JOptionPane.showConfirmDialog(this,
                    String.format("Type: %s%nEffect: %s%n%nEquip?", droppedArtifact.getClass().getSimpleName(), droppedArtifact.getEffect()),
                    "Enemy dropped Artifact", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Main.logger.logMessage("Equipping artifact");
                droppedArtifact.setToHero(hero);
            }
        } else if (villain.getHp() <= 0) {
            Main.logger.logMessage("Enemy defeated, no artifact dropped.");
            setMessage("%nDefeated enemy!");
        }

        if (villain.getHp() <= 0) {
            Main.logger.logMessage("Enemy dropped %s XP", villain.getXpDrop());
            Misc.addXP(hero, villain.getXpDrop());

            Main.logger.logMessage("Moved to X:%s Y:%s", newX, newY);
            doMove(newX, newY);
        } else if (hero.getHp() <= 0) {
            setMessage("%nYou Died!");
            setHeroValues();
            HeroStorage.RemoveHero(hero);
            hero = null;
        }
        villain = null;
    }

    private void btnBack_Click(ActionEvent evt) {
        Main.logger.logMessage("[PlayGame] Back button clicked");
        windowManager.showSelectHero();
    }

    private void btnConsole_Click(ActionEvent evt) {
        Main.logger.logMessage("[PlayGame] Console button clicked");
        windowManager.setConsole();
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
