package crudBaseDatos;
//Se importa los componentes
import javax.swing.UIManager; //look&feel
//-------------------Librerias de mysql para conexion---------------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//-----------------------------------------------------------------------------
import javax.swing.JOptionPane;// ventana emergente

public class CRUD extends javax.swing.JFrame {
    
    //----------------------Credenciales para la conexion-----------------------
    public static final String URL = "jdbc:mysql://localhost:3306/proyecto";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "toor";
    //--------------------------------------------------------------------------
    
    // -----------Variables para hacer las consultas y resultados---------------
    PreparedStatement ps;
    ResultSet rs;
    //--------------------------------------------------------------------------
    
    //---------------------Conexion con la base de datos------------------------
    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Problema con la conexion!", "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println(e);
        }
        return con;
    }
    //--------------------------------------------------------------------------
    
    //--------------Metodo para limpiar las cajas de texto----------------------
    private void limpiarCaja(){
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtUsuario.setText(null);
        txtNuevaContraseña.setText(null);
    }
    //--------------------------------------------------------------------------
    
    //------------------------Metodo para guardar registros--------------------
    private void guardar(){
        Connection con = null;

        try {
            con = getConnection();
            //-------Consulta y obtencion de los datos para enviarlos a BD------
            ps = con.prepareStatement("INSERT INTO usuarios (idusuario, nombre, apellido, sexo, nombreUsuario, contraseña) VALUES(?,?,?,?,?,?)");

            ps.setString(1, txtid.getText());
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtApellido.getText());
            ps.setString(4, cbxSex.getSelectedItem().toString());
            ps.setString(5, txtUsuario.getText());
            ps.setString(6, txtNuevaContraseña.getText());
            //------------------------------------------------------------------
            
            //-----------Actualizacion de registros y confirmacion--------------
            int res=ps.executeUpdate();
            if(res>0){
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiarCaja();
                
            }else{
                JOptionPane.showMessageDialog(null, "Error!");
                limpiarCaja();
            }
            
            con.close();
            //-----------------------------------------------------------------
            
        //------------------Cierre de la exeption------------------------------
        } catch (Exception e) {
            System.out.println(e);
        }
        //----------------------------------------------------------------------
    }
    //-------------------------------------------------------------------------
    
    //----------------------Metodo para modificar registro----------------------
    private void modificar(){
        Connection con = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE usuarios SET nombre=?, apellido=?,sexo=?, nombreUsuario=?, contraseña=? WHERE idusuario=?");
            
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtApellido.getText());
            ps.setString(3, cbxSex.getSelectedItem().toString());
            ps.setString(4, txtUsuario.getText());
            ps.setString(5, txtNuevaContraseña.getText());
            ps.setString(6, txtid.getText());
            
            int res=ps.executeUpdate();
            if(res>0){
                JOptionPane.showMessageDialog(null, "Registro modificado");
                limpiarCaja();
                
            }else{
                JOptionPane.showMessageDialog(null, "¡Error al modificar!", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCaja();
            }
            
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //-------------------------------------------------------------------------
    
    //---------------------Metodo para buscar registro--------------------------
    private void buscar(){
        Connection con=null;
        try{
            con = getConnection();
            
            ps = con.prepareStatement("SELECT * FROM usuarios WHERE idusuario=?");
            ps.setString(1,txtid.getText());
            rs=ps.executeQuery();
            
            if(rs.next()){
                txtid.setText(rs.getString("idusuario"));
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellido"));
                cbxSex.setSelectedItem(rs.getString("sexo"));
                txtUsuario.setText(rs.getString("nombreUsuario"));
                txtNuevaContraseña.setText(rs.getString("contraseña"));
            }else{
                JOptionPane.showMessageDialog(null, "Clave inexistente");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //--------------------------------------------------------------------------
    
    //-----------------Metodo para eliminar un registro-------------------------
    private void eliminar(){
        Connection con = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM usuarios WHERE idusuario=?");
            ps.setInt(1, Integer.parseInt(txtid.getText()));
            
            int res=ps.executeUpdate();
            if(res>0){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiarCaja();
                
            }else{
                JOptionPane.showMessageDialog(null, "¡Error al intentar eliminar!", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCaja();
            }
            
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //--------------------------------------------------------------------------
    public CRUD() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        LabelTitulo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelApellido = new javax.swing.JLabel();
        labelSexo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelContraseña = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        cbxSex = new javax.swing.JComboBox<>();
        txtUsuario = new javax.swing.JTextField();
        txtNuevaContraseña = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        LabelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo.setText("Control de usuarios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(LabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(149, 149, 149))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        labelNombre.setText("Nombre(s):");

        labelApellido.setText("Apellidos:");

        labelSexo.setText("Sexo:");

        labelUsuario.setText("Nombre de usuario:");

        labelContraseña.setText("Nueva contraseña:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        cbxSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Masculino", "Femenino", " " }));
        cbxSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSexActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        labelID.setText("ID:");

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBackgroundLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre)
                            .addComponent(labelApellido)
                            .addComponent(labelSexo)
                            .addComponent(labelUsuario)
                            .addComponent(labelContraseña)
                            .addComponent(btnGuardar))
                        .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBackgroundLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(txtApellido)
                                    .addComponent(txtUsuario)
                                    .addComponent(txtNuevaContraseña)))
                            .addGroup(panelBackgroundLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)))
                        .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBackgroundLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(labelID)
                                .addGap(18, 18, 18)
                                .addComponent(txtid))
                            .addGroup(panelBackgroundLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnBuscar)
                                    .addComponent(btnEliminar))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelID)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSexo)
                    .addComponent(cbxSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContraseña)
                    .addComponent(txtNuevaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnEliminar))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void cbxSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSexActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCaja();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--------------Se establece el look&feel de windows--------------------
        String os = System.getProperty("os.name").toLowerCase();
        String name = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        if (os.indexOf("win") >= 0) {
     try {
          UIManager.setLookAndFeel(name);
     }
     catch (Exception e) {}
}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
                new CRUD().setVisible(true);
            }
        });
    }
    //--------------------------------------------------------------------------

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxSex;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNuevaContraseña;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
