/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pfiches;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JList;
import javax.swing.JOptionPane;
import ptraitements.Client;
import ptraitements.Cours;
import ptraitements.Salle;

/**
 *
 * @author gabri
 */
public class FActivite extends javax.swing.JDialog {
    
    private Salle maSalle;
    private Client Client;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FActivite.class.getName());

    /**
     * Creates new form FActivite
     */
    public FActivite(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        LocalDate adj = LocalDate.now();
        initialiserAffichage(adj);
     
        
    }

    public void initialiserAffichage( LocalDate date ){
        
        LocalDate adj = LocalDate.now();

        
        // Trouver le lundi de la même semaine
        LocalDate lundi = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        
        //Trouver les autres jours de la semaine
        LocalDate mardi = lundi.plusDays(1);
        LocalDate mercredi = lundi.plusDays(2);
        LocalDate jeudi = lundi.plusDays(3);
        LocalDate vendredi = lundi.plusDays(4);
        LocalDate samedi = lundi.plusDays(5);
        LocalDate dimanche = lundi.plusDays(6);
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Conversion en String
        String textel = lundi.format(formatter);
        String textema = mardi.format(formatter);
        String texteme = mercredi.format(formatter);
        String textej = jeudi.format(formatter);
        String textev = vendredi.format(formatter);
        String textes = samedi.format(formatter);
        String texted = dimanche.format(formatter);
        
        
        jLabellundi.setText(textel);
        jLabelmardi.setText(textema);
        jLabelmercredi.setText(texteme);
        jLabeljeudi.setText(textej);
        jLabelvendredi.setText(textev);
        jLabelsamedi.setText(textes);
        jLabeldimanche.setText(texted);
        
        if(date.isAfter(adj)){
            for(Cours c : maSalle.getCoursFuturs()){
            LocalDate d = c.getDatecour();    
            
                switch(d.getDayOfWeek()){
                    case MONDAY -> {
                        AjouterCoursItem(c,jListLundi);
                    }
                    case TUESDAY  -> {
                        AjouterCoursItem(c,jListMardi);
                    }
                    case WEDNESDAY -> {
                        AjouterCoursItem(c,jListMercredi);
                    }
                    case THURSDAY -> {
                        AjouterCoursItem(c,jListJeudi);
                    }
                    case FRIDAY -> {
                        AjouterCoursItem(c,jListVendredi);
                    }
                    case SATURDAY -> {    
                        AjouterCoursItem(c,jListSamedi);
                    }
                    case SUNDAY -> {
                        AjouterCoursItem(c,jListDimanche);
                }
            }
        } 
    }else{
            for(Cours c : maSalle.getListeCoursPassees()){
            LocalDate d = c.getDatecour();    
            
                switch(d.getDayOfWeek()){
                    case MONDAY -> {
                        AjouterCoursItem(c,jListLundi);
                    }
                    case TUESDAY  -> {
                        AjouterCoursItem(c,jListMardi);
                    }
                    case WEDNESDAY -> {
                        AjouterCoursItem(c,jListMercredi);
                    }
                    case THURSDAY -> {
                        AjouterCoursItem(c,jListJeudi);
                    }
                    case FRIDAY -> {
                        AjouterCoursItem(c,jListVendredi);
                    }
                    case SATURDAY -> {    
                        AjouterCoursItem(c,jListSamedi);
                    }
                    case SUNDAY -> {
                        AjouterCoursItem(c,jListDimanche);
                    }
            
                }       
            }
        }
    }
    
    /**
     *
     * @param c
     * @param j
     */
    public void AjouterCoursItem(Cours c, JList j){
        String nom = c.getActivitecour() + " " + c.getHeurecour();
        j.add(nom, j);
    }    
        
   
    public void envoyerSalleClientVersActivite(Salle maSalle, Client client){
        this.maSalle = maSalle;
        this.Client = client;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollbar1 = new java.awt.Scrollbar();
        JB_Sem_prec = new javax.swing.JButton();
        JB_Sem_suiv = new javax.swing.JButton();
        JtextJour = new javax.swing.JTextField();
        JtextMois = new javax.swing.JTextField();
        JtextAnnee = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabellundi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListLundi = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelmardi = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListMardi = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelmercredi = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListMercredi = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabeljeudi = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListJeudi = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabelvendredi = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListVendredi = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelsamedi = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListSamedi = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabeldimanche = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListDimanche = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        JbutonRechercher = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JB_Sem_prec.setText("<");
        JB_Sem_prec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Sem_precActionPerformed(evt);
            }
        });

        JB_Sem_suiv.setText(">");
        JB_Sem_suiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Sem_suivActionPerformed(evt);
            }
        });

        JtextJour.setText("dd");
        JtextJour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextJourActionPerformed(evt);
            }
        });

        JtextMois.setText("mm");
        JtextMois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextMoisActionPerformed(evt);
            }
        });

        JtextAnnee.setText("aaaa");
        JtextAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextAnneeActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("LUNDI");

        jLabellundi.setText("dd/mm");

        jScrollPane1.setViewportView(jListLundi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabellundi)
                        .addGap(17, 17, 17))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabellundi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("MARDI");

        jLabelmardi.setText("dd/mm");

        jListMardi.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListMardi);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelmardi)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelmardi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("MERCREDI");

        jLabelmercredi.setText("dd/mm");

        jListMercredi.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jListMercredi);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabelmercredi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelmercredi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("JEUDI");

        jLabeljeudi.setText("dd/mm");

        jListJeudi.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListJeudi);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabeljeudi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabeljeudi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("VENDREDI");

        jLabelvendredi.setText("dd/mm");

        jListVendredi.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListVendredi);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelvendredi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jScrollPane5)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelvendredi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("SAMEDI");

        jLabelsamedi.setText("dd/mm");

        jListSamedi.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jListSamedi);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabelsamedi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane6)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelsamedi)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("DIMANCHE");

        jLabeldimanche.setText("dd/mm");

        jListDimanche.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jListDimanche);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabeldimanche))
                    .addComponent(jLabel7))
                .addContainerGap(13, Short.MAX_VALUE))
            .addComponent(jScrollPane7)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabeldimanche)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton1.setText("S'inscrire");

        JbutonRechercher.setText("Rechercher");
        JbutonRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbutonRechercherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JB_Sem_prec)
                        .addGap(18, 18, 18)
                        .addComponent(JB_Sem_suiv)
                        .addGap(58, 58, 58)
                        .addComponent(JtextJour, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JtextMois, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JtextAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JbutonRechercher)
                        .addGap(58, 58, 58))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Sem_prec)
                    .addComponent(JB_Sem_suiv)
                    .addComponent(JtextJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JtextMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JtextAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbutonRechercher))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_Sem_precActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Sem_precActionPerformed
        
        String datestring = jLabellundi.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(datestring, formatter);
        date = date.plusWeeks(-1);
        initialiserAffichage(date);



// TODO add your handling code here:
    }//GEN-LAST:event_JB_Sem_precActionPerformed

    private void JB_Sem_suivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Sem_suivActionPerformed

        String datestring = jLabellundi.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(datestring, formatter);
        date = date.plusWeeks(1);
        initialiserAffichage(date);

// TODO add your handling code here:
    }//GEN-LAST:event_JB_Sem_suivActionPerformed

    private void JtextJourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextJourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextJourActionPerformed

    private void JtextMoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextMoisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextMoisActionPerformed

    private void JtextAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextAnneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextAnneeActionPerformed

    private void JbutonRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbutonRechercherActionPerformed
        // TODO add your handling code here:
        try {
            
        int j, m, a;
        j = Integer.parseInt(JtextJour.getText().trim());
        m = Integer.parseInt(JtextMois.getText().trim());
        a = Integer.parseInt(JtextAnnee.getText().trim());
        
        System.out.println("Jour: " + j + " Mois: " + m + " Annee: " + a);
        // Vérification du mois
        if (m <= 0) {
        throw new MoisInvalideException("Le mois doit être > 0");
        }
        if (m < 1 || m > 12) {
            throw new MoisInvalideException("Le mois doit être compris entre 1 et 12");
        }
        
        // Vérification du jours
        if (j<1 || j >30 ) {
            throw new JourInvalideException("Le jour doit être compris entre 1 et 30");
        }

        
        // 3. Création de la date
        // Note: LocalDate.of lancera sa propre exception si tu mets 31 juin par exemple
        LocalDate date = LocalDate.of(a, m, j);
        
        
        initialiserAffichage(date);
        }
        catch (NumberFormatException ex) {
            String message = "Le format n'est pas bon !";
            JOptionPane.showMessageDialog(this, message); 
        }
        catch (MoisInvalideException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        catch (JourInvalideException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        catch (DateTimeException ex) {
        // Capture les erreurs comme le 31 février ou le 31 avril
        JOptionPane.showMessageDialog(this, "Cette date n'existe pas dans le calendrier.");
    }
                
                
    }//GEN-LAST:event_JbutonRechercherActionPerformed

    class MoisInvalideException extends Exception {
    public MoisInvalideException(String message) {
        super(message);
    }
    }
    
    class JourInvalideException extends Exception {
    public JourInvalideException(String message) {
        super(message);
    }}
    
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FActivite dialog = new FActivite(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Sem_prec;
    private javax.swing.JButton JB_Sem_suiv;
    private javax.swing.JButton JbutonRechercher;
    private javax.swing.JTextField JtextAnnee;
    private javax.swing.JTextField JtextJour;
    private javax.swing.JTextField JtextMois;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabeldimanche;
    private javax.swing.JLabel jLabeljeudi;
    private javax.swing.JLabel jLabellundi;
    private javax.swing.JLabel jLabelmardi;
    private javax.swing.JLabel jLabelmercredi;
    private javax.swing.JLabel jLabelsamedi;
    private javax.swing.JLabel jLabelvendredi;
    private javax.swing.JList<String> jListDimanche;
    private javax.swing.JList<String> jListJeudi;
    private javax.swing.JList<String> jListLundi;
    private javax.swing.JList<String> jListMardi;
    private javax.swing.JList<String> jListMercredi;
    private javax.swing.JList<String> jListSamedi;
    private javax.swing.JList<String> jListVendredi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private java.awt.Scrollbar scrollbar1;
    // End of variables declaration//GEN-END:variables
}