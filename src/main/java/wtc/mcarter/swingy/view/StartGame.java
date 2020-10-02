package wtc.mcarter.swingy.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import wtc.mcarter.swingy.controller.GamePlayController;
import wtc.mcarter.swingy.model.characters.Hero;

public class StartGame extends JDialog implements WindowManager {
    private GamePlayController gamePlayController;

    public StartGame(GamePlayController gamePlayController) {
        super((java.awt.Window) null, "Select Hero");
        setModal(true);

        this.gamePlayController = gamePlayController;
        initComponents();
    }

    // /unchecked/

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        btnSelect = new JButton();
        btnSelect.setText("Select hero");
        btnSelect.addActionListener((evt) -> {
            btnSelect_Click(evt);
        });
        btnConsole = new JButton();
        btnConsole.setText("Console Mode");
        btnConsole.addActionListener((evt) -> {
            btnConsole_Click(evt);
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsole, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE));
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(142, Short.MAX_VALUE)
                    .addComponent(btnSelect)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnConsole)
                    .addGap(135, 135, 135)));

        pack();
    }

    private void btnSelect_Click(ActionEvent evt) {
        showSelectHero();
    }

    private void btnConsole_Click(ActionEvent evt) {
        setConsole();
    }

    public void showSelectHero() {
        SelectHero selectHeroPane = new SelectHero(this);
        setContentPane(selectHeroPane);
        setTitle("Select Hero");
        pack();
    }

    @Override
    public void showNewHero() {
        NewHero newHeroPanel = new NewHero(this);
        setContentPane(newHeroPanel);
        setTitle("Create Hero");
        pack();
    }

    @Override
    public void showGame(Hero hero) {
        PlayGame playGamePanel = new PlayGame(this, hero);
        setContentPane(playGamePanel);
        setTitle("Swingy Game");
        pack();
    }

    @Override
    public void setConsole() {
        gamePlayController.SetConsole();
        this.dispose();
    }

    private JButton btnSelect;
    private JButton btnConsole;
}
