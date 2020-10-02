package wtc.mcarter.swingy.view;

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

public class SelectHero extends javax.swing.JPanel {
    private WindowManager windowManager;

    public SelectHero(WindowManager windowManager) {
        this.windowManager = windowManager;
        initComponents();
    }

    private void initComponents() {
        DefaultListModel<String> heroList = new DefaultListModel<String>();
        for (Hero hero : HeroStorage.getHeroList()) {
            heroList.addElement(hero.getName());
        }
        lstHeroList = new JList<String>(heroList);
        lstHeroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane lstHeroListScrollPane = new JScrollPane(lstHeroList);

        btnCreate = new JButton();
        btnStart = new JButton();
        lblHeroList = new JLabel();
        lblHeroStats = new JLabel();
        txtHeroStats = new JTextArea();

        btnCreate.setText("Create");
        btnStart.setText("Start");
        btnStart.setEnabled(false);
        lblHeroList.setText("Hero List");
        lblHeroStats.setText("Hero Stats");
        txtHeroStats.setText("");
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

    private JButton btnCreate;
    private JButton btnStart;
    private JLabel lblHeroList;
    private JList<String> lstHeroList;
    private JLabel lblHeroStats;
    private JTextArea txtHeroStats;
}
