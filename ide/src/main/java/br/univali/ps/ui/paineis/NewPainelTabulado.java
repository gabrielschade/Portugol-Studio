/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.ps.ui.paineis;

import br.univali.ps.nucleo.PortugolStudio;
import br.univali.ps.ui.Lancador;
import br.univali.ps.ui.abas.Aba;
import br.univali.ps.ui.swing.ColorController;
import br.univali.ps.ui.swing.Themeable;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Adson Esteves
 */
public class NewPainelTabulado extends javax.swing.JPanel implements Themeable{

    /**
     * Creates new form NewPainelTabulado
     */
    
    int tamanho_max;
    
    public NewPainelTabulado() {
        initComponents();
        configurarCores();
    }
    
    public Aba adicionaAba(Aba aba)
    {
        abaContainer.add(aba, aba.getName());
        adicionaAoCabecalho(aba);
        mudarParaAba(aba);
        aba.setPainelTabulado(this);
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
        
        cabecalhosAba.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                // Get x,y and store them
                PortugolStudio.getInstancia().getTelaPrincipal().pX = me.getX();
                PortugolStudio.getInstancia().getTelaPrincipal().pY = me.getY();

            }

             public void mouseDragged(MouseEvent me) {

                 SwingUtilities.invokeLater(() -> {
                     Lancador.getJFrame().setLocation(Lancador.getJFrame().getLocation().x + me.getX() - PortugolStudio.getInstancia().getTelaPrincipal().pX,Lancador.getJFrame().getLocation().y + me.getY() - PortugolStudio.getInstancia().getTelaPrincipal().pY);
                 });
                
            }
        });

        cabecalhosAba.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                SwingUtilities.invokeLater(() -> {
                    Lancador.getJFrame().setLocation(Lancador.getJFrame().getLocation().x + me.getX() - PortugolStudio.getInstancia().getTelaPrincipal().pX,Lancador.getJFrame().getLocation().y + me.getY() - PortugolStudio.getInstancia().getTelaPrincipal().pY);
                });
                
            }
        });
        return aba;
    }
    
    public Aba adicionaAoCabecalho(Aba aba)
    {
        int newSize = 200;
//        if(cabecalhosAba.getComponentCount()>2){
//            int canvas = cabecalhosAba.getWidth()-300;
//            if(canvas > 0){
//                int used = 0;
//                for (Component component : cabecalhosAba.getComponents()) {
//                    used+=component.getWidth();
//                }
//                System.out.println("used: "+used+" canvas: "+ canvas);
//                if(used>=canvas){
//                    int actualSize = cabecalhosAba.getComponent(1).getWidth();
//                    int cont = cabecalhosAba.getComponentCount()+2;
//                    newSize = actualSize - (actualSize/cont);
//                    for (Component component : cabecalhosAba.getComponents()) {
//                        if(!(component instanceof BotoesControleAba) && !(component instanceof CabecalhoAdicionarAba)){
//                            CabecalhoAba cabecalho = (CabecalhoAba) component;
//                            cabecalho.setMaxWidth(newSize);
//                            cabecalho.setPreferredSize(new Dimension(newSize, component.getHeight()));
//                        }
//                    }
//                    
//                }else{
//                    for (Component component : cabecalhosAba.getComponents()) {
//                        if(!(component instanceof BotoesControleAba) && !(component instanceof CabecalhoAdicionarAba)){
//                            CabecalhoAba cabecalho = (CabecalhoAba) component;
//                            cabecalho.setMaxWidth(999);
//                            cabecalho.setPreferredSize(new Dimension(cabecalho.getTamanhoCabecalho(), component.getHeight()));
//                        }
//                    }
//                }
//            }
//            //aba.getCabecalho().setPreferredSize(new Dimension(newSize, 26));
//        }
//        if(newSize<70){
//            JOptionPane.showMessageDialog(aba, "Não faz isso fera");
//        }
        cabecalhosAba.add(aba.getCabecalho());
        
        
        aba.getCabecalho().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                aba.selecionar();
            }
        });
        return aba;
    }
    
    public Aba mudarParaAba(Aba aba)
    {        
        CardLayout cl = (CardLayout) abaContainer.getLayout();
        cl.show(abaContainer, aba.getName());
        trocouAba(aba);
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
        return aba;
    } 
    
    public boolean contemAba(Aba aba)
    {
        Component[] components = abaContainer.getComponents();
        for (int i = 0; i < components.length; i++) {
            if(components[i] instanceof Aba)
            {                
                if((Aba)components[i] == aba)
                {                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public Aba getAbaSelecionada() {
        for (Component component : getAbaContainer().getComponents()) {
            if(component instanceof Aba)
            {
                if(component.isVisible())
                {
                    return (Aba)component;
                }                
            }            
        }
        return null;
    }
    
    public Integer getAbaSelecionadaNumber() {
        
        Component[] components = abaContainer.getComponents();
        for (int i = 0; i < components.length; i++) {
            if(components[i] instanceof Aba)
            {                
                if(((Aba)components[i]).isVisible())
                {                    
                    return i;
                }
            }
        }
        return null;
    }
    
    public void setAbaAtual(int abaNumber)
    {             
        Aba aba = (Aba)abaContainer.getComponents()[abaNumber];
        mudarParaAba(aba);
    }
    
    public void setAbaAtual(Aba aba)
    {
        mudarParaAba(aba);
    }

    public JPanel getAbaContainer() {
        return abaContainer;
    }

    public JPanel getCabecalhosAba() {
        return cabecalhosAba;
    }
    
    public void trocouAba(Aba aba){
        
    }

    @Override
    public void remove(Component comp) {
        if(comp instanceof Aba)
        {
            this.getAbaContainer().remove((Aba)comp);
            this.getCabecalhosAba().remove(((Aba) comp).getCabecalho());            
        }
        else
        {
            super.remove(comp);
        }
    }

    public JPanel getEspacador() {
        return espacador;
    }
    
    @Override
    public void configurarCores() {
        abaContainer.setBackground(ColorController.COR_CONSOLE);
        abaContainer.setForeground(ColorController.COR_LETRA);
        cabecalhosAba.setBackground(ColorController.COR_PRINCIPAL);
        cabecalhosAba.setForeground(ColorController.COR_LETRA);
        espacador.setBackground(ColorController.COR_PRINCIPAL);
        espacador.setForeground(ColorController.COR_LETRA);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        espacador = new javax.swing.JPanel();
        cabecalhosAba = new javax.swing.JPanel();
        abaContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        espacador.setPreferredSize(new java.awt.Dimension(0, 30));

        javax.swing.GroupLayout espacadorLayout = new javax.swing.GroupLayout(espacador);
        espacador.setLayout(espacadorLayout);
        espacadorLayout.setHorizontalGroup(
            espacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        espacadorLayout.setVerticalGroup(
            espacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(espacador, java.awt.BorderLayout.EAST);

        cabecalhosAba.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        cabecalhosAba.setMaximumSize(new java.awt.Dimension(32767, 40));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        cabecalhosAba.setLayout(flowLayout1);
        jPanel1.add(cabecalhosAba, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        abaContainer.setLayout(new java.awt.CardLayout());
        add(abaContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaContainer;
    private javax.swing.JPanel cabecalhosAba;
    private javax.swing.JPanel espacador;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
}
