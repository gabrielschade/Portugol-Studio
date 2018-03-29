/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.ps.ui.paineis;

import br.univali.ps.ui.abas.Aba;
import br.univali.ps.ui.abas.BotoesControleAba;
import br.univali.ps.ui.abas.CabecalhoAdicionarAba;
import br.univali.ps.ui.swing.ColorController;
import br.univali.ps.ui.swing.Themeable;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
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
            invalidate();
            repaint();
        });
        return aba;
    }
    
    public Aba adicionaAoCabecalho(Aba aba)
    {
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
//        Aba old = null;
//        for (Component comp : abaContainer.getComponents()) {
//            if (comp.isVisible() == true) {
//                old = (Aba) comp;
//            }
//        }
        cl.show(abaContainer, aba.getName());
//        if(aba.getCabecalho() != null){
//            if(aba.getCabecalho() instanceof BotoesControleAba){
//                aba.getCabecalho().setBackground(ColorController.COR_PRINCIPAL);
//            }else if(aba.isRemovivel()){
//                aba.getCabecalho().setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ColorController.AMARELO));
//                aba.getCabecalho().setBackground(ColorController.COR_PRINCIPAL);
//            }else{
//                aba.getCabecalho().setBackground(ColorController.COR_CONSOLE);
//            }
//            if(old!=null){
//                if(old.getCabecalho()!=null){
//                    if(old.getCabecalho() instanceof BotoesControleAba){
//                        old.getCabecalho().setBackground(ColorController.COR_CONSOLE);
//                    }else if(old.isRemovivel()){
//                        old.getCabecalho().setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, ColorController.COR_PRINCIPAL));
//                        old.getCabecalho().setBackground(ColorController.FUNDO_CLARO);
//                    }else{
//                        old.getCabecalho().setBackground(ColorController.COR_PRINCIPAL);
//                    }
//                }
//            }
//        }
        
        
        SwingUtilities.invokeLater(() -> {
            invalidate();
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
    
    @Override
    public void configurarCores() {
        abaContainer.setBackground(ColorController.COR_CONSOLE);
        abaContainer.setForeground(ColorController.COR_LETRA);
        cabecalhosAba.setBackground(ColorController.COR_PRINCIPAL);
        cabecalhosAba.setForeground(ColorController.COR_LETRA);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cabecalhosAba = new javax.swing.JPanel();
        abaContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        cabecalhosAba.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        cabecalhosAba.setMaximumSize(new java.awt.Dimension(32767, 40));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        cabecalhosAba.setLayout(flowLayout1);
        add(cabecalhosAba, java.awt.BorderLayout.NORTH);

        abaContainer.setLayout(new java.awt.CardLayout());
        add(abaContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaContainer;
    private javax.swing.JPanel cabecalhosAba;
    // End of variables declaration//GEN-END:variables

    
}
