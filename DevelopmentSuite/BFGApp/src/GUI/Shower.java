/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Shower.java
 *
 * Created on Jun 14, 2009, 1:57:03 PM
 */

package GUI;

import BruteForceGrid.DataCollection.AvailableDecryptorList;
import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.Decryptor;
import BruteForceGrid.DataCollection.GridPeer;
import BruteForceGrid.DataCollection.GridPeerList;
import BruteForceGrid.DataCollection.LocalJob;
import BruteForceGrid.DataCollection.LocalSubJob;
import BruteForceGrid.DataCollection.RemoteIPList;
import BruteForceGrid.DataCollection.RemoteJobList;
import BruteForceGrid.DataCollection.RemoteSubJob;
import BruteForceGrid.DataCollection.SubJob;
import BruteForceGrid.WorkingComponent.ListUpdater;
import BruteForceGrid.WorkingComponent.LocalJobDistributor;
import BruteForceGrid.WorkingComponent.PluginInterface;
import BruteForceGrid.WorkingComponent.RemoteRequestor;
import BruteForceGrid.WorkingComponent.Shutdowner;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Faraj
 */
class Updatex extends TimerTask
{
    @Override
    public void run() 
    {
        Shower.UpdateGridListView();
        Shower.UpdateLocalJobView();
        Shower.UpdateRemoteJobList();
        Shower.updateAlgoList();
        Shower.UpdateGeneralInfo();
        Shower.UpdateButtonValueBox();
        Shower.updateRILlist();
    }   
}

class Initem extends TimerTask
{
    @Override
    public void run() {
        Shower.updateInit();
    }
}
public class Shower extends javax.swing.JFrame {

    /** Creates new form Shower */
    public static Shower shower;
    private static int consoleCount = 0;
    private static Color lblCol;
    public Shower() {
        addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
                PluginInterface.kill();
                Shutdowner.doShut();
            }

            public void windowClosed(WindowEvent e) {

            }

            public void windowIconified(WindowEvent e) {

            }

            public void windowDeiconified(WindowEvent e) {

            }

            public void windowActivated(WindowEvent e) {

            }

            public void windowDeactivated(WindowEvent e) {

            }
        });

        initComponents();
        Timer t = new Timer();
        Timer t1 = new Timer();
        t.schedule(new Updatex(), 5000,1000);
        t1.schedule(new Initem() , 1000);
        lstGridPeers.removeAll();
        lstLocalJobs.removeAll();
        lstRemoteJobs.removeAll();
        lblCol = this.lblAlgo.getForeground();

        setTitle("Brute Force Grid Peer");
        setResizable(false);        
        setLocation(WIDTH, WIDTH);
    }

    public static void writeConsole(String msg)
    {

        try
        {
            msg = "\n"+msg+(shower.txtConsole.getText());
            int msglen = msg.length();
   
            if(msglen > 500)
                msg = msg.substring(0, 500);

            shower.txtConsole.setText(msg);
        }
        catch(Exception e)
        {
            
        }
    }

    private static void setValueBox(boolean bval)
    {
            shower.txtAlgo.setEnabled(bval);
            shower.txbxHash.setEnabled(bval);
            shower.txbxSize.setEnabled(bval);
            shower.chkbxCapital.setEnabled(bval);
            shower.chkbxSimple.setEnabled(bval);
            shower.chkbxSpecial.setEnabled(bval);
            shower.chkbxNumber.setEnabled(bval);            
    }

    public static void updateRILlist()
    {
        shower.lstRIList.removeAll();
        String[] sdata = new String[RemoteIPList.iPDataSets.size()];

        for (int i = 0; i < sdata.length; i++) {
            sdata[i] = RemoteIPList.iPDataSets.get(i).text();
        }
        shower.lstRIList.setListData(sdata);
    }

    public static void updateInit()
    {
        try
        {
            Shower.shower.comMaxPeerPerJob.setSelectedItem(String.valueOf(CommonRegister.MAX_REMOTE_PEERS_PER_JOB));
         
            Shower.shower.comMaxRemoteServers.setSelectedItem(String.valueOf(CommonRegister.MAX_REMOTE_JOBS));

            Shower.shower.comMaxGridPeers.setSelectedItem(String.valueOf(CommonRegister.MAX_GRID_PEERS));

            Shower.shower.comjobsize.setSelectedItem(String.valueOf(CommonRegister.LOCAL_SUB_JOB_SIZE));

            Shower.shower.txtServerAdd.setText(String.valueOf(CommonRegister.WEB_SERVER_DOMAIN));

            Shower.shower.txtDecryptorPath.setText(String.valueOf(CommonRegister.DECRYPTOR_LOCAL_ROOT_PATH));

            Shower.shower.txtLogFile.setText(String.valueOf(CommonRegister.LOG_FILE));

            Shower.shower.txtRIF.setText(String.valueOf(CommonRegister.REMOTE_IP_LIST_FILE_PATH));

            Shower.shower.txtconffile.setText(String.valueOf(CommonRegister.CONFIG_FILE));
        }
        catch(Exception e)
        {
            
        }
    }

    public static void UpdateButtonValueBox()
    {
        if(LocalJob.isFinished())
        {
            
            shower.btnStop.setEnabled(false);
            shower.btnPause.setEnabled(false);
            shower.btnStart.setEnabled(true);
            setValueBox(true);
        }
        else if(LocalJob.isPaused())
        {
    
            shower.btnStop.setEnabled(true);
            shower.btnPause.setEnabled(true);
            shower.btnStart.setEnabled(false);
            setValueBox(false);
        }
        else if(LocalJob.isStarted())
        {
     
            shower.btnStop.setEnabled(true);
            shower.btnPause.setEnabled(true);
            shower.btnStart.setEnabled(false);
            setValueBox(false);
        }
        else
        {
     
            shower.btnStop.setEnabled(false);
            shower.btnPause.setEnabled(true);
            shower.btnStart.setEnabled(true);
            setValueBox(true);
        }

    }

    public static void UpdateRemoteJobList()
    {
        String[] listData = new String[RemoteJobList.getRemoteJobCount()];
        RemoteSubJob[] jobs = RemoteJobList.getRemoteSubJobs();
        for (int i = 0; i < listData.length; i++)
        {
            RemoteSubJob job = jobs[i];
            try
            {
                listData[i] = job.display();
            }
            catch(Exception e)
            {
                RemoteJobList.deleteRemoteJob(job);
            }
        }
        shower.lstRemoteJobs.setListData(listData);
    }

    public static void UpdateGridListView()
    {
        String[] listData = new String[GridPeerList.getGridPeerCount()];
        for (int i = 0; i < GridPeerList.getGridPeerCount(); i++)
        {
            GridPeer gp = GridPeerList.getGridPeerByIndex(i);
            listData[i] = gp.toString();
        }
        shower.lstGridPeers.setListData(listData);
    }

    public static void updateAlgoList()
    {
        Object[] objects = AvailableDecryptorList.decryptors.toArray();
        if(objects.length != shower.txtAlgo.getItemCount())
            for (int i = 0; i < objects.length; i++) {
                Decryptor d = (Decryptor)objects[i];
                shower.txtAlgo.insertItemAt(d.getDiscription(), i);
            }

    }

    public static void UpdateGeneralInfo()
    {
        shower.lblConnectedPeers.setText(
                String.valueOf(GridPeerList.getConnectedGridPeerCount()));
        shower.lblPeersConnectable.setText(
                String.valueOf(GridPeerList.getConnectablePeerCount()));
        String val;
        try
        {
            val = Inet4Address.getLocalHost().getHostAddress();
        }
        catch(Exception e)
        {
            val = "127.0.0.1";
        }

        shower.lblLocalIP.setText(val);

        int totj = LocalJob.getNoOfSubJobs();
        int newj = LocalJob.getNewJobCount();
        int finj = LocalJob.getFinishJobCount();
        int penj = totj - finj - newj;

        shower.lblLocalJobs.setText(""+totj);
        shower.lblLocalJobsNew.setText(""+newj);
        shower.lblLocalJobsCompleted.setText(""+finj);
        shower.lblLocalJobsPending.setText(""+penj);
        shower.lblRemoteJobs.setText(""+RemoteJobList.getRemoteJobCount());
       

        if(LocalJob.isSuccess())
            val = "SUCCESS (" + LocalJob.getPassword() + ")";
        else if(LocalJob.isPaused())
            val = "PAUSED";
        else if(LocalJob.isStarted())
            val = "RUNNING";
        else if(LocalJob.isFinished())
            val = "STOPPED";

        shower.lblStatues.setText(val);

        int fVal = LocalJob.getFinishJobCount();
        int tVal = LocalJob.getNoOfSubJobs() + 1;
        shower.prgBar.setValue(((fVal)*100/tVal));
    }

    public static void UpdateLocalJobView()
    {
        int len = LocalJob.getNoOfSubJobs();

        boolean chkup = false;
        if(len > 50)
        {
            len = 50;
            chkup = true;
        }

        int cnt = 0;

        String[] listData = new String[len];

        if(chkup)
        {
            for (int i = 0; i < listData.length; i++)
            {
                LocalSubJob job = null;
                try {
                    job = LocalJob.getLocalSubJob(i + cnt);
                } catch (Exception ex) {
                    break;
                }

                try
                {
                    if(job.getState() == SubJob.FINISHED)
                    {
                        throw new Exception();
                    }
                    listData[i] = job.display();
                }
                catch(Exception ex)
                {
                    cnt++;
                    i--;
                }
            }
        }
        else
        {
            for (int i = 0; i < listData.length; i++)
            {
                LocalSubJob job = null;
                try {
                        job = LocalJob.getLocalSubJob(i);

                } catch (Exception ex) {
                    break;
                }
                try {
                    listData[i] = job.display();
                } catch (Exception ex) {
                    job.setState(SubJob.NEW);
                    job.setGridPeerID(-1);
                }
            }
        }

        int index = shower.lstLocalJobs.getSelectedIndex();
        shower.lstLocalJobs.setListData(listData);
        if(index > 0)
        {
            shower.lstLocalJobs.setSelectedIndex(index);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblLocalIP = new javax.swing.JLabel();
        lblConnectedPeers = new javax.swing.JLabel();
        lblPeersConnectable = new javax.swing.JLabel();
        lblLocalJobs = new javax.swing.JLabel();
        lblRemoteJobs = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtPeerName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblLocalJobsCompleted = new javax.swing.JLabel();
        lblLocalJobsPending = new javax.swing.JLabel();
        lblLocalJobsNew = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblHash = new javax.swing.JLabel();
        txbxHash = new javax.swing.JTextField();
        txbxSize = new javax.swing.JSpinner();
        txtAlgo = new javax.swing.JComboBox();
        lblAlgo = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        chkbxNumber = new javax.swing.JCheckBox();
        chkbxSpecial = new javax.swing.JCheckBox();
        chkbxCapital = new javax.swing.JCheckBox();
        chkbxSimple = new javax.swing.JCheckBox();
        lblchk = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        prgBar = new javax.swing.JProgressBar();
        btnStop = new javax.swing.JButton();
        lblStatues = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstGridPeers = new javax.swing.JList();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstLocalJobs = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstRemoteJobs = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstRIList = new javax.swing.JList();
        jLabel11 = new javax.swing.JLabel();
        txtRILadd = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jPanel13 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        comMaxPeerPerJob = new javax.swing.JComboBox();
        comMaxRemoteServers = new javax.swing.JComboBox();
        comMaxGridPeers = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtconffile = new javax.swing.JTextField();
        txtLogFile = new javax.swing.JTextField();
        txtDecryptorPath = new javax.swing.JTextField();
        txtRIF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtSetFileTransferPort1 = new javax.swing.JLabel();
        txtSetConnectivityPort1 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        txtServerAdd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comjobsize = new javax.swing.JComboBox();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                exitApp(evt);
            }
        });

        jLabel14.setText("Connected Peers");

        jLabel21.setText("Peers Connectable");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel22.setText("Local Sub Jobs");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel23.setText("Remote Jobs");

        jLabel24.setText("Local Peer Address");

        lblLocalIP.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblLocalIP.setText("127.0.0.1");

        lblConnectedPeers.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblConnectedPeers.setText("0");

        lblPeersConnectable.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblPeersConnectable.setText("0");

        lblLocalJobs.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblLocalJobs.setText("0");

        lblRemoteJobs.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRemoteJobs.setText("0");

        jLabel34.setText("Peer name");

        txtPeerName.setEditable(false);
        txtPeerName.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtPeerName.setForeground(new java.awt.Color(51, 51, 255));
        txtPeerName.setText("Mini HOST");
        txtPeerName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPeerName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPeerNameMouseClicked(evt);
            }
        });
        txtPeerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeerNameActionPerformed(evt);
            }
        });
        txtPeerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPeerNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeerNameKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Brute Force Grid");

        jLabel35.setText("Completed");

        jLabel36.setText("Pending");

        jLabel37.setText("New");

        lblLocalJobsCompleted.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblLocalJobsCompleted.setText("0");

        lblLocalJobsPending.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblLocalJobsPending.setText("0");

        lblLocalJobsNew.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblLocalJobsNew.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel34)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel14)
                            .addComponent(jLabel23)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel35))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLocalJobs, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(lblLocalJobsCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(lblPeersConnectable, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(lblConnectedPeers, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(lblLocalJobsPending, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(lblLocalJobsNew, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(lblRemoteJobs)
                            .addComponent(lblLocalIP)
                            .addComponent(txtPeerName, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtPeerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblLocalIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblConnectedPeers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblPeersConnectable))
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblLocalJobs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblLocalJobsCompleted))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lblLocalJobsPending))
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblLocalJobsNew))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblRemoteJobs))
                .addGap(362, 362, 362)
                .addComponent(jLabel2)
                .addGap(20, 20, 20))
        );

        jTabbedPane5.addTab("General", jPanel9);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHash.setText("Hash");

        txbxHash.setBackground(new java.awt.Color(102, 102, 102));
        txbxHash.setFont(new java.awt.Font("Tahoma", 1, 12));
        txbxHash.setForeground(new java.awt.Color(204, 204, 204));
        txbxHash.setToolTipText("hash to decrypt");
        txbxHash.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        txbxHash.setMargin(new java.awt.Insets(5, 10, 5, 5));

        txbxSize.setFont(new java.awt.Font("Tahoma", 0, 14));
        txbxSize.setForeground(new java.awt.Color(102, 102, 102));
        txbxSize.setToolTipText("predicted size of the password");
        txbxSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        txtAlgo.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtAlgo.setForeground(new java.awt.Color(102, 102, 102));
        txtAlgo.setToolTipText("algotrithm used to hash");
        txtAlgo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        lblAlgo.setText("Algorithm");

        lblSize.setText("Size");

        chkbxNumber.setText("0-9 Numeric Charactors");

        chkbxSpecial.setText("* & % Special Charactors");

        chkbxCapital.setText("A-Z Capital Letters");

        chkbxSimple.setText("a-z Simple Letters");
        chkbxSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbxSimpleActionPerformed(evt);
            }
        });

        lblchk.setText("Check");

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 12));
        btnStart.setText("Start");
        btnStart.setToolTipText("start new job");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnPause.setFont(new java.awt.Font("Tahoma", 0, 12));
        btnPause.setText("Pause");
        btnPause.setToolTipText("pause/resume job");
        btnPause.setEnabled(false);
        btnPause.setMaximumSize(new java.awt.Dimension(59, 23));
        btnPause.setMinimumSize(new java.awt.Dimension(59, 23));
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnStop.setFont(new java.awt.Font("Tahoma", 0, 12));
        btnStop.setText("Stop");
        btnStop.setToolTipText("stop current job");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        lblStatues.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblStatues.setText("STOPPED");

        jLabel3.setText("start a new job");

        jLabel6.setText("pause current job");

        jLabel9.setText("stop current job");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txbxHash, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(btnPause, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prgBar, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(lblHash)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkbxSpecial)
                                    .addComponent(chkbxCapital)
                                    .addComponent(chkbxSimple)
                                    .addComponent(chkbxNumber)))
                            .addComponent(lblSize)
                            .addComponent(lblAlgo)
                            .addComponent(lblchk)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAlgo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txbxSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblStatues))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txbxHash, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txbxSize, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAlgo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlgo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblchk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkbxNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkbxSimple)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkbxCapital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkbxSpecial)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(lblStatues)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prgBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Job", jPanel2);

        lstGridPeers.setBackground(new java.awt.Color(51, 51, 51));
        lstGridPeers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));
        lstGridPeers.setFont(new java.awt.Font("Tahoma", 0, 12));
        lstGridPeers.setForeground(new java.awt.Color(255, 255, 255));
        lstGridPeers.setEnabled(false);
        jScrollPane1.setViewportView(lstGridPeers);

        jTabbedPane4.addTab("Peers", jScrollPane1);

        lstLocalJobs.setBackground(new java.awt.Color(51, 51, 51));
        lstLocalJobs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));
        lstLocalJobs.setFont(new java.awt.Font("Tahoma", 0, 8));
        lstLocalJobs.setForeground(new java.awt.Color(255, 255, 255));
        lstLocalJobs.setEnabled(false);
        jScrollPane2.setViewportView(lstLocalJobs);

        jTabbedPane3.addTab("Local Jobs", jScrollPane2);

        lstRemoteJobs.setBackground(new java.awt.Color(51, 51, 51));
        lstRemoteJobs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));
        lstRemoteJobs.setFont(new java.awt.Font("Tahoma", 0, 8));
        lstRemoteJobs.setForeground(new java.awt.Color(255, 255, 255));
        lstRemoteJobs.setEnabled(false);
        jScrollPane3.setViewportView(lstRemoteJobs);

        jTabbedPane3.addTab("Remote jobs", jScrollPane3);

        jTabbedPane4.addTab("Jobs", jTabbedPane3);

        lstRIList.setBackground(new java.awt.Color(51, 51, 51));
        lstRIList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));
        lstRIList.setFont(new java.awt.Font("Tahoma", 0, 14));
        lstRIList.setForeground(new java.awt.Color(255, 255, 255));
        lstRIList.setEnabled(false);
        jScrollPane5.setViewportView(lstRIList);

        jLabel11.setText("IP manual addition");

        txtRILadd.setBackground(new java.awt.Color(51, 51, 51));
        txtRILadd.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtRILadd.setForeground(new java.awt.Color(204, 204, 255));
        txtRILadd.setText("<enter ip>");
        txtRILadd.setToolTipText("decryptor folder path");
        txtRILadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtRILadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRILaddMouseClicked(evt);
            }
        });
        txtRILadd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRILaddKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(txtRILadd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRILadd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("IP list", jPanel1);

        txtConsole.setBackground(new java.awt.Color(0, 0, 0));
        txtConsole.setColumns(20);
        txtConsole.setEditable(false);
        txtConsole.setFont(new java.awt.Font("Lucida Console", 0, 10));
        txtConsole.setForeground(new java.awt.Color(0, 255, 0));
        txtConsole.setRows(5);
        txtConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 4));
        txtConsole.setMargin(new java.awt.Insets(5, 5, 2, 2));
        jScrollPane4.setViewportView(txtConsole);

        jTabbedPane4.addTab("Console Preview", jScrollPane4);

        jTabbedPane5.addTab("Preview", jTabbedPane4);

        jLabel28.setText("Max Peers per job");

        jLabel29.setText("Max Remote jobs");

        jLabel31.setText("Max Grid Peers");

        comMaxPeerPerJob.setBackground(new java.awt.Color(51, 51, 51));
        comMaxPeerPerJob.setFont(new java.awt.Font("Tahoma", 0, 12));
        comMaxPeerPerJob.setForeground(new java.awt.Color(204, 204, 204));
        comMaxPeerPerJob.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "30", "50", "100", "200", "400", "500", "1000", "2000", "5000", "10000", "50000", "100000" }));
        comMaxPeerPerJob.setToolTipText("max peers assignable per job");
        comMaxPeerPerJob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comMaxPeerPerJobMouseClicked(evt);
            }
        });
        comMaxPeerPerJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comMaxPeerPerJobActionPerformed(evt);
            }
        });

        comMaxRemoteServers.setBackground(new java.awt.Color(51, 51, 51));
        comMaxRemoteServers.setFont(new java.awt.Font("Tahoma", 0, 12));
        comMaxRemoteServers.setForeground(new java.awt.Color(204, 204, 204));
        comMaxRemoteServers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "5", "10", "15", "20", "30", "50", "100" }));
        comMaxRemoteServers.setToolTipText("max remote jobs");
        comMaxRemoteServers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comMaxRemoteServersMouseClicked(evt);
            }
        });
        comMaxRemoteServers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comMaxRemoteServersActionPerformed(evt);
            }
        });

        comMaxGridPeers.setBackground(new java.awt.Color(51, 51, 51));
        comMaxGridPeers.setFont(new java.awt.Font("Tahoma", 0, 12));
        comMaxGridPeers.setForeground(new java.awt.Color(204, 204, 204));
        comMaxGridPeers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "30", "50", "100", "200", "400", "500", "1000", "2000", "5000", "10000", "50000", "100000" }));
        comMaxGridPeers.setToolTipText("total number of grid peers");
        comMaxGridPeers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comMaxGridPeersMouseClicked(evt);
            }
        });
        comMaxGridPeers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comMaxGridPeersActionPerformed(evt);
            }
        });

        jLabel7.setText("Config file");

        jLabel33.setText("Log file");

        jLabel4.setText("Decryptor directory");

        jLabel5.setText("Remote IP list File");

        txtconffile.setBackground(new java.awt.Color(51, 51, 51));
        txtconffile.setEditable(false);
        txtconffile.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtconffile.setForeground(new java.awt.Color(204, 204, 255));
        txtconffile.setText("conf.ini");
        txtconffile.setToolTipText("configuration file path");
        txtconffile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtconffile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtconffileMouseClicked(evt);
            }
        });
        txtconffile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconffileActionPerformed(evt);
            }
        });
        txtconffile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtconffileKeyPressed(evt);
            }
        });

        txtLogFile.setBackground(new java.awt.Color(51, 51, 51));
        txtLogFile.setEditable(false);
        txtLogFile.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtLogFile.setForeground(new java.awt.Color(204, 204, 255));
        txtLogFile.setText("log.txt");
        txtLogFile.setToolTipText("log file path");
        txtLogFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtLogFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLogFileMouseClicked(evt);
            }
        });
        txtLogFile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLogFileKeyPressed(evt);
            }
        });

        txtDecryptorPath.setBackground(new java.awt.Color(51, 51, 51));
        txtDecryptorPath.setEditable(false);
        txtDecryptorPath.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtDecryptorPath.setForeground(new java.awt.Color(204, 204, 255));
        txtDecryptorPath.setText("/decryptors");
        txtDecryptorPath.setToolTipText("decryptor folder path");
        txtDecryptorPath.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtDecryptorPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDecryptorPathMouseClicked(evt);
            }
        });
        txtDecryptorPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDecryptorPathKeyPressed(evt);
            }
        });

        txtRIF.setBackground(new java.awt.Color(51, 51, 51));
        txtRIF.setEditable(false);
        txtRIF.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtRIF.setForeground(new java.awt.Color(204, 204, 255));
        txtRIF.setText("ril.lst");
        txtRIF.setToolTipText("remote IP list file path");
        txtRIF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtRIF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRIFMouseClicked(evt);
            }
        });
        txtRIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRIFActionPerformed(evt);
            }
        });
        txtRIF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRIFKeyPressed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("manual restart required");

        jLabel26.setText("Con port");

        jLabel25.setText("Default port");

        jLabel27.setText("FT port");

        txtSetFileTransferPort1.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtSetFileTransferPort1.setForeground(new java.awt.Color(153, 153, 153));
        txtSetFileTransferPort1.setText("802");
        txtSetFileTransferPort1.setToolTipText("control port");

        txtSetConnectivityPort1.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtSetConnectivityPort1.setForeground(new java.awt.Color(153, 153, 153));
        txtSetConnectivityPort1.setText("801");
        txtSetConnectivityPort1.setToolTipText("file transfering port");

        txtPort.setBackground(new java.awt.Color(51, 51, 51));
        txtPort.setEditable(false);
        txtPort.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtPort.setForeground(new java.awt.Color(204, 204, 255));
        txtPort.setText("800");
        txtPort.setToolTipText("connection intaking port");
        txtPort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPortMouseClicked(evt);
            }
        });
        txtPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPortKeyPressed(evt);
            }
        });

        txtServerAdd.setBackground(new java.awt.Color(51, 51, 51));
        txtServerAdd.setEditable(false);
        txtServerAdd.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtServerAdd.setForeground(new java.awt.Color(204, 204, 255));
        txtServerAdd.setText("http://www.bfgserver.com");
        txtServerAdd.setToolTipText("web server address");
        txtServerAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txtServerAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtServerAddMouseClicked(evt);
            }
        });
        txtServerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServerAddActionPerformed(evt);
            }
        });
        txtServerAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtServerAddKeyPressed(evt);
            }
        });

        jLabel1.setText("Web server");

        jLabel10.setText("Job Size");

        comjobsize.setForeground(new java.awt.Color(204, 204, 204));
        comjobsize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1000", "10000", "100000", "1000000", "10000000", "100000000" }));
        comjobsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comjobsizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtRIF, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLogFile, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(txtconffile, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtDecryptorPath, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtServerAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(65, 65, 65))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSetConnectivityPort1)
                                    .addComponent(txtSetFileTransferPort1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(116, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(29, 29, 29)
                        .addComponent(comMaxPeerPerJob, 0, 73, Short.MAX_VALUE)
                        .addGap(65, 65, 65))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(33, 33, 33)
                        .addComponent(comMaxRemoteServers, 0, 73, Short.MAX_VALUE)
                        .addGap(65, 65, 65))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(44, 44, 44)
                        .addComponent(comMaxGridPeers, 0, 73, Short.MAX_VALUE)
                        .addGap(65, 65, 65))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(comjobsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(59, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comMaxPeerPerJob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(comMaxRemoteServers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(comMaxGridPeers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comjobsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSetConnectivityPort1)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSetFileTransferPort1)
                    .addComponent(jLabel26))
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtServerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDecryptorPath, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRIF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtconffile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane5.addTab("Settings", jPanel13);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitApp(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitApp
        // TODO add your handling code here:
    }//GEN-LAST:event_exitApp

    private void txtLogFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLogFileMouseClicked
        // TODO add your handling code here:
        txtLogFile.setEditable(true);
        txtLogFile.selectAll();
}//GEN-LAST:event_txtLogFileMouseClicked

    private void txtconffileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconffileActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtconffileActionPerformed

    private void txtconffileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtconffileMouseClicked
        // TODO add your handling code here:
        txtconffile.setEditable(true);
        txtconffile.selectAll();
}//GEN-LAST:event_txtconffileMouseClicked

    private void txtRIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRIFActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtRIFActionPerformed

    private void txtRIFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRIFMouseClicked
        // TODO add your handling code here:
        txtRIF.setEditable(true);
        txtRIF.selectAll();
    }//GEN-LAST:event_txtRIFMouseClicked

    private void txtDecryptorPathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDecryptorPathMouseClicked
        // TODO add your handling code here:
        txtDecryptorPath.setEditable(true);
        txtDecryptorPath.selectAll();
    }//GEN-LAST:event_txtDecryptorPathMouseClicked

    private void txtServerAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServerAddActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtServerAddActionPerformed

    private void txtServerAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtServerAddMouseClicked
        // TODO add your handling code here:
        txtServerAdd.setEditable(true);
        txtServerAdd.selectAll();
}//GEN-LAST:event_txtServerAddMouseClicked

    private void txtPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPortMouseClicked
        // TODO add your handling code here:
        txtPort.setEditable(true);
        txtPort.selectAll();
}//GEN-LAST:event_txtPortMouseClicked

    private void comMaxGridPeersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comMaxGridPeersActionPerformed
        // TODO add your handling code here:
        CommonRegister.MAX_GRID_PEERS = Integer.valueOf(comMaxGridPeers.getSelectedItem().toString());
}//GEN-LAST:event_comMaxGridPeersActionPerformed

    private void comMaxGridPeersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comMaxGridPeersMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comMaxGridPeersMouseClicked

    private void comMaxRemoteServersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comMaxRemoteServersActionPerformed
        // TODO add your handling code here:
        CommonRegister.MAX_REMOTE_JOBS = Integer.valueOf(comMaxRemoteServers.getSelectedItem().toString());
}//GEN-LAST:event_comMaxRemoteServersActionPerformed

    private void comMaxRemoteServersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comMaxRemoteServersMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comMaxRemoteServersMouseClicked

    private void comMaxPeerPerJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comMaxPeerPerJobActionPerformed
        // TODO add your handling code here:
        CommonRegister.MAX_REMOTE_PEERS_PER_JOB = Integer.valueOf(comMaxPeerPerJob.getSelectedItem().toString());
        comMaxPeerPerJob.setEditable(false);
    }//GEN-LAST:event_comMaxPeerPerJobActionPerformed

    private void comMaxPeerPerJobMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comMaxPeerPerJobMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comMaxPeerPerJobMouseClicked

    private void txtPeerNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPeerNameMouseClicked
        // TODO add your handling code here:
        txtPeerName.setEditable(true);
        txtPeerName.selectAll();
}//GEN-LAST:event_txtPeerNameMouseClicked

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
        if(LocalJob.isStarted()) {

            for (int i = 0; i < LocalJob.getNoOfSubJobs(); i++) {
                LocalSubJob localSubJob;

                try {
                    localSubJob = LocalJob.getLocalSubJob(i);
                } catch (Exception ex) {
                    continue;
                }

                if(localSubJob.getState() == SubJob.PENDING ||
                        localSubJob.getState() == SubJob.IDLE ||
                        localSubJob.getState() == SubJob.STARTED) {
                    try {
                        GridPeer gp = GridPeerList.getGridPeer(localSubJob.getGridPeerID());
                        RemoteRequestor.RequestToStopJob(gp);
                    } catch (Exception ex) {
                        ListUpdater.doUpdate(localSubJob.getGridPeerID());
                    }
                }
            }
        }

        LocalJob.stopJob();

        btnPause.setEnabled(false);
 
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
}//GEN-LAST:event_btnStopActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        if(!LocalJob.isPaused())
        {
            btnPause.setText("Resume");
            LocalJob.pauseJob();
            btnStop.setEnabled(true);
            btnStart.setEnabled(false);
        }
        else
        {
            btnPause.setText("Pause");
            LocalJob.resumeJob();
            btnStop.setEnabled(true);
            btnStart.setEnabled(false);
        }
}//GEN-LAST:event_btnPauseActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:

        boolean boolalgo = txtAlgo.getSelectedIndex() >= 0;
        boolean boolhash = !txbxHash.getText().equals("");
        boolean boolsize = Integer.valueOf(txbxSize.getValue().toString())>0;
        boolean boolchk = (chkbxCapital.isSelected()
                ||chkbxNumber.isSelected()
                ||chkbxSimple.isSelected()
                ||chkbxSpecial.isSelected());

        if(!boolsize)
            shower.lblSize.setForeground(Color.red);
        else
            shower.lblSize.setForeground(lblCol);

        if(!boolalgo)
            shower.lblAlgo.setForeground(Color.red);
        else
            shower.lblAlgo.setForeground(lblCol);

        if(!boolhash)
            shower.lblHash.setForeground(Color.red);
        else
            shower.lblHash.setForeground(lblCol);

        if(!boolchk)
            shower.lblchk.setForeground(Color.red);
        else
            shower.lblchk.setForeground(lblCol);

        if(boolalgo&&boolhash&&boolchk&&boolsize) {
            String algo,size;
            algo = txtAlgo.getSelectedItem().toString();
            size = txbxSize.getValue().toString();
            if(!LocalJob.isStarted())
            {
                if(!LocalJobDistributor.startNew(algo, txbxHash.getText(),
                        Integer.parseInt(size), chkbxSimple.isSelected(),
                        chkbxCapital.isSelected(), chkbxSpecial.isSelected(),
                        chkbxNumber.isSelected()))
                {
                     JOptionPane.showMessageDialog(null, "number of local sub jobs exceeded" +
                             " increase segment size for larger passwords ");
                     return;
                }
            }

            btnStart.setEnabled(false);
            btnPause.setEnabled(true);
            btnStop.setEnabled(true);
        }
}//GEN-LAST:event_btnStartActionPerformed

    private void chkbxSimpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbxSimpleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkbxSimpleActionPerformed

    private void txtPeerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeerNameActionPerformed
        // TODO add your handling code here:

        
    }//GEN-LAST:event_txtPeerNameActionPerformed

    private void txtPeerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeerNameKeyTyped
        // TODO add your handling code here:
       
     
    }//GEN-LAST:event_txtPeerNameKeyTyped

    private void txtPeerNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeerNameKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep();
            CommonRegister.PEER_NAME = txtPeerName.getText();
            txtPeerName.setEnabled(false);
        }
    }//GEN-LAST:event_txtPeerNameKeyPressed

    private void txtPortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep();
            int val = CommonRegister.DEFAULT_PORT;
            try {
                val = Integer.valueOf(txtPort.getText());
            } catch(Exception ex) {
                txtPort.setText(val+"");
                txtPort.setEditable(false);
                return;
            }
            CommonRegister.DEFAULT_PORT = Integer.valueOf(txtPort.getText());
            txtSetConnectivityPort1.setText(""+CommonRegister.CONNCETIVITY_REPLIER_PORT());
            txtSetFileTransferPort1.setText(""+CommonRegister.FILE_TRASFER_PORT());
            txtPort.setEditable(false);
        }
    }//GEN-LAST:event_txtPortKeyPressed

    private void txtServerAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServerAddKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep();
           CommonRegister.WEB_SERVER_DOMAIN = txtServerAdd.getText();
           txtServerAdd.setEditable(false);
        }
    }//GEN-LAST:event_txtServerAddKeyPressed

    private void txtDecryptorPathKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDecryptorPathKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep();
         CommonRegister.DECRYPTOR_LOCAL_ROOT_PATH = txtDecryptorPath.getText();
        txtDecryptorPath.setEditable(false);
        }
    }//GEN-LAST:event_txtDecryptorPathKeyPressed

    private void txtRIFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRIFKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep();
          CommonRegister.REMOTE_IP_LIST_FILE_PATH = txtRIF.getText();
        txtRIF.setEditable(false);
        }
    }//GEN-LAST:event_txtRIFKeyPressed

    private void txtconffileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconffileKeyPressed
        // TODO add your handling code here:
         int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
Toolkit.getDefaultToolkit().beep();
         CommonRegister.CONFIG_FILE = txtconffile.getText();
        txtconffile.setEditable(false);
        }
    }//GEN-LAST:event_txtconffileKeyPressed

    private void txtLogFileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLogFileKeyPressed
        // TODO add your handling code here:
                 int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
Toolkit.getDefaultToolkit().beep();
          CommonRegister.LOG_FILE = txtLogFile.getText();
        txtLogFile.setEditable(false);
        }
    }//GEN-LAST:event_txtLogFileKeyPressed

    private void comjobsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comjobsizeActionPerformed
        // TODO add your handling code here:
        CommonRegister.LOCAL_SUB_JOB_SIZE =
                Integer.valueOf(comjobsize.getSelectedItem().toString());
    }//GEN-LAST:event_comjobsizeActionPerformed

    private void txtRILaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRILaddMouseClicked
        // TODO add your handling code here:
        txtRILadd.setText("");
    }//GEN-LAST:event_txtRILaddMouseClicked

    private void txtRILaddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRILaddKeyPressed
        // TODO add your handling code here:
                 int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            try {
                Toolkit.getDefaultToolkit().beep();
                InetAddress addr = InetAddress.getByName(txtRILadd.getText());
                RemoteIPList.insertNewIP(addr);
                txtRILadd.setText("<enter ip>");
            } catch (Exception ex) {
                txtRILadd.setText("<enter ip>");
            }
        }
    }//GEN-LAST:event_txtRILaddKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void runit() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                shower = new Shower();
                shower.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JCheckBox chkbxCapital;
    private javax.swing.JCheckBox chkbxNumber;
    private javax.swing.JCheckBox chkbxSimple;
    private javax.swing.JCheckBox chkbxSpecial;
    private javax.swing.JComboBox comMaxGridPeers;
    private javax.swing.JComboBox comMaxPeerPerJob;
    private javax.swing.JComboBox comMaxRemoteServers;
    private javax.swing.JComboBox comjobsize;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel lblAlgo;
    private javax.swing.JLabel lblConnectedPeers;
    private javax.swing.JLabel lblHash;
    private javax.swing.JLabel lblLocalIP;
    private javax.swing.JLabel lblLocalJobs;
    private javax.swing.JLabel lblLocalJobsCompleted;
    private javax.swing.JLabel lblLocalJobsNew;
    private javax.swing.JLabel lblLocalJobsPending;
    private javax.swing.JLabel lblPeersConnectable;
    private javax.swing.JLabel lblRemoteJobs;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblStatues;
    private javax.swing.JLabel lblchk;
    private javax.swing.JList lstGridPeers;
    private javax.swing.JList lstLocalJobs;
    private javax.swing.JList lstRIList;
    private javax.swing.JList lstRemoteJobs;
    private javax.swing.JProgressBar prgBar;
    private javax.swing.JTextField txbxHash;
    private javax.swing.JSpinner txbxSize;
    private javax.swing.JComboBox txtAlgo;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtDecryptorPath;
    private javax.swing.JTextField txtLogFile;
    private javax.swing.JTextField txtPeerName;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtRIF;
    private javax.swing.JTextField txtRILadd;
    private javax.swing.JTextField txtServerAdd;
    private javax.swing.JLabel txtSetConnectivityPort1;
    private javax.swing.JLabel txtSetFileTransferPort1;
    private javax.swing.JTextField txtconffile;
    // End of variables declaration//GEN-END:variables

}
