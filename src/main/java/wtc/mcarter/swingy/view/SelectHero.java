package wtc.mcarter.swingy.view;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.storage.HeroStorage;

public class SelectHero extends javax.swing.JPanel {
    private WindowManager windowManager;

    public SelectHero(WindowManager windowManager) {
        this.windowManager = windowManager;
        initComponents();
    }

    private void setHeroStats(Object name, Object type, Object level, Object xp, Object atk, Object def, Object hp, Object weapon) {
        txtHeroStats.setText(String.format("Name: %s%nClass: %s%nLevel: %s%nXP: %s%nATK: %s%nDEF: %s%nHP: %s%nWeapon: %s",
                        name, type, level, xp, atk, def, hp, weapon));
    }

    private Hero getSelectedHero() {
        String selectedHeroName = lstHeroList.getSelectedValue();
        if (selectedHeroName == null)
            return null;
        for (Hero hero : HeroStorage.getHeroList()) {
            if (hero.getName().equals(selectedHeroName)) {
                return hero;
            }
        }
        return null;
    }

    private void initComponents() {
        DefaultListModel<String> heroList = new DefaultListModel<String>();
        for (Hero hero : HeroStorage.getHeroList()) {
            heroList.addElement(hero.getName());
        }
        lstHeroList = new JList<String>(heroList);
        lstHeroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstHeroList.addListSelectionListener((evt) -> {
            lstHeroList_ItemSelected(evt);
        });
        JScrollPane lstHeroListScrollPane = new JScrollPane(lstHeroList);

        btnCreate = new JButton();
        btnStart = new JButton();
        lblHeroList = new JLabel();
        lblHeroStats = new JLabel();
        txtHeroStats = new JTextArea();

        btnCreate.setText("Create");
        btnCreate.addActionListener((evt) -> {
            btnCreate_Click(evt);
        });
        btnStart.setText("Start");
        btnStart.addActionListener((evt) -> {
            btnStart_Click(evt);
        });
        btnStart.setEnabled(false);
        lblHeroList.setText("Hero List");
        lblHeroStats.setText("Hero Stats");
        setHeroStats("", "", "", "", "", "", "", "");
        txtHeroStats.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblHeroList)
                        .addComponent(lstHeroListScrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblHeroStats)
                        .addComponent(txtHeroStats, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(30, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(btnStart)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnCreate)
                    .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHeroList)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(lstHeroListScrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHeroStats)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(txtHeroStats, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnCreate))
                .addContainerGap(30, Short.MAX_VALUE)
        );
    }

    private void btnCreate_Click(ActionEvent evt) {
        windowManager.showNewHero();
    }

    private void btnStart_Click(ActionEvent evt) {
        Hero selectedHero = getSelectedHero();

        if (selectedHero != null)
            windowManager.showSelectMission(selectedHero);
    }

    private void lstHeroList_ItemSelected(ListSelectionEvent evt) {
        btnStart.setEnabled(lstHeroList.getSelectedIndex() != -1);

        Hero selectedHero = getSelectedHero();

        if (selectedHero != null) {
            setHeroStats(selectedHero.getName(), selectedHero.getClass().getSimpleName(), selectedHero.getLevel(),
                    selectedHero.getExperience(), selectedHero.getDamage(), selectedHero.getDefense(),
                    selectedHero.getHp(), selectedHero.getWeapon().getType().name());
            return;
        } else {
            setHeroStats("", "", "", "", "", "", "", "");
        }
    }

    private JButton btnCreate;
    private JButton btnStart;
    private JLabel lblHeroList;
    private JList<String> lstHeroList;
    private JLabel lblHeroStats;
    private JTextArea txtHeroStats;
}
