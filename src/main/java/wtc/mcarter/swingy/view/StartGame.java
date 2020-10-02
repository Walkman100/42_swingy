package wtc.mcarter.swingy.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

import wtc.mcarter.swingy.model.characters.Hero;

public class StartGame extends JDialog implements WindowManager {
    public StartGame() {
        super((java.awt.Window) null, "Select Hero");
        setModal(true);
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(122, Short.MAX_VALUE)
                                .addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(142, Short.MAX_VALUE)
                                .addComponent(btnSelect)
                                .addGap(135, 135, 135)));

        pack();
    }

    private void btnSelect_Click(ActionEvent evt) {
        showSelectHero();
    }

    public void showSelectHero() {
        SelectHero selectHeroPane = new SelectHero(this);
        setContentPane(selectHeroPane);
        pack();
    }

    @Override
    public void showNewHero() {
        NewHero newHeroPanel = new NewHero(this);
        setContentPane(newHeroPanel);
        pack();
    }

    @Override
    public void showGame(Hero hero) {
        PlayGame playGamePanel = new PlayGame(this, hero);
        setContentPane(playGamePanel);
        pack();
    }

    private JButton btnSelect;
}
