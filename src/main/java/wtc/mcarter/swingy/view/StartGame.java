package wtc.mcarter.swingy.view;

import wtc.mcarter.swingy.model.characters.Hero;

public class StartGame extends javax.swing.JDialog implements WindowManager {
    public StartGame() {
        super((java.awt.Window) null, "Select Hero");
        setModal(true);
        initComponents();
    }

    // /unchecked/

    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Select hero");
        jButton1.addActionListener((evt) -> {
            jButton1ActionPerformed(evt);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(122, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(142, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(135, 135, 135)));

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
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
    public void showSelectMission(Hero hero) {
        PlayMission playMissionPanel = new PlayMission(this, hero);
        setContentPane(playMissionPanel);
        pack();
    }

    private javax.swing.JButton jButton1;
}
